<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>银信证支付</title>
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width,initial-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="../css/base.css">

</head>
<body style=" ">

<div class="nav">
    <span class="arrow fistPageGoBack" data-index="true"><em></em></span>
    
    <h1 class=""><p class="brand">银信证支付</p></h1>
</div>
<div class="nav-wrap"></div>
<div class="error-promotion-tip"></div>
<div class="grid" style="background: #f2f2f2;margin-top: 0; width: 100%; overflow: hidden">
    <div class="safety-notice">
        <img src="../images/safety-icon.png">
        您正在安全支付环境中，请放心付款
    </div>
    <div class="orderInfo">
        

        <div class="money">
            ${amountDisplay}
            <span class="fs14 black6 unit">元</span>
            
        </div>

        <div class="orderDetail">
            <div class="infoItem clearfix">
                <span class="label">收款方</span>

                <div class="infoCon">单车娱乐</div>
            </div>
            <div class="infoItem clearfix">
                <span class="label">订单编号 </span>

                <div class="infoCon">${response.orderId}</div>
            </div>
        </div>
    </div>

    <%-- form start --%>
    <c:url var="confirmTargetUrl" value="/freeze/callback" />
    <form action="${confirmTargetUrl}" autocomplete="off" method="post" id="confirmForm" class="errorMsgRequest">
		<%--交易信息 start --%>
		<input type="hidden" name="appId" value="${appId}" />
		<input type="hidden" name="lcID" value="${response.lcId}" />
		<input type="hidden" name="charset" value="${charset}" />
    	<ul class="form-wrap">
            <li class="form-item clearfix">
                <label>银行卡号</label>
                <div class="form-field">
	                <select style="height:44px;line-height:44px;width:100%" class="J-check-input J-check-bank-num">
	                	<option selected="selected">62223631608004140033</option>
	                </select>
	                
<!--                     <input type="text" id="J-bank-input" class="J-check-input J-check-bank-num " name="bankCardNum" data-checknum="true" value="6222 2264 1234 5647 335" placeholder="请输入持卡人银行卡号" maxlength="24" data-format="bank" data-callback="input.status" data-open-clear="false" data-passed="true"> -->
<!--                     <span class="clear-btn J-clear-btn" style="display: none;">×</span> -->
<!--                     <div class="error-info animate" data-info="银行卡号格式错误"><em></em>银行卡号格式错误</div> -->
                </div>
            </li>
            <li class="form-item clearfix">
                <label>支付密码</label>
                <div class="form-field">
                    <input style="padding-left:4px" type="password" id="bankPassword" class="J-check-input J-check-bank-num " name="bankCardNum" value="123456" data-checknum="true" value="" placeholder="请输入支付密码" maxlength="6" data-format="bank" data-callback="input.status" data-open-clear="false" data-passed="true">
                    <span class="clear-btn J-clear-btn" style="display: none;">×</span>
                    <div class="error-info animate" data-info="支付密码错误"><em></em>支付密码错误</div>
                </div>
            </li>
            <%-- 
            

            <li class="form-item clearfix">
                <label>手机号码</label>
                <div class="form-field">
                    <input type="tel" id="J-mobile-input" class="J-check-input" name="mobile" value="" placeholder="银行预留手机号" maxlength="11" data-format="mobile" data-callback="input.status" data-open-clear="false" data-passed="true">
                    <span class="clear-btn J-clear-btn" style="display: none;">×</span>
                    <span class="info-btn J-info-btn" data-show="modal-mobile" clstag="wyplus|A|mobilebox"></span>
                    <div class="error-info animate" data-info="手机号格式错误"><em></em>手机号格式错误</div>
                </div>
            </li> 
            --%>

    	</ul>
	</form>
    <%-- form end --%>
</div>
<div class="grid94 pd40">
    <span id="J-next-btn" class="btn btn-disabled mt15 btn-actived" data-enabled="true">确认付款</span>
</div>

<!--popbox style start-->
<div class="md-modal md-effect-1" id="modal-mobile">
    <div class="md-content">
        <span class="md-close"></span>
        <h3>手机号说明</h3>
        <div class="content"><p>银行预留手机号是你在办理该银行卡时填写的手机号。</p><p>没有预留、手机号码已忘记、或者已停用可联系银行客服更新处理。</p></div>
    </div>
</div>
<!--popbox style end-->

<div class="fs12 black9 aligncenter bottomService" style="position:absolute; line-height:16px;bottom:4px;width:94%; padding:0 3%;">
      如需解绑银行卡，请使用手机号登录钱包完成操作。若有其他问题，请咨询 4000988511
</div>
<script src="../js/zepto.js"></script>
<script src="../js/base.js"></script>
<div class="mark hidden" id="J-mark"></div>
<div class="mark mark-gray hidden" id="J-mark-gray"></div>
<div id="J-loading" class="hidden">
<img src="../images/loading.png">
  <div class="barWrap">
    <span class="barlittle bar1"></span><span class="barlittle bar2"></span><span class="barlittle bar3"></span><span class="barlittle bar4"></span><span class="barlittle bar5"></span>
  </div>
</div>

<script>
  $('#J-next-btn').click(function(){
	  var passwd = $('#bankPassword').val();
	  
	  if (passwd == null || passwd == undefined || passwd == '') {
		  alert('请输入银行密码!');
		  return false;
	  } 
	  var regExp=/^\d+(\.\d+)?$/;
	  if (passwd.length != 6 || !regExp.test(passwd)) {
		  alert('请输入正确的银行密码!');
		  return false;
	  } 
	  $('#confirmForm')[0].submit();
  });
</script>

<input type="hidden" id="errorMsgInput" value="">
<div class="pop-help-wrap"></div>
<span id="goback-trigger"></span>

</body>
</html>