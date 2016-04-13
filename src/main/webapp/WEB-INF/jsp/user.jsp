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

    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link media="all" rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="icon" type="image/png" href="/img/salesa.png"/>
    <link rel="apple-touch-icon" href="/img/salesa.png"/>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
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


    <%--<ol class="breadcrumb">--%>
    <%--<li><a href="/">Главная</a></li>--%>
    <%--<li class="active">${category.name}</li>--%>
    <%--</ol>--%>

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
                    email: ${user.email}</p>
                    <p>
                    <div class="glyphicon glyphicon-phone"></div>
                    phone: ${user.phone}</p>
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
                        <div class="media">
                            <div class="col-sm-12">
                                <div class="media-left">
                                    <img class="media-object thumbnail adv-img-list-item" src="/img/1.png" alt="...">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${advert.title}</h4>
                                    <p>${advert.text}</p>
                                </div>
                                <div class="media-right">
                                    <c:choose>
                                        <c:when test="${advert.status == 'A'}">
                                            <font color="#48c083">Активно</font>
                                        </c:when>
                                        <c:when test="${advert.status == 'H'}">
                                            Забронировано
                                        </c:when>
                                        <c:when test="${advert.status == 'S'}">
                                            <font color="#сссссс">Продано</font>
                                        </c:when>
                                    </c:choose>
                                    <h6>${advert.modificationDate}</h6>
                                    <br>
                                    <br>
                                    <h6>${advert.currency}</h6>
                                </div>
                            </div>
                        </div>
                        <div class="media">
                            <div class="col-sm-12">
                                <div class="media-left">
                                    <img class="media-object thumbnail adv-img-list-item" src="/img/1.png" alt="...">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${advert.title}</h4>
                                    <p>${advert.text}</p>
                                </div>
                                <div class="media-right">
                                    <c:choose>
                                        <c:when test="${advert.status == 'A'}">
                                            <font color="#48c083">Активно</font>
                                        </c:when>
                                        <c:when test="${advert.status == 'H'}">
                                            Забронировано
                                        </c:when>
                                        <c:when test="${advert.status == 'S'}">
                                            <font color="#сссссс">Продано</font>
                                        </c:when>
                                    </c:choose>
                                    <h6>${advert.modificationDate}</h6>
                                    <br>
                                    <br>
                                    <h6>${advert.currency}</h6>
                                </div>
                            </div>
                        </div>
                        <div class="media">
                            <div class="col-sm-12">
                                <div class="media-left">
                                    <img class="media-object thumbnail adv-img-list-item" src="/img/1.png" alt="...">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${advert.title}</h4>
                                    <p>${advert.text}</p>

                                </div>
                                <div class="media-right">
                                    <c:choose>
                                        <c:when test="${advert.status == 'A'}">
                                            <font color="#48c083">Активно</font>
                                        </c:when>
                                        <c:when test="${advert.status == 'H'}">
                                            Забронировано
                                        </c:when>
                                        <c:when test="${advert.status == 'S'}">
                                            <font color="#сссссс">Продано</font>
                                        </c:when>
                                    </c:choose>
                                    <h6>${advert.modificationDate}</h6>
                                    <br>
                                    <br>
                                    <h6>${advert.currency}</h6>
                                </div>
                            </div>
                        </div>

                    </ol>
                </div>


                <div class="pages-user">
                    <div class="text-center">
                        <ul class="pagination">
                            <li><a href="?page=1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                            <c:forEach begin="1" end="${advertPageData.pageCount}" varStatus="loop">
                                <li><a href="?page=${loop.index}" data-original-title="" title="">${loop.index}</a></li>
                            </c:forEach>
                            <li><a href="?page=${advertPageData.pageCount}" aria-label="Next"><span
                                    aria-hidden="true">&raquo;</span></a>
                            </li>
                        </ul>

                    </div>
                </div>

                <div class="feedback-list-bar">
                    <h3>Отзывы пользователя</h3>
                    <div class="feedback-form row">
                        <form>
                            <textarea class="feedback-input" placeholder="Оставте ваш отзыв"></textarea>
                            <button class="feedback-btn">Send</button>
                        </form>
                    </div>
                    <ol class="user-advert-list">
                        <div class="media">
                            <div class="col-sm-12">
                                <div class="media-left">
                                    <img class="media-object thumbnail feedback-img-list-item" src="/img/1.png"
                                         alt="...">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${user.name}</h4>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium
                                        ad,
                                        aliquid commodi distinctio dolor dolore eos fugit iste laudantium molestiae nam
                                        necessitatibus perferendis porro quidem rem sequi tempora unde.</p>
                                    ...
                                </div>
                                <div class="media-right">
                                    <h6 class="media-heading">${advert.modificationDate}</h6>
                                </div>
                            </div>
                        </div>
                        <div class="media">
                            <div class="col-sm-12">
                                <div class="media-left">
                                    <img class="media-object thumbnail feedback-img-list-item" src="/img/1.png"
                                         alt="...">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${user.name}</h4>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium
                                        ad,
                                        aliquid commodi distinctio dolor dolore eos fugit iste laudantium molestiae nam
                                        necessitatibus perferendis porro quidem rem sequi tempora unde.</p>
                                    ...
                                </div>
                                <div class="media-right">
                                    <h6 class="media-heading">${advert.modificationDate}</h6>

                                </div>
                            </div>
                        </div>
                        <div class="media">
                            <div class="col-sm-12">
                                <div class="media-left">
                                    <img class="media-object thumbnail feedback-img-list-item" src="/img/1.png"
                                         alt="...">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${user.name}</h4>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium
                                        ad,
                                        aliquid commodi distinctio dolor dolore eos fugit iste laudantium molestiae nam
                                        necessitatibus perferendis porro quidem rem sequi tempora unde.</p>
                                    ...
                                </div>
                                <div class="media-right">
                                    <h6 class="media-heading">${advert.modificationDate}</h6>

                                </div>
                            </div>
                        </div>
                    </ol>
                </div>

                <div class="pages-user">
                    <div class="text-center">
                        <ul class="pagination">
                            <li><a href="?page=1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                            <c:forEach begin="1" end="${feedbackPageData.pageCount}" varStatus="loop">
                                <li><a href="?page=${loop.index}" data-original-title="" title="">${loop.index}</a></li>
                            </c:forEach>
                            <li><a href="?page=${feedbackPageData.pageCount}" aria-label="Next"><span
                                    aria-hidden="true">&raquo;</span></a>
                            </li>
                        </ul>

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
    </div>
</div>
</body>


</html>
