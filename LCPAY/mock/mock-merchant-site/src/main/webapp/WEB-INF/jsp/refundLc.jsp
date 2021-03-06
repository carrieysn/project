<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>银信证交易测试-退款接口测试</title>
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
	</script>
</head>
<body onload="">
	<!--头部-->
    <div class="header">
        <div class="logo"><img src="img/logo.png"></div>
    </div>
    
    <!--内容-->
    <div class="container">
        <p class="raya-firstpay">退款接口测试页面(<span class="requireicon">*</span>标记为必填参数)</p>
        <div class="raya-ordertable">
		<form action="<c:url value="/lc/reFund.lc" />" method="post" target="_blank" onsubmit="return validate()">
			<ul>
	            <li>
					<label>银信证ID：</label>
					 <input class="raya-input" type="text" id="lcId" name="lcId" value="${param.lcId}" maxlength="20" required /> 
					 <span class="requireicon">*</span>
				</li>
				<li>
					<label>退款订单号：</label>
					<input class="raya-input"  id="refundOrderId" name="refundOrderId" value="${param.refundOrderId}" maxlength="200" />
					<span class="requireicon">*</span>
				</li>
				<li>
					<label>订单ID：</label>
					<input class="raya-input"  id="orderId" name="orderId" value="${param.orderId}" maxlength="200" />
					<span class="requireicon">*</span>
				</li>
				<li>
					<label>退款金额（分）：</label>
					<input class="raya-input" id="amount" name="amount" value="${param.amount}" maxlength="200" />
					<span class="requireicon">*</span>
				</li>
				<li>
					<label>备注：</label>
					<input class="raya-input"  id="remark" name="remark" value="${param.remark}" maxlength="200" />
				</li>
			</ul>
			<input class="raya-submit" value="失效提交" type="submit">
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