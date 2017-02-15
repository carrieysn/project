<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>银信证交易测试-星意提现接口</title>
	<!--ICON FOR IE7-->
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <!--ICON FOR IE6-->
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <!--FOR Favorites ICON-->
    <link rel="Bookmark" href="/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/public.css">
    <link rel="stylesheet" type="text/css" href="css/all.css">
    <link rel="stylesheet" type="text/css" href="css/pay2.css">
    
	<link rel="stylesheet" type="text/css" href="css/calendar.css">
	<link rel="stylesheet" type="text/css" href="css/pay1.css">
    
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
			if($("#amount").val() == "") {
				alert("金额不能为空");
				return false;
			}
			/*if($("#currency").val() == "") {
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
		function summary(amt){
			var amount = $('#amount').val();
			if(amount == null)
			{
				amount = 0;
			}
			var total = 0;
			var checkboxs = document.getElementsByName("lcIds");
			var lcIdString = "";
			for(var i=0;i<checkboxs.length;i++)
			{
				if(checkboxs[i].checked){
					total = Number(total)+Number((document.getElementById("amt"+ i).value));
					if(lcIdString == "")
					{
						lcIdString = checkboxs[i].value+"#"+ Number((document.getElementById("amt"+ i).value));
					}
					else{
						lcIdString  = lcIdString + "," + checkboxs[i].value+"#"+ Number((document.getElementById("amt"+ i).value));
					}
				  }
			}
			 $('#amount').val((total/10).toFixed(0));
			 $('#lcIdString').val(lcIdString);
		}
		function initURL() {
			var time = new Date().getTime();
			$('#orderId').val('xy'+time);
			$('#orderDesc').val('xy'+time + "提现");
			var noticeUrl = "<c:out value='/MerchMock/xy/fundNoticeUrl'/>";
			var hostInfo = window.location.protocol+"//"+window.location.host;
			$('#noticeUrl').val(hostInfo+noticeUrl);
		}
	</script>
</head>
<body onload="initURL()">
	<!--头部-->
    <div class="header">
        <div class="logo"><img src="img/logo.png"></div>
    </div>
    
    <!--内容-->
    <div class="container">
        <p class="raya-firstpay">星意提现接口测试页面(<span class="requireicon">*</span>标记为必填参数)</p>
        <div class="raya-top">
        	<form action="getFundServlet" method="post">
	            <span class="raya-span">开证日期(yyyy-MM-dd)：</span>
	            <input class="Hyman-input" id="startDate" name="startDate" value="${param.startDate }" style="width:100px" maxlength="10">
	            	至
	           	<input class="Hyman-input" id="endDate" name="endDate" value="${param.endDate }" style="width:100px" maxlength="10">
	            <span>&nbsp; 银信证ID：</span>
	            <input class="Hyman-input" id="lcId" name="lcId" value="${param.lcId }" maxlength="10" style="width:100px">
	            <span>&nbsp; 订单号：</span>  
	            <input class="Hyman-input" id="order" name="order" value="${param.order }" maxlength="100" style="width:100px">
	            <input type="submit" class="Hyman-button" value="查询">
            </form>
        </div>
        <div class="Hyman-table">
            <table cellspacing="10px">
                <thead>
                    <tr>
                        <!-- <td width="180px">银信证流水</td> -->
                        <td width="30px">序号</td>
                        <td width="300px">银信证号</td>
                        <td width="100px">型号</td>
                        <td width="100px">金额(分)</td>
                        <td width="100px">余额(分)</td>
                        <td width="160px">开证时间</td>
                        <td width="100px">状态</td>
                        <td width="100px">操作</td>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="lc" items="${lcList}" varStatus="status">
						<tr style="background-color:${status.index%2==0?'#e8e8e8':''}">
							<td>${status.index + 1}</td>
							<td>${lc.LC_NO }</td>
							<td>${lc.LC_TYPE }</td>
							<td><fmt:formatNumber value="${lc.LC_AMOUNT }" type="number" pattern=",##0.00#" /></td>
							<td><fmt:formatNumber value="${lc.LC_BALANCE }" type="number" pattern=",##0.00#" /></td>
							<td>${lc.CREATE_TIME }</td>
							<td>${lc.LC_STATUS}</td>
							<td>
								<input type="checkbox"  value="${lc.LC_ID}" name="lcIds" onclick="summary()"/>
								<input type="hidden" value="<fmt:formatNumber value="${lc.LC_AMOUNT }" type="number" pattern="##0.00#" />"  id="amt${status.index}">
							</td>
						</tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
         <p class="raya-firstpay"></p>
         
        <div class="raya-ordertable">
		<form action="xy/getFund" method="post" target="_blank" onsubmit="return validate()">
			<ul>
				<li>
					<label>订单号：</label>
					<input class="raya-input" id="orderId" name="orderId" value=""  maxlength="30"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>订单描述：</label>
					<input class="raya-input" id="orderDesc" name="orderDesc" value="" maxlength="100"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>用户编码：</label>
					<input class="raya-input" id="userCode" name="userCode" value="1002" maxlength="200"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>币种：</label>
					<input class="raya-input" id="currency" name="currency" value="CNY" maxlength="5"/> <span class="requireicon">*</span>
				</li>
				<li>
					<label>金额：</label>
					<input class="raya-input" id="amount" name="amount" value="" maxlength="50" readonly="readonly"/><span class="requireicon">*</span>(角)
					<input type="hidden"  id="lcIdString" name="lcIdString" >
				</li>
					<li>
					<label>付款银行：</label>
					<input class="raya-input" id="payerBankCode" name="payerBankCode" value="ICBC" maxlength="50"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>付款人账号：</label>
					<input class="raya-input" id="payerBankAcctNo" name="payerBankAcctNo" value="4000023029200124946" maxlength="50"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>付款人手机号：</label>
					<input class="raya-input" id="payerMobile" name="payerMobile" value="13800000001" maxlength="50"/><span class="requireicon">*</span>
				</li>	
				
				<li>
					<label>收款银行：</label>
					<input class="raya-input" id="payeeBankCode" name="payeeBankCode" value="ICBC" maxlength="50"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款人账号：</label>
					<input class="raya-input" id="payeeBankAcctNo" name="payeeBankAcctNo" value="4000020829200148508" maxlength="50"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款人姓名：</label>
					<input class="raya-input" id="payeeName" name="payeeName" value="邻商惕半刺尝但农酵瘟入晋率咕" maxlength="50"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款人手机号：</label>
					<input class="raya-input" id="payeeMobile" name="payeeMobile" value="13400000001" maxlength="50"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款人类型：</label>
					<select class="raya-select"  id="payeeAcctType" name="payeeAcctType" >
						<option value="1" selected="selected">个人</option>
						<option value="0" >公司</option>
					</select><span class="requireicon">*</span>
				</li>
				<li>
					<label>收款方所在城市名称：</label>
					<input class="raya-input" id="payeeCityName" name="payeeCityName" value="广东省深圳"  maxlength="50"/><span class="requireicon">*</span>
				</li>
				<li>
					<label>通知地址：</label>
					<input class="raya-input" id="noticeUrl"  name="noticeUrl"  value="" maxlength=""/><span class="requireicon">*</span>
				</li>
			</ul>
			<input class="raya-submit" value="提现" type="submit">
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