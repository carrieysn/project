<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<% 
		pageContext.setAttribute("title", "银信证交易测试-预开证接口");
	%>
	<%@include file="head.jsp" %> 
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
        <div class="logo"><img src="<c:url value="/resources/img/logo.png" />"></div>
    </div>
    
    <!--内容-->
    <div class="container">
		<div class="raya-ordertable">
			<label>银信证返回数据：</label>
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