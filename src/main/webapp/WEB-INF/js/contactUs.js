$(document).ready(function () {

    console.log("DOCUMENT READY");

    $('#btn-send').click(function () {
        $.ajax({
            type: 'POST',
            url: '/contactUs',
            data: {
                name: $("#name").val(),
                email: $("#email").val(),
                subject: $("#subject").val(),
                message: $("#message").val(),
            },
            success: function () {
                alert("Ваше сообщение отправлено. Спасибо");
            }
        });
    });
});