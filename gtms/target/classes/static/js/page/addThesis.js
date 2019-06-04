$(function () {
    $("#addBtn").click(function () {
        add();
    })
    $("#returnBtn").click(function () {
        location.href = "teacherGiveThesis.html";
    })
})

function add() {
    let thesisTitle = $("#thesisTitle").val();
    let noticeInfo = $("#noticeInfo").val();
    if (thesisTitle == null || thesisTitle == "") {
        $.MsgBox.Alert("提示", "论文题目为必填！");
        return;
    }
    let data = {
        thesisTitle: thesisTitle,
        noticeInfo: noticeInfo
    }
    $.MsgBox.Alert("提示", "确定添加此论文信息？", function () {
        $.ajax({
            type: "post",
            url: "/thesis/addThesis?" + $.param(data),
            success: function (data) {
                $.MsgBox.Alert("提示", data.msg, function () {
                    location.href = "teacherGiveThesis.html";
                });
            },
            error: function () {
                $.MsgBox.Alert("错误", "添加论文信息异常！");
            }
        })
    })
}