function changeCurrency(currency) {
    var url = top.location.href;
    if (url.indexOf("currency") != -1) {
        url = url.replace(/(currency=).*?($|&)/, '$1' + currency + '$2');
    } else {
        if (url.indexOf("?") == -1) {
            url += "?currency=" + currency;
        } else {
            url += "&currency=" + currency;
        }
    }
    top.location.href = url;
}

function changeCurrencyOnAdvertPage(currency) {
    var url = top.location.href;
    if (url.indexOf("currency") != -1) {
        url = url.replace(/(currency=).*?($)/, '$1' + currency + '$2');
    } else {
        url += "?currency=" + currency;
    }
    top.location.href = url;
}
function changeCurrencyOnUserPage(currency) {
    var url = top.location.href;
    if (url.indexOf("currency") != -1) {
        url = url.replace(/(currency=).*?($)/, '$1' + currency + '$2');
    } else {
        url += "?currency=" + currency;
    }
    top.location.href = url;
}

