$(function () {
    $("#modifyBtn").hide();
    // 加载信息
    loadPersonInfo();
    $("#modifyInfo").change(function () {
        let status = $("#modifyInfo").is(":checked");
        if (status) {
            // 允许用户修改电话和邮箱
            $("#phone").attr("readonly", false);
            $("#email").attr("readonly", false);
            // 修改radio被选中，展示确认修改按钮给用户
            $("#modifyBtn").show();
        } else {
            // 不选中，修改无效，重新刷新页面
            location.href = "personInfo.html";
        }
    });

    $("#modifyBtn").click(function () {
        $.MsgBox.Confirm("提示", "确定提交修改后的信息吗？", commitInfo);
    })
})

function commitInfo() {
    // 获取修改后的用户电话和邮箱
    let phone = $("#phone").val();
    let email = $("#email").val();
    let data = {phone: phone, email: email};
    ``
    $.ajax({
        type: "post",
        url: "/person/modifyInfo?" + $.param(data),
        success: function (data) {
            $.MsgBox.Alert(data.code == 1 ? "提示" : "错误", data.msg, function () {
                location.href = "personInfo.html";
            });
        },
        error: function () {
            $.MsgBox.Alert("错误", "修改用户信息错误！");
        }
    })
}

function loadPersonInfo() {
    $.ajax({
        type: "post",
        url: "/person/getInfo",
        async: false,
        success: function (data) {
            let u = data.data.user;
            // 如果是学生信息
            if (1 == u.userType) {
                let studentPhone = data.data.personInfo.studentPhone == null ? '' : data.data.personInfo.studentPhone;
                let studentEmail = data.data.personInfo.studentEmail == null ? '' : data.data.personInfo.studentEmail;
                $("#forminfo").html(`
                     <li><label>姓名</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.studentName}" class="dfinput"/></li>
                     <li><label>学号</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.studentNo}"class="dfinput" /></li>
                     <li><label>专业</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.studentMajor}"class="dfinput" /></li>
                     <li><label>班级</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.studentClass}"class="dfinput" /></li>
                     <li><label>辅导员</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.studentInstructor}"class="dfinput" /></li>
                     <li><label>电话</label><input id="phone" name="" type="text" readonly="readonly" value="${studentPhone}"class="dfinput" /></li>
                     <li><label>邮箱</label><input id="email" name="" type="text" readonly="readonly" value="${studentEmail}"class="dfinput" /></li>
                `)
            } else {
                let teacherPhone = data.data.personInfo.teacherPhone == null ? '' : data.data.personInfo.teacherPhone;
                let teacherEmail = data.data.personInfo.teacherEmail == null ? '' : data.data.personInfo.teacherEmail;
                // 如果是老师信息或管理员信息
                $("#forminfo").html(`
                     <li><label>姓名</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.teacherName}" class="dfinput"/></li>
                     <li><label>教师编号</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.teacherNo}" class="dfinput" /></li>
                     <li><label>职称</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.teacherTitle}" class="dfinput" /></li>
                     <li><label>学历</label><input name="" type="text" readonly="readonly" value="${data.data.personInfo.teacherEducation}" class="dfinput" /></li>
                     <li><label>电话</label><input id="phone" name="" type="text" readonly="readonly" value="${teacherPhone}" class="dfinput" /></li>
                     <li><label>邮箱</label><input id="email" name="" type="text" readonly="readonly" value="${teacherEmail}" class="dfinput" /></li>
                `)
            }
        },
        error: function () {
            $.MsgBox.Alert("错误", "加载个人信息数据失败");
        }
    })
}