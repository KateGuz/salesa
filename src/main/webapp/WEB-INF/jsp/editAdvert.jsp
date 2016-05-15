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
    <%--<c:when test="${empty loggedUser}">--%>
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
                    <form:form modelAttribute="advert" method="post" action="/editAdvert/${advert.id}">
                        <form:input class="hidden" path="id"/>
                        <form:input class="hidden" path="user"/>
                        <div class="row">
                            <div class="col-sm-4 col-md-4">
                                <div style="text-align: center" class="col-sm-12">
                                    <div class="thumbnail">
                                        <img src="/img/mock.png" alt="pic">
                                    </div>
                                        <%--<div class="upload">--%>
                                    <input type="file" class="upload-photo" multiple>
                                        <%--</div>--%>
                                </div>
                            </div>
                            <div class="col-sm-8 col-md-8">
                                <div class="text-bar">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <form:input type="text" path="title"/>
                                            <c:if test="${!empty errors.title}"><p
                                                    class="error">${errors.title}</p></c:if>
                                        </div>
                                        <div class="col-md-4">
                                            <form:input type="number" path="price"/>
                                            <c:if test="${!empty errors.price}"><p
                                                    class="error">${errors.price}</p></c:if>
                                        </div>
                                        <div class="col-md-4">
                                            <label for="currency">Валюта</label>
                                            <form:select path="currency">
                                                <option>USD</option>
                                                <option>UAH</option>
                                                <option>EUR</option>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <form:textarea id="text" path="text" class="form-control" rows="5"/>
                                            <c:if test="${!empty errors.text}"><p
                                                    class="error">${errors.text}</p></c:if>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="wrap-category">
                                                <form:label for="category" path="">Категория:</form:label>
                                                <form:select id="category" path="category">
                                                    <c:forEach items="${categories}" var="category">
                                                        <c:choose>
                                                            <c:when test="${!empty category.children}">
                                                                <optgroup label="${category.name}">
                                                                    <c:forEach items="${category.children}"
                                                                               var="subCategory">
                                                                        <option value="${subCategory.id}">${subCategory.name}</option>
                                                                    </c:forEach>
                                                                </optgroup>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${category.id}">${category.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                            <div class="wrap-status">
                                                <label for="status">Статус:</label>
                                                <form:select id="status" path="status">
                                                    <option>Активно</option>
                                                    <option>Забронировано</option>
                                                    <option>Продано</option>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div align="center">
                            <input type="submit" value="Сохранить" class="save"/>
                        </div>
                    </form:form>
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
                    <p>All Rigths Reserved</p>
                </div>
            </div>
        </footer>
    </div>
</div>
<jsp:include page="forms.jsp"/>
</body>
</html>
