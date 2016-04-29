function addFeedback(userId) {
    var url = "/feedback/" + userId;
    var feedbackTextArea = $(".feedback-input");
    var feedbackText = feedbackTextArea.val();
    $.post(url, feedbackText, function () {
        $('#feedback-ok').modal('show');
        console.log("show");
        setTimeout(hideModal, 3000);
        console.log("hide");
        feedbackTextArea.val("");
        location.reload();
    });
    function hideModal(){
        $('#feedback-ok ').modal('hide');
    }
}

