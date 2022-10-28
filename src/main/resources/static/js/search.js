const receivedData = location.href.split('?search=')[1];
var keyword = decodeURI(receivedData);
document.getElementById('ment').innerHTML = keyword + '의 검색 결과입니다.'


$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/search",
        data: 'get',
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (dataList) {
            $(dataList).each(function (index, data) {
                flag[index]=0;
                music_id=data.music_id;
                console.log(data.img_url);
                console.log(data.title);
                //emotion_id의 형식이 카테고리명인지?
                if(data.emotion_id = keyword || data.title.indexOf(keyword)){
                    $(".result")
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
                                                    src : "images/unlike.png",
                                                    id : 'like'+index
                                                })
                                        )
                                )
                        )}
            });
        },
        error: function () {
            console.log("검색 결과 음악 정보 로딩 에러");
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