<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>银信证付款成功</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, user-scalable=no">
</head>

<body style="display: none">
<form action="${returnUrl}" method="post" id="returnForm">
    <input type="hidden" name="returnCode" value="${returnCode}">
    <input type="hidden" name="returnMsg" value="${returnMsg}">
    <!--交易信息 start-->
    <input type="hidden" name="encodedJsonData" value="${encryptResponse.encodedJsonData}">
    <input type="hidden" name="sign" value="${encryptResponse.sign}">
</form>

<script src='<c:url value="/resources/js/jquery-1.12.1.min.js" />'></script>
<script>
    var returnUrl = '${returnUrl}';
    var params = $("form").serialize();

    window.location.href = returnUrl + '?' + params;

    //    document.getElementById('returnForm').submit();
</script>
</body>
</html>