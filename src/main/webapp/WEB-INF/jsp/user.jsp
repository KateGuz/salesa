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
        <li><a href="/category/1">Недвижимость</a></li>
        <li class="active">Разное</li>
    </ol>


    <div class="user-info-bar col-xs-12">
        <div class="col-sm-4 col-md-3">
            <div class="user-img thumbnail">
                <img src="/img/2.png" alt="">
            </div>
            <div>
                <h4>
                    <div class="glyphicon glyphicon-user"></div>
                    <a href="/user/0">Linux Torwalds</a>
                </h4>
                <p>
                <div class="glyphicon glyphicon-envelope"></div>
                email: Lin_torv@linux.com</p>
                <p>
                <div class="glyphicon glyphicon-phone"></div>
                phone: 099-999-99-99</p>
                <button class="dislike-btn">Dislike</button>
            </div>
            <br>


        </div>


        <div class="col-sm-8 col-md-9 ">
            <div class="user-advert-lab">
                <h4>All user adverts</h4>
            </div>
            <div class="advert-list-bar">
                <ol class="user-advert-list">
                    <div class="media">
                        <div class="col-sm-3">
                            <div class="media-left">
                                <img class="media-object thumbnail adv-img-list-item" src="/img/1.png" alt="...">
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Media heading</h4>
                                ...
                            </div>
                        </div>
                    </div>

                </ol>

            </div>
        </div>
        <hr/>

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
