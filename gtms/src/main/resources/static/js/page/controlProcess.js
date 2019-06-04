var page = 1;
const rows = 5;
var totalPage;
$(function () {
    $(".select1").uedSelect({
        width: 145,
        height: 34
    });
    $("#queryBtn").click(function () {
        loadMenu();
    })
    loadMenu();
    $("#firstPage").click(function () {
        if (page == 1) {
            $.MsgBox.Alert("提示", "当前已经是第一页！");
        } else {
            page = 1;
            // 清除之前表格中的数据
            $("#data").empty();
            loadMenu();
        }
    });

    $("#prePage").click(function () {
        if (page == 1) {
            $.MsgBox.Alert("提示", "无上一页！");
        } else {
            page -= 1;
            // 清除之前表格中的数据
            $("#data").empty();
            loadMenu();
        }
    });

    $("#nextPage").click(function () {
        if (page == totalPage) {
            $.MsgBox.Alert("提示", "无下一页！");
        } else {
            page += 1;
            // 清除之前表格中的数据
            $("#data").empty();
            loadMenu();
        }
    });

    $("#lastPage").click(function () {
        if (page == totalPage) {
            $.MsgBox.Alert("提示", "当前已经是最后一页！");
        } else {
            page = totalPage;
            // 清除之前表格中的数据
            $("#data").empty();
            loadMenu();
        }
    });

    $(document).on("click", ".switchOn", "", function () {
        let status = $(this).attr("class");
        if ("switchOn" == status) {
            $(this).attr("class", "switchOn switchOff");
            let id = $(this).attr("id");
            changeMenuStatus(id, 2);
        } else {
            $(this).attr("class", "switchOn");
            let id = $(this).attr("id");
            changeMenuStatus(id, 1);
        }
    })
})

function changeMenuStatus(id, flag) {
    let data = {
        id: id,
        menuStatus: flag
    }
    $.ajax({
        type: "post",
        url: "menu/updateMenuStatus?" + $.param(data),
        success:function () {
            
        },
        error:function () {
            $.MsgBox.Alert("错误","更改菜单状态错误！");
        }
    })
}

function loadMenu() {
    $("#data").empty();
    let pageInfo = {
        page: page,
        rows: rows,
        menuText:$("#menuText").val(),
        menuBelong:$("#menuBelong").val(),
        menuStatus:$("#menuStatus").val()
    }
    $.ajax({
        type: 'get',
        url: '/menu/list?' + $.param(pageInfo),
        success: function (data) {
            totalPage = Math.ceil(data.data.total / 5);
            $("#totalData").html(data.data.total);
            $("#currentPage").html(page + "/" + totalPage);
            for (let i = 0; i < data.data.rows.length; i++) {
                let gridData = (data.data.rows)[i];
                let belongUser = gridData.menuBelong == "2" ? "教师" : "学生";
                let status = gridData.menuStatus == "1" ? "开启" : "关闭";
                let id = gridData.id;
                let switchStatus;
                if (gridData.menuStatus == "1") {
                    switchStatus = "switchOn";
                } else {
                    switchStatus = "switchOn switchOff";
                }
                $("#data").append(`
                    <tr>
                        <td>${gridData.menuText}</td>
                        <td>${belongUser}</td>
                        <td>${status}</td>
                        <td>
                            <div id="${id}" class="${switchStatus}">
                                <em></em>
                            </div>     
                        </td>
                    </tr>
                `)
            }
        },
        error: function () {
            $.MsgBox.Alert("错误", "加载菜单列表失败");
        }
    })
}