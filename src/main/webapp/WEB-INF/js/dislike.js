/**
 * Created by artem on 26.04.2016.
 */
function addDislike(userId) {
    var url = "/dislike/" + userId;
    $.post(url, function () {
        alert("Ваш отзыв добавлен. Спасибо");
        location.reload();
    });
}