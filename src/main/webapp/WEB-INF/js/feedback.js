function addFeedback(userId) {
    var url = "/feedback/" + userId;
    var feedbackTextArea = $(".feedback-input");
    var feedbackText = feedbackTextArea.val();
    $.post(url, feedbackText, function () {
        $('#feedback-ok').modal('show');
        setTimeout(hideModal, 3000);
        feedbackTextArea.val("");
        $('#users-feedback').load(document.URL + ' #users-feedback');
        //document.getElementById('users-feedback').classList.add('show-div');
    });
    function hideModal(){
        $('#feedback-ok ').modal('hide');
    }
}

