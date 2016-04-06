<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Hello world</title>
</head>
<body>
<h1>Advertisements</h1>

<ul>
    <c:forEach var="advert" items="${adverts}">
        <li>${advert.id}</li>
        <li>${advert.title}</li>
        <li>${advert.text}</li>
    </c:forEach>
</ul>

<h1>Categories</h1>

<ul>
    <c:forEach var="category" items="${categories}">
        <li>${category.id}</li>
        <li>${category.name}</li>
    </c:forEach>
</ul>

</body>
</html>