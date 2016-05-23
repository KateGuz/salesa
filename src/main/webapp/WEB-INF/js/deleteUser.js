function deleteUser(userId) {
    if(confirm("Вы уверены, что хотите удалить этого юзера?")){
        $.ajax({
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                url: '/deleteUser/' + userId,
                processData: false,
                contentType: false,
                type: 'DELETE'
            })
            .success(function () {
                top.location.href = '/';
            });
    }
    else {
        top.location.href = '/user/' + userId;
    }
}