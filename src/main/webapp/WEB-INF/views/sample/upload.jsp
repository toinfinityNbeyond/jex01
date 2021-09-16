<%--
  Created by IntelliJ IDEA.
  User: cloud
  Date: 2021/09/07
  Time: 2:40 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <input type="file" name="uploadFiles" multiple><button id="uploadBtn">UPLOAD</button>

    <div class="uploadResult">

    </div>

    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script> <!-- 실제 업로드된 파일이 나오는 곳 -->


    <script>

    const uploadResultDiv = document.querySelector(".uploadResult")

    document.querySelector("#uploadBtn").addEventListener("click", (e) => {

        const formData = new FormData()
        const  fileInput = document.querySelector("input[name = 'uploadFiles']")

        for (let i =0; i < fileInput.files.length; i++){
            formData.append("uploadFiles", fileInput.files[i]) //컨트롤에서 받는 이름 . 파라미터 이름. "uploadFiles"이름 중요

        } // 같은 이름으로 여러개를 담는게 핵심

        console.log(formData)   //dir - 속성 값들이 자세히 나옴. 폼 데이터가 어떻게 나오는지.....

        //axios 로 업로드
        const headerObj = { headers: {'Content-Type' : 'multipart/form-data'} }

        axios.post("/upload", formData, headerObj).then((response) => {
            const arr = response.data
            console.log(arr)
            let str = ""
            // 루프생성, 스프레드 연산자 이용
            for (let i = 0; i < arr.length; i ++) {
                const {uuid,fileName,uploadPath, image, thumbnail, fileLink} = {...arr[i]}

                if (image) {
                    str += `<div data-uuid='\${uuid}'><img src='/viewFile?file=\${thumbnail}'/><span>\${fileName}</span>
                            <button onclick="javascript:removeFile('\${fileLink}',this)" >x</button></div>` // 업로드를 여러번 할 수 있어서 누적. this 는 현재 객체를 의미
                } else{
                    str += `<div data-uuid='\${uuid}'><a href='/downFile?file=\${fileLink}'>\${fileName}</a></div>`
                }

            } //end for
            uploadResultDiv.innerHTML += str
        })

    },false)

    function removeFile(fileLink,ele) {
        console.log(fileLink)
        axios.post("/removeFile", {fileName:fileLink}).then(response => {
            const targetDiv = ele.parentElement
            targetDiv.remove()
        })
    }



</script>
</body>
</html>
