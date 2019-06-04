var data;
var prePwd;
var newPwd;
var sureNewPwd;
$(function () {
    $("#modifyBtn").click(function () {
        prePwd = $("#prePwd").val();
        newPwd = $("#newPwd").val();
        sureNewPwd = $("#sureNewPwd").val();
        if ("" == prePwd.trim()) {
            $.MsgBox.Alert("提示", "请输入原密码");
            return;
        }
        if ("" == newPwd.trim()) {
            $.MsgBox.Alert("提示", "请输入新密码");
            return;
        }
        if (sureNewPwd.trim() != newPwd.trim()) {
            $.MsgBox.Alert("提示", "两次输入的新密码不一致");
            return;
        }
        $.MsgBox.Confirm("提示","确定修改密码？",function () {
            data = {prePwd: prePwd, newPwd: newPwd, sureNewPwd: sureNewPwd}
            $.ajax({
                type: "post",
                url: "/user/modifyPwd?" + $.param(data),
                success:function (data) {
                    if (data.code == 1){
                        $.MsgBox.Alert("提示",data.msg,function () {
                            location.href = "modifyPwd.html";
                        });
                    }
                },
                error:function () {
                    $.MsgBox.Alert("错误","修改用户密码异常");
                }
            })
        });
    })

})