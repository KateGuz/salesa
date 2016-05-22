function saveAdvertWithImage() {
    var formData = new FormData();
    formData.append('title', $('#title').val());
    formData.append('price', $('#price').val());
    formData.append('currency', $('#currency').val());
    formData.append('text', $('#description').val());
    formData.append('categoryId', $('#category').val());
    formData.append('status', $('#status').val());
    formData.append('mainImage', $('#advertImage')[0].files[0]);

    var files = $('#otherAdvertImages').prop('files');
    for(var i=0;i<files.length;i++){
        formData.append('otherImages', files[i]);
    }
    $.ajax({
            headers: {
                Accept : "application/json; charset=utf-8"
            },
            url: '/addAdvert',
            data: formData,
            processData: false,
            contentType: false,
            type: 'POST'
        })
        .success(function (userId) {
            console.log("Redirect to User page with id -> " + userId);
            top.location.href = '/user/' + userId;
        });
}
