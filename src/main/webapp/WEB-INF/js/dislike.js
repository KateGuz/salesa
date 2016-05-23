function addDislike(userId) {
    var url = "/dislike/" + userId;
    
    $.post(url, function () {
        message($('#dislike'));
        $('#dislike-button').attr("disabled", "");
    });

    function message(a){
        a.modal('show');
        setTimeout(function() {a.modal('hide')}, 2000);
    }
}