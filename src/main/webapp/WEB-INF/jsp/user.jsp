<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/feedback.js"></script>
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
    <div class="row">
        <div class="col-xs-12">
            <div class="user-info-bar col-sm-4 col-md-3">
                <div class="user-img thumbnail">
                    <img src="https://placehold.it/250x250" alt="avatar">
                </div>
                <div>
                    <h4>
                        <div class="glyphicon glyphicon-user"></div>
                        ${user.name}
                    </h4>
                    <p>
                    <div class="glyphicon glyphicon-envelope"></div>
                    email: ${user.email} </p>
                    <p>
                    <div class="glyphicon glyphicon-phone"></div>
                    phone: ${user.phone} </p>
                    <button class="dislike-btn">Dislike</button>
                </div>
                <br>
            </div>

            <div class="col-sm-8 col-md-9 ">
                <div class="user-advert-lab">
                    <h4>Обьявления пользователя</h4>
                </div>
                <div class="advert-list-bar">
                    <ol class="user-advert-list">
                        <c:forEach items="${adverts}" var="advert" varStatus="loop">
                            <div class="media">
                                <a href="/advert/${advert.id}">
                                    <div class="col-sm-12">
                                        <div class="media-left">
                                            <img class="media-object thumbnail adv-img-list-item" src="/img/1.png"
                                                 alt="...">
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">${advert.title}</h4>
                                            <p>${advert.text}</p>
                                            <br>
                                            <h6><tags:localDateTime date="${advert.modificationDate}"/></h6>
                                        </div>
                                        <div class="media-right">
                                            <c:choose>
                                                <c:when test="${advert.status == 'A'}">
                                                    <font color="#48c083">Активно</font>
                                                </c:when>
                                                <c:when test="${advert.status == 'H'}">
                                                    Заброни-<br>ровано
                                                </c:when>
                                                <c:when test="${advert.status == 'S'}">
                                                    <font color="#сссссс">Продано</font>
                                                </c:when>
                                            </c:choose>
                                            <br>
                                            <br>
                                            <h6>${advert.price}<span> </span>${advert.currency}</h6>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </ol>
                </div>


                <div class="feedback-list-bar">
                    <h3>Отзывы о пользователе</h3>
                    <div class="feedback-form row">
                        <textarea class="feedback-input" placeholder="Оставьте ваш отзыв"></textarea>
                        <button class="feedback-btn" onclick="addFeedback(${user.id})">Send</button>
                    </div>
                    <ol class="user-advert-list">
                        <c:forEach items="${feedbacks}" var="feedback" varStatus="loop">
                            <div class="media">
                                <a href="/user/${feedback.author.id}">
                                    <div class="col-sm-12">
                                        <div class="media-left">
                                            <img class="media-object thumbnail feedback-img-list-item" src="/img/1.png"
                                                 alt="...">
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">User id: ${feedback.author.id}</h4>
                                            <p>${feedback.text}</p>
                                        </div>
                                        <div class="media-right">
                                            <h6 class="media-heading"><tags:localDateTime date="${feedback.creationDate}"/></h6>
                                        </div>
                                    </div>
                                </a>
                            </div>

                        </c:forEach>
                    </ol>
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
</div>
</body>

<script>
    var adHeight = $('.img-wrapper').height();
    if (adHeight < 170) {
        var margintop = (170 - adHeight) / 2;
        $('.img-wrapper img').css('margin-top', margintop);
    }
</script>

</html>
