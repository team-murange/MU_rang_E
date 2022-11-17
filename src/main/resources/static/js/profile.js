
const profile_img = document.getElementsByClassName("profile");

var flag = new Array(15);
var user_id;


$(document).ready(function () {
    const promise_user = new Promise((resolve, reject) => {
        $.ajax({
            url: "http://localhost:8080/user",
            data: 'get',
            contentType: "application/json;charset=UTF-8",
            dataType: "text",
            success: function (data) {
                user_id = data;
                resolve();
            },
            error: function () {
                console.log('유저 아이디 없음')
            }
        });
    });
    promise_user.then(()=> {
        $.ajax({
            url: "http://localhost:8080/user/" + user_id,
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
                $(dataList.content).each(function (index, data) {
                    flag[index]=1;
                    $(".sample")
                        .append(
                            $("<li>")
                                .addClass("playlist")
                                .append(
                                    $("<a>")
                                        .attr({href: data.soundcloud_url})
                                        .append(
                                            $("<img>")
                                                .addClass("album")
                                                .attr({
                                                    src: data.img_url
                                                }),
                                        ),
                                    $("<div>")
                                        .addClass("sub")
                                        .append(
                                            $("<p>")
                                                .addClass("title")
                                                .text(data.title),
                                            $("<img>")
                                                .addClass("like")
                                                .attr({
                                                    id: 'like' + index,
                                                    src: "images/like.png",
                                                    onclick: "click_heart(" + index+","+data.id + ")"
                                                })
                                        ),
                                )
                        )
                });
            },
            error: function () {
                console.log("유저의 음악 정보 로딩 에러");
            }
        });
    });
});

var likey = document.getElementsByClassName("like");

function click_heart(index, id)  {
    if(flag[index] == 1){
        likey[index].src='images/unlike.png';
        flag[index]=0;
        $.ajax({
            //좋아요 취소 api 주소 들어가야함
            url: "http://localhost:8080/like/"+user_id+'/'+id,
            data: 'get',
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            success: function () {
                console.log('좋아요 취소 전송 성공');
            },
            error: function () {
                console.log('좋아요 취소 전송 실패');
            }
        });
    }
    else{
        //다시 좋아요 누르기 위해서는 감정 정보가 필요해서 작동하지 않도록 주석처리해놓음
        // likey[index].src='images/like.png';
        // flag[index]=1;
    }
}




// const searchForm = document.querySelector('form');
//
// searchForm.addEventListener('submit', event => {
//     const searchInput = event.target['search'];
//     location.href = `search.html?${searchInput.value}`
// });