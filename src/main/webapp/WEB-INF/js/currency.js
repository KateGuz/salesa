function changeCurrency(currency) {
    top.location.href = "/" + $('.activePage').attr('id') + "&currency=" + currency;
}

function changeCurrencyOnAdvertPage(currency) {
    var url = top.location.href;
    if (url.indexOf("currency") != -1) {
        url = url.replace(/(currency=).*?($)/, '$1' + currency+ '$2');
    } else {
        url += "?currency=" + currency;
    }
    top.location.href = url;
}
function changeCurrencyOnUserPage(currency) {
    var url = top.location.href;
    if (url.indexOf("currency") != -1) {
        url = url.replace(/(currency=).*?($)/, '$1' + currency+ '$2');
    } else {
        url += "?currency=" + currency;
    }
    top.location.href = url;
}

