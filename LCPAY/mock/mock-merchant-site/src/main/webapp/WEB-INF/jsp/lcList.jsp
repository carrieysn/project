<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<% 
		pageContext.setAttribute("title", "银信证交易测试-预开证接口");
	%>
	<%@include file="head.jsp" %> 
	
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
		
	</script>
</head>
<body onload="">
	<!--头部-->
    <div class="header">
        <div class="logo"><img src="<c:url value="/resources/img/logo.png" />"></div>
    </div>
    
    <!--内容-->
    <div class="container">
        <div class="raya-top">
        	<form action="<c:url value="/getLcListServlet"/>" method="post">
	            <span class="raya-span">开证日期(yyyy-MM-dd)：</span>
	            <input class="Hyman-input" id="startDate" name="startDate" value="${param.startDate }" style="width:100px" maxlength="10">
	            	至
	           	<input class="Hyman-input" id="endDate" name="endDate" value="${param.endDate }" style="width:100px" maxlength="10">
	            <span>&nbsp; 银信证ID：</span>
	            <input class="Hyman-input" id="lcId" name="lcId" value="${param.lcId }" maxlength="10" style="width:100px">
	            <span>&nbsp; 商户号：</span>  
	            <input class="Hyman-input" id="mrchId" name="mrchId" value="${param.mrchId }" maxlength="10" style="width:100px">
	            <input type="submit" class="Hyman-button" value="查询">
            </form>
        </div>
        <div class="Hyman-table">
            <table cellspacing="10px">
                <thead>
                    <tr>
                        <!-- <td width="180px">银信证流水</td> -->
                        <td width="300px">银信证号</td>
                        <td width="100px">型号</td>
                        <td width="100px">金额(分)</td>
                        <td width="100px">余额(分)</td>
                        <td width="160px">开证时间</td>
                        <td width="100px">状态</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="lc" items="${lcList}" varStatus="status">
                	
						<tr style="background-color:${status.index%2==0?'#e8e8e8':''}">
							<td>${lc.LC_NO }</td>
							<td>${lc.LC_TYPE }</td>
							<td><fmt:formatNumber value="${lc.LC_AMOUNT}" type="number" pattern=",##0.##" /></td>
							<td><fmt:formatNumber value="${lc.LC_BALANCE}" type="number" pattern=",##0.##" /></td>
							<td>${lc.CREATE_TIME }</td>
							
                			<c:if test="${lc.LC_STATUS == '10'}"><td>已开证</td></c:if>
                			<c:if test="${lc.LC_STATUS == '20'}"><td>已收证</td></c:if>
                			<c:if test="${lc.LC_STATUS == '30'}"><td>已履约</td></c:if>
                			<c:if test="${lc.LC_STATUS == '31'}"><td>部分履约</td></c:if>
                			<c:if test="${lc.LC_STATUS == '32'}"><td>已展期</td></c:if>
                			<c:if test="${lc.LC_STATUS == '40'}"><td>已申请解付</td></c:if>
                			<c:if test="${lc.LC_STATUS == '41'}"><td>已刹车</td></c:if>
                			<c:if test="${lc.LC_STATUS == '50'}"><td>已执行解付</td></c:if>
                			<c:if test="${lc.LC_STATUS == '88'}"><td>已解付完成</td></c:if>
                			<c:if test="${lc.LC_STATUS == '90'}"><td>已解冻退回</td></c:if>
                			<c:if test="${lc.LC_STATUS == '91'}"><td>预失效</td></c:if>
                			
							<td>
								<a href="<c:url value="/page/recvLc"/>?lcId=${lc.LC_ID }&recvAccountType=${lc.PAYER_TYPE}&recvBankCode=${lc.RECV_BANK_CODE}&remark=${lc.LC_NO}" target="_blank">[收证]</a>
								<a href="<c:url value="/page/appointmentLc"/>?lcId=${lc.LC_ID }&amount=${lc.LC_BALANCE }&orderId=${lc.ORDER_ID }" target="_blank">[履约]</a>
								<a href="<c:url value="/page/deferLc"/>?lcId=${lc.LC_ID }" target="_blank">[展期]</a>
								<a href="<c:url value="/page/applyLc"/>?lcId=${lc.LC_ID }&amount=${lc.LC_AMOUNT}&remark=${lc.LC_NO}" target="_blank">[申请解付]</a>
								<a href="<c:url value="/page/invalidateLc"/>?lcId=${lc.LC_ID }&amount=${lc.LC_AMOUNT}&remark=${lc.LC_NO}" target="_blank">[失效]</a><br/>
								<a href="<c:url value="/page/suspendLc"/>?lcId=${lc.LC_ID }&remark=${lc.LC_NO}" target="_blank">[暂停解付]</a>
								<a href="<c:url value="/page/transferLc"/>?lcId=${lc.LC_ID }&amount=${lc.LC_AMOUNT}&remark=${lc.LC_NO}" target="_blank">[解付]</a>
							</td>
						</tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
	<div class="footer">
		深圳市银信网银科技有限公司 2016 CIFPAY.all Rights Reservde Tel 0755-3368898
	</div>
</body>
</html>