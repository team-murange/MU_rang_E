// import {image} from "@tensorflow/tfjs";

var flag = new Array();
var user_id = '0'
var liked_id = new Array();
var music_id = new Array();
//좋아요 음악을 불러와서 하나씩 음악 아이디를 배열에 저장해놓고
//필요한 음악들 불러와서 표시할 때 
//해당 음악 아이디가 좋아요배열에 존재하면 이미지를 색칠된 하트로 넣는다
const searchForm = document.querySelector('form');

searchForm.addEventListener('submit', event => {
 const searchInput = event.target['search'];
 location.href = `search.html?${searchInput.value}`
});



$(document).ready(function () {
//좋아요한 음악 불러오기
    $.ajax({
        url : "http://localhost:8800/like/" + user_id,
        data : 'get',
        contentType:"application/json;charset=UTF-8",
        dataType : "json",
        success : function(data) {
            $(dataList).each(function (index, data) {
                liked_id.append(data.music_id)
            });

        },
        error : function() {
        console.log('좋아요 음악 불러오기 실패');
        },
    });


    $.ajax({
        url: "http://localhost:8080/top",
        data: 'get',
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (dataList) {
            $(dataList).each(function (index, data) {
                music_id[index]=data.music_id;
                console.log(data.img_url);
                console.log(data.title);
                $(".sample")
                    .append(
                        $("<li>")
                            .addClass("playlist")
                            .append(
                                $("<img>")
                                    .addClass("album")
                                    .attr({
                                        src : data.img_url
                                    }),
                                $("<div>")
                                    .addClass("sub")
                                    .append(
                                        $("<p>")
                                            .addClass("title")
                                            .text(data.title),
                                        $("<img>")
                                            .addClass("like")
                                            .attr({
                                                id : 'like'+index
                                            })
                                    )
                            )
                    )
                if(liked_id.indexOf(data.music_id)){
                    $(".like").attr({
                        src : "images/like.png",
                    }),
                    flag[index]=1;
                }
                else{
                    $(".like").attr({
                        src : "images/unlike.png",
                    }),
                    flag[index]=0;
                }
            });
        },
        error: function () {
            console.log("유저의 음악 정보 로딩 에러");
        }
    });
});


$(function() {
	$(".like").click(function() {
		var flag_num = id.substr(4);
        if(flag[flag_num] == 0){
            document.getElementById(id).src='images/like.png';
            flag[flag_num]=1;
                $.ajax({
                    url: "http://localhost:8080/like/"+user_id+"/"+music_id[flag_num],
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
        }
	});
});


// var flag = new Array(30);
// var likey = document.getElementsByClassName("like");
// window.onload = function () {
//     for(var i=0; i<30; i++){
//         likey[i].id = "like"+i;
//         likey[i].src='images/unlike.png';
//         if(i<5 || (i>=15 && i<20)) likey[i].style.display='block';
//         flag[i]=0;
//     }
//
// }
//
// function like_toggle(id)  {
//     var flag_num = id.substr(4);
//     if(flag[flag_num] == 0){
//     document.getElementById(id).src='images/like.png';
//         flag[flag_num]=1;
//         //전달해줄 음악 id를 어떻게 가져올 것인가?
//             $.ajax({
//                 url: "http://localhost:8080/like/{user-id}/{music-id}",
//                 data: 'get',
//                 contentType: "application/json;charset=UTF-8",
//                 dataType: "json",
//                 success: function (data) {
//                     console.log('좋아요 전송 성공');
//                 },
//                 error: function () {
//                     console.log('좋아요 전송 실패');
//                 }
//             });
//     }
//     else{
//         document.getElementById(id).src='images/unlike.png';
//         flag[flag_num]=0;
//     }
// }
//
// function slide1(){
//     for(var i=0; i<15; i++){
//         if(i<5)likey[i].style.display='block';
//         else likey[i].style.display='none';
//     }
// }
//
// function slide2(){
//     for(var i=0; i<15; i++){
//         if(i>=5 && i<10)likey[i].style.display='block';
//         else likey[i].style.display='none';
//     }
// }
//
// function slide3(){
//     for(var i=0; i<15; i++){
//         if(i>=10 && i<15)likey[i].style.display='block';
//         else likey[i].style.display='none';
//     }
// }
//
//
// function second_slide1(){
//     for(var i=15; i<30; i++){
//         if(i>=15 && i<20)likey[i].style.display='block';
//         else likey[i].style.display='none';
//     }
// }
//
// function second_slide2(){
//     for(var i=15; i<30; i++){
//         if(i>=20 && i<25)likey[i].style.display='block';
//         else likey[i].style.display='none';
//     }
// }
//
// function second_slide3(){
//     for(var i=15; i<30; i++){
//         if(i>=25 && i<30)likey[i].style.display='block';
//         else likey[i].style.display='none';
//     }
// }
