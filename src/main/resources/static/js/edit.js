(function (w, d, u) {
    var id = document.getElementById("id");
    var title = document.getElementById("title");
    var price = document.getElementById('price');
    var image = document.getElementById('image');
    var summary = document.getElementById("summary");
    var detail = document.getElementById('detail');
    var submitBtn = document.getElementById('submit');
    var imgpre = document.getElementById('imgpre');
    var isSubmitting = false;

    var check = function () {
        var result = true;
        [
            [title, function (value) {
                return value.length < 2 || value.length > 80
            }],
            [summary, function (value) {
                return value.length < 2 || value.length > 140
            }],
            [image, function (value) {
                return value == '' || !(/^(http|https):\/\//.test(value) && /\.(jpg|gif|png)$/.test(value))
            }],
            [detail, function (value) {
                return value.length < 2 || value.length > 1000
            }],
            [price, function (value) {
                return value == '' || !Number(value)
            }]
        ].forEach(function (item) {
            var value = item[0].value.trim();
            if (item[1](value)) {
                item[0].classList.add('z-err');
                result = false;
            }
            item[0].value = value;
        });
        return result;
    };

    var doSubmit = function() {
        isSubmitting = true;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                isSubmitting = false;
                var response = JSON.parse(xhr.response);
                if (xhr.status == 200) {
                    window.location = "/detail/" + response.id;
                }
                else {
                    window.alert(response.error || "提交失败！");
                }
            }
        };
        var request = {};
        request.title = title.value;
        request.price = price.value;
        request.image = image.value;
        request.summary = summary.value;
        request.detail = detail.value;
        if (id) {
            xhr.open("PUT", "/api/items/" + id.value, true);
        } else {
            xhr.open("POST", "/api/items/", true);
        }
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.send(JSON.stringify(request));
    };

    image.addEventListener('input', function (e) {
        var value = image.value.trim();
        if (value != '' && /^(http|https):\/\//.test(value) && /\.(jpg|gif|png)$/.test(value)) {
            imgpre.src = value;
        }
    });

    submitBtn.addEventListener('click', function (e) {
        if (!isSubmitting && check()) {
            price.value = Number(price.value);
            doSubmit();
        }
    });

    [title, summary, image, detail, price].forEach(function (item) {
        item.addEventListener('input', function (e) {
            item.classList.remove('z-err');
        })
    });
})(window, document);