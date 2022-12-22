var url_path = "https://www.murange.site";

var user_id;
var restart = 0;
var first_emotion = "none";
var second_emotion = "none";
var flag = new Array();
const PL = document.getElementById("playlist");

const video = document.getElementById("video");

Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri("/models"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
  faceapi.nets.faceRecognitionNet.loadFromUri("/models"),
  faceapi.nets.faceExpressionNet.loadFromUri("/models"),
  $.ajax({
    url: url_path + "/user",
    data: "get",
    contentType: "application/json;charset=UTF-8",
    dataType: "text",
    success: function (data) {
      user_id = data;
      resolve();
    },
    error: function () {
      console.log("유저 아이디 없음");
    },
  }),
]).then(
  $.ajax({
    url: url_path + "/user/" + user_id,
    data: "get",
    contentType: "application/json;charset=UTF-8",
    dataType: "json",
    success: function (data) {
      document.getElementById("id_insert").innerText =
        data.name + "님의 \n감정을 분석해 볼게요.";
    },
    error: function () {
      console.log("유저 정보 로딩 에러");
    },
  })
);

function startDetection() {
  if (restart == 0) {
    startVideo();
    document.getElementById("start__button").innerHTML = "다시 분석하기";
    restart = 1;
  } else {
    PL.classList.remove("animate");
    predict();
  }
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
function loading() {
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
    var sad_text = " " + resizedDetections[0].expressions.sad.toFixed(3);
    var neutral_text =
      " " + resizedDetections[0].expressions.neutral.toFixed(3);
    var angry_text = " " + resizedDetections[0].expressions.angry.toFixed(3);
    var surprised_text =
      " " + resizedDetections[0].expressions.surprised.toFixed(3);
    var happy_text = " " + resizedDetections[0].expressions.happy.toFixed(3);
    var disgusted_text =
      " " + resizedDetections[0].expressions.disgusted.toFixed(3);
    var fearful_text =
      " " + resizedDetections[0].expressions.fearful.toFixed(3);

    document.getElementById("sad").innerHTML = sad_text; //.substr(0,6)
    document.getElementById("neutral").innerHTML = neutral_text; //.substr(0,6)
    document.getElementById("angry").innerHTML = angry_text; //.substr(0,6)
    document.getElementById("surprised").innerHTML = surprised_text; //.substr(0,6)
    document.getElementById("happy").innerHTML = happy_text; //.substr(0,6)
    document.getElementById("disgusted").innerHTML = disgusted_text; //.substr(0,6)
    document.getElementById("fearful").innerHTML = fearful_text; //.substr(0,6)
  }, 100);

  predict();
}

function predict() {
  document.getElementById("emotion_result").innerHTML = "Please wait";
  //감정수치 저장할 배열
  var sad_ary = [0, 0, 0, 0, 0];
  var neutral_ary = [0, 0, 0, 0, 0];
  var angry_ary = [0, 0, 0, 0, 0];
  var surprised_ary = [0, 0, 0, 0, 0];
  var happy_ary = [0, 0, 0, 0, 0];
  var disgusted_ary = [0, 0, 0, 0, 0];
  var fearful_ary = [0, 0, 0, 0, 0];

  doing();
  function doing() {
    let left_second = 6;
    let count_second = 0;

    //1초마다 감정수치 저장
    var predicting = setInterval(async () => {
      document.getElementById("emotion_result").innerHTML =
        left_second - 1 + "seconds left";

      if (count_second < 5) {
        sad_ary[count_second] = parseFloat(
          document.getElementById("sad").innerHTML
        );
        neutral_ary[count_second] = parseFloat(
          document.getElementById("neutral").innerHTML
        );
        angry_ary[count_second] = parseFloat(
          document.getElementById("angry").innerHTML
        );
        surprised_ary[count_second] = parseFloat(
          document.getElementById("surprised").innerHTML
        );
        happy_ary[count_second] = parseFloat(
          document.getElementById("happy").innerHTML
        );
        disgusted_ary[count_second] = parseFloat(
          document.getElementById("disgusted").innerHTML
        );
        fearful_ary[count_second] = parseFloat(
          document.getElementById("fearful").innerHTML
        );
      }

      left_second = left_second - 1;
      count_second = count_second + 1;
      if (left_second < 0) {
        //5초 뒤 실행되는 코드
        clearInterval(predicting);

        //각 감정의 평균값 계산
        const sad_sum = sad_ary.reduce(function add(sum, currValue) {
          return sum + currValue;
        }, 0);
        const sad_avr = sad_sum / 5;

        const neutral_sum = neutral_ary.reduce(function add(sum, currValue) {
          return sum + currValue;
        }, 0);
        const neutral_avr = neutral_sum / 10;

        const angry_sum = angry_ary.reduce(function add(sum, currValue) {
          return sum + currValue;
        }, 0);
        const angry_avr = angry_sum / 5;

        const surprised_sum = surprised_ary.reduce(function add(
          sum,
          currValue
        ) {
          return sum + currValue;
        },
        0);
        const surprised_avr = surprised_sum / 5;

        const happy_sum = happy_ary.reduce(function add(sum, currValue) {
          return sum + currValue;
        }, 0);
        const happy_avr = happy_sum / 5;

        const disgusted_sum = disgusted_ary.reduce(function add(
          sum,
          currValue
        ) {
          return sum + currValue;
        },
        0);
        const disgusted_avr = disgusted_sum / 5;

        const fearful_sum = fearful_ary.reduce(function add(sum, currValue) {
          return sum + currValue;
        }, 0);
        const fearful_avr = fearful_sum / 5;

        //최대값, 두번째 최대값 찾기

        var max_ary = [];
        max_ary.push(
          ["sad", sad_avr],
          ["neutral", neutral_avr],
          ["angry", angry_avr],
          ["surprised", surprised_avr],
          ["happy", happy_avr],
          ["disgusted", disgusted_avr],
          ["fearful", fearful_avr]
        );
        max_ary.sort(function (a, b) {
          return b[1] - a[1];
        });

        first_emotion = max_ary[0][0];
        if (max_ary[1][1] != 0) second_emotion = max_ary[1][0];

        document.getElementById("emotion_result").innerHTML =
          first_emotion + " " + second_emotion;
        video.removeEventListener("play", loading);

        //표정분석결과 전달
        const promise_result = new Promise((resolve, reject) => {
          $.ajax({
            url:
              url_path +
              "/figure/" +
              user_id +
              "/" +
              first_emotion +
              "/" +
              second_emotion,
            data: "get",
            async: false,
            contentType: "application/json;charset=UTF-8",
            success: function () {
              console.log("분석결과 전송 성공");
              PL.classList.add("animate");
              resolve();
            },
            error: function () {
              console.log("분석 결과 전송 error");
            },
          });
        });
        promise_result.then(() => {
          $(document).ready(function () {
            $.ajax({
              url: url_path + "/music/" + first_emotion + "/" + second_emotion,
              data: "get",
              async: false,
              contentType: "application/json;charset=UTF-8",
              dataType: "json",
              success: function (dataList) {
                console.log("음악 정보 로딩 성공");
                $(".result").html(" ");
                $(dataList.content).each(function (index, data) {
                  flag[index] = 0;
                  $(".result").append(
                    $("<li>")
                      .addClass("like__pos")
                      .append(
                        $("<img>")
                          .addClass("like__music")
                          .attr({
                            src: "images/unlike.png",
                            onclick:
                              "like_toggle(" + index + "," + data.id + ")",
                          }),
                        $("<div>")
                          .addClass("result__music") /*result__music*/
                          .html(data.music_url)
                      )
                  );
                });
              },
              error: function () {
                console.log("음악 정보 로딩 에러");
              },
            });
          });
        });
      }
    }, 1000);
  }
}

//좋아요 코드

var likey = document.getElementsByClassName("like__music");

function like_toggle(index, id) {
  if (flag[index] == 0) {
    likey[index].src = "images/like.png";
    flag[index] = 1;
    $.ajax({
      url: url_path + "/like/" + user_id + "/" + id + "/" + first_emotion,
      data: "get",
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      success: function (data) {
        console.log("좋아요 전송 성공");
      },
      error: function () {
        console.log("좋아요 전송 실패");
      },
    });
  } else {
    likey[index].src = "images/unlike.png";
    flag[index] = 0;
    //좋아요 취소
  }
}
