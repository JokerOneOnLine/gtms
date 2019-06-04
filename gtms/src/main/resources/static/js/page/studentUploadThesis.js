var page = 1;
const rows = 5;
var totalPage;
$(function () {
    loadDataGrid();

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
        url: "/studentTeacherRelation/getAgreeThesisByStudentNo?" + $.param(pageInfo),
        success: function (data) {
            totalPage = Math.ceil(data.total / 5);
            $("#totalData").html(data.total);
            $("#currentPage").html(page + "/" + totalPage);
            for (let i = 0; i < data.rows.length; i++) {
                let gridData = (data.rows)[i];
                let status = (gridData.thesisStatus == 2 ? "未上传" : "已上传");
                $("#data").append(`
                    <tr>
                        <td>${i + 1}</td>
                        <td>${gridData.studentNo}</td>
                        <td>${gridData.studentName}</td>
                        <td>${gridData.studentMajor}</td>
                        <td>${gridData.studentClass}</td>
                        <td>${gridData.studentInstructor}</td>
                        <td>${gridData.studentPhone}</td>
                        <td>${gridData.studentEmail}</td>
                        <td>${gridData.thesisTitle}</td>
                        <td style="color: red">${status}</td>
                        <td><a style="text-decoration: underline;cursor: pointer" onclick="uploadThesis('${gridData.thesisTitle}','${gridData.thesisNo}')">上传论文</a></td>
                    </tr>
                `)
            }
        },
        error: function () {
            $.MsgBox.Alert("错误", "加载论文题目信息错误！");
        }
    })
}

function uploadThesis(thesisTitle, thesisNo) {
    Dialog.init(`<form action="/file/uploadThesis" method="post" enctype="multipart/form-data">
        <input type="hidden" name="thesisNo" id="thsisNo" value="${thesisNo}"/>
        <input type="hidden" name="thesisTitle" id="thesisTitle" value="${thesisTitle}"/>
        <p>选择文件: <input type="file" name="file"/></p>
    </form>`, {
        title: '给定模板',
        button: {
            确定: function () {
                // 使用jq的form插件
                $("form").ajaxSubmit({
                    success: function (responseText, statusText) {
                        //alert('状态: ' + statusText + '\n 返回的内容是: \n' + responseText);
                        $.MsgBox.Alert("提示",responseText.msg);
                        // 清除表格数据
                        $("#data").empty();
                        // 重新加载数据
                        loadDataGrid();
                    },
                    resetForm: true
                });
                Dialog.close(this);
            },
            点击关闭: function () {
                Dialog.close(this);
            }
        }
    })
}