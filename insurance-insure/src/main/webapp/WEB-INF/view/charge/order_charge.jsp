
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
   <script  type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.11.3.min.js"></script>
   <script type="text/javascript" src="${ctx}/static/js/charge.js"></script> 
   <link rel="stylesheet" type="text/css" href="${ctx}/static/css/reset.css" />
   <link rel="stylesheet" type="text/css" href="${ctx}/static/css/charge/jhxcon.css" />
    <body>
     <form id="toPayForm" class="toPayForm" action="${ctx}/chargePay/openLcOrder" method="post"   onsubmit="return validate()">
          <input type="hidden" id="orderNo" name="orderNo" value="${billNo}"/>
          <input type="hidden" id="getPremium" name="premium" value="${getamout}"/>
         <section class="pay-pro-con pay-pro-con4" id="pay-pro-con4">
            <div class="con4pay">
                <span>充值单号:</span>
                <strong>${billNo}</strong>
            </div>
            <div class="con4mid1 clearfix">
                <ul>
                    <li class="right">${amout}元 </li>
                    <li class="left">充值金额</li>
                </ul>
            </div>
            <div class="con4pay con4paycon">
                <span style="color:red;">*</span>
                <span>预留手机号:</span>
                <span><input type="text" name="mobilePhone" id="mobilePhone" placeholder="请输入预留手机号" onkeyup="checkPhone();"/></span>
            </div> 
            <div class="con4pay con4paycon">
                <span>付款方式:</span>
                <span class="btn btncifpay"><span class="chose"></span></span>
                <img src="${ctx}/static/images/jhxpic.png" alt="" class="figpay-logo"/>
            </div>
            <div class="bank">
                <span style="color:red;">*</span>
                <span>开证银行:</span>
                <input type="hidden" name="bankCode" id="bankCode"/>
                <span class="bank-btn1 bank-btn" id="ICBC">
                    <span class="back-chose bank-chose"></span>
                </span>
                <img src="${ctx}/static/images/gobanklogo.png" alt="" class="bank1-logo" />
                <span class="bank-btn2 bank-btn" id="ICBC">
                    <span class="back-chose bank-chose"></span>
                </span>
                <img src="${ctx}/static/images/xybank.png" alt="" class="bank2-logo" />
                <span class="bank-btn3 bank-btn" id="ICBC">
                    <span class="back-chose bank-chose"></span>
                </span>
                <img src="${ctx}/static/images/pabank.png" alt="" class="bank3-logo" />
               
            </div>
            <div class="con4pay con4paycon" style="display:none;margin-top:5px;text-align:center;" id="errorMessage" >
               <span style="color:red;font-size:14px;">*请选择开证银行</span>
            </div>
            <div class="btn-next4">
                <button type="submit" id="next4"> 去支付</button>
                <p id="back4">返回上一步重新填写</p>
            </div>
         </section>
       </form>
        <div class="popup" id="popup">
            <div class="popup-infor">
                <h2>提示<img src="${ctx}/static/images/jhxclose.png" alt="" class="closepop"/></h2>
                <div class="popup-top">
                    <dl class="clearfix">
                        <dt><img src="${ctx}/static/images/jhxpic2.png" alt="" /></dt>
                        <dd class="part1">请您在打开的页面上完成付款</dd>
                        <dd class="part2">付款完成前请不要关闭此窗口。</dd>
                        <dd class="part3">完成付款后请根据您的情况点击下面按钮:</dd>
                    </dl>
                </div>
                <div class="popup-down clearfix">
                    <button  class="back">已完成支付</button>
                    <button  class="go closepop" >返回重新支付方式</button>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
    //充值保费支付
    function validate(){
    	var orderNo = $("#orderNo").val();
    	var premium = $("#getPremium").val();
    	var bankCode = $("#bankCode").val();
    	var mobilephone = $.trim($("#mobilePhone").val());
    	
    	if(mobilephone == ""){
    		$("#mobilePhone").css({border:"1px solid red"})
    		$("#mobilePhone").val("");
            $("#mobilePhone").attr("placeholder","请输入预留手机号");
            $("#mobilePhone").focus();  
    		return false;
    	}else{
    		 var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
    		 if (!isMobile.exec(mobilephone) && mobilephone.length != 11) {  
              	$("#mobilePhone").css({border: "1px solid red"})
      	        $("#mobilePhone").val("");
                $("#mobilePhone").attr("placeholder","请输入正确预留手机号");
                $("#mobilePhone").focus();  
            	return false;
              }
    	}
    	if(bankCode == ""){
    		$("#errorMessage").css({
    			"display":"block"
    		})
    		return false;
    	} 
    }
    function checkPhone(){
    	var mobilephone = $.trim($("#mobilePhone").val());
    	var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
    	 if (isMobile.exec(mobilephone) && mobilephone.length == 11) {  
           	 $("#mobilePhone").css({border: "1px solid #d3d3d3"})
           }
    }
    $("#back4").on("click",function(){
    	try {
    		if(window.opener){
       			 window.opener.back();
       	    }
		} catch (e) {
		}
    	
    	
    })
    </script>
</html>