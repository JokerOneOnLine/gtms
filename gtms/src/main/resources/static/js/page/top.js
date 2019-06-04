$(function () {
    //顶部导航切换
    $(".nav li a").click(function () {
        $(".nav li a.selected").removeClass("selected")
        $(this).addClass("selected");
    })

    // 获取登录session值
    $.ajax({
        type: 'post',
        url:"/getLoginUserInfo",
        success:function (data) {
            $("#loginUser").html(data.userAccount);
        }
    })
})