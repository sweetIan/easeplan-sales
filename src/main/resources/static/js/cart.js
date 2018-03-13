(function (window, document) {
    var changeItemAmount = function (id, num, node) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    node.parentElement.parentElement.children[1].textContent = num;
                    node.parentElement.style.display = "none";
                    node.parentElement.previousElementSibling.style.display = "";
                }
                else {
                    window.alert("修改失败");
                }
            }
        };
        xhr.open("PUT", "/api/cart/" + id, true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send("num=" + num);
    };

    var removeItem = function (id, node) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var rowElement = node.parentElement.parentElement;
                    rowElement.parentElement.removeChild(rowElement);
                }
                else {
                    window.alert("删除失败");
                }
            }
        };
        xhr.open("DELETE", "/api/cart/" + id, true);
        xhr.send();
    };

    var handleClick = function(e) {
        var e = arguments[0] || window.event;
        target = e.srcElement ? e.srcElement : e.target;
        if(target.nodeName == "BUTTON" && target.classList.contains("changeAmount")){
            target.style.display = "none"
            target.nextElementSibling.style.display = "block";
        }else if(target.nodeName == "BUTTON" && target.classList.contains("submitBtn")){
            var id = target.parentElement.parentElement.firstElementChild.value;
            var num = target.previousElementSibling.value;
            changeItemAmount(id, num, target);
        }else if(target.nodeName == "BUTTON" && target.classList.contains("cancelBtn")){
            target.parentElement.style.display = "none";
            target.parentElement.previousElementSibling.style.display = "";
        }else if(target.nodeName == "BUTTON" && target.classList.contains("removeBtn")){
            var id = target.previousElementSibling.value;
            removeItem(id, target);
        }
        return false;
    };

    document.getElementById("cartTable").addEventListener("click", handleClick);
    document.getElementById("back").addEventListener("click", function (ev) {
        var url = document.getElementById("referer").value;
        window.location = url;
    });
    document.getElementById("purchase").addEventListener("click", function (ev) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    window.alert("购买成功！");
                }
                else {
                    window.alert("购买失败！");
                }
            }
        };
        xhr.open("POST", "/api/purchase", true);
        xhr.send();
    });
})(window, document);