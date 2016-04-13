$(document).ready(function () {

    console.log("DOCUMENT READY");

    $('#btn-log').click(function () {
        $.ajax({
            type: 'GET',
            url: '/user',
            data: {
                email: $("#log_email").val(),
                pass: $("#log_password").val(),
            },
            success: function (res, status, xhr) {
                //window.location.reload(true);
                window.location.href = '/'
            }
        });
    });
    $('#btn-reg').click(function () {
        $.ajax({
            type: 'POST',
            url: '/user',
            dataType: 'html',
            data: {
                name: $("#reg_name").val(),
                email: $("#reg_email").val(),
                pass: $("#reg_password").val(),
            },
            success: function (res, status, xhr) {
                //alert(xhr.getResponseHeader("info"));
            }
        });
    });
});