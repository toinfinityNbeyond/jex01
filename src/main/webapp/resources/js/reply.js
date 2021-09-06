
async function doA() { //어떤 데이터가 나오는지 확인. 댓글 get 방식을 비동기로 처리하는 방법

    console.log("doA...........")

    const response = await axios.get("/replies")
    const data = response.data

    console.log("doA..data", data)
    return data //doA를 실행하면 배열이 나옴

} // 선언문. 결과는 비동기
//비동기 방식인데 보이는건 동기화

const doB = (fn) => {  //화살표 함수. 댓글 get방식을 콜백으로 처리하는 방식. promise
    console.log("doB...........")
    try {
        axios.get('/replies').then(response => {   //비동기 -> 데이터가 언제 나올 지 모름 .
            console.log(response)
            const  arr = response.data //arr로 받은 이유는 함수라서
            fn(arr)    //함수를 전달해주고 비동기 가 끝나면 함수를 실행해줘-> 콜백 . 실행해줘~  파라미터로 받은 fn함수를 호출할 수 있다. 값이야.....
        })
    } catch (error) {
        console.log(err)
    }
} // 선언식. 결과는 비동기



async function doC(obj) { //post. 포스트 방식은 객체를 던진다. 댓글 등록

    const response = await axios.post("/replies", obj) //처리된 후에 응답이 올거니까 reponse 로 받음
    return response.data

}

const doD = async (rno) => { // 댓글 삭제
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}

const doE = async (reply) => { //댓글 수정 put방식

    const response = await axios.put(`/replies/${reply.rno}`, reply)

    return response.data
} // 등록과 삭제가 섞인게 수정
//메모리상에 doA() doB() 가 있다

const getReplyList = async (bno) => {

    const  response = await  axios.get(`/replies/list/${bno}`)

    return response.data
}


async function addReply(obj) { //post. 포스트 방식은 객체를 던진다. 댓글 등록    add 리플라이 호출-> 갱신 -> 다시 목록데이터를 가져와야함

    const response = await axios.post("/replies", obj) //처리된 후에 응답이 올거니까 reponse 로 받음
    return response.data

}


const removeReply = async (rno) => { // 비동기
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}

const modifyReply = async (reply) => { //댓글 수정 put방식. 비동기

    const response = await axios.put(`/replies/${reply.rno}`, reply)

    return response.data
}

