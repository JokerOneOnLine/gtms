var page = 1;
const rows = 5;
var totalPage;
$(function () {
    $(".select1").uedSelect({
        width: 145,
        height: 34
    });
    // 查询按钮点击事件
    $("#queryBtn").click(function () {
        page = 1;
        loadDataGrid();
    })
    // 加载表格数据
    loadDataGrid();

    $("#selectBtn").click(function () {
        let checkedObj = $("input[name='thesis']:checked");
        if (checkedObj.length <= 0) {
            $.MsgBox.Alert("提示", "请选择一条数据进行操作！");
            return;
        }
        if (checkedObj.length > 1) {
            $.MsgBox.Alert("提示", "只能选择一条数据进行操作！");
            return;
        }
        $.MsgBox.Confirm("提示", "确定选择该选题吗？", function () {
            let thesisId = $(checkedObj[0]).val();
            $.ajax({
                typ: "post",
                url: "/studentTeacherRelation/getStudentSelectThesisResult?thesisId=" + thesisId,
                async: false,
                success: function (data) {
                    $.MsgBox.Alert("提示", data.msg, function () {
                        location.href = "studentSelectThesis.html";
                    });
                },
                error: function () {
                    $.MsgBox.Alert("错误", "选题异常！");
                }
            });
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
    // 重新加载之前先清除之前的数据
    $("#data").empty();
    let queryData = {
        page: page,
        rows: rows,
        studentName: $("#studentName").val(),
        studentNo: $("#studentNo").val(),
        thesisTitle: $("#thesisTitle").val(),
        teacherName: $("#teacherName").val(),
        teacherNo: $("#teacherNo").val(),
        opinionFlagStr: $("#opinionFlagStr").val(),
    }
    $.ajax({
        type: "post",
        url: "/studentTeacherRelation/getThesisInfoDetail?" + $.param(queryData),
        async: false,
        success: function (data) {
            totalPage = Math.ceil(data.total / 5);
            $("#totalData").html(data.total);
            $("#currentPage").html(page + "/" + totalPage);
            for (let i = 0; i < data.rows.length; i++) {
                let gridData = (data.rows)[i];
                let opinionStatus = "";
                if (gridData.thesisNo == null || gridData.thesisNo == "") {
                    opinionStatus = "未选题";
                } else {
                    opinionStatus = gridData.opinionFlag == null ? "未审核" : (gridData.opinionFlag == 1 ? "审核通过" : "审核未通过");
                }
                $("#data").append(`
                    <tr>
                        <td>${i + 1}</td>
                        <td>${gridData.studentName}</td>
                        <td>${gridData.studentNo}</td>
                        <td>${gridData.studentMajor}</td>
                        <td>${gridData.studentClass}</td>
                        <td>${gridData.studentPhone}</td>
                        <td>${gridData.studentEmail}</td>
                        <td>${gridData.thesisTitle}</td>
                        <td>${gridData.teacherNo}</td>
                        <td>${gridData.teacherName}</td>
                        <td>${gridData.teacherTitle}</td>
                        <td>${gridData.teacherPhone}</td>
                        <td>${gridData.teacherEmail}</td>
                        <td>${opinionStatus}</td>
                        <td>${gridData.teacherOpinion}</td>
                    </tr>
                `)
            }
        },
        error: function () {
            $.MsgBox.Alert("错误", "查询论文数据失败！");
        }
    })
}