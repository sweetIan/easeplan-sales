(function (window, document) {
    var doBuy = function(e) {
        var ele = e.target;
        var buy = ele && ele.dataset.buy;
        if (buy) {
            var r = confirm("确定将物品放入购物车？");
            if (r == true) {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == XMLHttpRequest.DONE) {
                        if (xhr.status == 200) {
                            window.alert("已放入购物车！")
                        }
                        else {
                            window.alert(xhr.response.error || "购买失败！");
                        }
                    }
                };
                xhr.open("POST", "/api/buy", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("id=" + buy);
            }
        }
    }
    var buyBtn = document.getElementById("buyBtn");
    if (buyBtn) {
        buyBtn.addEventListener("click", doBuy);
    }
})(window, document);