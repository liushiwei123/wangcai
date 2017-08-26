<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>支出管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 手动引入 -->
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	$(function(){
		//加载帐户的下拉框
		loadAccountData();
		//加载父类型下拉框
		loadPayOutTypePid();
		
		$("#submit").click(function(){
			$.ajax({
				url:"payOut",
				type:"post",
				data:$("#fm").serialize(),
				dataType:"json",
				success:function(data){
					if(data.resultCode==200){
						alert("支出添加成功！");
						window.location.href="payOut?op=queryPayOutByParams";
					}else{
						alert(data.msg);
					}
				}
			})
		})
	})
	function loadAccountData(){
		$.ajax({
			url:"account",
			type:"post",
			data:"op=queryAccountByUser",
			dataType:"json",
			success:function(data){
				if(data!=null && data.length>0){
					var op="<option value=''>请选择帐户</option>";
					for(var i=0;i<data.length;i++){
						op=op+"<option value='"+data[i].id+"'>"+data[i].aname+"</option>";
					}
					$("#aid").append(op);
				}
			}
		})
	}
	function loadPayOutTypePid(){
		$.ajax({
			url:"payOutType",
			type:"post",
			data:"pid=0",
			dataType:"json",
			success:function(data){
				if(data!=null && data.length>0){
					var op="<option value=''>请选择父类型</option>";
					for(var i=0;i<data.length;i++){
						op=op+"<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
					$("#pid").append(op);
				}
			}
		})
	}
	function loadPayOutTypeSid(pid){
		$.ajax({
			url:"payOutType",
			type:"post",
			data:"pid="+pid,
			dataType:"json",
			success:function(data){
				$("#sid").empty();
				if(data!=null && data.length>0){
					var op="<option value=''>请选择父类型</option>";
					for(var i=0;i<data.length;i++){
						op=op+"<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
					$("#sid").append(op);
				}
			}
		})
	}
	</script>
  </head>
  
  <body>
    <form id="fm">
    	支出名称:<input name="name" type="text"><br/>
    	支出金额:<input name="money" type="text"><br/>
    	支出所属帐户:
    	<select name="aid" id="aid">
    	
    	</select><br/>
    	支出类型:<br/>
    	父类型&nbsp;:
    	<select name="pid" id="pid" onchange="loadPayOutTypeSid(this.value)">
    		
    	</select><br/>
    	子类型&nbsp;:
    	<select name="sid" id="sid">
    	
    	</select><br/>
    	支出备注:<input name="remark" type="text"><br/>
    	<input name="op" value="addPayOut" type="hidden" />
    	<input type="button" id="submit" value="添加支出">
    	
    </form>
  </body>
</html>
