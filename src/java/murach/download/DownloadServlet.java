package murach.download;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import murach.business.User;
import murach.data.UserIO;
import murach.util.CookieUtil;

public class DownloadServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewAlbums";  // default action
        }

        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        if (action.equals("viewAlbums")) {
            url = "/index.jsp";
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        } else if (action.equals("viewCookies")) {
            url = "/view_cookies.jsp";
        } else if (action.equals("deleteCookies")) {
            url = deleteCookies(request, response);
        }

        // trước khi forward, thử lấy firstName từ cookie và decode
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String firstName = CookieUtil.getCookieValue(cookies, "firstNameCookie");
            if (firstName != null) {
                try {
                    firstName = URLDecoder.decode(firstName, StandardCharsets.UTF_8.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("firstName", firstName);
            }
        }

        // forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        if ("registerUser".equals(action)) {
            url = registerUser(request, response);
        }

        // trước khi forward, decode firstNameCookie để hiển thị
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String firstName = CookieUtil.getCookieValue(cookies, "firstNameCookie");
            if (firstName != null) {
                try {
                    firstName = URLDecoder.decode(firstName, StandardCharsets.UTF_8.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("firstName", firstName);
            }
        }

        // forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String checkUser(HttpServletRequest request,
                             HttpServletResponse response) {

        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        session.setAttribute("productCode", productCode);
        User user = (User) session.getAttribute("user");

        String url;
        // if User object doesn't exist, check email cookie
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress =
                    CookieUtil.getCookieValue(cookies, "emailCookie");

            if (emailAddress != null) {
                try {
                    emailAddress = URLDecoder.decode(emailAddress, StandardCharsets.UTF_8.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // if cookie doesn't exist, go to Registration page
            if (emailAddress == null || emailAddress.equals("")) {
                url = "/register.jsp";
            }
            // if cookie exists, create User object and go to Downloads page
            else {
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/EmailList.txt");
                user = UserIO.getUser(emailAddress, path);
                session.setAttribute("user", user);
                url = "/" + productCode + "_download.jsp";
            }
        }
        // if User object exists, go to Downloads page
        else {
            url = "/" + productCode + "_download.jsp";
        }
        return url;
    }

    private String registerUser(HttpServletRequest request,
                                HttpServletResponse response) {

        // get the user data
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // store the data in a User object
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // write the User object to a file
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/EmailList.txt");
        UserIO.add(user, path);

        // store the User object as a session attribute
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        try {
            // add a cookie that stores the user's email
            Cookie c1 = new Cookie("emailCookie",
                    URLEncoder.encode(email, StandardCharsets.UTF_8.toString()));
            c1.setMaxAge(60 * 60 * 24 * 365 * 2); // 2 years
            c1.setPath("/");
            response.addCookie(c1);

            // add a cookie that stores the user's first name
            Cookie c2 = new Cookie("firstNameCookie",
                    URLEncoder.encode(firstName, StandardCharsets.UTF_8.toString()));
            c2.setMaxAge(60 * 60 * 24 * 365 * 2);
            c2.setPath("/");
            response.addCookie(c2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // create and return a URL for the appropriate Download page
        String productCode = (String) session.getAttribute("productCode");
        String url = "/" + productCode + "_download.jsp";
        return url;
    }

    private String deleteCookies(HttpServletRequest request,
                                 HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // delete the cookie
                cookie.setPath("/"); // allow the download application to access it
                response.addCookie(cookie);
            }
        }
        String url = "/delete_cookies.jsp";
        return url;
    }
}
