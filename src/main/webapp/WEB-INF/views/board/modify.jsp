<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Modify Page</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard v1</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <!-- left column -->
                <div class="col-md-12">
                    <!-- general form elements -->
                    <div class="card card-primary">
                        <div class="card-header">
                            <h3 class="card-title">Board Modify</h3>
                        </div>
                        <!-- /.card-header -->
                        <form id="form1">
                            <input type="hidden" name="page" value="${pageRequestDTO.page}">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">

                            <c:if test="${pageRequestDTO.type != null}">
                                <input type="hidden" name="type" value= "${pageRequestDTO.type}">
                                <input type="hidden" name="keyword" value= "${pageRequestDTO.keyword}">
                            </c:if>

                            <div class="card-body">
                                <div class="form-group">
                                    <label for="exampleInputEmail10">BNO</label>
                                    <input type="text" name="bno" class="form-control" id="exampleInputEmail10" value="<c:out value="${boardDTO.bno}"></c:out>" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Title</label>
                                    <input type="text" name="title" class="form-control" id="exampleInputEmail1" value="<c:out value="${boardDTO.title}"></c:out>" >
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail2">Writer</label>
                                    <input type="text" name="writer" class="form-control" id="exampleInputEmail2" value="<c:out value="${boardDTO.writer}"></c:out>" readonly>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- textarea -->
                                        <div class="form-group">
                                            <label>Textarea</label>
                                            <textarea name="content" class="form-control" rows="3" ><c:out value="${boardDTO.content}"></c:out>
                                        </textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.card-body -->
                            <div class="temp"></div>

                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btnList">목록</button>
                                <button type="submit" class="btn btn-warning btnMod">수정</button>
                                <button type="submit" class="btn btn-danger btnDel">삭제</button>
                            </div>
                        </form>
                    </div>


                    <label for="exampleInputFile">File input</label>
                    <div class="input-group">
                        <div class="custom-file">
                            <input type="file" name="uploadFiles" class="custom-file-input" id="exampleInputFile" multiple>
                            <label class="custom-file-label" for="exampleInputFile">Choose file</label>
                        </div>
                        <div class="input-group-append">
                            <span class="input-group-text" id="uploadBtn">Upload</span>
                        </div>
                    </div>


                    <div class="uploadResult">
                        <c:forEach items="${boardDTO.files}" var="attach">
                            <div data-uuid="${attach.uuid}" data-filename="${attach.fileName}" data-uploadpath="${attach.uploadPath}" data-image="${attach.image}">
                                <c:if test="${attach.image}">
                                    <img src="/viewFile?file=${attach.getThumbnail()}">
                                </c:if>
                                <span>${attach.fileName}</span>
                                <button onclick="javascript:removeDiv(this)">x</button> <!--파일의 링크를 찾아서 삭제.Div만 삭제. this는 자기자신을 가르키고 상위인 부모를 찾아서 삭제. 버튼의 상위버전인 Div를 찾아서 삭제 -->
                            </div>
                        </c:forEach>
                    </div>
                    <!-- /.card -->
                </div>
            </div>
        </div>
    </section>
</div>
<!-- /.content-wrapper -->


<form id="actionForm" action="/board/list" method="get">
    <input type="hidden" name="page" value="${pageRequestDTO.page}">
    <input type="hidden" name="size" value="${pageRequestDTO.size}">

    <c:if test="${pageRequestDTO.type != null}">
        <input type="hidden" name="type" value= "${pageRequestDTO.type}">
        <input type="hidden" name="keyword" value= "${pageRequestDTO.keyword}">
    </c:if>


</form>



<%@include file="../includes/footer.jsp"%>

<script>
    const form = document.querySelector("#form1")
    const actionForm = document.querySelector("#actionForm")

    document.querySelector(".btnList").addEventListener("click", (e) => {
        e.preventDefault()
        e.stopPropagation()
        actionForm.submit();
    },false);

    document.querySelector(".btnDel").addEventListener("click", (e) => {
        e.preventDefault()
        e.stopPropagation()

        form.setAttribute("action","/board/remove")
        form.setAttribute("method","post")
        form.submit()

    },false);




    document.querySelector(".btnMod").addEventListener("click", (e) => {
        //dom 관련
        e.preventDefault()
        e.stopPropagation()

        const fileDivArr = uploadResultDiv.querySelectorAll("div")

        if (fileDivArr && fileDivArr.length > 0 ){ //파일 fileDivArr divarr 이 클 떄 기존에 첨부파일 수정을 하나도 안했으면 첨부파일을 새로 추가한게 없어도 등록
            let str = "";
            for (let i = 0; i < fileDivArr.length ; i++) {
                const target = fileDivArr[i]
                const uuid = target.getAttribute("data-uuid")
                const fileName = target.getAttribute("data-filename")
                const uploadPath = target.getAttribute("data-uploadpath")
                const image = target.getAttribute("data-image")

                str += `<input type='hidden' name='files[\${i}].uuid' value='\${uuid}' >`
                str += `<input type='hidden' name='files[\${i}].fileName' value='\${fileName}' >`
                str += `<input type='hidden' name='files[\${i}].uploadPath' value='\${uploadPath}' >`
                str += `<input type='hidden' name='files[\${i}].image' value='\${image}' >`
            }

            document.querySelector(".temp").innerHTML = str


        } //end if



        form.setAttribute("action","/board/modify")
        form.setAttribute("method","post")
        form.submit()

    },false);


</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>

    const uploadResultDiv = document.querySelector(".uploadResult")

    document.querySelector("#uploadBtn").addEventListener("click", (e) => {

        const formData = new FormData()
        const fileInput = document.querySelector("input[name = 'uploadFiles']")

        for (let i =0; i < fileInput.files.length; i++){
            formData.append("uploadFiles", fileInput.files[i]) //컨트롤에서 받는 이름 . 파라미터 이름. "uploadFiles"이름 중요

        } // 같은 이름으로 여러개를 담는게 핵심

        console.log(formData)   //dir - 속성 값들이 자세히 나옴. 폼 데이터가 어떻게 나오는지.....

        //axios 로 업로드
        const headerObj = { headers: {'Content-Type' : 'multipart/form-data'}}

        axios.post("/upload", formData, headerObj).then((response) => {
            const arr = response.data
            console.log(arr)
            let str = ""
            // 루프생성, 스프레드 연산자 이용
            for(let i = 0; i < arr.length; i ++) {
                const {uuid,fileName,uploadPath, image, thumbnail, fileLink} = {...arr[i]}

                if (image) {
                    str += `<div data-uuid='\${uuid}' data-filename='\${fileName}' data-uploadpath='\${uploadPath}' data-image='\${image}'><img src='/viewFile?file=\${thumbnail}'/><span>\${fileName}</span>
                            <button onclick="javascript:removeDiv(this)" >x</button></div>` // 업로드를 여러번 할 수 있어서 누적. this 는 현재 객체를 의미
                } else{
                    str += `<div data-uuid='\${uuid}'data-filename='\${fileName}' data-uploadpath='\${uploadPath}' data-image='\${image}'><a href='/downFile?file=\${fileLink}'>\${fileName}</a></div>`
                }

            } //end for
            uploadResultDiv.innerHTML += str
        })

    },false)


    function removeDiv(ele){
        ele.parentElement.remove()
    }

</script>
</body>
</html>
