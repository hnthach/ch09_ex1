<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>Downloads</h1>

<h2>Nhạc của J97</h2>

<table>
    <tr>
        <th>Song title</th>
        <th>Audio Format</th>
    </tr>
    <tr>
        <td>Sóng gió</td>
        <td>
            <audio controls>
                <source src="/musicStore/sound/${productCode}/songgio.mp3" type="audio/mpeg">
                Trình duyệt của bạn không hỗ trợ phát nhạc.
            </audio>
        </td>
    </tr>
    <tr>
        <td>Hồng nhan</td>
        <td>
            <audio controls>
                <source src="/musicStore/sound/${productCode}/hongnhan.mp3" type="audio/mpeg">
                Trình duyệt của bạn không hỗ trợ phát nhạc.
            </audio>
        </td>
    </tr>
</table>

<p><a href="?action=viewAlbums">View list of albums</a></p>
<p><a href="?action=viewCookies">View all cookies</a></p>

</body>
</html>
