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
    <title>User</title>
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
                    <form class="navbar-form " role="search" action="/search">
                        <div class="input-group">
                            <input type="text" class="form-control" name="searchText">
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
                                   aria-haspopup="true" aria-expanded="false">${selectedCurrency}<span
                                        class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a role="button" onclick="changeCurrencyOnUserPage('USD')">USD</a></li>
                                    <li><a role="button" onclick="changeCurrencyOnUserPage('UAH')">UAH</a></li>
                                    <li><a role="button" onclick="changeCurrencyOnUserPage('EUR')">EUR</a></li>
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
        <div class="row">
            <div class="col-xs-12">
                <div class="user-info-bar col-sm-4 col-md-4">
                    <div class="user-img thumbnail">
                        <c:choose>
                            <c:when test="${!empty user.avatar}">
                                <img src="/avatar/${user.id}" alt="avatar">
                            </c:when>
                            <c:otherwise>
                                <img src="/img/mock-user.png" alt="avatar">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div style="text-align: center">
                        <c:choose>
                            <c:when test="${loggedUser.id == user.id}">
                                <p class="title-text">
                                    Выберите аватарку:
                                </p>
                                <input type="file" id="avatar" class="center-block choose-file">
                                <h4>
                                    <div class="glyphicon glyphicon-user"></div>
                                    <input type="text" id="name" value="${user.name}" required>
                                </h4>
                                <p>
                                <div class="glyphicon glyphicon-envelope"></div>
                                <input type="text" id="email" value="${user.email}" required>
                                </p>
                                <c:choose>
                                    <c:when test="${!empty user.phone}">
                                        <p>
                                        <div class="glyphicon glyphicon-phone"></div>
                                        <input type="text" id="phone" value="${user.phone}">
                                        </p>
                                    </c:when>
                                </c:choose>
                                <p>
                                <div class="glyphicon glyphicon-eye-close"></div>
                                <input type="checkbox" id="checkbox" checked onclick="showPassword()">
                                <input type="password" id="password" value="${user.password}">
                                </p>
                                <input type="button" class="save-changes save" onclick="editProfile(${user.id})"
                                       value="Save changes"/>
                                <p>
                                    <c:choose>
                                        <c:when test="${user.status == 'B'}"></c:when>
                                        <c:otherwise>
                                            <a href="/addAdvert">
                                                <button class="create"><strong>Создать объявление</strong></button>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${loggedUser.type == 'A'}">
                                            <a href="#reportGeneration" data-toggle="modal"
                                               data-target="#reportGeneration">
                                                <button class="reportRequest"><strong>Создать отчет</strong></button>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <h4>
                                    <div class="glyphicon glyphicon-user"></div>
                                        ${user.name}
                                </h4>
                                <p>
                                <div class="glyphicon glyphicon-envelope"></div>
                                ${user.email} </p>
                                <p>
                                <c:choose>
                                    <c:when test="${!empty user.phone}">
                                        <div class="glyphicon glyphicon-phone"></div>
                                        ${user.phone} </p>
                                    </c:when>
                                </c:choose>
                                <button class="dislike-btn" onclick="addDislike(${user.id})">
                                    <i class="glyphicon glyphicon-thumbs-down"> Dislike</i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <br>
                </div>
                <div class="col-sm-8 col-md-8">
                    <div class="advert-list-bar" id="adverts-list">
                        <ol class="user-advert-list">
                            <c:forEach items="${adverts}" var="advert" varStatus="loop">
                                <div class="media">
                                    <div class="col-sm-12">
                                        <h6 id="user-advert-status">
                                            <c:choose>
                                                <c:when test="${advert.status == 'A'}">
                                                    <font color="#48c083">Активно&nbsp;&nbsp;</font>
                                                </c:when>
                                                <c:when test="${advert.status == 'H'}">
                                                    Забронировано&nbsp;
                                                </c:when>
                                                <c:when test="${advert.status == 'S'}">
                                                    <font color="#сссссс">Продано&nbsp;</font>
                                                </c:when>
                                            </c:choose>
                                        </h6>
                                        <div class="price-delete">
                                            <h6 id="user-advert-price">${advert.price}&nbsp;${advert.currency}</h6>
                                            <c:choose>
                                                <c:when test="${!empty loggedUser.name}">
                                                    <c:choose>
                                                        <c:when test="${loggedUser.type == 'A'}">
                                                            <button class="deleteAdvBtn"
                                                                    onclick="deleteAdvert(${advert.id})">×
                                                            </button>
                                                        </c:when>
                                                    </c:choose>
                                                </c:when>
                                            </c:choose>
                                            <div class="col-sm-4">
                                                <div class="thumbnail">
                                                    <c:choose>
                                                    <c:when test="${empty advert.images}">
                                                        <img class="media-object thumbnail adv-img-list-item image-advert-user"
                                                             src="/img/mock.png">
                                                    </c:when>
                                                    <c:otherwise>
                                                    <c:forEach
                                                            items="${advert.images}"
                                                            var="image"
                                                            varStatus="loop">
                                                        <c:choose>
                                                            <c:when test="${image.type == 'M'}">
                                                                <img src="/image/${image.id}"
                                                                     alt="advert's photo"
                                                                     class="media-object thumbnail adv-img-list-item image-advert-user">
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>
                                                </div>
                                                </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class=" media-right">
                                            <a href="/advert/${advert.id}">
                                                <h4 class="media-heading">${advert.title}</h4>
                                            </a>
                                            <p>${advert.text}</p>
                                            <br>
                                            <h6><tags:localDateTime
                                                    date="${advert.modificationDate}"
                                                    pattern="${pattern}"/></h6>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </ol>
                    </div>
                    <div class="feedback-list-bar">
                        <h3>Отзывы о пользователе</h3>
                        <c:choose>
                            <c:when test="${empty loggedUser}">
                                <div class="alert alert-info" role="alert">
                                    <p><a href="#user-security-log" data-toggle="modal"
                                          data-target="#user-security-log"><strong>Авторизируйтесь</strong></a>,
                                        чтобы
                                        оставить отзыв о продавце </p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="feedback-form">
                                            <textarea class="feedback-input"
                                                      placeholder="Оставьте ваш отзыв"></textarea>
                                        <button class="feedback-btn" onclick="addFeedback(${user.id})">Send
                                        </button>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <ol class="user-advert-list" id="users-feedback">
                            <c:forEach items="${feedbacks}" var="feedback" varStatus="loop">
                                <div class="media well">
                                    <div class="media-left">
                                        <img class="media-object thumbnail feedback-img-list-item"
                                             src="/img/1.png">
                                    </div>
                                    <div class="media-body">
                                        <a href="/user/${feedback.author.id}">
                                            <h4 class="media-heading">${feedback.author.name}</h4>
                                        </a>
                                        <p>${feedback.text}</p>
                                        <br>
                                        <div>
                                            <h6 class="media-heading">
                                                <tags:localDateTime date="${feedback.creationDate}"/></h6>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </ol>
                    </div>
                    <div class="separator-box">
                        <div class="text-center">
                            <ul class="pagination">
                                <li><a href="?page=1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                                </li>
                                <c:forEach begin="1" end="${pageData.pageCount}" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.index == activePage}">
                                            <li><a class="activePage" id="?page=${loop.index}"
                                                   href="?page=${loop.index}"
                                                   data-original-title="" title="">${loop.index}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a id="?page=${loop.index}" href="?page=${loop.index}"
                                                   data-original-title=""
                                                   title="">${loop.index}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <li><a href="?page=${pageData.pageCount}" aria-label="Next"><span
                                        aria-hidden="true">&raquo;</span></a>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <footer>
                        <div class="foot">
                            <div class="well">
                                <p>Salesa</p>
                                <p>All Rights Reserved</p>
                            </div>
                        </div>
                    </footer>
                </div>
            </div>
        </div>

        <jsp:include page="forms.jsp"/>

        <div class="wrap-modal">
            <div class="modal fade" id="feedback-ok" role="dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <p>Ваш отзыв добавлен. Спасибо!</p>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
