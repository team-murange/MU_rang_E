var user_id = '0'
var random = "";

$(document).ready(function () {
    const promise1 = new Promise((resolve, reject) => {
            $.ajax({
                url: "http://localhost:8080/random/title",
                data: 'get',
                contentType: "application/json;charset=UTF-8",
                dataType: "text",
                success: function (data) {
                    var random_title = data.charAt(0).toUpperCase() + data.slice(1);
                    document.getElementsByClassName('topic')[0].innerText = random_title;
                    random = data;
                    resolve();
                },
                error: function () {
                    console.log('error')
                }
            });
        });

        promise1.then(()=>{
            $.ajax({
                url: "http://localhost:8080/random/" + random,
                data: 'get',
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (dataList) {
                console.log(dataList);
                var tmp = dataList.content;
                console.log(tmp);
                    $(tmp).each(function (index, data) {
                        console.log(data.img_url);
                        console.log(data.title);
                        console.log(data.soundcloud_url);
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
                                        $("<p>")
                                            .addClass("title")
                                            .text(data.title)
                                    )
                                )
                            )
                    });
                },
                error: function () {
                    console.log("메인 페이지 음악 정보 로딩 에러");
                }
            });

        })
});