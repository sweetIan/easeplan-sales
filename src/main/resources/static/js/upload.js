(function (window, document) {
    var fileSelect = document.getElementById('fileSelect');
    var uploadButton = document.getElementById('uploadBtn');
    var image = document.getElementById('image');
    var imgpre = document.getElementById('imgpre');

    uploadButton.onclick = function(event) {
        uploadButton.innerHTML = 'Uploading...';
        var file = fileSelect.files[0];
        var formData = new FormData();
        if (!file.type.match('image.*')) {
            window.alert("絵");
            return;
        }
        formData.append('file', file, file.name);

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var url=xhr.responseText
                    image.value=url;
                    imgpre.src=url;
                    window.alert("上传成功！");
                }
                else {
                    window.alert("上传失败！");
                }
            }
        };
        xhr.open("POST", "/pics", true);
        xhr.send(formData);
    };
})(window, document);