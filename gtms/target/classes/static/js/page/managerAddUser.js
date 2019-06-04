$(document).ready(function (e) {
    $(".select1").uedSelect({
        width: 345
    });
    roleChange();
    $("#submitStudentInfo").click(function () {
        submitInfo(1);
    })
    $("#submitTeacherInfo").click(function () {
        submitInfo(2);
    })
});

function roleChange() {
    let role = $("#role").val();
    if (role == "2") {
        $("#studentInfo").hide();
        $("#teacherInfo").show();
    } else {
        $("#studentInfo").show();
        $("#teacherInfo").hide();
    }
}

function submitInfo(flag) {
    let info;
    let url;
    if (flag == 1) {
        info = {
            studentNo: $("#studentNo").val(),
            studentName: $("#studentName").val(),
            studentMajor: $("#studentMajor").val(),
            studentInstructor: $("#studentInstructor").val(),
            studentClass: $("#studentClass").val(),
            studentPhone: $("#studentPhone").val(),
            studentEmail: $("#studentEmail").val()
        };
        if (info.studentNo == "" || info.studentName == "" || info.studentMajor == "" || info.studentInstructor == "" || info.studentClass == "") {
            $.MsgBox.Alert("提示","必填项未填写！");
            return;
        }
        url = "/user/addStudent?";
    } else {
        info = {
            teacherNo: $("#teacherNo").val(),
            teacherName: $("#teacherName").val(),
            teacherTitle: $("#teacherTitle").val(),
            teacherEducation: $("#teacherEducation").val(),
            teacherPhone: $("#teacherPhone").val(),
            teacherEmail: $("#teacherEmail").val()
        }
        if (info.teacherNo == "" || info.teacherName == "" || info.teacherTitle == "" || info.teacherEducation == "") {
            $.MsgBox.Alert("提示","必填项未填写！");
            return;
        }
        url = "/user/addTeacher?";
    }
    $.ajax({
        type: "post",
        url: url + $.param(info),
        success: function (data) {
            $.MsgBox.Alert("提示", data.msg);
        },
        error: function () {
            $.MsgBox.Alert("错误", "添加用户信息失败！");
        }
    })
}