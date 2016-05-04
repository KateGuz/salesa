<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <jsp:include page="head-include.jsp"/>
</head>
<body>

<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="size">
        <nav class="navbar navbar-default">
            <div class="row">
                <div class="navbar-header col-sm-4">
                    <a class="navbar-brand" href="/">Salesa</a>
                </div>
                <div class="col-sm-4">
                    <form class="navbar-form " role="search">
                        <div class="input-group">
                            <input type="text" class="form-control">
                            <span class="input-group-btn">
                                <button class="btn btn-default go" type="submit">Поиск</button>
                            </span>
                        </div>
                    </form>
                </div>
                <div class=" col-sm-4">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">${selectedCurrency}<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                        <li><a role="button" onclick="changeCurrency('USD')">USD</a></li>
                                        <li><a role="button" onclick="changeCurrency('UAH')">UAH</a></li>
                                        <li><a role="button" onclick="changeCurrency('EUR')">EUR</a></li>
                                </ul>
                            </li>
                            <c:choose>
                                <c:when test="${empty loggedUser.name}">
                                    <li class="userLink"><a href="#user-security-log" data-toggle="modal"
                                                            data-target="#user-security-log">Вход</a>
                                    </li>
                                    <li class="out"></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="userLink"><a href="/user/${loggedUser.id}">${loggedUser.name}&nbsp;</a>
                                    </li>
                                    <li class="out"><a href="/signOut">Выйти</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>

<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="size">
        <nav class="category-filter-row">
            <div class="row">
                <div class="col-sm-2">
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            Категории
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
                            <c:forEach items="${categories}" var="category">
                                <c:choose>
                                    <c:when test="${!empty category.children}">
                                        <li class="dropdown-submenu">
                                            <a tabindex="-1"
                                               href="/category/${category.id}">${category.name}</a>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${category.children}" var="subCategory">
                                                    <li>
                                                        <a href="/category/${subCategory.id}">${subCategory.name}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="/category/${category.id}">${category.name}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-10">
                    <div class="btn-group nav navbar-nav navbar-right">
                        <%-- <button type="button" class="btn btn-default"></button>--%>
                        <button type="button" class="btn btn-default">Самые дешевые</button>
                        <button type="button" class="btn btn-default">Самые дорогие</button>
                        <button type="button" class="btn btn-default">Активные</button>
                    </div>
                </div>
                </a>
            </div>
        </nav>
    </div>
</div>

<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="size">
        <div class="row">
            <c:forEach items="${pageData.adverts}" var="advert" varStatus="loop">
                <div class="display-block">
                    <div id="thumbnail" class="col-xxs-12 col-xs-6 col-sm-4  col-md-4  col-lg-4">
                        <div class="test">
                            <a href="/advert/${advert.id}">
                                <div class="well">
                                    <p class="status">
                                        <c:choose>
                                            <c:when test="${advert.status == 'A'}">
                                                <font color="#48c083">Активно</font>
                                            </c:when>
                                            <c:when test="${advert.status == 'H'}">
                                                Забронировано
                                            </c:when>
                                        </c:choose>
                                        &nbsp;
                                    </p>
                                    <div class="thumbnail">
                                        <img src="/img/1.png" alt="advert's photo">
                                    </div>
                                    <div class="wrap-title">
                                        <p class="title">${advert.title}</p>
                                    </div>
                                    <div class="date-price-wrap">
                                        <p class="date"><tags:localDateTime date="${advert.modificationDate}"/></p>
                                        <p class="price">${advert.price} &nbsp; ${advert.currency}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="pages">
        <div class="size">
            <div class="text-center">
                <ul class="pagination">
                    <li><a href="?page=1&currency=${selectedCurrency}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    <c:forEach begin="1" end="${pageData.pageCount}" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.index == activePage}">
                                <li><a class="activePage" id="?page=${loop.index}" href="?page=${loop.index}&currency=${selectedCurrency}" data-original-title=""
                                       title="">${loop.index}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a id="?page=${loop.index}" href="?page=${loop.index}&currency=${selectedCurrency}" data-original-title="" title="">${loop.index}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <li><a href="?page=${pageData.pageCount}&currency=${selectedCurrency}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="size">
        <footer>
            <div class="foot">
                <div class="well">
                    <p>Salesa</p>
                    <p>All Rigths Reserved</p>
                </div>
            </div>
        </footer>
    </div>
</div>
<jsp:include page="forms.jsp"/>

</body>

</html>
