function sortByPrice(sortType) {
    var url = top.location.href;
    var value = false;
    if ("ASC" === sortType) {
        value = true;
    }

    if (url.indexOf("isSortPriceAsc") != -1) {
        url = url.replace(/(isSortPriceAsc=).*?($|&)/, '$1' + value + '$2');
        url = url.replace(/(page=).*?($|&)/, '$1' + "1" + '$2');
    } else {
        if (url.indexOf("?") == -1) {
            url += "?isSortPriceAsc=" + value;
        } else {
            url = url.replace(/(page=).*?($|&)/, '$1' + "1" + '$2');
            url += "&isSortPriceAsc=" + value;
        }
    }
    top.location.href = url;
}
function showActive(){
    var url = top.location.href;
    if(url.indexOf("isActiveParam") == -1){
        if (url.indexOf("?") == -1) {
            url += "?isActiveParam=true";
        } else {
            url = url.replace(/(page=).*?($|&)/, '$1' + "1" + '$2');
            url += "&isActiveParam=true";
        }
    }
    top.location.href = url;
}
