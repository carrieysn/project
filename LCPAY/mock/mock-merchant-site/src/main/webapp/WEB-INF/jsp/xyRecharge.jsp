<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>银信证交易测试-星意充值接口</title>
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
			/*if($("#openBankCode").val() == "") {
				alert("开证银行编码不能为空");
				return false;
			}
			if($("#currency").val() == "") {
				alert("币种不能为空");
				return false;
			}
			if($("#lcAmount").val() == "") {
				alert("开证金额不能为空");
				return false;
			}
			if($("#lcType").val() == "") {
				alert("银信证类型不能为空");
				return false;
			}
			if($("#openType").val() == "") {
				alert("开证方式不能为空");
				return false;
			}
			if($("#isAutoRecv").val() == "") {
				alert("收证方式不能为空");
				return false;
			}
			if($("#recvValidTime").val() == "") {
				alert("收证期限不能为空");
				return false;
			}
			if($("#sendValidTime").val() == "") {
				alert("提示解付期限不能为空");
				return false;
			}
			if($("#confirmPayValidTime").val() == "") {
				alert("申请解付期限不能为空");
				return false;
			}
			if($("#payerMobile").val() == "") {
				alert("付款人手机号码不能为空");
				return false;
			}
			if($("#noticeUrl").val() == "") {
				alert("后台消息通知地址不能为空");
				return false;
			}
			if($("#returnUrl").val() == "") {
				alert("页面返回地址不能为空");
				return false;
			}
			if($("#mrchOrderUrl").val() == "") {
				alert("商户订单地址不能为空");
				return false;
			}*/
			return true;
		}
		function initURL() {
			var time = new Date().getTime();
			$('#orderId').val('xy'+time);
			var showUrl = "<c:out value='/MerchMock/xyRecharge.jsp'/>";
			var returnUrl = "<c:out value='/MerchMock/xy/rechargeReturnUrl'/>";
			var noticeUrl = "<c:out value='/MerchMock/xy/rechargeNoticeUrl'/>";
			var hostInfo = window.location.protocol+"//"+window.location.host;
			$('#returnUrl').val(hostInfo+returnUrl);
			$('#noticeUrl').val(hostInfo+noticeUrl);
			$('#showUrl').val(hostInfo+showUrl);
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
        <p class="raya-firstpay">星意接口测试页面(<span class="requireicon">*</span>标记为必填参数)</p>
        <div class="raya-ordertable">
		<form action="xy/rechargePay" method="post" target="_blank" onsubmit="return validate()">
			<ul>
				<li>
					<label>订单号：</label>
					<input class="raya-input" id="orderId" name="orderId" value="W201507021407312510" maxlength="30"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>订单描述：</label>
					<input class="raya-input" id="orderDesc" name="orderDesc" value="" maxlength="100"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>充值类型：</label>
					<select class="raya-select"  id="type" name="type" >
						<option value="1" selected="selected">个人</option>
						<option value="2" >公司</option>
					</select><span class="requireicon">*</span>
				</li>
				<li>
					<label>币种：</label>
					<input class="raya-input" id="currency" name="currency" value="CNY" maxlength="5"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>金额：</label>
					<input class="raya-input" id="amount" name="amount" value="51234" maxlength="50"/><span class="requireicon">*</span>(单位：角)
				</li>
				<li>
					<label>支付者手机号：</label>
					<input class="raya-input" id="payerMobile" name="payerMobile" value="13424589999" maxlength="200"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>用户编码：</label>
					<input class="raya-input" id="userCode" name="userCode" value="1002" maxlength="200"/> <span class="requireicon">*</span>angelshiqian04
				</li>
				<li>
					<label>商品地址：</label>
					<input class="raya-input"  id="showUrl" name="showUrl" value="" maxlength="200"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>返回地址：</label>
					<input class="raya-input" id="returnUrl"  name="returnUrl"  value="" maxlength="200"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>通知地址：</label>
					<input class="raya-input" id="noticeUrl"  name="noticeUrl"  value="" maxlength=""/><span class="requireicon">*</span>
				</li>
			</ul>
			<input class="raya-submit" value="充值" type="submit">
		</form>
		</div>
		<div class="Clearfix"></div>
	</div>
	
	<!--尾部-->
	<div class="footer">
		深圳市银信网银科技有限公司 2016 CIFPAY.all Rights Reservde Tel 0755-3368898
	</div>
</body>
</html>