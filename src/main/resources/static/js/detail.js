(function (window, document) {
    var doBuy = function (e) {
        var ele = e.target;
        var id = ele && ele.dataset.buy;
        if (id) {
            var r = confirm("确定将物品放入购物车？");
            if (r == true) {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == XMLHttpRequest.DONE) {
                        if (xhr.status == 200) {
                            window.alert("已加入购物车！")
                        }
                        else {
                            window.alert(xhr.response.error || "购买失败！");
                        }
                    }
                };
                xhr.open("POST", "/api/cart/" + id, true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send();
            }
        }
    }
    var buyBtn = document.getElementById("buyBtn");
    if (buyBtn) {
        buyBtn.addEventListener("click", doBuy);
    }
})(window, document);