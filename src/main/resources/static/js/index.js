(function (window, document) {
    var doDelete = function (id, node) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    node.parentElement.removeChild(node);
                }
                else {
                    window.alert("删除失败");
                }
            }
        };
        xhr.open("DELETE", "/api/items/" + id, true);
        xhr.send();
    };

    var handleClick = function (e) {
        var e = arguments[0] || window.event;
        target = e.srcElement ? e.srcElement : e.target;
        if (target.nodeName == "BUTTON" && target.classList.contains("deleteBtn")) {
            var r = confirm("确定删除该内容？");
            if (r == true) {
                var id = target.previousElementSibling.value;
                var node = target.parentElement;
                doDelete(id, node);
            }
        }
        return false;
    };

    var plist = document.getElementById("plist");
    if (plist) {
        plist.addEventListener("click", handleClick);
    }
})(window, document);