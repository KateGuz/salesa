function addFeedback(userId) {
    var url = "/feedback/" + userId;
    var feedbackTextArea = $(".feedback-input");
    var feedbackText = feedbackTextArea.val();
    $.post(url, feedbackText, function () {
        alert("Ваш отзыв добавлен. Спасибо");
        feedbackTextArea.val("");
        location.reload();
    });
}