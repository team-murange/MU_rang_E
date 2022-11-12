
let container = $('#pagination');
var tmp ;
var ul = new Array();
$(function () {
    const promise_random = new Promise((resolve, reject) => {
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
            }
        });
    });
    promise_random.then(()=> {
            for (i = 0; i < 3; i++) {
                $.ajax({
                    url: "http://localhost:8080/random/" + random + '?page=' + i,
                    data: 'get',
                    async:false,
                    contentType: "application/json;charset=UTF-8",
                    dataType: "json",
                    success: function (dataList) {
                        tmp = dataList.content;
                        $(tmp).each(function (index, data) {
                            ul.push(data);
                        });

                    },
                    error: function () {
                    }
                });
            }

            container.pagination({
                dataSource: ul,
                pageSize: 5,
                showPrevious: false,
                showNext: false,
                callback: function (data, pagination) {
                    $(ul).each(function (index, data) {
                    });
                    var dataHtml = '<ul>';
                    $.each(data, function (index, item) {
                        dataHtml += '<li class="playlist"> <a href="' + item.soundcloud_url + '"><img class="album" src="' + item.img_url + '"><p class="title">' + item.title + '</p></a>' + '</li>';
                    });

                    dataHtml += '</ul>';

                    $("#data-container").html(dataHtml);
                }
            })
    });

})