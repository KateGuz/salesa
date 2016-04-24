
$(document).ready(function () {
    $('#btn-log').click(function () {
        $.ajax({
            type: 'POST',
            url: '/signIn',
            dataType: 'json',
            data: {
                email: $("#email").val(),
                pass: $("#password").val()
            },
            success: function (loggedUser) {
                console.log(loggedUser.id);
               $('.userLink').html(loggedUser.name);

            },
            error: function (reponse) {
                alert("error : " + reponse);
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
                pass: $("#reg-password").val()
            },
            success: function () {
                $('#success-reg').modal('show');
                setTimeout(hideModal, 2000);
            }
        })
    });

    function hideModal(){
        $('#success-reg').modal('hide');
    }
});

