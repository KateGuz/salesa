$(document).ready(function() {
    var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
    $("#email").change(function(){
        var email = $(this).val();
        if(email.length >= 3 && pattern.test(email)){
            $.ajax({
                type: "POST",
                url: "/validate",
                data: {
                    email: email
                },
                success: function(){
                    email.css({'border' : '1px solid #569b44'});
                    return true;
                }
            });
        }
        else{
            email.css({'border' : '1px solid #ff0000'});
            return false;
        }
    });
});

