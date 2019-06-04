$(function () {
    var url = location.search; //获取url中"?"符后的字串 ('?modFlag=business&role=1')
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1); //substr()方法返回从参数值开始到结束的字符串；
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
    var thesisNo = theRequest.thesisNo;

    $("#returnBtn").click(function () {
        location.href = "teacherSelectStudent.html";
    });

    $("#addBtn").click(function () {
        // 获取单选框的值 1同意，2不同意
        let opinion = $("input[name='opinion']:checked").val();
        let teacherOpinion = $("#noticeInfo").val();
        let data = {
            opinionFlag: opinion,
            thesisNo: thesisNo,
            teacherOpinion: teacherOpinion,
        }
        $.ajax({
            type: "post",
            url: "/studentTeacherRelation/operate?" + $.param(data),
            success: function (data) {
                $.MsgBox.Alert("提示", data.msg, function () {
                    location.href = "teacherSelectStudent.html";
                })
            },
            error: function () {
                $.MsgBox.Alert("错误", "操作失败！",function () {
                    location.href = "teacherSelectStudent.html";
                });
            }
        })
    });
})