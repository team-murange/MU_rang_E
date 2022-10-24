var flag = new Array(15); 
var likey = document.getElementsByClassName("like");
 
window.onload = function () {
    for(var i=0; i<15; i++){
        likey[i].id = "like"+i;
        likey[i].src='images/unlike.png';
        if(i<5) likey[i].style.display='block';
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
        //좋아요취소
    }
}

function slide1(){
    for(var i=0; i<15; i++){
        if(i<5)likey[i].style.display='block';
        else likey[i].style.display='none';
    }
}

function slide2(){
    for(var i=0; i<15; i++){
        if(i>=5 && i<10)likey[i].style.display='block';
        else likey[i].style.display='none';
    }
}

function slide3(){
    for(var i=0; i<15; i++){
        if(i>=10 && i<15)likey[i].style.display='block';
        else likey[i].style.display='none';
    }
}




//좋아요한 음악 불러오기
$.ajax({
    url : "http://localhost:8800/like/{user-id}",
    data : 'get',
    contentType:"application/json;charset=UTF-8",
    dataType : "json",
    success : function(data) {
    for (step = 1; step < 15; step++) {
        document.getElementById('pl1_music'+step).src = data[step-1].img_url;
    }},
    error : function() {
       console.log('좋아요 음악 불러오기 실패');
    },
          });