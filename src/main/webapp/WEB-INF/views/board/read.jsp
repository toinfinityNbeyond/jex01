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

                        <!--파일이 있는지 확인하고 read에서 첨부파일 조회하는 코드-->
                        <div>
                            <c:forEach items="${boardDTO.files}" var="attach">
                                <div>
                                    <!--첨부파일 링크를 보여주는 곳-->
                                    <c:if test="${attach.image}">
                                        <img onclick="javascript:showOrigin('${attach.getFileLink()}')" src="/viewFile?file=${attach.getThumbnail()}">    <!--attach.getFileLink()이 파라미터로 들어간다.-->
                                    </c:if>
                                        ${attach.fileName}
                                </div>
                            </c:forEach>
                        </div>



                    </div>
                    <!-- /.card -->
                    <div class="card direct-chat direct-chat-primary">
                        <div class="card-header">
                            <h3 class="card-title">Replies</h3>

                            <div class="card-tools">
                                <span title="3 New Messages" class="badge badge-primary addReplyBtn">Add Reply</span>
                                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                    <i class="fas fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-tool" title="Contacts" data-widget="chat-pane-toggle">
                                    <i class="fas fa-comments"></i>
                                </button>
                                <button type="button" class="btn btn-tool" data-card-widget="remove">
                                    <i class="fas fa-times"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <!-- Conversations are loaded here -->
                            <div class="direct-chat-messages">
                            </div>
                            <!--/.direct-chat-messages-->
                        </div>
                    </div>
                    <!--/.direct-chat -->
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
        <input type="hidden" name="type" value="${pageRequestDTO.type}">
        <input type="hidden" name="keyword" value="${pageRequestDTO.keyword}">
    </c:if>

</form>


<div class="modal fade" id="modal-sm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Reply</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="text" name="replyer">
                <input type="text" name="reply">
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary operBtn">Save changes</button>  <!--버튼 누르면 등록-->
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="modal fade" id="modal-lg">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Modify/Remove</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="rno">
                <input type="text" name="replyerMod">
                <input type="text" name="replyMod">
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-info btnModReply">Modify</button>
                <button type="button" class="btn btn-danger btnRem">Remove</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--원본 사진을 띄워줌-->
<div class="modal fade" id="modal-image">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <img id="targetImage">
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<%@include file="../includes/footer.jsp"%>

<script>
    const actionFrom = document.querySelector("#actionForm")

    document.querySelector(".btnList").addEventListener("click",()=> {actionFrom.submit()}, false)

    document.querySelector(".btnMod").addEventListener("click",()=> {

        const bno = '${boardDTO.bno}'

        actionFrom.setAttribute("action","/board/modify")
        actionForm.innerHTML +=`<input type='hidden' name='bno' value='\${bno}'>`
        actionFrom.submit()
    }, false)

</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/resources/js/reply.js"></script>

<script>

    const modalImage = new bootstrap.Modal(document.querySelector('#modal-image')) //jquery를 사용하지 않으려고 new 이 후 부터 복붙

    function showOrigin(fileLink){

        //alert(fileLink);
        document.querySelector("#targetImage").src = `/viewFile?file=\${fileLink}`
        modalImage.show()
    }

    function after(result){
        console.log("after............")
        console.log("result", result)
    }


    //const doB() = function (arr) -> doB(after(result)) = function(arr) -> result = arr



    //doA().then(result => console.log(result))

    //doB(after)

    //const reply = {bno:230, replyer:'user00', reply:'2222223333300000'}

    //doC(reply).then(result => console.log(result))

    //doD(112).then(result => console.log(result))

    //const reply = {rno:112, reply:"Update reply text..."}

    //doE(reply).then(result => console.log(result))

    function getList(){  //댓글을 추가,수정 -> 목록을 새로 뿌려주려고 함수로 빼둠.
        const target = document.querySelector(".direct-chat-messages") //read.jsp 안에서 클래스를 불러옴.
        const bno = '${boardDTO.bno}' //230


        function convertTemp(replyObj) {

            console.log(replyObj)

            const {rno,bno,reply,replyer,replyDate,modDate}  = {...replyObj} //스프레드 연산자(전개 연산자)

            const temp =`<div class="direct-chat-msg">
                <div class="direct-chat-infos clearfix">
                    <span class="direct-chat-name float-left">\${rno}--\${replyer}</span>
                    <span class="direct-chat-timestamp float-right">\${replyDate}}</span>
                </div>
                <div class="direct-chat-text" data-rno='\${rno}' data-replyer= '\${replyer}'>\${reply}</div> <!--\${reply} 는 우리가 눈으로 보는 댓글 내용 -->
            </div>`
            // data-rno='\${rno}' 는 클래스가 가지는 값

            return temp

        }


        getReplyList(bno).then(data => {
            console.log(data) // 받아오는 데이터가 배열. array
            let str ="";

            data.forEach(reply => {
                str += convertTemp(reply)
            })
            target.innerHTML = str
        })
    }

    //최초 실행 . 즉시실행 함수
    (function() {
        getList()
    })()


    const modalDiv = $("#modal-sm")

    let oper = null



    document.querySelector(".addReplyBtn").addEventListener("click",function(){

        oper = 'add'
        modalDiv.modal('show')

        },false)


    document.querySelector(".operBtn").addEventListener("click",function (){

        const bno ='${boardDTO.bno}'
        const replyer = document.querySelector("input[name='replyer']").value //태그이름 [속성= '값']
        const reply = document.querySelector("input[name='reply']").value

        if (oper === 'add' ){  //버튼을 클릭해서 엑시오스로
            const replyObj ={bno , replyer,reply}  //{bno:bno , replyer:replyer, reply:reply} 와 동일한 의미
            console.log(replyObj)
            addReply(replyObj).then(result => {
                getList()    //ajax 비동기 호출이 두 번 일어난다. // 넣은 댓글까지 보여준다.
                modalDiv.modal('hide')
                document.querySelector("input[name='replyer']").value = "" // 모달창의 값을 비워준다. -> 이 문장이 없으면 모달창이 다시 뜨면 그 전 값이 그대로 들어가있다.
                document.querySelector("input[name='reply']").value = ""


            })
        }

    }, false)

    //수정/삭제 dom

    // modal의 인풋창의 변수
    const modModal = $("#modal-lg")  //jquery -> 모달이라는 함수가 없어서. 모달창을 띄우려고 필요하다.
    const modReplyer = document.querySelector("input[name= 'replyerMod']")
    const modReply = document.querySelector("input[name= 'replyMod']")
    const modRno = document.querySelector("input[name='rno']")  //속성 값을 변수에 넣는다.


    //이벤트를 파라미터로 받는다. 댓글창을 클릭하면 모달창이 나오게. direct-chat-messages(댓글창 영역)
    document.querySelector(".direct-chat-messages").addEventListener("click", (e)=>{
        const target = e.target
        const bno = '${boardDTO.bno}'

        // true면 function convertTemp 의 data-rno='\${rno}' data-replyer= '\${replyer}' 속성을 가져온다.
        if (target.matches(".direct-chat-text")) {
            // 말풍선 변수
            const rno = target.getAttribute("data-rno")
            const replyer = target.getAttribute("data-replyer")
            const reply = target.innerHTML // 속성으로 뽑지 않고 우리가 보이는 화면을 reply에 넣는다. reply 자체가 innerHTML 속성으로 들어가 있기 때문에.
            console.log(rno, replyer, reply, bno)
            modRno.value = rno
            modReply.value = reply
            modReplyer.value = replyer // 댓글 말풍선을 누르면 모달창의 댓글 말풍선의 정보가 뜬다.


            //값을 가지고 있는 상태에서 모달창이 뜨게 되는 것

            //romove버튼애 rno값 받아오기 -> 댓글 삭제를 위해 가지고 있는 값. 갖고만 있는 셋팅값
            // 없으면 삭제 안됨 ㅎㅎ
           document.querySelector(".btnRem").setAttribute("data-rno",rno)

            modModal.modal('show')


        }

    }, false)


        document.querySelector(".btnRem").addEventListener("click",(e)=>{ //버튼 자체에 rno 가 있음. 위에서 가지고 있는 값을 진짜 삭제하는 기능

            const rno = e.target.getAttribute("data-rno")  //data-rno(속성값) 값을 가지고 있는 애를 넣는다.
            // alert(rno)
            removeReply(rno).then(result => {
                getList()   // 삭제 완료된 이후에 목록값 뿌리기
                modModal.modal("hide")
            })
        },false) // 비동기

        document.querySelector(".btnModReply").addEventListener("click",(e) =>{ //말풍선 댓글을 눌렀을 때 모달창에 보여지는 값들은 이미 들어가 있는 상태. 셋팅값. (댓글 정보를 가지고 있다)

            const replyObj = {rno: modRno.value , reply: modReply.value } // 수정을 위해 정보 수집. 객체에 담는다.

            console.log(replyObj)

            modifyReply(replyObj).then(result => {
                getList()
                modModal.modal("hide")
            })

        },false)



</script>

</body>
</html>
