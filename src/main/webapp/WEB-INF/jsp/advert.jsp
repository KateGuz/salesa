<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advert</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link media="all" rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="icon" type="image/png" href="/img/salesa.png"/>
    <link rel="apple-touch-icon" href="/img/salesa.png"/>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

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
    <div class="wrap-category">
        <ol class="breadcrumb">
            <c:forEach items="${breadcrumbsTree}" var="category">
                <li><a href="/category/${category.id}">${category.name}</a></li>
            </c:forEach>
        </ol>
    </div>
    <div class="advert-info-bar col-xs-12">
        <div class="col-md-4">
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

            </div>
        </div>


        <div class="col-md-8">
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
                        <h5>Забронировано </h5>
                    </c:when>
                </c:choose>
                <br>
                <p><tags:localDateTime date="${advert.modificationDate}"/></p>
            </div>
        </div>
        <hr/>
        <div class="user-info col-md-12">
            <div class="col-md-4">
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