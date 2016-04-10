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
    <link media="all" rel="stylesheet" href="css/style.css" type="text/css"/>
    <link rel="icon" type="image/png" href="img/salesa.png"/>
    <link rel="apple-touch-icon" href="img/salesa.png"/>
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


    <ol class="breadcrumb">
        <li><a href="/">Главная</a></li>
        <li class="active">${category.name}</li>
    </ol>
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
                <h3>${advert.title}</h3>
                <h4>${advert.price}&nbsp;${advert.currency}</h4>
                <p>${advert.text}</p>
                <br>
                <c:choose>
                    <c:when test="${advert.status == 'A'}">
                        <font color="#48c083"><h5>Активно</h5></font>
                    </c:when>
                    <c:when test="${advert.status == 'H'}">
                        <h5>Забронировано </h5>
                    </c:when>
                </c:choose>
                <br>
                <p>${advert.modificationDate}</p>
            </div>
        </div>
        <hr/>
        <div class="user-info col-md-12">
            <div class="col-md-4">
                <h4>
                    <div class="glyphicon glyphicon-user"></div>
                    <a href="/user/${user.id}">${user.name}</a>
                </h4>
                <p>
                <div class="glyphicon glyphicon-envelope"></div>
                email: ${user.email}</p>
                <p>
                <div class="glyphicon glyphicon-phone"></div>
                phone: ${user.phone}</p>

            </div>
        </div>
    </div>

    <div class="separator-box">
        <div class="text-center">
            <ul class="pagination"></ul>
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
