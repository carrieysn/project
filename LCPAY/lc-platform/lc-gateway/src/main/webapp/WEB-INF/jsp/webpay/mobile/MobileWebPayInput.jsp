<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="cssPath1" value="/resources/css/webpay/mobile/reset.css?v=20160726B" />
<c:url var="cssPath2" value="/resources/css/webpay/mobile/style.css?v=20160726B" />
<c:url var="zeptoAlertCss" value="/resources/css/zepto/zepto.alert.css?v=20160726B" />

<c:url var="cBankLogoPng" value="/img/webpay/mobile/banklogo/${hiddenParams.defaultCBankCode}.png" />
<c:url var="mBankLogoPng" value="/img/webpay/mobile/banklogo/${hiddenParams.mBankCode}.png" />

<c:url var="prevBtnPng" value="/resources/img/webpay/mobile/prev.png" />
<c:url var="zeptoJs" value="/resources/js/zepto/zepto.min.js" />
<c:url var="zeptoAlertJs" value="/resources/js/zepto/zepto.alert.js" />
<c:url var="sendSmsCodeApiUrl" value="/webpay/mobile/sendSmsCode" />
<c:url var="confirmPayApiUrl" value="/webpay/mobile/confirmpay" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width,height=device-height">
	<title>银信证支付</title>
	<link rel="stylesheet" href="${cssPath1}">
	<link rel="stylesheet" href="${cssPath2}">
	<link rel="stylesheet" href="${zeptoAlertCss}">

	<script>
		// 重置页面 rem 单位基准值
		function setHtmlFontSize() {
			var clientWidth = document.documentElement.clientWidth>720?720:document.documentElement.clientWidth;
		//    document.documentElement.style.fontSize = document.documentElement.clientWidth / 3.75 + 'px'
		console.log(clientWidth / 3.75 + 'px');
			document.documentElement.style.fontSize = clientWidth / 7.2 + 'px'
		}
		setHtmlFontSize()

		window.addEventListener('resize', setHtmlFontSize, false);
	</script>
</head>
<body>
	<div class="zcx-bar">
		<h1 class="title">银信证支付</h1>
		<a href="javascript:history.back()" class="button"><img src="${prevBtnPng}" alt=""></a>
	</div>
	<div class="zcx-cardwrapper">
		<div class="logowrap"></div>
		<div class="introWrap">
			<h3>银行记账式全新消费体验</h3>
			<p class="ellipsis">购物消费只需在自己银行账户记账，货到满意银行再帮你付款</p>
		</div>
		<div class="moneywrap">
			<dl>
				<dt>人民币</dt>
				<dd class='num'><span class='big'>&yen;${amountIntegerPart}</span>.${amountFractionPart}</dd>
			</dl>
			<dl>
				<dt class='elli'>(大写)</dt>
				<dd class='uppercase elli'>${chineseAmount}</dd>
			</dl>
		</div>
		<div class="bankwrap clear">
			<div class="cont fl">
				<p class="identity">委托记账付款银行</p>
				<div class="banklogo">
					<img src="${cBankLogoPng}">
				</div>
				<div class="fu statu">待记账付款</div>
				<div class="other red">
					付款人： ${hiddenParams.defaultCMobile}<br>
					最晚确认时间： ${hiddenParams.lastConfirmTime}
				</div>
			</div>
			<div class="cont fr">
				<p class="identity">委托记账收款银行</p>
				<%-- 
				<div class="banklogo empty">
					<!-- <img src="${mBankLogoPng}"> -->
				</div>
				--%>
				<div class="banklogo">
					<img src="${mBankLogoPng}">
				</div>
				<div class="shou statu">待记账收款</div>
				<div class="other blue">
					<%-- 操作时效：20小时18分钟<br> --%>
					收款人户名：${hiddenParams.mBankAccountName}<br>
					收款人账号：${markedMBankAccountNo}
					<%-- 关联订单：${hiddenParams.orderNo} --%>
				</div>
			</div>
		</div>
		<ul class="stepwrap">
			<li class="step step1 on">收付双方记账</li>
			<li class="step step2">收款</li>
			<li class="step step3">划款到账</li>
			<li class="intro stepintro1" style="display: none;">
				<h3>收付双方记账信息</h3>
				<%-- 
				<dl>
					<dt>5月9号 22:04:28</dt>
					<dd>记账付款,记账人：张三</dd>
					<dt>5月9号 22:04:28</dt>
					<dd>设置收款期限，期限时长：7天</dd>
					<dt>5月9号 22:04:28</dt>
					<dd>记账收款，记账人：李四</dd>
				</dl>
				--%>
			</li>
			<li class="intro stepintro2" style="display: none;">
				<h3>付款信息</h3>
				<%--
				<dl>
					<dt>5月9号 22:04:28</dt>
					<dd>记账付款,记账人：张三</dd>
					<dt>5月9号 22:04:28</dt>
					<dd>设置收款期限，期限时长：7天</dd>
					<dt>5月9号 22:04:28</dt>
					<dd>记账收款，记账人：李四</dd>
				</dl>
				--%>
			</li>
			<li class="intro stepintro3" style="display: none;">
				<h3>划款到账</h3>
				<%--
				<dl>
					<dt>5月9号 22:04:28</dt>
					<dd>记账付款,记账人：张三</dd>
					<dt>5月9号 22:04:28</dt>
					<dd>设置收款期限，期限时长：7天</dd>
					<dt>5月9号 22:04:28</dt>
					<dd>记账收款，记账人：李四</dd>
				</dl>
				--%>
			</li>
		</ul>
		<p class="stepintro">付款人和收款人分别完成记账</p>
		<div class="footerwrap">
			<div class="bank">
				发出银行：
				<img src="${cBankLogoPng}">
			</div>
			<div class="cardcode">${cBankName} CP200</div>
		</div>
		<div class="paymentwrap">
		<c:url var="inputFormUrl" value="/webpay/mobile/confirm" />
		<form id="inputForm" action="${inputFormUrl}" method="post">
			<input type="hidden" id="merchantId" name="merchantId" value="${hiddenParams.merchantId}" />
			<input type="hidden" id="orderNo" name="orderNo" value="${hiddenParams.orderNo}" />
			<input type="hidden" id="tradeName" name="tradeName" value="${hiddenParams.tradeName}" />
			<input type="hidden" id="amount" name="amount" value="${hiddenParams.amount}" />
			<input type="hidden" id="currency" name="currency" value="${hiddenParams.currency}" />
			<input type="hidden" id="mBankAccountNo" name="mBankAccountNo" value="${hiddenParams.mBankAccountNo}" />
			<input type="hidden" id="mBankAccountName" name="mBankAccountName" value="${hiddenParams.mBankAccountName}" />
			<input type="hidden" id="mBankCode" name="mBankCode" value="${hiddenParams.mBankCode}" />
			<input type="hidden" id="defaultCBankAccountNo" name="defaultCBankAccountNo" value="${hiddenParams.defaultCBankAccountNo}" />
			<input type="hidden" id="defaultCBankCode" name="defaultCBankCode" value="${hiddenParams.defaultCBankCode}" />
			<input type="hidden" id="defaultCMobile" name="defaultCMobile" value="${hiddenParams.defaultCMobile}" />
			<input type="hidden" id="lastConfirmTime" name="lastConfirmTime" value="${hiddenParams.lastConfirmTime}" />
			<input type="hidden" id="returnUrl" name="returnUrl" value="${hiddenParams.returnUrl}" />
			<input type="hidden" id="notifyUrl" name="notifyUrl" value="${hiddenParams.notifyUrl}" />
			<input type="hidden" id="hiddenParamsSign" name="hiddenParamsSign" value="${hiddenParamsSign}" />
			
			<h3>记账付款</h3>
			<div class="formgroup">
				<span>银行账号</span>
				<input type="number" id="cBankAccountNo" name="cBankAccountNo" value="${hiddenParams.defaultCBankAccountNo}" maxlength="19" />
			</div>
			<div class="formgroup">
				<span>手机号码</span>
				<input type="tel" id="smsCodeMobile" name="smsCodeMobile" value="${hiddenParams.defaultCMobile}" placeholder='请填写银行预留手机号' maxlength="11">
			</div>
			<div class="formgroup">
				<span>验证码</span>
				<input type="number" id="smsCode" name="smsCode" maxlength="5" />
				<a href="javascript:;" class="code">发送验证码</a>
			</div>
			<div class="formgroup">
				<a href="javascript:;" class="button cancel fl">取消</a>
				<a href="javascript:;" class="button confirm fr">确定</a>
				<span class="showtips">验证失败</span>
			</div>
		</form>
		</div>
	</div>
	<form id="returnMerchantForm" action="${hiddenParams.returnUrl}" method="${returnMerchantUrlMethod}">
		<input type="hidden" name="returnCode" value="" />
		<input type="hidden" name="lcId" value="" />
		<input type="hidden" name="orderNo" value="" />
		<input type="hidden" name="amount" value="" />
	</form>
	<script src='${zeptoJs}'></script>
	<script src='${zeptoAlertJs}'></script>
	<script>
	window.smsCodeSentFlag = false;
	$(".stepwrap .step").on("click",function(){
		var idx = ($(this).index() + 1);
		var cls = 'intro' + idx;
		var selector = 'stepintro' + idx;
		var ele = $('.' + selector).eq(0);
		ele.siblings('.intro').hide();
		ele.get(0).className = 'intro ' + cls + ' ' + selector;
		if(!!~ele.css('display').indexOf('none')){
			ele.show();
		}else{
			ele.hide();
		}
	});
	
	$('.fu').on('click',function(){
		$('.paymentwrap').show();
	});
	
	$('.paymentwrap .cancel').on('click',function(){
		$('.paymentwrap').hide();
	});
	
	$('.paymentwrap .code').on('click',function(){
		var diglogWidth = $('.paymentwrap');
		if(!$.trim($("#cBankAccountNo").val())) {
	        $.dialog({
	            content : '请输入银行账号',
	            title: "操作错误提示",
				width: diglogWidth,
                ok : function() {
                },
                lock : true
	        });
	        return false;
		} else if(!$.trim($("#smsCodeMobile").val())) {
	        $.dialog({
	            content : '请输入手机号码',
	            title: "操作错误提示",
				width: diglogWidth,
                ok : function() {
                },
                lock : true
	        });
	        return false;
		}
		
		$.ajax(
		{
			type: 'post',
			url: '${sendSmsCodeApiUrl}', 
			dataType: 'json',
			data:{ 
				merchantId: $("#merchantId").val(),
				orderNo: $("#orderNo").val(),
				tradeName: $("#tradeName").val(),
				amount: $("#amount").val(),
				currency: $("#currency").val(),
				defaultCBankAccountNo: $("#defaultCBankAccountNo").val(),
				defaultCBankCode: $("#defaultCBankCode").val(),
				defaultCMobile: $("#defaultCMobile").val(),
				mBankAccountNo: $("#mBankAccountNo").val(),
				mBankAccountName: $("#mBankAccountName").val(),
				mBankCode: $("#mBankCode").val(),
				mobile: $("#mobile").val(),
				lastConfirmTime: $("#lastConfirmTime").val(),
				cBankAccountNo: $("#cBankAccountNo").val(),
				smsCodeMobile: $("#smsCodeMobile").val(),
				returnUrl: $("#returnUrl").val(),
				notifyUrl: $("#notifyUrl").val(),
				hiddenParamsSign: $("#hiddenParamsSign").val()
			}, 
			success: function(data, status, xhr){
				if("success" == data.returnCode) {
					window.smsCodeSentFlag = true;
					var updatedHiddenParamsSign = data.updatedHiddenParamsSign;
					$("#hiddenParamsSign").val(updatedHiddenParamsSign);
					$(".paymentwrap .code").html("验证码已发送");
					$(".paymentwrap .code").off();
					$("#smsCode").focus();
					var diglogWidth = $('.paymentwrap');
			        $.dialog({
			            content : '验证码已成功通过短信发送至' + $("#smsCodeMobile").val(),
			            title: "验证码已发送",
						width: diglogWidth,
			            time : 1000
			        });
				} else {
		            $.dialog({
	                    content : "" + data.returnMsg,
	                    title : '短信发送失败',
	                    ok : function() {
	                    },
	                    lock : true
	                });
				}
			},
			error: function(xhr, errorType, error){
	            $.dialog({
                    content : "短信验证码发送失败！" + error,
                    title : '短信验证码发送失败',
                    ok : function() {
                    },
                    lock : true
                });
			}
		});
	});
	
	$('.paymentwrap .confirm').on('click',function(){
		var diglogWidth = $('.paymentwrap');
		
		if(!window.smsCodeSentFlag) {
	        $.dialog({
	            content : '请先点击获取短信验证码',
	            title: "操作错误提示",
				width: diglogWidth,
                ok : function() {
                },
                lock : true
	        });
	        return false;
		} else if(!$.trim($("#cBankAccountNo").val())) {
	        $.dialog({
	            content : '请输入银行账号',
	            title: "操作错误提示",
				width: diglogWidth,
                ok : function() {
                },
                lock : true
	        });
	        return false;
		} else if(!$.trim($("#smsCodeMobile").val())) {
	        $.dialog({
	            content : '请输入手机号码',
	            title: "操作错误提示",
				width: diglogWidth,
                ok : function() {
                },
                lock : true
	        });
	        return false;
		} else if(!$.trim($("#smsCode").val())) {
	        $.dialog({
	            content : '请输入验证码',
	            title: "操作错误提示",
				width: diglogWidth,
                ok : function() {
                },
                lock : true
	        });
	        return false;
		}
		
		$.ajax(
		{
			type: 'post',
			url: '${confirmPayApiUrl}',
			dataType: 'json',
			data: { 
				merchantId: $("#merchantId").val(),
				orderNo: $("#orderNo").val(),
				tradeName: $("#tradeName").val(),
				amount: $("#amount").val(),
				currency: $("#currency").val(),
				defaultCBankAccountNo: $("#defaultCBankAccountNo").val(),
				defaultCBankCode: $("#defaultCBankCode").val(),
				defaultCMobile: $("#defaultCMobile").val(),
				mBankAccountNo: $("#mBankAccountNo").val(),
				mBankAccountName: $("#mBankAccountName").val(),
				mBankCode: $("#mBankCode").val(),
				mobile: $("#mobile").val(),
				lastConfirmTime: $("#lastConfirmTime").val(),
				smsCodeMobile: $("#smsCodeMobile").val(),
				returnUrl: $("#returnUrl").val(),
				notifyUrl: $("#notifyUrl").val(),
				cBankAccountNo: $("#cBankAccountNo").val(),
				smsCode: $("#smsCode").val(),
				hiddenParamsSign: $("#hiddenParamsSign").val()
			},
			success: function(data, status, xhr){
				if('success' == data.returnCode) {
					var lcId = data.lcId;
					var orderNo = data.orderNo;
					var amount = data.amount;
					$("#returnMerchantForm input[name=returnCode]").val("success");
					$("#returnMerchantForm input[name=lcId]").val(lcId);
					$("#returnMerchantForm input[name=orderNo]").val(orderNo);
					$("#returnMerchantForm input[name=amount]").val(amount);
					$('.paymentwrap').hide();
			        $.dialog({
			            content : '支付成功！交易流水号：' + lcId + '，3秒后自动返回至商户网站...',
			            title: "支付成功",
						width: diglogWidth,
			            time : 3000,
			            timeHandler: function(){
			            	var returnFormUrl = $("#returnMerchantForm").attr("action");
			            	if(returnFormUrl.indexOf('#') > 0) {
			            		var redirectUrl = returnFormUrl + '?returnCode=' + data.returnCode + '&lcId=' + lcId + '&orderNo=' + orderNo + '&amount=' + amount;
			            		window.location.href = redirectUrl;
			            	} else {
			            		$("#returnMerchantForm").submit();
			            	}
			            }
			        });
				} else {
		            $.dialog({
	                    content : "支付失败！[" + data.returnMsg + "]",
	                    title : '支付失败',
	                    ok : function() {
	                    },
	                    lock : true
	                });
				}
			},
			error: function(xhr, errorType, error){
	            $.dialog({
                    content : "支付失败！" + error,
                    title : '支付失败',
                    ok : function() {
                    },
                    lock : true
                });
			}
		});
	});
	</script>
</body>
</html>