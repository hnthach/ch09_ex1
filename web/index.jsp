<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>List of Albums</h1>

<c:if test="${not empty firstName}">
    <div class="welcome">
        Welcome back, <span class="rainbow-text">${firstName}</span> 🎶
    </div>
</c:if>


<div class="album-list">
    <div class="album-card">
        <a href="download?action=checkUser&amp;productCode=8601">
            Nhạc Sơn Tùng MTP
        </a>
    </div>

    <div class="album-card">
        <a href="download?action=checkUser&amp;productCode=pf01">
            Nhạc J97
        </a>
    </div>

    <div class="album-card">
        <a href="download?action=checkUser&amp;productCode=pf02">
            Nhạc Bolero
        </a>
    </div>

    <div class="album-card">
        <a href="download?action=checkUser&amp;productCode=jr01">
            Nhạc của Đen
        </a>
    </div>
</div>

</body>
</html>
