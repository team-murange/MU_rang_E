
var user_id = '0'


$(document).ready(function () {
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
                                $("<img>")
                                    .addClass("album")
                                    .attr({
                                        src : data.img_url
                                    }),
                                $("<p>")
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
});