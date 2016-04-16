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
/*
function addFeedback(userId) {
    var url = "/feedback/" + userId;
    var feedbackTextArea = $(".feedback-input");
    var feedbackText = feedbackTextArea.val();
    //
    var xhr = new XMLHttpRequest();
    if (xhr.status == 401) {
        alert("Пожалуйста, авторизируйтесь!");
    } else {
        $.post(url, feedbackText, function () {
            alert("Ваш отзыв добавлен. Спасибо");
            feedbackTextArea.val("");
            location.reload();
        });
    }

}*/
