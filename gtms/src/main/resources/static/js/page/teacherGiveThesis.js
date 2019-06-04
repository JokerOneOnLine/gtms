var page = 1;
const rows = 5;
var totalPage;
$(function () {
    loadDataGrid();

    $("#addBtn").click(function () {
        location.href = "addThesis.html";
    });

    $("#modifyBtn").click(function () {
        let checkedObj = $("input[name='thesis']:checked");
        if (checkedObj.length <= 0) {
            $.MsgBox.Alert("提示", "请选择一条数据进行操作！");
            return;
        }
        if (checkedObj.length > 1) {
            $.MsgBox.Alert("提示", "只能选择一条数据进行操作！");
            return;
        }
        let thesisId = $(checkedObj[0]).val();
        location.href = "modifyThesis.html?thesisId=" + thesisId;
    });

    $("#deleteBtn").click(function () {
        let checkedObj = $("input[name='thesis']:checked");
        if (checkedObj.length <= 0) {
            $.MsgBox.Alert("提示", "请选择至少一条数据进行操作！");
            return;
        }
        let thesisIds = new Array();
        checkedObj.each(function (i, item) {
            thesisIds.push(item.value);
        });
        $.MsgBox.Alert("提示","确定删除所选择的论文信息？",function () {
            $.ajax({
                type: "post",
                url: "/thesis/deleteThesisInfoByIds?ids=" + thesisIds,
                success: function (data) {
                    $.MsgBox.Alert("提示", data.msg,function () {
                        location.href="teacherGiveThesis.html";
                    });
                },
                error: function () {
                    $.MsgBox.Alert("错误", "删除论文信息失败！");
                }
            })
        })

    });

    $("#firstPage").click(function () {
        if (page == 1) {
            $.MsgBox.Alert("提示", "当前已经是第一页！");
        } else {
            page = 1;
            // 清除之前表格中的数据
            $("#data").empty();
            loadDataGrid();
        }
    });

    $("#prePage").click(function () {
        if (page == 1) {
            $.MsgBox.Alert("提示", "无上一页！");
        } else {
            page -= 1;
            // 清除之前表格中的数据
            $("#data").empty();
            loadDataGrid();
        }
    });

    $("#nextPage").click(function () {
        if (page == totalPage) {
            $.MsgBox.Alert("提示", "无下一页！");
        } else {
            page += 1;
            // 清除之前表格中的数据
            $("#data").empty();
            loadDataGrid();
        }
    });

    $("#lastPage").click(function () {
        if (page == totalPage) {
            $.MsgBox.Alert("提示", "当前已经是最后一页！");
        } else {
            page = totalPage;
            // 清除之前表格中的数据
            $("#data").empty();
            loadDataGrid();
        }
    });
})

function loadDataGrid() {
    let pageInfo = {
        page: page,
        rows: rows
    }
    $.ajax({
        type: "post",
        url: "/thesis/getThesisInfoByTeacherNo?" + $.param(pageInfo),
        success: function (data) {
            totalPage = Math.ceil(data.total / 5);
            $("#totalData").html(data.total);
            $("#currentPage").html(page + "/" + totalPage);
            for (let i = 0; i < data.rows.length; i++) {
                let gridData = (data.rows)[i];
                $("#data").append(`
                    <tr>
                        <td><input name="thesis" type="checkbox" value="${gridData.id}"/></td>
                        <td>${i + 1}</td>
                        <td>${gridData.thesisTitle}</td>
                        <td>${gridData.noticeInfo}</td>
                    </tr>
                `)
            }
        },
        error: function () {
            $.MsgBox.Alert("错误", "加载论文题目信息错误！");
        }
    })
}