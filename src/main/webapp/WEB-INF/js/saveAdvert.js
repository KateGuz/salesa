$(document).ready(function (userId) {

        $('form').submit(function(event) {
            var formData = {
                title: $('#title').val(),
                price: $('#price').val(),
                currency: $('#currency').val(),
                text: $('#description').val(),
                categoryId: $('#category').val(),
                status: $('#status').val()
            };
            $.ajax({
                    type        : 'POST',
                    url         : '/addAdvert/',
                    data        : formData,
                    /*dataType    : 'json'*/
                  /*  encode          : true*/
                })
                .success(function(data) {
                    console.log(data);
                });
            event.preventDefault();
        });

    /*$('.save').click(function () {
        $.ajax({
            url: '/addAdvert',
            type: 'POST',
            data: {
                title: $('#title').val(),
                price: $('#price').val(),
                currency: $('#currency').val(),
                text: $('#description').val(),
                category: $('#category').val(),
                status: $('#status').val()
            },
            success: function(){
                console.log("kozaebis'!");
            },
            error(){
                console.log(" ah tu j bliat'!");
            }
        });
    });*/
})