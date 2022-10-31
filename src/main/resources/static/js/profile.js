
const profile_img = document.getElementsByClassName("profile");

var user_id = '0'

$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/user/"+user_id,
        data: 'get',
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (data) {
            $("#picture").attr("src", data.img_url);
            $("#username").html(data.name);
            $("#email").html(data.email);
        },
        error: function () {
            console.log("유저 정보 로딩 에러");
        }
    });

    $.ajax({
        url: "http://localhost:8080/like/" + user_id,
        data: 'get',
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (dataList) {
            $(dataList).each(function (index, data) {
                console.log(data.img_url);
                console.log(data.title);
                $(".sample")
                    .append(
                        $("<li>")
                            .addClass("playlist")
                            .append(
                                $("<a>")
                                    .attr({href:data.soundcloud_url})
                                    .append(
                                        $("<img>")
                                            .addClass("album")
                                            .attr({
                                                src : data.img_url
                                            }),
                                    ),
                                        $("<span>")
                                            .addClass("title")
                                            .text(data.title)
                            )
                    )
            });
        },
        error: function () {
            console.log("유저의 음악 정보 로딩 에러");
        }
    });

    //감정기록 가져와서 날짜 색칠할 코드
    $.ajax({
        url : "http://localhost:8080/calendar/"+user_id,
        data : 'get',
        contentType:"application/json;charset=UTF-8",
        dataType : "json",
        success : function(data) {
            var i;
            for(i=0; i<data.length; i++){
                document.getElementById(data[i].date).style.backgroundColor = data[i].colorCode;
            }
        },
        error : function(data) {
            alert('유저 정보 로딩 에러');
        }
    });


});

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
            url: "http://localhost:8080/like/"+user_id,
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
    url : "http://localhost:8800/like/" + user_id,
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


const searchForm = document.querySelector('form');

searchForm.addEventListener('submit', event => {
 const searchInput = event.target['search'];
 location.href = `search.html?${searchInput.value}`
});