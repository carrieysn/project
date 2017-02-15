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
<form id="autoReturnForm" action="${merchantReturnUrl}" method="${formSubmitMethod}">
	<c:forEach items="${merchantReturnParams}" var="merchantReturnParam">
	<input type="hidden" name="${merchantReturnParam.name}" value="${merchantReturnParam.value}" />
	</c:forEach>
</form>
<script type="text/javascript">
document.getElementById("autoReturnForm").submit();
</script>
</body>
</html>