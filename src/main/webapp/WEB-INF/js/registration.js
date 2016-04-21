$(document).ready(function () {
    $('#btn-log').click(function () {
        $.ajax({
            type: 'POST',
            url: '/signIn',
            data: {
                email: $("#email").val(),
                pass: $("#password").val(),
            },
            success: function (res, status, xhr) {
                location.reload();
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
                $('#success-reg').show();
                console.log("show");
                $('#btn-ok').click(function () {
                    $('#success-reg').hide();
                    console.log("hide");
                });
               /*showMessage();*/
                location.reload();

            }
        });
    });
    function showMessage(){
        $('#success-reg').show();
        console.log("success!");
    }

    function hideMessage(){
        $('#btn-ok').click(function () {
            $('#success-reg').hide();
        });
    }
});

