$(function () {
    //导航切换
    $(".menuson li").click(function () {
        $(".menuson li.active").removeClass("active")
        $(this).addClass("active");
    });

    $('.title').click(function () {
        var $ul = $(this).next('ul');
        $('dd').find('ul').slideUp();
        if ($ul.is(':visible')) {
            $(this).next('ul').slideUp();
        } else {
            $(this).next('ul').slideDown();
        }
    });

    // 加载菜单
    $.ajax({
        type: "post",
        url: "/menu/getMenuByMenuBelong",
        success: function (data) {
            if (data.code == 1) {
                for (let i = 0; i < data.data.length; i++) {
                    $("#leftmenu").append(`<dd>
        <div class="title">
            <span><img src="${data.data[i].menuIcon}"/></span><a href="${data.data[i].menuUrl}" target="rightFrame">${data.data[i].menuText}</a>
        </div>
    </dd>`)
                }
            } else {
                $.MsgBox.Alert(data.msg);
            }
        },
        error: function () {
            $.MsgBox.Alert("错误", "加载菜单出错！");
        }
    })
})