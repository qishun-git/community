//comment post
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}

//actual comment function
function comment2target(targetId, type, content) {
    if (content == null) {
        alert("Comment something~ Meow~");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            console.log(response);
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=863d3236eec08109af8a&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", "t");
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}


//comment comment
function comment(e) {
    var id = $(e).data("id");
    var content = $("#input-" + id).val();
    comment2target(id, 2, content);
}

//comment collapse
function collapseComments(e) {
    var id = $(e).data("id");
    var comments = $("#comment-" + id);
    $(e).toggleClass("active")
    comments.toggleClass("in");
    if (comments.hasClass("in")) {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    }).append($("<span/>", {
                        "class": "comment-time pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    }))).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "comment-menu"
                    }).append($("<span/>", {
                        "class": "glyphicon glyphicon-thumbs-down comment-btn",
                        "aria-hidden": "true"
                    })).append($("<span/>", {
                        "class": "glyphicon glyphicon-thumbs-up comment-btn",
                        "aria-hidden": "true"
                    })).append($("<span/>", {
                        "class": "glyphicon glyphicon-comment comment-btn",
                        "aria-hidden": "true",
                        "onclick": "subComment(this)",
                        "data-name": comment.user.name,
                        "data-id": comment.parentId
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment sep"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

function subComment(e) {
    var name = $(e).data("name");
    var id = $(e).data("id");
    $("#input-" + id).val("@" + name + ": ")
}

function selectTag(e) {
    value = $(e).data("id");
    var pre = $("#tag").val();
    if (pre) {
        if (pre.indexOf(value) == -1) {
            $("#tag").val(pre + "," + value);
        }
    } else {
        $("#tag").val(value);
    }

}

function showSelectTag() {
    $("#select-tag").show();
}


