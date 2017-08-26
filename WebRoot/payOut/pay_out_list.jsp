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
	var type=${p_type}
	$(function(){
		loadPayOutTypePid();
	})
	function loadPayOutTypePid(){
		$.ajax({
			url:"payOutType",
			type:"post",
			data:"pid=0",
			dataType:"json",
			success:function(data){
				if(data!=null && data.length>0){
					var op="<option value=''>请选择类型</option>";
					for(var i=0;i<data.length;i++){
						if(type==data[i].id){
							op=op+"<option value='"+data[i].id+"' selected='selected'>"+data[i].name+"</option>";
						}else{
							op=op+"<option value='"+data[i].id+"'>"+data[i].name+"</option>";
						}
					}
					$("#p_type").append(op);
				}
			}
		})
	}
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
	function delPayOut(){
		var ids="";
		$("input:checked").each(function(){
			ids=ids+this.value+",";
		})
		if(ids==""){
			alert("至少选择一行要删除的记录！");
			return;
		}
		ids=ids.substring(0, ids.length-1);
		$.ajax({
			url:"payOut",
			type:"post",
			data:"op=deletePayOut&ids="+ids,
			dataType:"json",
			success:function(data){
				if(data.resultCode==200){
					alert("记录删除成功！");
					window.location.href="payOut?op=queryPayOutByParams";
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
<form name='form3' action='payOut' method='post'>
<input type='hidden' name='op' value='queryPayOutByParams' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>支出类型：</td>
          <td width='160'>
          <select name="p_type" style='width:150' id="p_type">
          
          </select>
        </td>
        <td width='70'>
          	支出名称：
        </td>
        <td width='160'>
          	<input type="text" name="p_name" value="${p_name}" style='width:150px'>
        </td>
        
         <td width='110'>
    		创建时间:
        </td>
        <td width='110'>
    		<input type="text" name="p_time" value="${p_time }" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" style='width:150px'>
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
	<td width="10%">支出名称</td>
	<td width="10%">类型</td>
	<td width="10%">金额</td>
	<td width="8%">备注</td>
	<td width="15%">创建日期</td>
	<td width="15%">更新日期</td>
	<td width="10%">所属帐户</td>
	<td width="10%">操作</td>
</tr>


<c:forEach items="${pageInfo.currentDatas}" var="payOut">
	<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${payOut.id}" class="np"></td>
	<td>${payOut.id}</td>
	<td>${payOut.name}</td>
	<td> 
		${payOut.typeName}
	</td>
	<td>${payOut.money}</td>
	<td>${payOut.remark}</td>
	<td><fmt:formatDate value="${payOut.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td><fmt:formatDate value="${payOut.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${payOut.aname}</td>
	<td><a href="payOut?op=queryPayOutById&id=${payOut.id}">编辑</a> </td>
</tr>
</c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="10">
	&nbsp;
	<a href="javascript:selAll()" class="coolbg">全选</a>
	<a href="javascript:noSelAll()" class="coolbg">取消</a>
	<a href="javascript:fx()" class="coolbg">反选</a>
	<a href="javascript:delPayOut()" class="coolbg">&nbsp;删除&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center">
		<c:choose>
			<c:when test="${pageInfo.totalElements>0}">
				<c:if test="${pageInfo.hasFirstPage}">
					<a href="payOut?op=queryPayOutByParams&p_name=${p_name}&p_type=${p_type}&p_time=${p_time}">首页</a>
				</c:if>
				<c:if test="${pageInfo.hasPrePage}">
					<a href="payOut?pageNum=${pageInfo.prePage}&op=queryPayOutByParams&p_name=${p_name}&p_type=${p_type}&p_time=${p_time}">上一页</a>
				</c:if>
				<c:forEach var="p" begin="${pageInfo.startNavPage}" end="${pageInfo.endNavPage}">
					<a  <c:if test="${pageInfo.pageNum==p}">style="color:red"</c:if>
					href="payOut?op=queryPayOutByParams&pageNum=${p}&p_name=${p_name}&p_type=${p_type}&p_time=${p_time}">第${p}页</a>
				</c:forEach>
				<c:if test="${pageInfo.hasEndPage}">
					<a href="payOut?pageNum=${pageInfo.endPage}&op=queryPayOutByParams&p_name=${p_name}&p_type=${p_type}&p_time=${p_time}">末页</a>
				</c:if>
				<c:if test="${pageInfo.hasNextPage}">
					<a href="payOut?pageNum=${pageInfo.nextPage}&op=queryPayOutByParams&p_name=${p_name}&p_type=${p_type}&p_time=${p_time}">下一页</a>
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