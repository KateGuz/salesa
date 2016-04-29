/**
 * Created by Artem on 29.04.2016.
 */
function filteringByStatus(page, status) {
    var url = "?page=1" + page + "&sort=" + status;
    $.get(url, function () {
    });
}

function filteringByPrice() {
    $.get();
}

function filteringByPriceDesc() {
    $.get();
}