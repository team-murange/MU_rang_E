
var user_id = '0'
var restart = 0;
var first_emotion = 'none'
var second_emotion = 'none'


const video = document.getElementById("video");

Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri("/models"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
  faceapi.nets.faceRecognitionNet.loadFromUri("/models"),
  faceapi.nets.faceExpressionNet.loadFromUri("/models"),
])//.then(startVideo);

function startDetection() {
    if(restart==0){
        startVideo();
        document.getElementById('start__button').innerHTML = '다시 분석하기'
        restart =1;
    }
    else predict();
}


function startVideo() {
  video.addEventListener("play", loading);
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then(function (stream) {
      video.srcObject = stream;
    })
    .catch(function (err) {
      console.log(err);
    });
}

//api setting
function loading(){
  const canvas = faceapi.createCanvasFromMedia(video);
  document.body.append(canvas);
  const displaySize = { width: canvas.width, height: canvas.height };
  faceapi.matchDimensions(canvas, displaySize);

  var drawing = setInterval(async () => {
    const detections = await faceapi
      .detectAllFaces(video, new faceapi.TinyFaceDetectorOptions())
      .withFaceLandmarks()
      .withFaceExpressions();
    const resizedDetections = faceapi.resizeResults(detections, displaySize);
    canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
    faceapi.draw.drawDetections(canvas, resizedDetections);
    faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);
    
    // 창에 감정수치 표시, 소숫점 4자리까지
    document.getElementById('sad').innerHTML=  ' '+resizedDetections[0].expressions.sad.toFixed(4)
    document.getElementById('neutral').innerHTML=  ' '+resizedDetections[0].expressions.neutral.toFixed(4)
    document.getElementById('angry').innerHTML=  ' '+resizedDetections[0].expressions.angry.toFixed(4)
    document.getElementById('surprised').innerHTML=  ' '+resizedDetections[0].expressions.surprised.toFixed(4)
    document.getElementById('happy').innerHTML=  ' '+resizedDetections[0].expressions.happy.toFixed(4)
    document.getElementById('disgusted').innerHTML=  ' '+resizedDetections[0].expressions.disgusted.toFixed(4)
    document.getElementById('fearful').innerHTML=  ' '+resizedDetections[0].expressions.fearful.toFixed(4)
  }, 100);

  predict();
}


function predict(){
  document.getElementById('emotion_result').innerHTML = 'Please wait'
  //감정수치 저장할 배열
  var sad_ary = [0, 0, 0, 0, 0];
  var neutral_ary = [0, 0, 0, 0, 0];
  var angry_ary = [0, 0, 0, 0, 0];
  var surprised_ary = [0, 0, 0, 0, 0];
  var happy_ary = [0, 0, 0, 0, 0];
  var disgusted_ary = [0, 0, 0, 0, 0];
  var fearful_ary = [0, 0, 0, 0, 0];

  doing();
  function doing(){
  let left_second=5;
  let count_second=0;

  //1초마다 감정수치 저장
  var predicting = setInterval(async () => {
    document.getElementById('emotion_result').innerHTML = left_second + "seconds left"

    if(count_second<5) {
      sad_ary[count_second] = parseFloat(document.getElementById('sad').innerHTML);
      neutral_ary[count_second] = parseFloat(document.getElementById('neutral').innerHTML);
      angry_ary[count_second] = parseFloat(document.getElementById('angry').innerHTML);
      surprised_ary[count_second] = parseFloat(document.getElementById('surprised').innerHTML);
      happy_ary[count_second] = parseFloat(document.getElementById('happy').innerHTML);
      disgusted_ary[count_second] = parseFloat(document.getElementById('disgusted').innerHTML);
      fearful_ary[count_second] = parseFloat(document.getElementById('fearful').innerHTML);
    }

    left_second = left_second-1;
    count_second = count_second+1;
    if(left_second<0) 
    {
      //5초 뒤 실행되는 코드 
      clearInterval(predicting)

      //각 감정의 평균값 계산
      const sad_sum = sad_ary.reduce(function add(sum, currValue) {
        return sum + currValue;
      }, 0);
      const sad_avr = sad_sum / 5;

      const neutral_sum = neutral_ary.reduce(function add(sum, currValue) {
        return sum + currValue;
      }, 0);
      const neutral_avr = neutral_sum / 5;

      const angry_sum = angry_ary.reduce(function add(sum, currValue) {
        return sum + currValue;
      }, 0);
      const angry_avr = angry_sum / 5;

      const surprised_sum = surprised_ary.reduce(function add(sum, currValue) {
        return sum + currValue;
      }, 0);
      const surprised_avr = surprised_sum / 5;

      const happy_sum = happy_ary.reduce(function add(sum, currValue) {
        return sum + currValue;
      }, 0);
      const happy_avr = happy_sum / 5;

      const disgusted_sum = disgusted_ary.reduce(function add(sum, currValue) {
        return sum + currValue;
      }, 0);
      const disgusted_avr = disgusted_sum / 5;

      const fearful_sum = fearful_ary.reduce(function add(sum, currValue) {
        return sum + currValue;
      }, 0);
      const fearful_avr = fearful_sum / 5;



      //최대값, 두번째 최대값 찾기


      var max_ary = [];
      max_ary.push(['sad',sad_avr],['neutral',neutral_avr],['angry',angry_avr],['surprised',surprised_avr],['happy',happy_avr],['disgusted',disgusted_avr],['fearful',fearful_avr]);
      max_ary.sort(function(a, b) {
        return b[1] - a[1];
      });

      first_emotion=max_ary[0][0];
      if(max_ary[1][1]!=0)
        second_emotion=max_ary[1][0];

      document.getElementById('emotion_result').innerHTML= first_emotion+" "+second_emotion;
      video.removeEventListener("play", loading);


      //오늘 날짜에 색깔 칠하는 코드
      //필요시 수정하여 사용할 예정
      // let today = new Date();
      // var theDay = today.getDate();
      // var theMonth = today.getMonth()+1;
      // if(theDay<10) theDay = '0'+theDay;
      // if(theMonth<10) theMonth = '0'+theMonth;
      // var today_var = ''+ today.getFullYear()+ theMonth + theDay;
      //document.getElementById(today_var).style.backgroundColor= '#73aace';





      //표정분석결과 전달
        $.ajax({
            url : "http://localhost:8080/music/"+first_emotion+"/"+second_emotion,
            data : 'get',
            contentType:"application/json;charset=UTF-8",
            dataType : "json",
            success : function(data) {
               console.log('분석결과 전송 성공')
            },
            error : function(data){
              console.log('분석 결과 전송 error');
            }
          });

        $(document).ready(function () {
            $.ajax({
                url: "http://localhost:8080/like/" + user_id,
                data: 'get',
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (dataList) {
                    $(dataList).each(function (index, data) {
                        console.log(data.music_url);
                        $(".result")
                            .append(
                                $("<li>")
                                    .addClass("result__music")
                                    .html(data.music_url)
                            )
                    });
                },
                error: function () {
                    console.log("음악 정보 로딩 에러");
                }
            });
        });


//추천 음악 불러오기
//       $.ajax({
//         url : "http://localhost:8080/music/"+first_emotion+"/"+second_emotion,
//         data : 'get',
//         contentType:"application/json;charset=UTF-8",
//         dataType : "json",
//         success : function(data) {
//         alert('success');
//         for (step = 1; step < 11; step++) {
//             document.getElementById('pl1__music__'+step).src = data[step-1].music_url;
//             document.getElementById('pl1__music__'+step).style.backgroundSize = "contain";
//             document.getElementById('pl1__music__'+step).innerHTML=data[step-1].title;
//             document.getElementById('pl1__music__'+step).style.backgroundImage = data[step-1].img_url;
//         }},
//         error : function() {
//         alert('음악 재생 에러');
//         },
//               });
      
    }
  },1000)
  }  


};



//좋아요 코드

var flag = new Array(10); 
var likey = document.getElementsByClassName("like");
 
window.onload = function () {
    for(var i=0; i<10; i++){
        likey[i].id = "like"+i;
        likey[i].src='images/unlike.png';
        flag[i]=0;
    }
}

function like_toggle(id)  {
    var flag_num = id.substr(4);
    if(flag[flag_num] == 0){
    document.getElementById(id).src='images/like.png';
        flag[flag_num]=1;
        $.ajax({
          url: "http://localhost:8080/like/{user-id}/{music-id}",
          data: 'get',
          contentType: "application/json;charset=UTF-8",
          dataType: "json",
          success: function (data) {
              console.log('좋아요 전송 성공');
          },
          error: function () {
              console.log('좋아요 전송 실패');
          }
      });
    }
    else{
        document.getElementById(id).src='images/unlike.png';
        flag[flag_num]=0;
        //좋아요 취소
    }
}
