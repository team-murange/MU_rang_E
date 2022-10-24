
var flag = new Array(30); 
var likey = document.getElementsByClassName("like");
window.onload = function () {
    for(var i=0; i<30; i++){
        likey[i].id = "like"+i;
        likey[i].src='images/unlike.png';
        if(i<5 || (i>=15 && i<20)) likey[i].style.display='block';
        flag[i]=0;
    }

}

function like_toggle(id)  {
    var flag_num = id.substr(4);
    if(flag[flag_num] == 0){
    document.getElementById(id).src='images/like.png';
        flag[flag_num]=1;
        //전달해줄 음악 id를 어떻게 가져올 것인가?
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
        // 좋아요 삭제 api 어떻게?
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


function second_slide1(){
    for(var i=15; i<30; i++){
        if(i>=15 && i<20)likey[i].style.display='block';
        else likey[i].style.display='none';
    }
}

function second_slide2(){
    for(var i=15; i<30; i++){
        if(i>=20 && i<25)likey[i].style.display='block';
        else likey[i].style.display='none';
    }
}

function second_slide3(){
    for(var i=15; i<30; i++){
        if(i>=25 && i<30)likey[i].style.display='block';
        else likey[i].style.display='none';
    }
}