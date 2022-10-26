
var user_id = '0'

$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/like/" + user_id,
        data: 'get',
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (dataList) {
            $(dataList).each(function (index, data) {
                console.log(data.music_url);
                $(".result")
                    .append(
                        $("<li>")
                            .addClass("result__music")
                            .html(data.music_url)
                    )
            });
        },
        error: function () {
            console.log("음악 정보 로딩 에러");
        }
    });
});