<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        pageContext.setAttribute("title", "银信证交易测试");
    %>
    <%@include file="head.jsp" %>

    <script type="text/javascript">
        $(function () {
            if ("${param.startTime }" == "") {
                setSearchTime("startTime", 0);
                setSearchTime("endTime", 0);
            }

        });

        function setSearchTime(id, num) {
            var date = new Date();
            date.setDate(date.getDate() + num);
            var years = date.getFullYear();//获取年
            var months = date.getMonth() + 1;//获取月

            if (months < 10) {//当月份不满10的时候前面补0，例如09
                months = '0' + months;
            }

            var day = date.getDate();//获取日
            if (day < 10) {//当日期不满10的时候前面补0，例如09
                day = '0' + day;
            }
            $("#" + id).val(years + "-" + months + "-" + day);
        }
    </script>
</head>
<body>
<!--头部-->
<div class="header">
    <div class="logo"><img src="<c:url value="/resources/img/logo.png" />"></div>
</div>
<!--内容-->
<div class="container">
    <div class="raya-top">
        <div align="left" style="font-size: large;">
            <a href="<c:url value="/page/lcList" />" target="_blank">银信证列表</a><br>
            <a href="<c:url value="/page/preOpenLc" />" target="_blank">预开证</a><br>
            <a href="<c:url value="/page/openLc" />" target="_blank">开证</a><br>
            <a href="<c:url value="/page/payment" />" target="_blank">银联预开证</a><br>
            <a href="<c:url value="/page/recvLc" />" target="_blank">收证</a><br>
            <a href="<c:url value="/page/appointmentLc" />" target="_blank">履约</a><br>
            <a href="<c:url value="/page/deferLc" />" target="_blank">展期</a><br>
            <a href="<c:url value="/page/applyLc" />" target="_blank">申请解付</a><br>
            <a href="<c:url value="/page/suspendLc" />" target="_blank">暂停</a><br>
            <a href="<c:url value="/page/transferLc" />" target="_blank">解付转账</a><br>
            <a href="<c:url value="/page/invalidateLc" />" target="_blank">失效</a><br>
            <%--<a href="refundLc" target="_blank">商户退款</a><br>--%>
            <a href="<c:url value="/page/batchOpen" />" target="_blank">批量开证</a><br>
            <a href="<c:url value="/page/batchApply" />" target="_blank">批量提现</a><br>

            <a href="<c:url value="/page/batchOpen" />" target="_blank">星意-开证</a><br>
            <a href="<c:url value="/page/batchApply" />" target="_blank">星意-提现</a><br>

            <hr/>
            <a href="<c:url value="/page/xyRecharge" />" target="_blank">星意-充值</a><br>
            <a href="<c:url value="/page/xyOffline" />" target="_blank">星意-企业线下转帐</a><br>
            <a href="getFundServlet" target="_blank">星意-提现</a><br>
            <%--
            <hr/>
            <c:url var="billcenterOrderSettlementUrl" value="/billcenter/order/settlement"/>
            <a href="${billcenterOrderSettlementUrl}" target="_blank">账单中心-支付</a><br>
            --%>
            <hr/>
            <a href="<c:url value="/page/smsSend" />" target="_blank">发送短信</a><br>
            <a href="<c:url value="/page/smsCheck" />" target="_blank">验证短信验证码</a><br>
            <a href="<c:url value="/page/cache" />" target="_blank">缓存</a><br>
        </div>
    </div>
</div>
<!--尾部-->
<div class="footer">
    深圳市银信网银科技有限公司 2016 CIFPAY.all Rights Reservde Tel 0755-3368898
</div>


<%-- <div>
    <span class="pageInfo">总记录数: ${total}</span>
    <span>当前第${page }/${totalPage}页</span>
    <span><a href="javascript:void(0)" onclick="chagePage(-1)">上一页</a></span>
    <span><a href="javascript:void(0)" onclick="chagePage(1)">下一页</a></span>
  </div> --%>
</body>
</html>