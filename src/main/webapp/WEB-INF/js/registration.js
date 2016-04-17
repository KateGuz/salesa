$(document).ready(function () {

    console.log("DOCUMENT READY");

    $('#btn-log').click(function () {
        $.ajax({
            type: 'POST',
            url: '/signIn',
            data: {
                email: $("#email").val(),
                pass: $("#password").val(),
            },
            success: function (res, status, xhr) {
                //window.location.reload(true);
                //window.location.href = '/'
            }
        });
    });
    $('#btn-reg').click(function () {
        $.ajax({
            type: 'POST',
            url: '/signUp',
            dataType: 'html',
            data: {
                name: $("#name").val(),
                email: $("#email").val(),
                pass: $("#password").val(),
            },
            success: function () {
                alert("Вы зарегистрированы. Спасибо");
            }
        });
    });
});


