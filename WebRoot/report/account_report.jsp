<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'account_report.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<!-- 引入 ECharts 文件 -->
	<script type="text/javascript" src="js/echarts.common.min.js"></script>
	<script type="text/javascript">
	$(function(){
		var data1=[];
		var data2=[];
		$.ajax({
			url:"account",
			type:"post",
			data:"op=queryAccountByUser",
			dataType:"json",
			success:function(data){
				if(data!=null && data.length>0){
					for(var i=0;i<data.length;i++){
						data1[i]=data[i].aname;
						data2[i]=data[i].money;
					}
					// 基于准备好的dom，初始化echarts实例
			        var myChart = echarts.init(document.getElementById('main'));

			        // 指定图表的配置项和数据
			        var option = {
			            title: {
			                text: '帐户柱状图'
			            },
			            tooltip: {},
			            legend: {
			                data:['金额']
			            },
			            xAxis: {
			                data: data1
			            },
			            yAxis: {},
			            series: [{
			                name: '金额',
			                type: 'bar',
			                data: data2
			            }]
			        };

			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
				}
			}
		})
	})
	
	</script>
  </head>
  
  <body>
   	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
  </body>
</html>
