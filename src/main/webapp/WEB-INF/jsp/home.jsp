<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <jsp:include page="head-include.jsp"/>
</head>

<body>
<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="size">
        <nav class="navbar navbar-default">
            <div class="row">
                <div class="navbar-header col-sm-2">
                    <a class="navbar-brand" href="/">Salesa</a>
                </div>
                <div class="col-sm-4">
                    <form class="navbar-form " role="search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Искать...">
                            <span class="input-group-btn">
                                <button class="btn btn-default go" type="submit">Поиск</button>
                            </span>
                        </div>
                    </form>
                </div>

                <div class=" col-sm-6">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">Валюта<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">USD</a></li>
                                    <li><a href="#">UAH</a></li>
                                    <li><a href="#">CHF</a></li>
                                    <li><a href="#">RUB</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Связаться с нами</a></li>
                            <c:choose>
                                <c:when test="${!empty loggedUser}">
                                    ${!empty loggedUser}
                                </c:when>
                                <c:otherwise>
                                    <li><a href="#user-security-log" data-toggle="modal"
                                           data-target="#user-security-log">Вход</a></li>
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
        <nav class="category-filter-row">
            <div class="row">
                <div class="col-sm-2">
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
                                            <a tabindex="-1"
                                               href="/category/${category.id}">${category.name}</a>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${category.children}" var="subCategory">
                                                    <li>
                                                        <a href="/category/${subCategory.id}">${subCategory.name}</a>
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
                </div>

                <div class="col-sm-10">
                    <div class="btn-group nav navbar-nav navbar-right">
                        <p style="margin-outside: 10px">Сортировать по:</p>&nbsp;
                        <button type="button" class="btn btn-default">Новые</button>
                        <button type="button" class="btn btn-default">Самые дешевые</button>
                        <button type="button" class="btn btn-default">Самые дорогие</button>
                        <button type="button" class="btn btn-default">Активные</button>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>

<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="size">
        <div class="row">
            <c:forEach items="${pageData.adverts}" var="advert" varStatus="loop">
                <div class="display-block">
                    <div id="thumbnail" class="col-xxs-12 col-xs-6 col-sm-4  col-md-4  col-lg-4">
                        <div class="test">
                            <a href="/advert/${advert.id}">
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
                                        &nbsp;
                                    </p>
                                    <div class="thumbnail">
                                        <img src="/img/1.png" alt="advert's photo">
                                    </div>
                                    <div class="wrap-title">
                                        <p class="title">${advert.title}</p>
                                    </div>
                                    <div class="date-price-wrap">
                                        <p class="date"><tags:localDateTime date="${advert.modificationDate}"/></p>
                                        <p class="price">${advert.price} &nbsp; ${advert.currency}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="pages">
        <div class="size">
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
    </div>
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

<div class="modal fade " id="user-security-log" role="dialog">
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
<div class="modal fade " id="user-security-reg" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <button class="close" type="button" data-dismiss="modal">&times;</button>
            <div class="modal-body">
                <form:form>
                    <h3>Registration</h3>
                    <hr>
                    <input type="text" name="reg_name" id="reg_name" placeholder="Name">
                    <br>
                    <input type="text" name="reg_email" id="reg_email" placeholder="Email">
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
