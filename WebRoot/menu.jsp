<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="skin/js/frame/menu.js"></script>
<base target="main" />
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>帐户管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='account/account_add.jsp' target='main'>添加帐户</a></div>
              </div>
            </li>
            <li><a href='account?op=queryAccountByParams&a_type=-1' target='main'>查询帐户</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      
      
      <!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>收入管理</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='payIn/add_pay_in.jsp' target='main'>添加收入</a></div>
              </div>
            </li>
            <li><a href='payIn?op=queryPayInByParams' target='main'>查询收入</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      <!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>支出管理</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='payOut/add_pay_out.jsp' target='main'>添加支出</a></div>
              </div>
            </li>
            <li><a href='payOut?op=queryPayOutByParams' target='main'>查询支出</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      
       <!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>报表管理</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='report/account_report.jsp' target='main'>帐户柱状图</a></div>
              </div>
            </li>
            <li><a href='report/pay_out_sum.jsp' target='main'>支出消费饼状图</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      
      
      
      
      <!-- Item 2 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>系统帮助</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            <li><a href='http://www.865171.cn' target='_blank'>官方网站</a></li>
            <li><a href='http://www.865171.cn/admin-templates/' target='_blank'>更多后台模板</a></li>
          </ul>
        </dd>
      </dl>
      <!-- Item 2 End -->
	  </td>
  </tr>
</table>
</body>
</html>