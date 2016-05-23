<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="true" %>
<html>
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
                    <form class="navbar-form " role="search" action="/search">
                        <div class="input-group">
                            <input type="text" class="form-control" name="searchText" required>
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
                                    <li><a role="button" onclick="changeCurrencyOnAdvertPage('USD')">USD</a></li>
                                    <li><a role="button" onclick="changeCurrencyOnAdvertPage('UAH')">UAH</a></li>
                                    <li><a role="button" onclick="changeCurrencyOnAdvertPage('EUR')">EUR</a></li>
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

<c:choose>
    <c:when test="${empty loggedUser}">
        <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
            <div class="size">
                <div class="alert alert-info" role="alert">
                    <p><a href="#user-security-log" data-toggle="modal"
                          data-target="#user-security-log"><strong>Авторизируйтесь</strong></a>, чтобы
                        иметь возможность изменять объявление.</p>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
            <div class="size">
                <div class="advert-info-bar col-xs-12 well">
                    <form id="create" method="post">
                        <div class="row">
                            <div class="col-sm-8 col-md-8 left-padding">
                                <div class="text-bar">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <input type="text" id="title" size="30" value="${advert.title}" required>
                                        </div>
                                        <div class="col-md-3 ">
                                            <input type="number" id="price" size = "5" value="${advert.price}" required>
                                        </div>
                                        <div class="col-md-3">
                                           <%-- <label for="currency">Валюта</label>--%>
                                            <select id="currency" required>
                                                <c:choose>
                                                    <c:when test="${advert.currency == 'USD'}">
                                                        <option selected>USD</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option>USD</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${advert.currency == 'EUR'}">
                                                        <option selected>EUR</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option>EUR</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${advert.currency == 'UAH'}">
                                                        <option selected>UAH</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option>UAH</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                <textarea class="form-control" rows="5" id="description" required>${advert.text}</textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="wrap-category">
                                                <label for="category">Категория:</label>
                                                <select id="category">
                                                    <c:forEach items="${categories}" var="category">
                                                        <c:choose>
                                                            <c:when test="${!empty category.children}">
                                                                <optgroup label="${category.name}">
                                                                    <c:forEach items="${category.children}"
                                                                               var="subCategory">
                                                                        <c:choose>
                                                                            <c:when test="${advert.category.id == subCategory.id}">
                                                                                <option selected value="${subCategory.id}">${subCategory.name}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${subCategory.id}">${subCategory.name}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </optgroup>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:choose>
                                                                    <c:when test="${advert.category.id == subCategory.id}">
                                                                        <option selected value="${category.id}">${category.name}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${category.id}">${category.name}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="wrap-status">
                                                <label for="status">Статус:</label>
                                                <select id="status">
                                                    <c:choose>
                                                        <c:when test="${advert.currency == 'EUR'}">
                                                            <option value="A" selected>Активно</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="A">Активно</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${advert.currency == 'USD'}">
                                                            <option value="H" selected>Забронировано</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="H">Забронировано</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${advert.currency == 'UAH'}">
                                                            <option value="S" selected>Продано</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="S">Продано</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-md-4">
                                <div style="text-align: center" class="col-sm-12">
                                    <p class="title-text">
                                        Выберите основное изображение для объявления:
                                    </p>
                                    <input type="file" id="advertImage" class="center-block choose-file"
                                           onchange="addAdditionalImages()">
                                    <p class="title-text">
                                        Выберите дополнительные изображения:
                                    </p>
                                    <input type="file" id="otherAdvertImages" class="center-block choose-file" disabled
                                           multiple="multiple">
                                </div>
                            </div>
                        </div>
                        <div align="center">
                            <input type="button" onclick="editAdvert(${advert.id})" value="Сохранить"
                                   class="save save-advert">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="size">
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
<jsp:include page="forms.jsp"/>
</body>
</html>
