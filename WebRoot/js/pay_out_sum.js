$(function(){
	loadReport(null,null);
})
function searchTime(){
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	loadReport(startTime,endTime);
}
function loadReport(startTime,endTime){
	if(startTime==null || startTime=="" || startTime=="undefined"){
		startTime="";
	}
	if(endTime==null || endTime=="" || endTime=="undefined"){
		endTime="";
	}
	var data1=[];//类型名称
	var data2=[];//数组对象 {类型名称，总金额}
	$.ajax({
		url:"payOutSum",
		type:"post",
		data:"startTime="+startTime+"&endTime="+endTime,
		dataType:"json",
		success:function(data){
			if(data!=null && data.length>0){
				for(var i=0;i<data.length;i++){
					data1[i]=data[i].name;
					var obj={};//声明js对象
					obj.name=data[i].name;
					obj.value=data[i].total;
					data2[i]=obj;
				}
//				console.info(data2);
//				console.info(data1);
				// 基于准备好的dom，初始化echarts实例
		        var myChart = echarts.init(document.getElementById('main'));
		        option = {
		        	    title : {
		        	        text: '支出类型饼状图',
		        	        subtext: '纯属虚构',
		        	        x:'center'
		        	    },
		        	    tooltip : {
		        	        trigger: 'item',
		        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		        	    },
		        	    legend: {
		        	        orient: 'vertical',
		        	        left: 'left',
		        	        data: data1
		        	    },
		        	    series : [
		        	        {
		        	            name: '支出金额所占比例',
		        	            type: 'pie',
		        	            radius : '55%',
		        	            center: ['50%', '60%'],
		        	            data:data2,
		        	            itemStyle: {
		        	                emphasis: {
		        	                    shadowBlur: 10,
		        	                    shadowOffsetX: 0,
		        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		        	                }
		        	            }
		        	        }
		        	    ]
		        	};
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
			}
			
		}
	})
}
