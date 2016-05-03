function addAdvertImage() {
    var formData = new FormData();
    formData.append('file', $('#advertImage')[0].files[0]);
    console.log("form data " + formData);

    var advertId = $("#advertId").val();
    $.ajax({
        url: '/advert/' + advertId + '/image',
        data: formData,
        processData: false,
        contentType: false,
        type: 'POST'
    });
}