var url_path = "https://www.murange.site";

const profile_img = document.getElementsByClassName("profile");

var flag = new Array(15);
var user_id;

$(document).ready(function () {
  const promise_user = new Promise((resolve, reject) => {
    $.ajax({
      url: url_path + "/user",
      data: "get",
      contentType: "application/json;charset=UTF-8",
      dataType: "text",
      success: function (data) {
        user_id = data;
        resolve();
      },
    });
  });
  promise_user.then(() => {
    $.ajax({
      url: url_path + "/user/" + user_id,
      data: "get",
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      success: function (data) {
        $("#picture").attr("src", data.img_url);
        $("#username").html(data.name);
        $("#email").html(data.email);
      },
    });
    $.ajax({
      url: url_path + "/like/" + user_id,
      data: "get",
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      success: function (dataList) {
        $(dataList.content).each(function (index, data) {
          flag[index] = 1;
          $(".sample").append(
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
                        src: "images/like.png",
                        onclick:
                          "click_heart(" + index + "," + data.likeId + ")",
                      })
                  )
              )
          );
        });
      },
    });
  });
});

var likey = document.getElementsByClassName("like");

function click_heart(index, likeId) {
  if (flag[index] == 1) {
    likey[index].src = "images/unlike.png";
    flag[index] = 0;
    $.ajax({
      url: url_path + "/like/" + likeId,
      type: "DELETE",
      data: "text",
    });
  } else {
    //다시 좋아요 누르기 위해서는 감정 정보가 필요해서 작동하지 않도록 주석처리해놓음
    // likey[index].src='images/like.png';
    // flag[index]=1;
  }
}
