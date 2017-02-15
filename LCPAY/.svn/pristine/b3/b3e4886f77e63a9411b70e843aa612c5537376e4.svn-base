<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width,height=device-height">
	<title>银信证交易测试-订单结算</title>
	<!--ICON FOR IE7-->
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <!--ICON FOR IE6-->
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <!--FOR Favorites ICON-->
    <link rel="Bookmark" href="/favicon.ico">
    <link rel="stylesheet" type="text/css" href="/MerchMock/css/public.css">
    <link rel="stylesheet" type="text/css" href="/MerchMock/css/all.css">
    <link rel="stylesheet" type="text/css" href="/MerchMock/css/pay2.css">
    
	<script type="text/javascript" src="/MerchMock/js/jquery_1_7.js"></script>
	<script type="text/javascript">
		$(function() {
			//刷新付页面列表
			//if("${result }"=="SUCCESS") {
				if(window.parent) {
		 	  		window.parent.opener.location.reload();
		 	  		//setTimeout(function(){window.close();}, 2000);
				}
			//}
		});
	</script>
</head>
<body>
	<!--头部-->
    <div class="header">
        <div class="logo"><img src="/MerchMock/img/logo.png"></div>
    </div>
    
    <!--内容-->
    <div class="container">
	    <p class="raya-firstpay">模拟账单中心订单结算场景(<span class="requireicon">*</span>标记为必填参数)</p>
	    <div class="raya-ordertable">
			<c:url var="formSubmitUrl" value="/billcenter/order/topay" />
			<form action="${formSubmitUrl}" method="post">
			<ul>
				<li>
					<label>订单名称：</label>
					<input name="tradeName" value="模拟账单中心订单支付" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>订单金额（分）：</label>
					<input type="number" name="amount" value="100" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>付款银行代码：</label>
					<input type="text" name="cBankCode" value="ICBC" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>默认付款卡号：</label>
					<input type="number" name="cBankAccountNo" value="6666888899990000123" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>手机号：</label>
					<input type="number" name="mobile" value="13800138000" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款银行代码：</label>
					<input type="text" name="mBankCode" value="ICBC" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款卡号：</label>
					<input type="number" name="mBankAccountNo" value="8888888888888888999" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款方名称：</label>
					<input type="text" name="mBankAccountName" value="钱少少" class="raya-input" /><span class="requireicon">*</span>
				</li>
				<li>
					<label>最晚确认时间：</label>
					<input type="text" name="lastConfirmTime" value="${lastConfirmTime}" class="raya-input" /><span class="requireicon">*</span>
				</li>
			</ul>
			<button type="submit" class="raya-submit">银信证支付</button>
			</form>
		</div>
		<div class="Clearfix"></div>
	</div>
	
	<!--尾部-->
	<div class="footer">
		深圳市银信网银科技有限公司 2015 CIFPAY.all Rights Reservde Tel 0755-3368898
	</div>
</body>
</html>