$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    $(window).resize(function(){
        $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })
    $('.loginbtn').click(function () {
        let userType = $("input[name='role']:checked").val();
        let userAccount = $(".loginuser").val().trim();
        let userPassword = $(".loginpwd").val().trim();
        // 校验账户和密码是否输入
        if (userAccount == "" || userPassword == "") {
            $.MsgBox.Alert("提示", "请输入用户名或密码！");
            return;
        }
        let param = {
            'userType':userType,
            'userAccount':userAccount,
            'userPassword':userPassword
        };
        $.ajax({
            type: 'post',
            url: '/login',
            data: $.param(param),
            success:function (data) {
                if(data.code == 1){
                    window.location.href = "main.html";
                }else{
                    $.MsgBox.Alert("提示", data.msg);
                }
            },
            error:function () {
                // 登录存在错误，弹出提示信息
                $.MsgBox.Alert("错误", "登录出错");
            }
        })
    })
});