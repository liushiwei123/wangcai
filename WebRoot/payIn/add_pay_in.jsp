<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加收入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 手动引入jquery -->
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	$(function(){
		//加载帐户的下拉框
		loadAccountData();
		$("#submit").click(function(){
			$.ajax({
				url:"payIn",
				type:"post",
				data:$("#fm").serialize(),
				dataType:"json",
				success:function(data){
					if(data.resultCode==200){
						alert("收入添加成功！");
						window.location.href="payIn?op=queryPayInByParams";
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
					$("#account").append(op);
				}
			}
		})
	}
	</script>
  </head>
  <body>
    <form	id="fm">
    		收入名称:<input name="name" type="text"><br/>
    		收入类型：
    		<select name="type">
    			<option value=" ">请选择</option>
    			<option value="0">工资</option>
    			<option value="1">红包</option>
    			<option value="2">其他</option>
    		</select></br>
    		收入金额:<input name="money" type="text"><br/>
    		备注信息:<input name="remark" type="text"><br/>
    		帐&nbsp;户：
    		<select id="account" name="aid">
    			<!-- 通过js进行填充 -->
    		</select> 
    		<input name="op" value="addPayIn" type="hidden"><br/>
    		<input type="button" id="submit" value="添加收入">
    </form>
  </body>
</html>