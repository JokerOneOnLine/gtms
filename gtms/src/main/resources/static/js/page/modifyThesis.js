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
    var thesisId = theRequest.thesisId;

    // 进入页面初始化数据
    loadInitData(thesisId);

    $("#addBtn").click(function () {
        modify(thesisId);
    })
    $("#returnBtn").click(function () {
        location.href = "teacherGiveThesis.html";
    })
})

function loadInitData(thesisId) {
    let param = {
        id: thesisId
    }
    $.ajax({
        type: "post",
        url: "/thesis/getThesisInfoById?" + $.param(param),
        success: function (data) {
            $("#thesisTitle").val(data.data.thesisTitle);
            $("#noticeInfo").val(data.data.noticeInfo);
        },
        error: function () {
            $.MsgBox.Alert("错误", "初始化论文信息失败！");
        }
    })
}

function modify(thesisId) {
    let thesisTitle = $("#thesisTitle").val();
    let noticeInfo = $("#noticeInfo").val();
    if (thesisTitle == null || thesisTitle == "") {
        $.MsgBox.Alert("提示", "论文题目为必填！");
        return;
    }
    let data = {
        id: thesisId,
        thesisTitle: thesisTitle,
        noticeInfo: noticeInfo
    }
    $.MsgBox.Alert("提示", "确定修改此论文信息？", function () {
        $.ajax({
            type: "post",
            url: "/thesis/modifyThesis?" + $.param(data),
            success: function (data) {
                $.MsgBox.Alert("提示", data.msg, function () {
                    location.href = "teacherGiveThesis.html";
                });
            },
            error: function () {
                $.MsgBox.Alert("错误", "修改论文信息异常！");
            }
        })
    })
}