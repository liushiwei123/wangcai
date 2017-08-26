<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>账户列表</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript"  src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	function selAll(){
		$(":checkbox").attr("checked",true);
	}
	function noSelAll(){
		$(":checked").attr("checked",false);
	}
	function fx(){
		$(":checkbox").each(function(){
			$(this).attr("checked",!this.checked);
		})
	}
	function delAccount(){
		var ids="";
		$("input:checked").each(function(){
			ids=ids+this.value+",";
		})
		if(ids==""){
			alert("至少选择一行要删除的记录！");
			return;
		}
		console.info(ids);
		ids=ids.substring(0, ids.length-1);
		$.ajax({
			url:"account",
			type:"post",
			data:"op=deleteAccount&ids="+ids,
			dataType:"json",
			success:function(data){
				if(data.resultCode==200){
					alert("记录删除成功！");
					window.location.href="account?op=queryAccountByParams";
				}else{
					alert(data.msg);
				}
			}
		})
	}
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="98%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td align="center">
    <input type='button' class="coolbg np" onClick="location='';" value='添加文档' />
    <input type='button' class="coolbg np" onClick="location='';" value='我的文档' />
    <input type='button' class='coolbg np' onClick="location='';" value='稿件审核' />
    <input type='button' class="coolbg np" onClick="location='';" value='栏目管理' />
    <input type='button' class="coolbg np" onClick="location='';" value='更新列表' />
    <input type='button' class="coolbg np" onClick="location='';" value='更新文档' />
    <input type='button' class="coolbg np" onClick="location='';" value='文章回收站' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--第2步操作：  查询条件  -->
<form name='form3' action='account' method='post'>
<input type='hidden' name='op' value='queryAccountByParams' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>帐户类型：</td>
          <td width='160'>
          <select name="a_type" style='width:150'>
          		<option value='-1' <c:if test="${a_type==-1}">selected="selected"</c:if> >全部</option>
          		<option value='0' <c:if test="${a_type==0}">selected="selected"</c:if> >工商银行</option>
          		<option value='1' <c:if test="${a_type==1}">selected="selected"</c:if> >建设银行</option>
          </select>
        </td>
        <td width='70'>
          	帐户名称：
        </td>
        <td width='160'>
          	<input type="text" name="a_name" value="${a_name}" style='width:150px'>
        </td>
        
         <td width='110'>
    		创建时间:
        </td>
        <td width='110'>
    		<input type="text" name="a_time" value="${a_time }" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" style='width:150px'>
        </td>
        <td>
          <input name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!-- 第1步操作：显示 内容列表   -->
<form name="form2">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif">&nbsp;文档列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">id</td>
	<td width="10%">账户名称</td>
	<td width="10%">类型</td>
	<td width="10%">金额</td>
	<td width="8%">备注</td>
	<td width="15%">创建日期</td>
	<td width="15%">更新日期</td>
	<td width="10%">操作</td>
</tr>


<c:forEach items="${pageInfo.currentDatas}" var="account">
	<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${account.id}" class="np"></td>
	<td>${account.id}</td>
	<td>${account.aname}</td>
	<td> 
		<c:choose>
			<c:when test="${account.type==0}">
				工商银行
			</c:when>
			<c:otherwise>
				建设银行
			</c:otherwise>
		</c:choose>
	</td>
	<td>${account.money}</td>
	<td>${account.remark}</td>
	<td><fmt:formatDate value="${account.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td><fmt:formatDate value="${account.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td><a href="account?op=queryAccountById&id=${account.id}">编辑</a> </td>
</tr>
</c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="10">
	&nbsp;
	<a href="javascript:selAll()" class="coolbg">全选</a>
	<a href="javascript:noSelAll()" class="coolbg">取消</a>
	<a href="javascript:fx()" class="coolbg">反选</a>
	<a href="javascript:delAccount()" class="coolbg">&nbsp;删除&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center">
		<c:choose>
			<c:when test="${pageInfo.totalElements>0}">
				<c:if test="${pageInfo.hasFirstPage}">
					<a href="account?op=queryAccountByParams&a_name=${a_name}&a_type=${a_type}&a_time=${a_time}">首页</a>
				</c:if>
				<c:if test="${pageInfo.hasPrePage}">
					<a href="account?pageNum=${pageInfo.prePage}&op=queryAccountByParams&a_name=${a_name}&a_type=${a_type}&a_time=${a_time}">上一页</a>
				</c:if>
				<c:forEach var="p" begin="${pageInfo.startNavPage}" end="${pageInfo.endNavPage}">
					<a  <c:if test="${pageInfo.pageNum==p}">style="color:red"</c:if>
					href="account?op=queryAccountByParams&pageNum=${p}&a_name=${a_name}&a_type=${a_type}&a_time=${a_time}">第${p}页</a>
				</c:forEach>
				<c:if test="${pageInfo.hasEndPage}">
					<a href="account?pageNum=${pageInfo.endPage}&op=queryAccountByParams&a_name=${a_name}&a_type=${a_type}&a_time=${a_time}">末页</a>
				</c:if>
				<c:if test="${pageInfo.hasNextPage}">
					<a href="account?pageNum=${pageInfo.nextPage}&op=queryAccountByParams&a_name=${a_name}&a_type=${a_type}&a_time=${a_time}">下一页</a>
				</c:if>
				 	当前页/总页数:${pageInfo.pageNum}/${pageInfo.totalPages}   	总记录数：${pageInfo.totalElements}
			</c:when>
			<c:otherwise>
				暂无记录
			</c:otherwise>
		</c:choose>
	</td>
</tr>
</table>

</form>
</body>
</html>