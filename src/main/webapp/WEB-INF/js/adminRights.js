function makeAdmin(userId) {
    var url = "/makeAdmin/" + userId;
    $.post(url, function () {
        message($('#makeAdmin'));
        $('#admin-buttons').load(document.URL + ' #admin-buttons');
    });

    function message(a) {
        a.modal('show');
        setTimeout(function () {
            a.modal('hide')
        }, 2000);
        var test = "Сделать админом";
        var adminButton = $("#make-admin");
        if ($(adminButton).attr("value") === test) {
            $(adminButton).attr("value", "Лишить админских прав");
        } else {
            $(adminButton).attr("value", "Сделать админом");
        }
    }
}