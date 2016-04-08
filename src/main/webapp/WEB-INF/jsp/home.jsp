<%@ page import="com.salesa.entity.Advert" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link media="all" rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="icon" type="image/png" href="/img/salesa.png"/>
    <link rel="apple-touch-icon" href="/img/salesa.png"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
<header>
    <div class="container">
        <nav class="navbar navbar-default">
            <div class="row">
                <div class="navbar-header col-sm-2">
                    <a class="navbar-brand" href="#">Salesa</a>
                </div>
                <div class="form-wrap col-sm-4 col-sm-offset-2">
                    <form class="navbar-form " role="search">
                        <div class="form-group">
                            <input type="text" class="form-control">
                        </div>
                        <button type="submit" class="go">Поиск</button>
                    </form>
                </div>
                <div class="menu-ul-wrap col-sm-3 col-sm-offset-1">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Связаться с нами</a></li>
                        <li><a href="#">Вход</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
<div class="container">
    <nav class="navbar navbar-default sort">
        <div class="row">
            <div class="category-ul-wrap col-sm-2 ">
                <ul class="nav navbar-nav">
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            Категории
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
                            <c:forEach items="${categories}" var="category">
                                <c:if test="${category.parent.id == 0}">
                                    <li class="dropdown-submenu">
                                        <a tabindex="-1" href="/category/${category.id}">${category.name}</a>
                                        <ul class="dropdown-menu">
                                            <c:forEach items="${categories}" var="subCategory">
                                                <c:if test="${subCategory.parent.id == category.id}">
                                                    <li><a href="/category/${subCategory.id}">${subCategory.name}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </ul>
            </div>
            <div class="sort-ul-wrap col-sm-7 col-sm-offset-3">
                <ul class="nav navbar-nav">
                    <li>
                        <p>Сортировать по:</p>
                    </li>
                    <li>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">Новые</button>
                            <button type="button" class="btn btn-default">Самые дешевые</button>
                            <button type="button" class="btn btn-default">Самые дорогие</button>
                            <button type="button" class="btn btn-default">Активные</button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="fuck-off-ad-block">

        <c:forEach items="${pageData.adverts}" var="advert" varStatus="loop">
            <c:if test="${loop.index  % 3 == 0}">
                <div class="row">
            </c:if>
            <div class="col-sm-4 col-xs-12">
                <div class="well">
                    <p class="status">
                        <c:choose>
                            <c:when test="${advert.status == 'A'}">
                                <font color="#20b2aa">Активно</font>
                            </c:when>
                            <c:when test="${advert.status == 'H'}">
                                Забронировано
                            </c:when>
                        </c:choose>
                    </p>
                    <div class="img-wrapper">
                        <img src="/img/1.png" alt="advert's photo">
                    </div>
                    <div class="wrap-title">
                        <p class="title">${advert.title}</p>
                    </div>
                    <div class="date-price-wrap">
                        <fmt:parseDate value="${advert.modificationDate}" pattern="yy-MM-dd" var="parsedDate"
                                       type="time"/>
                        <p class="date"><c:out value="${parsedDate}"/></p>
                        <p class="price">${advert.price} &nbsp; ${advert.currency}</p>
                    </div>
                </div>
            </div>
            <c:if test="${(loop.index + 1) % 3  == 0}">
                </div>
            </c:if>
        </c:forEach>

    </div>

    <div class="pages">
        <div class="text-center">
            <ul class="pagination">
                <li><a href="?page=1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                <c:forEach begin="1" end="${pageData.pageCount}" varStatus="loop">
                    <li><a href="?page=${loop.index}" data-original-title="" title="">${loop.index}</a></li>
                </c:forEach>
                <li><a href="?page=${pageData.pageCount}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                </li>
            </ul>

        </div>
    </div>
    <footer>
        <div class="foot">
            <div class="well">
                <p>Salesa</p>
                <p>All Rigths Reserved</p>
            </div>
        </div>
    </footer>
</div>
<script>
    var adHeight = $('.img-wrapper').height();
    if (adHeight < 170) {
        var margintop = (170 - adHeight) / 2;
        $('.img-wrapper img').css('margin-top', margintop);
    }
</script>

</body>

</html>
