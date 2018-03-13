(function (w, d, u) {
    var id = document.getElementById("id");
    var title = document.getElementById("title");
    var price = document.getElementById('price');
    var image = document.getElementById('image');
    var fileSelect = document.getElementById('fileSelect');
    var uploadButton = document.getElementById('uploadBtn');
    var summary = document.getElementById("summary");
    var detail = document.getElementById('detail');
    var submitBtn = document.getElementById('submit');
    var imgpre = document.getElementById('imgpre');
    var isSubmitting = false;

    var form = document.getElementById('uploadForm');
    var uploadType = document.getElementById('uploadType');
    uploadType.onclick = function (event) {
        event = window.event || event;
        var node = event.srcElement || event.target;
        if (node.nodeName === "INPUT") {
            if (node.value === 'url') {
                image.parentElement.style.display = "block";
                form.style.display = "none";
            } else {
                form.style.display = "block";
                image.parentElement.style.display = "none";
            }
            image.classList.remove("z-err");
            form.classList.remove("z-err");
        }
    };

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
                return value == '' || !/\.(jpg|gif|png)$/.test(value)
            }],
            [detail, function (value) {
                return value.length < 2 || value.length > 1000
            }],
            [price, function (value) {
                return value == '' || !Number(value) || !Number.isInteger(Number(value))
            }]
        ].forEach(function (item) {
            var value = item[0].value.trim();
            if (item[1](value)) {
                item[0].classList.add('z-err');
                result = false;
            }
            item[0].value = value;
        });
        if (image.value == '') {
            fileSelect.classList.add('z-err');
        }
        return result;
    };

    var doSubmit = function () {
        isSubmitting = true;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                isSubmitting = false;
                if (xhr.status == 200) {
                    var response = JSON.parse(xhr.response);
                    window.location = "/detail/" + response.id;
                }
                else {
                    window.alert("提交失败！");
                }
            }
        };
        var request = {};
        request.title = title.value;
        request.price = Number(price.value);
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
        if (value != '' && /\.(jpg|gif|png)$/.test(value)) {
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
    fileSelect.addEventListener("change", function (e) {
        fileSelect.classList.remove('z-err');
        image.classList.remove('z-err');
    });

    uploadButton.onclick = function (event) {
        uploadButton.innerHTML = 'Uploading...';
        var file = fileSelect.files[0];
        var formData = new FormData();
        if (!file.type.match('image.*')) {
            window.alert("只能上传jpg/png/gif图片！");
            return;
        }
        formData.append('file', file, file.name);

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var url = xhr.responseText
                    image.value = url;
                    imgpre.src = url;
                    window.alert("上传成功！");
                } else {
                    window.alert("上传失败！");
                }
            }
        };
        xhr.open("POST", "/api/pics", true);
        xhr.send(formData);
    };
})(window, document);