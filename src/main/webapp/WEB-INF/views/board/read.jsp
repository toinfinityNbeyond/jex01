<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Read Page</h1>
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
                            <h3 class="card-title">Board Read</h3>
                        </div>
                        <!-- /.card-header -->

                        <div class="card-body">
                            <div class="form-group">
                                <label for="exampleInputEmail10">BNO</label>
                                <input type="text" name="bno" class="form-control" id="exampleInputEmail10" value="<c:out value="${boardDTO.bno}"></c:out>" readonly>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Title</label>
                                <input type="text" name="title" class="form-control" id="exampleInputEmail1" value="<c:out value="${boardDTO.title}"></c:out>" readonly>
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
                                        <textarea name="content" class="form-control" rows="3" disabled><c:out value="${boardDTO.content}"></c:out>
                                        </textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.card-body -->

                        <div class="card-footer float-right">
                            <button type="button" class="btn btn-default btnList">LIST</button>
                            <button type="button" class="btn btn-info btnMod">MODIFY</button>
                        </div>

                    </div>
                    <!-- /.card -->
                </div>
            </div>
        </div>
    </section>
</div>
<!-- /.content-wrapper -->

<form id="actionForm" action="/board/list" method="get">
    <input type="hidden" name="page" value="${pageRequestDTO.page}">  <!--값을 준 해당 페이지로 돌아가기 위해-->
    <input type="hidden" name="size" value="${pageRequestDTO.size}">

    <c:if test="${pageRequestDTO.type != null}">
        <input type="hidden" name="type" value= "${pageRequestDTO.type}">
        <input type="hidden" name="keyword" value= "${pageRequestDTO.keyword}">
    </c:if>
</form>

<%@include file="../includes/footer.jsp"%>

<script>
    const actionFrom = document.querySelector("#actionForm")

    document.querySelector(".btnList").addEventListener("click",()=> {actionFrom.submit()}, false)

    document.querySelector(".btnMod").addEventListener("click",()=> {

        const bno = '${boardDTO.bno}'
        actionForm.setAttribute("action","/board/modify")
        actionForm.innerHTML +=`<input type='hidden' name='bno' value='\${bno}'>`
        actionFrom.submit()
    }, false)

</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script> <!--axios 를 사용하기 위함-->
<script src="/resources/js/reply.js"></script>  <!--스크립트 단위로 읽고 로딩을 하고 해석. 실무에서는 이렇게 안씀-> 우리 수준....-->
<!--스크립트라고 하나에 다 묶으면 안됨-->

<script>

    function after (result) { // 배열을 받아서 사용하는 예제 //reply.js(/resources/js) 에 선언한 arr 이 result 가 된다. 콜백 방식
        //
        //이름만 준다.
        // 값을 주는 이름이랑 받는 이름이랑 상관이 없다.
       // fn은 read.jsp의 function after가 들어감
        console.log("after......")
        console.log("result" , result) //.js 에서 fn(arr)으로 받아온 결과를 확인한다.
    }

    //const doB() = function (arr) -> doB(after(result)) = function(arr) -> result = arr



    //doA().then(result => console.log(result))  //엑시오스를 겟으로 보냈는데 언제올지 모르고 약속만 해줌. 약속이 되면 then옴
    //then 은 비동기에서 사용 ㄴ 돌아오면 이걸 실행해달라

    // doB(after)   //after괄호가 없으면 객체 . after() 괄호가 있으면 실행하는 결과

    //const reply = {bno:230, replyer:'user00', reply: '2222222233333330000'}  // 객체 리터럴. 임의로 값을 줘서 확인하련ㄴ 것

    //doC(reply).then(result => console.log(result)) //data 가져온 것 확인했으니 then 써서 리턴 값 잘 갖고 오는지 확인

    //doD(112).then(result => console.log(result)) //댓글 번호 추가

    const reply = {rno:112, reply:"Update reply text...."} //객체 전달 , 임의의 댓글
    doE(reply).then(result => console.log(result)) // 수정 완료된 결과를 보여줌

</script>


</body>
</html>


<%--<script>--%>

<%--    function after(result){--%>
<%--        console.log("after………………")--%>
<%--        console.log("result",result) // .js에서 fn(arr)으로 받아온 결과를 확인한다.--%>
<%--    }--%>

<%--    // console.log(doA()) //doA()호출했으니까 read.jsp에 있는 이게 먼저 출력하고 -> promise만 먼저 반환해줌 (thread.sleep을 찍어놔서 나중에 반환된 값이 나중에 찍힘)--%>
<%--    // doA().then(result => console.log(result)) // 제대로 결과값이 나오려면 결국 Then을 사용해야 한다.--%>

<%--    // doB(after) //위의 after함수를 객체로 받아서 (괄호없이) 파라미터로 전달--%>

<%--    // const reply = {bno:201, replyer:'user00', reply:'12314839471897'}--%>
<%--    // doC(reply).then(result => console.log(result))--%>

<%--    // doD(112).then(result => console.log(result))--%>

<%--    const reply = {rno:112, reply:"Update reply text………"} // 댓글 112번에 입력할 내용--%>
<%--    doE(reply).then(result => console.log(result))//위에 입력한 reply 호출--%>


<%--</script>--%>