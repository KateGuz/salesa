function changeCurrency(currency) {
    var url = "/" + $('.activePage').attr('id') + "&currency=" + currency;

    top.location.href = url;
}
