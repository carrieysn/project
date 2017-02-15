<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>银信证</title>
</head>
<body>
<form id="autoForwardForm" action="${externalTradeSystemPaymentFrontUrl}" method="post">
	<c:forEach items="${externalPaymentParams}" var="externalPaymentParam">
	<input type="hidden" name="${externalPaymentParam.name}" value="${externalPaymentParam.value}" />
	</c:forEach>
</form>
<script type="text/javascript">
document.getElementById("autoForwardForm").submit();
</script>
</body>
</html>