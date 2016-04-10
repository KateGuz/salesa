%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Advert</title>
</head>
<body>
<h1><c:out value="${advertDetails.advert.title}"/></h1>
<p><c:out value="${advertDetails.advert.price}"/></p>
<c:out value="${advertDetails.advert.currency}"/>
<p><c:out value="${advertDetails.advert.status}"/></p>
<%--<p><c:out value="${advertDetails.advert.category.name}"/></p>
<p><c:out value="${advertDetails.advert.category.parent}"/></p>--%>
<h1><c:out value="${advertDetails.user.name}"/></h1>
<p><c:out value="${advertDetails.user.email}"/></p>
<p><c:out value="${advertDetails.user.phone}"/></p>
</body>
</html>