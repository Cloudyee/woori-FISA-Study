//Node.js, Express 패키지를 활용하여 간단한 서버 구성

//express 모듈을 설치했다고 해서 바로 사용할 수 있는 것은 아님
//server.js에서 사용하려면 해달 모듈을 import 해야함
const express = require('express'); //express 모듈 불러오기
const { request } = require('http');
const app = express(); //express app(서버를 구성할) 인스턴스 생성, 서버를 구성할 정보를 넣을 변수

const httpRequest = require('request');

//TODO: .detenv
const clientID = 'Q6nx0JatUeIF0Xw0TpjY';
const clientSecret = 'Du4HP_mqNx';

//미들웨어 설정
app.use(express.static('public')); //경로 요청시 동작한다는 구조 /정적인 자원(static) 은 public 폴더 안에 있음
app.use(express.json());//역직렬화 처리용 모듈

//127.0.0.1:3000/ 경로(Root)로 접근했을 때 동작시킬 핸들러
app.get('/', (request, respponse) => { //요청정보 값/서버 클라이언트에게 줄 값
    request.sendFile('index.html'); //indext.html을 갖다달라는 것
})

//TODO: 그 외에 언어 감지, 언어 번역 요청시 동작할 핸들러\

//127.0.0.1:3000/detect 경로로 브라우저가 접근시 동작할 핸들러
app.post('/detect', (request, response) => {
    console.log(request.body);

    //언어 감지 요청을 위한 요청 메시지 구성 정보
    //1. URL
    const url = 'https://openapi.naver.com/v1/papago/detectLangs';

    //2. Body, Header 등 데이터
    const options = {
        url,
        form: request.body,
        headers: {
            'Content-type': 'application/json',
            'X-Naver-Client-Id': clientID,
            'X-Naver-Client-Secret': clientSecret
        }
    }


    httpRequest.post(options, (error, httpRequest, body) => {
        if (!error && response.statusCode == 200) {
            response.send(body); //서버가 클라이언트로 데이터를 응답
        }
    })//날릴 데이터, 콜백 함수
})
//127.0.0.1===localhost 동일
//언어 번역 요청 부분 localhst:3000/translate
app.post('/translate', (request, response) => {
    const url = 'https://openapi.naver.com/v1/papago/n2mt';

    const options = {
        url,
        form: request.body,
        headers: {
            'Content-type': 'application/json',
            'X-Naver-Client-Id': clientID,
            'X-Naver-Client-Secret': clientSecret
        }
    }

    httpRequest.post(options, (error, httpRequest, body) => {
        if (!error && response.statusCode == 200) {
            response.send(body); //서버가 클라이언트로 데이터를 응답
        }
    })//날릴 데이터, 콜백 함수

})

const port = 3000; //3000이라는 포트에서 대기, 이걸 출력하라고 명령
app.listen(port, () =>
    console.log(`http://127.0.0.1:3000/ app listening on port ${3000}`)
);