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
