$(document).ready(function () {

    console.log("DOCUMENT READY");
/*
    var iframe = document.createElement("IFRAME");
    iframe.setAttribute("src", 'data:text/plain,');
    document.documentElement.appendChild(iframe);*/


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
                name: $("#reg-name").val(),
                email: $("#reg-email").val(),
                pass: $("#reg-password").val(),
            },
            success: function (res, status, xhr) {
            window.location.reload(),
               alert("Вы зарегистрированы. Спасибо");
            }
        });
    });
    /*window.frames[0].window.
    iframe.parentNode.removeChild(iframe);*/
});





