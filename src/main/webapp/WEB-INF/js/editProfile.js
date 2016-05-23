function editProfile(userId) {
    var formData = new FormData();
    formData.append('name', $('#name').val());
    formData.append('email', $('#email').val());
    formData.append('phone', $('#phone').val());
    formData.append('password', $('#password').val());
    formData.append('avatar', $('#avatar')[0].files[0]);


    $.ajax({
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            url: '/editProfile/' + userId,
            data: formData,
            processData: false,
            contentType: false,
            type: 'POST'
        })
        .success(function (userId) {
            top.location.href = '/user/' + userId;
        });
    
}