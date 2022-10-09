const video = document.getElementById("video");


Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri("/models"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
  faceapi.nets.faceRecognitionNet.loadFromUri("/models"),
  faceapi.nets.faceExpressionNet.loadFromUri("/models"),
]).then(startVideo);

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
  predict();

    
}

//api setting
function loading(){
  const canvas = faceapi.createCanvasFromMedia(video);
  document.body.append(canvas);
  const displaySize = { width: video.width, height: video.height };
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
  setTimeout(function(){
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
      var max = Math.max(sad_avr,neutral_avr,angry_avr,surprised_avr,happy_avr,disgusted_avr,fearful_avr);
      var second_max = 0;
      var first_emotion = 'none'
      var second_emotion = 'none'

      if(max ==sad_avr) {
        first_emotion = 'sad';
        second_max = Math.max(neutral_avr,angry_avr,surprised_avr,happy_avr,disgusted_avr,fearful_avr);
      }
      else if(max ==neutral_avr) {
        first_emotion = 'neutral';
        second_max = Math.max(sad_avr,angry_avr,surprised_avr,happy_avr,disgusted_avr,fearful_avr);
      }
      else if(max ==angry_avr) {
        first_emotion = 'angry';
        second_max = Math.max(sad_avr,neutral_avr,surprised_avr,happy_avr,disgusted_avr,fearful_avr);
      }
      else if(max ==surprised_avr) {
        first_emotion = 'surprised';
        second_max = Math.max(sad_avr,neutral_avr,angry_avr,happy_avr,disgusted_avr,fearful_avr);
      }
      else if(max ==happy_avr) {
        first_emotion = 'happy';
        second_max = Math.max(sad_avr,neutral_avr,angry_avr,surprised_avr,disgusted_avr,fearful_avr);
      }
      else if(max ==disgusted_avr) {
        first_emotion = 'disgusted';
        second_max = Math.max(sad_avr,neutral_avr,angry_avr,surprised_avr,happy_avr,fearful_avr);
      }
      else {
        first_emotion = 'fearful';
        second_max = Math.max(sad_avr,neutral_avr,angry_avr,surprised_avr,happy_avr,disgusted_avr);
      }

      if(second_max != 0){
        if(second_max==sad_avr) second_emotion = 'sad';
        else if(second_max == neutral_avr) second_emotion = 'neutral';
        else if(second_max == angry_avr) second_emotion = 'angry';
        else if(second_max == surprised_avr) second_emotion = 'surprised';
        else if(second_max == happy_avr) second_emotion = 'happy';
        else if(second_max == disgusted_avr) second_emotion = 'disgusted';
        else if(second_max == fearful_avr) second_emotion = 'fearful';
      }


      document.getElementById('emotion_result').innerHTML= first_emotion+" "+second_emotion;
      
    }
  },1000)
  },2000)
  }  


};
