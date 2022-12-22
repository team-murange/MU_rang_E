var url_path = "https://www.murange.site";

const receivedData = location.href.split("?search=")[1];
var keyword = decodeURI(receivedData);
document.getElementById("ment").innerHTML = keyword + "의 검색 결과입니다.";

var flag = new Array();
var user_id = "0";
var liked_id = new Array();
var music_id = new Array();

const searchForm = document.querySelector("form");

searchForm.addEventListener("submit", (event) => {
  const searchInput = event.target["search"];
  location.href = `search.html?${searchInput.value}`;
});

$(document).ready(function () {
  $.ajax({
    url: url_path + "/like/" + user_id,
    data: "get",
    contentType: "application/json;charset=UTF-8",
    dataType: "json",
    success: function (dataList) {
      $(dataList).each(function (index, data) {
        flag[index] = 0;
        music_id[index] = data.music_id;
        if (data.title.includes(keyword)) {
          $(".result").append(
            $("<li>")
              .addClass("playlist")
              .append(
                $("<a>")
                  .attr({ href: data.soundcloud_url })
                  .append(
                    $("<img>").addClass("album").attr({
                      src: data.img_url,
                    })
                  ),
                $("<div>")
                  .addClass("sub")
                  .append(
                    $("<p>").addClass("title").text(data.title),
                    $("<img>")
                      .addClass("like")
                      .attr({
                        id: "like" + index,
                        src: "images/unlike.png",
                        onclick: "click_heart(" + index + ")",
                      })
                  )
              )
          );
        }
      });
    },
    error: function () {
      console.log("음악 정보 로딩 에러");
    },
  });
});

function click_heart(id) {
  if (flag[id] == 0) {
    document.getElementById("like" + id).src = "images/like.png";
    flag[id] = 1;
    $.ajax({
      url: url_path + "/like/" + user_id + "/" + music_id[id],
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
    document.getElementById("like" + id).src = "images/unlike.png";
    flag[id] = 0;
  }
}

//
// $(document).ready(function () {
//     //좋아요한 음악 불러오기
//         $.ajax({
//             url : "http://localhost:8080/like/" + user_id,
//             data : 'get',
//             contentType:"application/json;charset=UTF-8",
//             dataType : "json",
//             success : function(data) {
//                 $(dataList).each(function (index, data) {
//                     liked_id.append(data.music_id)
//                 });
//
//             },
//             error : function() {
//             console.log('좋아요 음악 불러오기 실패');
//             },
//         });
//
//     $.ajax({
//         url: "http://localhost:8080/search",
//         data: 'get',
//         contentType: "application/json;charset=UTF-8",
//         dataType: "json",
//         success: function (dataList) {
//             $(dataList).each(function (index, data) {
//                 music_id=data.music_id;
//                 console.log(data.img_url);
//                 console.log(data.title);
//                 //emotion_id의 형식이 카테고리명인지?
//                 if(data.emotion_id = keyword || data.title.indexOf(keyword)){
//                     $(".result")
//                         .append(
//                             $("<li>")
//                                 .addClass("playlist")
//                                 .append(
//                                     $("<img>")
//                                         .addClass("album")
//                                         .attr({src : data.img_url}),
//                                     $("<div>")
//                                         .addClass("sub")
//                                         .append(
//                                             $("<p>")
//                                                 .addClass("title")
//                                                 .text(data.title),
//                                             $("<a>")
//                                                 .attr({href : data.music_url})
//                                                 .append(
//                                                     $("<img>")
//                                                         .addClass("like")
//                                                         .attr({
//                                                             id : 'like'+index
//                                                         }),
//                                                 )
//                                         )
//                                 )
//
//                         )
//                         if(liked_id.indexOf(data.music_id)){
//                             $(".like").attr({
//                                 src : "images/like.png",
//                             }),
//                             flag[index]=1;
//                         }
//                         else{
//                             $(".like").attr({
//                                 src : "images/unlike.png",
//                             }),
//                             flag[index]=0;
//                         }
//                     }
//             });
//         },
//         error: function () {
//             console.log("검색 결과 음악 정보 로딩 에러");
//         }
//     });
// });
//
// $(function() {
// 	$(".like").click(function() {
// 		var flag_num = id.substr(4);
//         if(flag[flag_num] == 0){
//             document.getElementById(id).src='images/like.png';
//             flag[flag_num]=1;
//                 $.ajax({
//                     url: "http://localhost:8080/like/"+user_id+"/"+music_id[flag_num],
//                     data: 'get',
//                     contentType: "application/json;charset=UTF-8",
//                     dataType: "json",
//                     success: function (data) {
//                         console.log('좋아요 전송 성공');
//                     },
//                     error: function () {
//                         console.log('좋아요 전송 실패');
//                     }
//                 });
//         }
//         else{
//             document.getElementById(id).src='images/unlike.png';
//             flag[flag_num]=0;
//         }
// 	});
// });
