function showPassword() {
    var check = document.getElementById("checkbox");
    var e = document.getElementById("password");
    if(check.hasAttribute("checked")){
        e.removeAttribute("type");
        e.setAttribute("type", "text");
        check.removeAttribute("checked");
    }
    else{
        e.removeAttribute("type");
        e.setAttribute("type", "password");
        check.setAttribute("checked", "");
    }
}