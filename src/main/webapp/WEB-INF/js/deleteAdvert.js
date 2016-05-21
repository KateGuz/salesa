function deleteAdvert(advertId, userId) {
    if(confirm("Вы уверены, что хотите удалить это объявление?")){
        $.ajax({
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                url: '/deleteAdvert/' + advertId,
                processData: false,
                contentType: false,
                type: 'DELETE'
            })
            .success(function (userId) {
                top.location.href = '/user/' + userId;
            });
    }
    else {
        top.location.href = '/user/' + userId;
    }
}