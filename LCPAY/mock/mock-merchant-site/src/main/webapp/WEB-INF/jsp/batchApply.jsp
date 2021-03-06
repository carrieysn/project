<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>银信证交易测试-批量提现</title>
	<!--ICON FOR IE7-->
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <!--ICON FOR IE6-->
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <!--FOR Favorites ICON-->
    <link rel="Bookmark" href="/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/public.css">
    <link rel="stylesheet" type="text/css" href="css/all.css">
    <link rel="stylesheet" type="text/css" href="css/pay2.css">
    
	<script type="text/javascript" src="js/jquery_1_7.js"></script>
	<script type="text/javascript">
		$(function() {
			//$("#orderId").val(Math.ceil(Math.random()*10000000000));
			initURL();
		});
		
		$(function() {
			setValidTime("recvValidTime", 1);
			setValidTime("sendValidTime", 8);
			setValidTime("confirmPayValidTime", 15);
		});
		
		function setValidTime(id, num) {
			var date = new Date();
			date.setDate(date.getDate() + num);
			var years=date.getFullYear();//获取年
			var months=date.getMonth()+1;//获取月
			
			if(months<10){//当月份不满10的时候前面补0，例如09
			  months='0'+months;
			}
			
			var day=date.getDate();//获取日
			if(day<10){//当日期不满10的时候前面补0，例如09
				day='0'+day;
			}
			$("#"+id).val(years+"-"+months+"-"+day);
		}
		
		function validate() {
			return true;
		}
		function initURL() {
			var returnUrl = "<c:out value='/success.jsp'/>";
			var noticeUrl = "<c:out value='/notice'/>";
			var merOrderUrl = "<c:out value='/merOrder.jsp'/>";
			var hostInfo = window.location.protocol+"//"+window.location.host;
			$('#returnUrl').val(hostInfo+returnUrl);
			$('#noticeUrl').val(hostInfo+noticeUrl);
			$('#merOrderUrl').val(hostInfo+merOrderUrl);
		}
	</script>
</head>
<body onload="initURL()">
	<!--头部-->
    <div class="header">
        <div class="logo"><img src="img/logo.png"></div>
    </div>
    
    <!--内容-->
    <div class="container">
        <p class="raya-firstpay">批量提现页面</p>
        <div class="raya-ordertable">
		<form action="<c:url value="/lc/batchApply.lc" />" method="post" target="_blank" >
			
			<div id="div1" id="div1">
				<fieldset style="border:1px solid #ddd">
					<legend>提现</legend>
					
					<ul>
						<li>
							<label>银信证ID：</label>
							<input class="raya-input" type="text" id="lcId" name="lcId" value="" maxlength="50" required /> <span class="requireicon">*</span>
						</li>
						<li>
							<label>履约ID：</label>
							<input class="raya-input" type="text" id="lcAppointmentId" name="lcAppointmentId" value="" maxlength="200" required /> <span class="requireicon">*</span>
						</li>
						<li>
							<label>申请解付凭证：</label>
							<input class="raya-input" id="signCode" name="signCode" value="" maxlength="200"/>
						</li>
						<li>
							<label>备注：</label>
							<input class="raya-input"  id="remark" name="remark" value="批量提现接口-测试" maxlength="200"/>
						</li>
						<li></li>
					</ul>
					<div style="width:100%; text-align:center">
						<a href="javascript:void(0)" onclick="add1()" >增加提现</a>
						<a href="javascript:void(0)" onclick="delete1(this)" >删除</a>
					</div>
				</fieldset>
			</div>
			
			<input class="raya-submit" value="提现" type="submit">
			
		</form>
		</div>
		<div class="Clearfix"></div>
	</div>
	
	<!--尾部-->
	<div class="footer">
		深圳市银信网银科技有限公司 2016 CIFPAY.all Rights Reservde Tel 0755-3368898
	</div>

<script type="text/javascript">
var s1 = $("#div1").html();
function add1() {
	var o1 = $("#div1");
	o1.append(s1);
}
function delete1(o) {
	var o1 = $(o);
	
	o1.parent().parent().remove();
}
</script>
	
</body>
</html>