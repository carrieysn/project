<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银信证交易测试-正在跳转至银信证支付</title>
</head>
<body>
	<form id="payForm" action="${cifpayWebPayUrl}" method="post">
		<input type="hidden" name="merId" value="${merId}" /><br/>
		<input type="hidden" name="sign" value="${sign}" /><br/>
		<input type="hidden" name="encodedJsonData" value="${encodedJsonData}" /><br/>
	</form>
	<script type="text/javascript">
	document.getElementById("payForm").submit();
	</script>
</body>
</html>