<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银信证预开证</title>
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

$(function() { 
	$("form").submit();
});
</script>
</head>
<body>
<div style="display:block;">
    <div>
        <p style="text-align:center">正在转向中，请稍后...</p>
        <p style="text-align:center">如果系统长时间没响应，请<a href="javascript:void(0)" onclick="$('form').submit();">点击此处</a>重新提交</p>
    </div>
	<form action="${openRslt.url}" method="post" id="openLcForm" class="openLcForm">
		<input type="hidden" name="data" value="${openRslt.data}" />
		<input type="hidden" name="mac" value="${openRslt.mac}" />
		<input type="hidden" name="key" value="${openRslt.key}" />
	</form>
</div>

</body>
</html>