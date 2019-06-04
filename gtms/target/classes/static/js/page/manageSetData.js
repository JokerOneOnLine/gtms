$(function () {
    $("#modifyBtn").hide();
    // 加载信息
    loadData();
    $("#modifyInfo").change(function () {
        let status = $("#modifyInfo").is(":checked");
        if (status) {
            // 允许用户修改电话和邮箱
            $("#studentNum").attr("readonly", false);
            $("#teacherNum").attr("readonly", false);
            // 修改radio被选中，展示确认修改按钮给用户
            $("#modifyBtn").show();
        } else {
            // 不选中，修改无效，重新刷新页面
            location.href = "manageSetData.html";
        }
    });

    $("#modifyBtn").click(function () {
        $.MsgBox.Confirm("提示", "确定提交修改后的信息吗？", commitInfo);
    })
})

function commitInfo() {
    // 获取修改后的数据
    let studentNum = $("#studentNum").val();
    let teacherNum = $("#teacherNum").val();
    let data = {studentNum: studentNum, teacherNum: teacherNum};
    $.ajax({
        type: "post",
        url: "/init/modifyInitData?" + $.param(data),
        success: function (data) {
            $.MsgBox.Alert(data.code == 1 ? "提示" : "错误", data.msg, function () {
                location.href = "manageSetData.html";
            });
        },
        error: function () {
            $.MsgBox.Alert("错误", "修改用户信息错误！");
        }
    })
}

function loadData() {
    $.ajax({
        type: "post",
        url: "/init/getInitData",
        async: false,
        success: function (data) {
            $("#studentNum").val(data.data.studentNum);
            $("#teacherNum").val(data.data.teacherNum);
        },
        error: function () {
            $.MsgBox.Alert("错误", "加载个人信息数据失败");
        }
    })
}