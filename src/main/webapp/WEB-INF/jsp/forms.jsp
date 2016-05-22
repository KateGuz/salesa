<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--  <jsp:include page="head-include.jsp"/>--%>
</head>
<body>
<%--login-modal--%>
<div class="modal fade " id="user-security-log" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <button class="close" type="button" data-dismiss="modal">&times;</button>
            <div class="modal-body">
                <h3>Вход</h3>
                <form>
                    <input type="email" name="email" id="email" placeholder="Email" maxlength="30"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                    <br>
                    <input type="password" name="pass" id="pass" placeholder="Password" required>
                    <br>
                    <input type="submit" value="ok" id="btn-log" data-dismiss="modal">
                </form>
                <br>
                <br>
                <p>Еще не зарегистрированы?
                    <a href="#user-security-reg" data-toggle="modal"
                       data-target="#user-security-reg"
                       data-dismiss="modal">Зарегистрироваться
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>

<%--registration-modal--%>
<div class="modal fade " id="user-security-reg" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <button class="close" type="button" data-dismiss="modal">&times;</button>
            <div class="modal-body">
                <h3>Регистрация</h3>
                <hr>
                <form>
                    <input type="text" name="name" id="reg-name" placeholder="Name" required>
                    <br>
                    <input type="email" name="email" id="reg-email" placeholder="Email" maxlength="30"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                    <br>
                    <input type="pass" name="pass" id="reg-pass" placeholder="Password" required>
                    <br>
                    <input type="submit" id="btn-reg" value="ok" data-dismiss="modal">
                </form>
                <br>
                <br>
                <p>Уже зарегистрированы?<a href="#user-security-log" data-toggle="modal"
                                           data-target="#user-security-log"
                                           data-dismiss="modal">&nbsp;Войти на сайт</a></p>
            </div>
        </div>
    </div>
</div>

<%--success-registration-modal--%>
<div class="wrap-modal">
    <div class="modal fade" id="success-reg" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Вы зарегистрированы. Спасибо!</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%--error-log-reg-modal--%>
<div class="wrap-modal">
    <div class="modal fade" id="error" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Пожалуйста, проверьте введенные данные!</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%--success-comment-modal--%>
<div class="wrap-modal">
    <div class="modal fade" id="feedback-ok" role="dialog">
        <div class="modal-content">
            <div class="modal-body">
                <p>Ваш отзыв добавлен. Спасибо!</p>
            </div>
        </div>
    </div>
</div>

<%--dislike-dmodal--%>
<div class="wrap-modal">
    <div class="modal fade" id="dislike" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Ваш отзыв добавлен, спасибо.</p>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="wrap-modal">
    <div class="modal fade" id="makeAdmin" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Пользователь был назначен админом, спасибо.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%--reportRequest-form-modal--%>
<div class="modal fade " id="reportGeneration" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <button class="close" type="button" data-dismiss="modal">&times;</button>
            <div class="modal-body">
                <form id="createReport">
                    <div class="wrap-format">
                        <label for="format">Формат</label>
                        <select id="format" required>
                            <option>xlsx</option>
                            <option>pdf</option>
                        </select>
                    </div>
                    <br>
                    <div class="wrap-currency">
                        <label for="currency">Валюта</label>
                        <select id="currency" required>
                            <option>USD</option>
                            <option>EUR</option>
                            <option>UAH</option>
                        </select>
                    </div>
                    <div class="wrap-date">
                        <label for="dateFrom">от :</label>
                        <input type="date" id="dateFrom">
                    </div>
                    <div class="wrap-date">
                        <label for="dateTo">до:</label>
                        <input type="date" id="dateTo">
                    </div>
                    <input type="email" id="emailTo" placeholder="Email" maxlength="30"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                    <br>
                    <input type="submit" class="submitReport" data-dismiss="modal"
                           onclick="generateReport()">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="wrap-modal">
    <div class="modal fade" id="deleteUser" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <p>Пользователь был удалён, спасибо.</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
