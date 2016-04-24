function addFeedback(userId) {
    var url = "/feedback/" + userId;
    var feedbackTextArea = $(".feedback-input");
    var feedbackText = feedbackTextArea.val();
    $.post(url, feedbackText, function () {
        $('#feedback-ok').modal('show');
        setTimeout(hideModal, 2500);
        feedbackTextArea.val("");
        location.reload();
    });
    function hideModal(){
        $('#feedback-ok ').modal('hide');
    }
}

