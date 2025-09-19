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

<h2>Nhạc Sơn Tùng MTP</h2>

<table>
    <tr>
        <th>Song title</th>
        <th>Audio Format</th>
    </tr>
    <tr>
        <td>Nơi này có anh</td>
        <td>
            <audio controls>
                <source src="/musicStore/sound/${productCode}/noinaycoanh.mp3" type="audio/mpeg">
                Trình duyệt của bạn không hỗ trợ phát nhạc.
            </audio>
        </td>
    </tr>
    <tr>
        <td>Em của ngày hôm qua</td>
        <td>
            <audio controls>
                <source src="/musicStore/sound/${productCode}/emcuangayhomqua.mp3" type="audio/mpeg">
                Trình duyệt của bạn không hỗ trợ phát nhạc.
            </audio>
        </td>
    </tr>
</table>

<p><a href="?action=viewAlbums">View list of albums</a></p>
<p><a href="?action=viewCookies">View all cookies</a></p>

</body>
</html>
