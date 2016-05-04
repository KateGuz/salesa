function addDislike(userId) {
    var url = "/dislike/" + userId;
    $.post(url, function () {
        alert("Ваш отзыв добавлен. Спасибо");
    });
}