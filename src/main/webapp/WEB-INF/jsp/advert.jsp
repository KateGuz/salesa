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
    <title>Advert</title>
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
                                    <li><a role="button" onclick="changeCurrencyOnAdvertPage('USD')">USD</a></li>
                                    <li><a role="button" onclick="changeCurrencyOnAdvertPage('UAH')">UAH</a></li>
                                    <li><a role="button" onclick="changeCurrencyOnAdvertPage('EUR')">EUR</a></li>
                                </ul>
                            </li>
                            <c:choose>
                                <c:when test="${empty loggedUser.name}">
                                    <li class="userLink"><a href="#user-security-log" data-toggle="modal" data-target="#user-security-log">Вход</a>
                                    </li>
                                    <li class="out"></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="userLink"><a href="/user/${loggedUser.id}">${loggedUser.name}&nbsp;</a></li>
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
        <div class="wrap-category">
            <ol class="breadcrumb">
                <c:forEach items="${breadcrumbsTree}" var="category">
                    <li><a href="/category/${category.id}">${category.name}</a></li>
                </c:forEach>
            </ol>
        </div>
        <div class="advert-info-bar col-xs-12 well">
            <div class="row">
                <div class="col-sm-4 col-md-4">
                    <div class="advert-img-bar thumbnail">
                        <div id="carousel" class="carousel slide">
                            <ol class="carousel-indicators">
                                <li class="active" data-target="#carousel" data-slide-to="0"></li>
                                <li data-target="#carousel" data-slide-to="1"></li>
                                <li data-target="#carousel" data-slide-to="2"></li>
                            </ol>

                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="/img/2.png" alt="">
                                </div>
                                <div class="item">
                                    <img src="/img/3.png" alt="">
                                </div>
                                <div class="item">
                                    <img src="/img/1.png" alt="">
                                </div>
                            </div>

                            <a href="#carousel" class="left carousel-control" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a href="#carousel" class="right carousel-control" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                        <div style="text-align: center" class="col-sm-12">
                            <h4>
                                <div class="glyphicon glyphicon-user"></div>
                                <a href="/user/${advert.user.id}">${advert.user.name}</a>
                            </h4>
                            <p>
                            <div class="glyphicon glyphicon-envelope"></div>
                            email: ${advert.user.email}
                            <p>
                            <div class="glyphicon glyphicon-phone"></div>
                            phone: ${advert.user.phone}
                        </div>

                    </div>
                </div>

                <div class="col-sm-8 col-md-8">
                    <div class="advert-text-bar">
                        <h4 class="title">${advert.title}</h4>
                        <h4>${advert.price}&nbsp;${advert.currency}</h4>
                        <p>${advert.text}</p>
                        <br>
                        <c:choose>
                            <c:when test="${advert.status == 'A'}">
                                <font color="#48c083"><strong><h5>Активно</h5></strong></font>
                            </c:when>
                            <c:when test="${advert.status == 'H'}">
                                <h5><strong>Забронировано</strong> </h5>
                            </c:when>
                        </c:choose>
                        <br>
                        <p><tags:localDateTime date="${advert.modificationDate}"/></p>
                    </div>
                </div>
                <hr>

            </div>
        </div>
    </div>
</div>
<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
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