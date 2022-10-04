var dragging = false;
var days = document.querySelectorAll('.day');
var offset = 0;

//현재 시각 받아오기
var theTime = new Date();
var theMonth = theTime.getMonth()+1;
var theDate = theTime.getDate();

function activateDay() {
  var activeElement = document.activeElement;
  var activeAItem = document.querySelector('.active-a');
  
  if (activeAItem && activeBItem) {
    clearActiveDays();
    activeElement.classList.add('active-a');
    return;
  }
  activeElement.classList.add('active-a');
}

function clearActiveDays() {
  var activeAItem = document.querySelector('.active-a');
  if (activeAItem) activeAItem.classList.remove('active-a');
}



function startMove(item) {
  var activeAItem = document.querySelector('.active-a');
  
    calculateRange();
    clearActiveDays();
    item.classList.add('active-a');
  
}



function endMove(item) {
  dragging = false;
}

window.addEventListener('mouseup', e => {
  dragging = false;
});

days.forEach((item, index) => {
  var dayNumber = item.querySelector('.day-number').innerHTML;
  
  if (dayNumber === '1' && !item.classList.contains('next-mon')) {
    offset = index;
  }
  
  item.addEventListener('mousedown', e => {
    startMove(item);
  });
  item.addEventListener('mouseup', e => {
    endMove(item);
  });
});


document.querySelector('.reset').addEventListener('click', e => {
  clearActiveDays();
});

 
function dateSet(){

  document.getElementById("monthtitle").innerText = '2022년 '+theMonth+'월';
  document.getElementById("mon"+theMonth).style.display = 'block';
  if (theMonth < 10) {
		var findMonth = '0'+theMonth;
  }
  else
    findMonth = theMonth;
  if (theDate < 10) {
		var findDate = '0'+theMonth;
	}
	else
    findDate = theDate;
  var Today = 'd'+findMonth+findDate;
  var todayItem = document.getElementById(Today);
  //어떻게 현재 날짜에 동그라미 표시되게 할 수 있을까?
  //todayItem.add.classList('todayMark');
}

// 이전달, 다음달 코드
function switchMon(idx){
  document.getElementById('mon'+theMonth).style.display='none'
  if(idx){ // next
    if(theMonth==12){
      //1월가져오기
      document.getElementById("mon1").style.display='block';
      theMonth=1;
    }
    else{
      theMonth++;
    document.getElementById("mon"+theMonth).style.display='block';
    }
  }
  else{ //prev
    if(theMonth==1){
      //12월 가져오기
      document.getElementById("mon12").style.display='block';
      theMonth=12;
    }
    else
    theMonth--;
    document.getElementById('mon'+theMonth).style.display='block';
  }

  document.getElementById("monthtitle").innerText = '2022년 '+theMonth+'월';
}