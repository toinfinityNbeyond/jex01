<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">List Page</h1>
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
            <!-- Main row -->
            <div class="row">
                <!-- Left col -->
                <section class="col-lg-12">
                    <!-- TO DO List -->
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">Bordered Table</h3>
                            <sec:authorize access="isAuthenticated()">
                                <button><a href="/board/register">register</a> </button>
                            </sec:authorize>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th style="width: 20px">BNO</th>
                                    <th>TITLE</th>
                                    <th>WRITER</th>
                                    <th>REGDATE</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${dtoList}" var="dto">
                                    <tr>
                                        <td><c:out value="${dto.bno}"></c:out></td>
                                        <td><a href="javascript:moveRead(${dto.bno})"> <c:out value="${dto.title}"></c:out></a></td>
                                        <td><c:out value="${dto.writer}"></c:out></td>
                                        <td><c:out value="${dto.regDate}"></c:out></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                            <form action="/board/list" method="get">
                                <input type="hidden" name="page" value="1"> <!--검색 시 결과가 1페이지부터 나오게 하려고 value값을 1 로 줬다.-->
                                <input type="hidden" name="size" value="${pageMaker.size}">
                            <div class="col-sm-3">
                                <!-- select -->
                                <div class="form-group">
                                    <label>Search</label>
                                    <select name="type" class="custom-select">
                                        <option value="">-------</option>
                                        <option value="T" ${pageRequestDTO.type == "T"?"selected" : ""}>제목</option>
                                        <option value="TC" ${pageRequestDTO.type == "TC"?"selected" : ""}>제목내용</option>
                                        <option value="C" ${pageRequestDTO.type == "C"?"selected" : ""}>내용</option>
                                        <option value="TCW" ${pageRequestDTO.type == "TCW"?"selected" : ""}>제목내용작성자</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-9">
                                <div class="input-group input-group-sm">
                                    <input type="text" class="form-control" name="keyword" value="${pageRequestDTO.keyword}">
                                    <span class="input-group-append"> <button type="submit" class="btn btn-info btn-flat">Go!</button></span>
                                </div>
                            </div>
                            </form>  <!--버튼이 폼안에 있어야 작동을 해서 폼으로 감싸줬다.-->

                        </div>
                        <!-- /.card-body -->

                        <div class="card-footer clearfix">
                            <ul class="pagination pagination-sm m-0 float-right">
                                <c:if test="${pageMaker.prev}">
                                    <li class="page-item"><a class="page-link" href="javascript:movePage(${pageMaker.start -1})"> << </a></li>
                                </c:if>

                                <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="num">
                                    <li class="page-item ${pageMaker.page == num?'active': ''}"><a class="page-link" href="javascript:movePage(${num})">${num}</a></li>
                                </c:forEach>   <!--1부터 10까지 돌아서 페이지번호 찍히게 forEach 선언-->
                                <!--c아웃 대신 a태그를  쓴 이유가 단순 클릭해서 페이지를 넘기기는 때문에, c 아웃은 공격 방어-> 단순처리가 그럴 일이 없어서 a태그 사용-->

                                <c:if test="${pageMaker.next}">
                                    <li class="page-item"><a class="page-link" href="javascript:movePage(${pageMaker.end + 1})"> >> </a></li>
                                </c:if>

                            </ul>
                        </div>
                    </div>
                    <!-- /.card -->
                </section>
                <!-- /.Left col -->
            </div>
            <!-- /.row (main row) -->
        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<div class="modal fade" id="modal-sm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Small Modal</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>One fine body&hellip;</p>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<form id="actionForm" action="/board/list" method="get">

    <input type="hidden" name="page" value="${pageMaker.page}">
    <input type="hidden" name="size" value="${pageMaker.size}">

    <c:if test="${pageRequestDTO.type != null}"> <!--검색 조건 있을 때만 URL에 나오게-->
    <input type="hidden" name="type" value= "${pageRequestDTO.type}">
    <input type="hidden" name="keyword" value= "${pageRequestDTO.keyword}"> <!--검색 조건과 키워드가 유지를 해주려고 사용-->
    </c:if>

</form>

<%@include file="../includes/footer.jsp"%>

<script>

    const actionForm = document.querySelector("#actionForm")  // 아이디 값을 가져옴

    const result = '${result}'

    if(result && result !== ''){
        $('#modal-sm').modal('show')

        window.history.replaceState(null, '', '/board/list');
    }


    function movePage(pageNum) {

        //클릭한 페이지 번호 값으로 바꾼다.
        actionForm.querySelector("input[name='page']") .setAttribute("value",pageNum)  // 페이지 엘리먼트
        // 페이지번호 누르면 해당 페이지로 이동.
        actionForm.submit()

        //#이랑 . 이 css의 셀렉터 기능
    }


    function moveRead(bno){
    // alert(bno)
        actionForm.setAttribute("action","/board/read") // read 로 이동하도로
        actionForm.innerHTML += `<input type = 'hidden' name = 'bno' value = '\${bno}'>` // 단순 문자로 인식하게 \$로 작성
        actionForm.submit()


    }


</script>



</body>
</html>
