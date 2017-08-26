<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'account_add.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#submit").click(function(){
			$.ajax({
				url:"account",
				type:"post",
				data:$("#fm").serialize(),
				dataType:"json",
				success:function(data){
					if(data.resultCode==200){
						alert("帐户修改成功");
						window.location.href="account?op=queryAccountByParams&a_type=-1"
					}else{
						alert(data.msg);
					}
				}
				
			});
		});
	});
	
</script>

</head>

<body>
	<form id="fm">
		账户名称: <input name="aname" type="text" value="${account.aname}" /><br /> 
		账户类型: <select name="type">
			<option value=" ">请选择</option>
			<option value="0" <c:if test="${account.type==0}">selected="selected"</c:if>  >工商银行</option>
			<option value="1" <c:if test="${account.type==1}">selected="selected"</c:if>>建设银行</option>
			</select> <br /> 
		账户金额: <input name="money" type="text" value="${account.money }"/><br /> 
		备注信息: <input name="remark" type="text" value="${account.remark }"/><br /> 
		<input name="op" value="updateAccount" type="hidden">
		<input name="id" type="hidden" value="${account.id}"/>
		<input type="button" id="submit" value="修改账户">
	</form>
</body>
</html>
