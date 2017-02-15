<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE >
<html>
<head>
    <%
        pageContext.setAttribute("title", "银信证交易测试-收证接口");
    %>
    <%@include file="head.jsp" %>

    <script type="text/javascript">
        $(function () {
            //$("#orderId").val(Math.ceil(Math.random()*10000000000));
            initURL();
        });

        $(function () {
            setValidTime("recvValidTime", 1);
            setValidTime("sendValidTime", 8);
            setValidTime("confirmPayValidTime", 15);
        });

        function setValidTime(id, num) {
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
        function initURL() {
            var returnUrl = "<c:out value='/success.jsp'/>";
            var noticeUrl = "<c:out value='/notice'/>";
            var merOrderUrl = "<c:out value='/merOrder.jsp'/>";
            var hostInfo = window.location.protocol + "//" + window.location.host;
            $('#returnUrl').val(hostInfo + returnUrl);
            $('#noticeUrl').val(hostInfo + noticeUrl);
            $('#merOrderUrl').val(hostInfo + merOrderUrl);
        }
    </script>
</head>
<body onload="initURL()">
<!--头部-->
<div class="header">
    <div class="logo"><img src="<c:url value="/resources/img/logo.png" />"></div>
</div>

<!--内容-->
<div class="container">
    <p class="raya-firstpay">收证接口测试页面(<span class="requireicon">*</span>标记为必填参数)</p>
    <div class="raya-ordertable">
        <form action="<c:url value="/lc/recv.lc" />" method="post" target="_blank" onsubmit="return validate()">
            <ul>
                <li>
                    <label>银信证ID：</label>
                    <input class="raya-input" type="text" id="lcId" name="lcId" value="${param.lcId}" maxlength="20" required/> <span class="requireicon">*</span>
                </li>
                <li>
                    <label>收证人类型：</label>
                    <select class="raya-select" id="recvAccountType" name="recvAccountType" required>
                        <option value="1" selected="selected">个人</option>
                        <option value="2">企业</option>
                    </select><span class="requireicon">*</span>
                </li>
                <li>
                    <label>收证银行：</label>
                    <input class="raya-input" id="recvBankCode" name="recvBankCode" value="UNION" maxlength="16" required/> <span class="requireicon">*</span>
                </li>
                <li>
                    <label>收证账号：</label>
                    <input class="raya-input" id="recvBankAccountNo" name="recvBankAccountNo" value="${param.recvBankAccountNo}" maxlength="30" required/> <span class="requireicon">*</span>
                </li>
                <li>
                    <label>备注：</label>
                    <input class="raya-input" id="remark" name="remark" value="${param.lcNo }" maxlength="200"/>
                </li>
            </ul>
            <input class="raya-submit" value="确认收证" type="submit">
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