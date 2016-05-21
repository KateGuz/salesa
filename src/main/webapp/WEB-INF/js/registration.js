$(document).ready(function () {
    $('#btn-log').click(function () {
        if ($("#email").val() === '' || $("#password").val() === '') {
            $('#btn-log').on("click", function (e) {
                e.preventDefault;
            });
        }
        $.ajax({
            type: 'POST',
            url: '/signIn',
            dataType: 'json',
            data: {
                email: $("#email").val(),
                pass: $("#password").val()
            },
            success: function (loggedUser) {
                $('.userLink').html('<a href="/user/${loggedUser.id}">${loggedUser.name}</a>');
                $('.out').html('<a href="/signOut">Выйти</a>').show();
                $('.alert').hide();
            },
            error: function () {
                message($('#error'));
            }
        });

        $('#buttons').load(document.URL + ' #buttons');
        $('#adverts-list').load(document.URL + ' #adverts-list');
        $('#feedback-list').load(document.URL + ' #feedback-list');
        $('#advert-info').load(document.URL + ' #advert-info');
    });
    $('#btn-reg').click(function () {
        if ($("#reg-name").val() === '' || $("#reg-email").val() === '' || $("#reg-password").val() === '') {
            $('#btn-log').on("click", function (e) {
                e.preventDefault;
            });
        }
        $.ajax({
            type: 'POST',
            url: '/signUp',
            dataType: 'json',
            data: {
                name: $("#reg-name").val(),
                email: $("#reg-email").val(),
                pass: $("#reg-password").val()
            },
            success: function (loggedUser) {
                message($('#success-reg'));
                $('.userLink').html('<a href="/user/${loggedUser.id}">${loggedUser.name}</a>');
                $('.out').html('<a href="/signOut">Выйти</a>').show();
            },
            error: function () {
                message($('#error'));
            }
        });
        $('#advert-info').load(document.URL + ' #advert-info');
        $('#buttons').load(document.URL + ' #buttons');
        $('#adverts-list').load(document.URL + ' #adverts-list');
        $('#feedback-list').load(document.URL + ' #feedback-list');
    });
    function message(a) {
        a.modal('show');
        setTimeout(function () {
            a.modal('hide')
        }, 2000);
    }
});
