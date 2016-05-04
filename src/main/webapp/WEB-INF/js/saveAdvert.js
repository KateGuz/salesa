$(document).ready(function () {

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
                    dataType    : 'json'
                  /*  encode          : true*/
                })
                .success(function(userId) {
                    top.location.href = '/user/' + userId ;
                });
            event.preventDefault();
        });

})