<%@ page  contentType="text/html;charset=utf-8" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>注册页面</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {color: #528311; font-size: 12px; }
.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style></head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf">&nbsp;</td>
  </tr>
  <tr>
    <td height="608" background="skin/images/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="skin/images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <form action="user" method="post">
	          <tr>
	            <td width="424" height="95" background="skin/images/login_06.gif">&nbsp;</td>
	            <td width="183" background="skin/images/login_07.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="21%" height="30"><div align="center"><span class="STYLE3">用户</span></div></td>
	                <td width="79%" height="30"><input type="text" name="userName"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
	              </tr>
	              <tr>
	                <td height="30"><div align="center"><span class="STYLE3">密码</span></div></td>
	                <td height="30"><input type="password" name="userPwd"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
	              </tr>
	              <tr>
	                <td height="30">&nbsp;</td>
	                <td height="30">
	                	<input type=submit value="注册"/><input type=reset value="重置"/><br/>${msg}
	                </td>
	              </tr>
	             <input type="hidden" name="op" value="userRegister"/>
        </form>
	            </table>
          </td>
            <td width="255" background="skin/images/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="247" valign="top" background="skin/images/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%">&nbsp;</td>
            <td width="22%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;</td>
                <td width="56%" class="STYLE4">版本 2008V1.0 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#a2d962">&nbsp;</td>
  </tr>
</table>
</body>
</html>
