$(function () {
    $("#pie").css("height","300px")
            .css("width","1000px")
            .css("border","1px solid darkgreen");
    $("#bar").css("height","300px")
        .css("width","1000px")
        .css("border","1px solid darkgreen");
    initPieEcharts();
})

function initPieEcharts() {
    $.ajax({
        type:"post",
        url:"/studentTeacherRelation/getEchartData",
        success:function (data) {
            paddingData(data.data);
        }
    })
}

function paddingData(data) {
    var domPie = $("#pie")[0];
    var pieEchart = echarts.init(domPie);
    optionPie = {
        title : {
            text: '选题情况统计(饼图)',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['选题审核通过','选题未审核','选题未通过']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '40%',
                            funnelAlign: 'left',
                            max: 1500
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'',
                type:'pie',
                radius : '45%',
                center: ['40%', '50%'],
                data:[
                    {value:data.passNum, name:'选题审核通过'},
                    {value:data.underPassNum, name:'选题未审核'},
                    {value:data.noPassNum, name:'选题未通过'},
                ]
            }
        ]
    };
    if (optionPie && typeof optionPie === "object") {
        pieEchart.setOption(optionPie, true);
    }


    var domBar = document.getElementById("bar");
    var barEchart = echarts.init(domBar);
    let optionBar = {
        title : {
            text: '选题情况统计(柱图)',
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        grid: {y: 70, y2:30, x2:20},
        xAxis: {
            type: 'category',
            data: ['选题审核通过','选题未审核','选题未通过']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name:'',
            data: [data.passNum, data.underPassNum, data.noPassNum],
            type: 'bar'
        }]
    };
    ;
    if (optionBar && typeof optionBar === "object") {
        barEchart.setOption(optionBar, true);
    }
    
}