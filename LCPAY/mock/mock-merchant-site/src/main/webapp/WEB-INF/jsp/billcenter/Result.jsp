<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>银信证交易测试</title>
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
    	<c:choose>
    		<c:when test="${'SUCCESS' == returnCode}">
		    	<div class="raya-ordertable" style="color:blue">
					<label><b>交易结果：</b>${returnCode}</label>
				</div>
    		</c:when>
    		<c:otherwise>
		    	<div class="raya-ordertable" style="color:red">
					<label><b>交易结果：</b>${returnCode}</label>
				</div>
    		</c:otherwise>
    	</c:choose>
		<div class="raya-ordertable">
			<label>返回数据：</label>
			${returnData }
		</div>
		<div class="Clearfix" style="height:100px"></div>
	</div>
	
	<!--尾部-->
	<div class="footer">
		深圳市银信网银科技有限公司 2015 CIFPAY.all Rights Reservde Tel 0755-3368898
	</div>
</body>
</html>