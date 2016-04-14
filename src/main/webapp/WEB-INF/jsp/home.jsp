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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link media="all" rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="icon" type="image/png" href="/img/salesa.png"/>
    <link rel="apple-touch-icon" href="/img/salesa.png"/>
    <script src="/js/jquery-1.12.3.min.js" type="text/javascript" language="JavaScript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/js/registration.js"></script>
</head>
<body>
<header>
    <div class="container">
        <nav class="navbar navbar-default">
            <div class="row">
                <div class="navbar-header col-sm-2">
                    <a class="navbar-brand" href="/">Salesa</a>
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
                        <c:choose>
                            <c:when test="${empty loggedUser.name}">
                                <li><a href="#user-security-log" data-toggle="modal" data-target="#user-security-log">Вход</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="/user/${loggedUser.id}">${loggedUser.name}&nbsp;</a></li>
                                <li><a href="/signOut">Sign Out</a></li>
                            </c:otherwise>
                        </c:choose>
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
                                <c:choose>
                                    <c:when test="${!empty category.children}">
                                        <li class="dropdown-submenu">
                                            <a tabindex="-1" href="/category/${category.id}">${category.name}</a>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${category.children}" var="subCategory">
                                                    <li><a href="/category/${subCategory.id}">${subCategory.name}</a>
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
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-0 col-lg-4">
            </c:if>
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
                </p>
                <div class="thumbnail">
                    <img src="/img/1.png" alt="advert's photo">
                </div>
                <div class="wrap-title">
                    <p class="title">${advert.title}</p>
                </div>
                <div class="date-price-wrap">
                    <fmt:parseDate value="${advert.modificationDate}" pattern="yy-MM-dd" var="parsedDate"
                                   type="time"/>
                    <p class="date"><tags:localDateTime date="${advert.modificationDate}"/></p>
                    <p class="price">${advert.price} &nbsp; ${advert.currency}</p>
                </div>
            </div>
            <c:if test="${(loop.index + 1) % 3  == 0}">
                </div>
            </c:if>
        </c:forEach>

    </div>
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


<div class="modal fade" id="user-security-log" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <button class="close" type="button" data-dismiss="modal">&times;</button>
            <div class="modal-body">
                <form:form>
                    <h3>Log in </h3>
                    <hr>
                    <input type="text" name="log_email" id="log_email" placeholder="Email">
                    <br>
                    <input type="text" name="log_password" id="log_password" placeholder="Password">
                    <br>
                    <button class="button" id="btn-log">Submit</button>
                    <br>
                    <br>
                    <p>Not yet registered?<a href="#user-security-reg" data-toggle="modal"
                                             data-target="#user-security-reg"
                                             data-dismiss="modal">&nbsp;Click here!</a></p>
                </form:form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="user-security-reg" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <button class="close" type="button" data-dismiss="modal">&times;</button>
            <div class="modal-body">
                <form:form>
                    <h3>Registration</h3>
                    <hr>
                    <input type="text" name="reg_name" id="reg_name" placeholder="Name">
                    <br>
                    <input type="password" name="reg_email" id="reg_email" placeholder="Email">
                    <br>
                    <input type="text" name="reg_password" id="reg_password" placeholder="Password">
                    <br>
                    <button class="button" id="btn-reg">Submit</button>
                    <br>
                    <br>
                    <p>Already registered?<a href="#user-security-log" data-toggle="modal"
                                             data-target="#user-security-log"
                                             data-dismiss="modal">&nbsp;Click here!</a></p>
                </form:form>
            </div>
        </div>
    </div>
</div>


</body>

</html>
