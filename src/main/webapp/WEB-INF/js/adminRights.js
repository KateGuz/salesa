function makeAdmin(userId) {
    var url = "/makeAdmin/" + userId;
    $.post(url, function () {
        message($('#makeAdmin'));
        $('#admin-buttons').load(document.URL + ' #admin-buttons');
    });

    function message(a){
        a.modal('show');
        setTimeout(function() {a.modal('hide')}, 2000);
    }
}

function deleteAdvert(advertId) {
    var url = "/deleteAdvert/"+ advertId;
    $.post(url, function () {
        message($('#deleteAdvert'));
    });

    function message(a){
        a.modal('show');
        setTimeout(function() {a.modal('hide')}, 2000);
    }
}

function deleteUser(userId) {
    var url = "/deleteUser/"+ userId;
    $.post(url, function () {
        message($('#deleteUser'));
    });

    function message(a){
        a.modal('show');
        setTimeout(function() {a.modal('hide')}, 2000);
    }
}