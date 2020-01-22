function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    if (!content) {
        alert("Please enter some comments.");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
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