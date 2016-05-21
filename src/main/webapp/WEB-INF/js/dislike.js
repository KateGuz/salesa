function addDislike(userId) {
    var url = "/dislike/" + userId;
    
    $.post(url, function () {
        message($('#dislike'));
        document.getElementById('dislike-button').classList.add('hidden-div');
        /*document.getElementById('dislike-button').disabled=true;*/
    });

    function message(a){
        a.modal('show');
        setTimeout(function() {a.modal('hide')}, 2000);
    }
}