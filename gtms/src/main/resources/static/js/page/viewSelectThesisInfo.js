$(function () {
    // 加载表格数据
    loadDataGrid();
})

function loadDataGrid() {
    $.ajax({
        type: "post",
        url: "/studentTeacherRelation/getStudentSelectThesisByStudentNo",
        async: false,
        success: function (data) {
            for (let i = 0; i < data.data.length; i++) {
                let gridData = (data.data)[i];
                $("#data").append(`
                    <tr>
                        <td>${gridData.studentNo}</td>
                        <td>${gridData.studentName}</td>
                        <td>${gridData.studentClass}</td>
                        <td>${gridData.teacherNo}</td>
                        <td>${gridData.teacherName}</td>
                        <td>${gridData.thesisTitle}</td>
                        <td style="color: red">${gridData.opinionFlagStr}</td>
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