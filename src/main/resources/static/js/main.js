var url_path = "https://www.murange.site";

let container = $("#pagination");
var tmp;
var ul = new Array();

$(document).ready(function openLoading() {
  //화면 높이와 너비를 구합니다.
  let maskHeight = $(document).height();
  let maskWidth = window.document.body.clientWidth;
  //출력할 마스크를 설정해준다.
  let mask =
    "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
  // 로딩 이미지 주소 및 옵션
  let loadingImg = "";
  loadingImg +=
    "<div id='loadingImg' style='position:absolute; top: calc(50% - (200px / 2)); width:100%; z-index:99999999;'>";
  loadingImg +=
    " <img src='https://loadingapng.com/animation.php?image=4&fore_color=000000&back_color=FFFFFF&size=128x128&transparency=1&image_type=0&uncacher=75.5975991029623' style='position: relative; display: block; margin: 0px auto;'/>";
  loadingImg += "</div>";
  //레이어 추가
  $("body").append(mask).append(loadingImg);
  //마스크의 높이와 너비로 전체 화면을 채운다.
  $("#mask").css({
    width: maskWidth,
    height: maskHeight,
    opacity: "0.3",
  });
  //마스크 표시
  $("#mask").show();
  //로딩 이미지 표시
  $("#loadingImg").show();
});

$(function () {
//  $.ajax({
//    url: url_path + "/user",
//    data: "get",
//    contentType: "application/json;charset=UTF-8",
//    dataType: "text",
//    success: function (data) {
//      (document.getElementById("login_link").innerText = "my profile"),
//        (document.getElementById("login_link").href = "profile.html");
//    }
//  });
  const promise_random = new Promise((resolve, reject) => {
    $.ajax({
      url: url_path + "/random/title",
      data: "get",
      contentType: "application/json;charset=UTF-8",
      dataType: "text",
      success: function (data) {
        var random_title = data.charAt(0).toUpperCase() + data.slice(1);
        document.getElementsByClassName("topic")[0].innerText = random_title;
        random = data;
        resolve();
      },
    });
  });
  // 로딩창 끄는 함수
  function closeLoading() {
    $("#mask, #loadingImg").hide();
    $("#mask, #loadingImg").empty();
  }
  promise_random.then(() => {
    for (i = 0; i < 3; i++) {
      $.ajax({
        url: url_path + "/random/" + random + "?page=" + i,
        data: "get",
        async: false,
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (dataList) {
          tmp = dataList.content;
          $(tmp).each(function (index, data) {
            ul.push(data);
          });
        },
        error: function () {},
      });
    }

    container.pagination({
      dataSource: ul,
      pageSize: 5,
      showPrevious: false,
      showNext: false,
      callback: function (data, pagination) {
        var dataHtml = "<ul>";
        $.each(data, function (index, item) {
          dataHtml +=
            '<li class="playlist"> <a href="' +
            item.soundcloud_url +
            '" target="_blank"><img class="album" src="' +
            item.img_url +
            '"><p class="title">' +
            item.title +
            "</p></a>" +
            "</li>";
        });

        dataHtml += "</ul>";

        $("#data-container").html(dataHtml);
        closeLoading();
        $(".playlist__emotion").css("display", "block");
      },
    });
  });
});
