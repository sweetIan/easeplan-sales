(function (window, document) {
    var showErrorLabel = function () {
        document.getElementById("errorLabel").style.display = "inline-block";
    };
    var hideErrorLabel = function () {
        document.getElementById("errorLabel").style.display = "none";
    };
    var doLogin = function () {
        hideErrorLabel();
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == XMLHttpRequest.DONE) {
                if (xhttp.status == 200) {
                    var url = document.getElementById("referer").value;
                    window.location = url;
                }
                else {
                    showErrorLabel();
                }
            }
        };
        xhttp.open("POST", "/api/login", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("username=" + username + "&password=" + md5(password));
    };

    document.getElementById("submitBtn").addEventListener("click", doLogin);
    document.getElementById("loginForm").onkeypress = function (e) {
        if (!e) {
            e = window.event;
        }
        if ((e.keyCode || e.which) == 13) {
            doLogin();
        }
    };

})(window, document);