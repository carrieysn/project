<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta name="renderer" content="webkit"/>
        <title>交还险</title>
        <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/tb/reset.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/header.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/tb/jhxcon.css" /> 
    </head>
    <body>
       <jsp:include page="header.jsp" flush="true"/>
        <section class="pro-con">
       <form id="doInsureForm" class="doInsureForm">
            <h2><img src="${ctx}/static/images/jianjiao_03.png" alt="" /><a href="${ctx}">返回保险详情页</a></h2>
            <section class="pay-pro">
                <div class="registor">
                    <ul>
                        <li class="bac0277bd">录入投保信息</li>
                        <li>录入投保人信息</li>
                        <li>预览投保信息</li>
                        <li>支付保费</li>
                        <li>完成投保</li>
                    </ul>
                </div>
            </section>
            <section class="pay-pro-con pay-pro-con1" id="pay-pro-con1">
                <div class="pay-part pay-part1">
                    <em>保险期限：</em>
                    <section>
                        <input type="hidden" name = "insurancePeriod" id="insurePeriod" value=""/> 
                       <!--  <span class="addcls"></span> -->
                        <span></span>
                        <strong id="1" >1个月</strong>
                        <span></span>
                        <strong id="3">3个月</strong>
                        <span></span>
                        <strong id="6">6个月</strong>
                        <span></span>
                        <strong id="12">12个月</strong>
                    </section>
                </div>
               <div class="pay-part pay-part1" style="height: 20px; line-height: 20px; display: none;" id="errorDiv">
                    <section>
                        <strong style="color:red;font-size:12px;">*请选择保险期限</strong>
                    </section>
                </div>
                <div class="pay-part pay-part2">
                    <em>保费：</em>
                    <section>
                       <input type="text"  placeholder="请输入保费金额"  id="payMuch" name="premium" maxlength="8" onfocus="checkholder(this);" onblur="changeshow(this);"/>
                       <label for="">元</label>
                       <div class="pay-num" >*投保金额必须大于等于1万元</div>
                       <div class="pay-much"></div>
                    </section>
                </div>
                <div class="btn1-rel">
                    <input type="hidden" value="投保人销售的且符合交还险保险条款的商品" name="insuredid"/>
                    <p>保险标的：投保人销售的且符合交还险保险条款的商品
                    </p>
                </div>
                <div class="btn-next1">
                    <button type="button" id="next1">下一步</button>
                </div>
            </section>
            <section class="pay-pro-con pay-pro-con2" id="pay-pro-con2">
                <div class="pay-part pay-part1 pay-part10" >
                    <em>投保人类型:</em>
                    <section>
                        <input type="hidden" name="policyHolderInfo.holderType" value="" id="holderType"/>
                        <span id="paypart100span"></span>
                        <strong id="1">企业</strong>
                        <span class="" id="paypart10span"></span>
                        <strong id="2">个人</strong>
                    </section>
                   
                </div>
                <div class="policy policy-holder errorshow" id="holderErrorFlag" style="text-align: left;padding-left: 105px; margin-bottom: 0px; height: 20px; padding-bottom: 2px; display: none;">
                    <span style = "color:red;font-size:12px;">*请选择投保人类型</span>
                </div>
                <div class="policy policy-holder">
                    <label for="holderName"><span>*</span>投保人:</label>
                    <input type="text"  name="policyHolderInfo.holderName" id="holderName" maxlength="50"  placeholder=""   onfocus="checkholder(this);"/>
                    <span class="errorTag" id="holderNameError"></span>
                </div>
                <div class="policy IDcard">
                    <div>
                        <span><i>*</i>证件类型:</span>
                        <input type="hidden"  name="policyHolderInfo.idType" value="" id="idType"/>
                        <ul id="IDcardUl">
                            <li class="first"></li>
                            <li class="" id="li1">机构代码</li>
                            <li id="li2">身份证</li>
                        </ul>
                        <img src="${ctx}/static/images/jhxpop.png" alt="" id="IDcardImg" />
                    </div>
                </div>
                <div class="policy policy-holder">
                    <label for=""><span>*</span>证件号:</label>
                    <input type="text" name="policyHolderInfo.idNo" id="idNo"  maxlength="50" placeholder=""   onfocus="checkholder(this);"/>
                    <span class="errorTag" id="idNoError"></span>
                </div>
                <div class="policy policy-holder">
                    <label for=""><span>*</span>联系人:</label>
                    <input type="text" name="policyHolderInfo.contacts" id="contacts" maxlength="5" placeholder=""   onfocus="checkholder(this);" />
                    <span class="errorTag" id="contactsError"></span>
                </div>
                <div class="policy policy-holder">
                    <label for=""><span>*</span>联系电话:</label>
                    <input type="text" name="policyHolderInfo.phone" id="phone"  placeholder=""  onfocus="checkholder(this);"/>
                    <span class="errorTag"  id="phoneError"></span>
                </div>
                 <div class="policy policy-holder errorshow" id="holderphoneError" style="text-align: left;padding-left: 105px; margin-bottom: 0px; height: 20px; padding-bottom: 2px; display:none;">
                    <span style = "color:red;font-size:12px;">*请输入正确电话号码,例如：0755-829***66，138****3890</span>
                </div>
                <div class="policy policy-holder">
                    <label for=""><span>*</span>邮箱:</label>
                    <input type="text" name="policyHolderInfo.email" id="email"  maxlength="50" placeholder=""   onfocus="checkholder(this);"/>
                    <span class="errorTag"  id="emailError"></span>
                </div>
                 <div class="policy policy-holder errorshow" id="holderemailError" style="text-align: left;padding-left: 105px; margin-bottom: 0px; height: 20px; padding-bottom: 2px; display:none;">
                    <span style = "color:red;font-size:12px;">*请输入正确邮箱</span>
                </div>
                <div class="btn-next2">
                    <button type="button" id="next2"  >下一步</button>
                    <p id="back2">返回上一步重新填写</p>
                </div>
            </section>
            <section class="pay-pro-con pay-pro-con3" id="pay-pro-con3">
                <div class="conpad">
                    <h2>信息预览</h2>
                    <div class="content">
                        <div class="content-top">
                            <h3>投保信息</h3>
                            <ul>
                                <li><span>保险期限:</span><i id="periodCofirm"></i></li>
                                <li>
                                    <div style="float:left;">
	                                	<span>保费:</span><i class="much" id="premiumConfirm"></i>
	                                </div>
	                                <strong id="bignum" style="margin-left:30px;"></strong>
                                </li>
                                <li><span>保险标的:</span><i title="投保人销售的且符合交还险保险条款的商品">投保人销售的且符合交还险保险条款的商品</i></li>
                            </ul>
                        </div>
                        <div class="content-top">
                            <h3>投保人信息</h3>
                            <ul class="content-top1">
                                <li><span class="dateleft">投保人类型:</span><i  id="holderTypeConfirm"></i></li>
                                <li>
		                                <span class="dateright" style="float:left;">投保人名称:</span>
		                                <i id="holderNameConfirm" style="width:406px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float:left;height:30px;line-height:30px;"></i>
                                </li>
                                <li><span class="dateleft">证件类型:</span><i id="idTypeConfirm"></i></li>
                                <li>
		                                <span class="dateright" style="float:left;">证件号:</span>
		                                <i id="idNoConfirm" style="width:406px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float:left;height:30px;line-height:30px;"></i>
                                </li>
                                <li>
		                                <span class="dateleft" style="float:left;">联系人:</span>
		                                <i id="contactsConfirm" style="width:406px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float:left;height:30px;line-height:30px;"></i>
                                </li>
                                <li><span class="dateright">联系电话:</span><i id="phoneConfirm"></i></li>
                                <li>
		                                <span class="dateleft" style="float:left;">联系邮箱:</span>
		                                <i id="emailConfirm" style="width:406px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;float:left;height:30px;line-height:30px;" ></i>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="btn-next3"><button type="button" id="next3" >下一步</button>
                    <p id="back3">返回上一步重新填写</p>
                </div>
            </section>
            </form>
            
            
            
            
            <form id="toPayForm" class="toPayForm" action="${ctx}/toPay/openLcOrder" method="post"  target="_blank" onsubmit="return validate()">
             <section class="pay-pro-con pay-pro-con4" id="pay-pro-con4">
             <input type="hidden" name="orderNo" id="orderNo"/>
                <div class="con4pay">
                    <span>订单号:</span>
                    <strong id="chargeBillNoShow"></strong>
                </div>
                <div class="con4mid1 clearfix">
                    <ul>
                        <li class="right">保费（元）</li>
                        <li class="left">险种</li>
                         <input type="hidden" name="premium" id="getPremium"/>
                        <li class="right" id="premiumShow"></li>
                        <li class="left" id="insureNameShow"></li>
                    </ul>
                </div>
                <div class="con4pay con4paycon">
	                <span style="color:red;">*</span>
	                <span>预留手机号:</span>
	                <span><input type="text" name="mobilePhone" id="mobilePhone" placeholder="" onkeyup="getCheckMobile();"  onfocus="checkholder(this);" onblur="changeshow(this);"/></span>
                </div> 
                 <div class="con4pay con4paycon" style="margin-top:0px;padding-left:100px;display:none;" id="bankmobileError">
                     <span style="color:red;font-size:12px;">*请输入正确预留手机号</span>
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
                    <button type="submit" id="next4">去支付</button>
                   <!--  <p id="back4">返回上一步重新填写</p> -->
                </div>
             </section>
             </form>
             <section class="content5" id="content5" >
                
                 <div class="small-logo"></div>
                 <div id="showPolicyInfo" style="display:none;">
                 <div class="congra">恭喜您已投保成功！</div>
                 <div class="congra-mid">预缴保单号为:<span id="payPolicyNo"></span></div>
                 <div class="congra-footer">您当前获得保额为<label id="amountInfo"></label>的保障，对应的保费保额杠杆比为1:1。为了方便保险证管理，请下载<span>保险证生成器</span>安装使用</div>
                 <div class="infor-content">
                     <h2>保险单信息</h2>
                     <div class="infor-con-mid">
                         <div class="infor-con-mid1">
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>险种名称:</span>
                                         <strong id="payProduct"></strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>预缴保单号:</span>
                                         <strong id="payOrderNo"></strong>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>预交保费:</span>
                                         <strong id="payPremium"></strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>保额:</span>
                                         <strong id="payAmount"></strong>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>预缴保单有效时间:</span>
                                         <strong id="payUseTime"></strong>
                                     </div>
                                 </li>
                             </ul>
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>投保人名称:</span>
                                         <strong id="payHolderName"></strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>投保人类型:</span>
                                         <strong id="payHolderType"></strong>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>证件类型:</span>
                                         <strong id="payIdType"></strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>证件号码:</span>
                                         <strong id="payIdNo"></strong>
                                     </div>
                                 </li>
                             </ul>
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>被保险人:</span>
                                         <strong id="payBuyer"></strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>保费缴费方式:</span>
                                         <strong id="payType"></strong>
                                     </div>
                                 </li>
                             </ul>
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>保险人名称:</span>
                                         <strong id="payInsureName"></strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>保险公司服务热线:</span>
                                         <strong id="payInsureHotline"></strong>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>保险人地址:</span>
                                         <strong id="payInsureAddress"></strong>
                                     </div>
                                 </li>
                             </ul>
                         </div>
                     </div>
                 </div>
                 </div>
                 <div class="dowload">
	                     <h2>下载保险证生成器</h2>
	                     <div class="download-con">
	                     <input type="hidden"  id="fileNamedown" value="insurecert-generator.zip"/>
	                         <dl>
	                             <dt><img src="${ctx}/static/images/jhx07.png" alt="" /></dt>
	                             <dd>
	                                 <h3>互联网保险创新产品</h3>
	                                 <div class="pace">让交易从此放心</div>
	                                 <div class="down-btn">
	                                     <button type="button" onclick="downloadFile();">下载保险证生成器</button>
	                                 </div>
	                                 <div class="date">更新日期:<span>2016-01-28  V1.0</span></div>
	                                 <div class="date">文件大小:<span>20MB</span></div>
	                             </dd>
	                         </dl>
	                     </div>
                 </div>
             </section>
        </section>
        <footer>
            <ul class="clearfix">
                <li>联系我们</li>
                <li>网站地址</li>
                <li>隐私条款</li>
                <li>法律声明</li>
                <li>互联网保险安全教育</li>
                <li>人才招聘</li>
                <li>友情链接</li>
                <li>帮助中心</li>
            </ul>
            <div class="copyright">
                <div class="copyright-con">
                    <h3>版权所有©太平财产保险有限公司</h3>
                    <p>CopyRight©China Pacific Insurance(group) Co.,Ltd.. All Rights Reserved 沪ICP备12028297号</p>
                </div>
            </div>
        </footer>
        <div class="popup" id="popup">
            <div class="popup-infor">
                <h2>提示<img id="close-bd" src="${ctx}/static/images/jhxclose.png" alt="" /></h2>
                <div class="popup-top">
                    <dl class="clearfix">
                        <dt><img src="${ctx}/static/images/jhxpic2.png" alt="" /></dt>
                        <dd class="part1">请您在打开的页面上完成付款</dd>
                        <dd class="part2">付款完成前请不要关闭此窗口。</dd>
                        <dd class="part3">完成付款后请根据您的情况点击下面按钮:</dd>
                    </dl>
                </div>
                <div class="popup-down clearfix">
                    <button href="" class="back" id="back5">已完成支付</button>
                    <button href="" class="go" id="go">返回重新支付方式</button>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="${ctx}/static/js/jhxcon.js"></script>
    <script type="text/javascript" src= "${ctx}/static/js/validateCus.js"></script>
    <script type="text/javascript">
    function checkholder(objelement){
    	var objvalue = $(objelement).val();
    	var objholder = $(objelement).attr("placeholder");
    	if(objvalue == "" || objvalue == objholder){
    		$(objelement).val("");
    		$(objelement).attr("placeholder","");
    		$(objelement).css({color:"black"})
    	} 
    }
    function changeshow(objem){
    	var objvalue = $(objem).val();
    	var objholder = $(objem).attr("placeholder");
        if (objvalue == '' || objvalue == objholder) {
        	$(objem).css({color:'#aaa'})
        	$('.placeholder').css({ color:'#aaa' })
            $(objem).addClass('placeholder');
        	$(objem).val(objholder);
        }
    	
    }
    $("#next1").on("click",function(){
    	var period = $("#insurePeriod").val();
    	var payMuch = $("#payMuch").val();
    	if(period == ""){
    		$("#errorDiv").css({
    			display:"block"
    		});
    		return;
    	}else{
    		$("#errorDiv").css({
    			display:"none"
    		});
    	}
    	if(payMuch == "" || payMuch == "请输入保费金额"){
   		 $("#payMuch").css({
                border: "1px solid red"
            });
   		    $("#payMuch").val("请输入保费金额");
   		    $('#payMuch').css({ color:'#aaa' })
	        $("#payMuch").attr("placeholder","请输入保费金额");
   		    return;
   	   }else{
   		   if(payMuch == '请输入整数数字'){
   				$("#payMuch").val("请输入整数数字");
   			    $('#payMuch').css({ color:'#aaa' })
		        $("#payMuch").attr("placeholder","请输入整数数字");
		        return;
   		   }
   		   if(Math.floor(payMuch)<10000){
   			  $("#payMuch").css({
                  border: "1px solid red"
              });
              $(".pay-num").css({
                  color:"red"
              })
              return;
   		   }
   	   }
    	if(payMuch.length>8){
        	var d = dialog({
   			    title: '警告',
   			    content: '输入内容长度非法',
   			    cancel: false,
   				okValue: '确定',
   				width: 280,
   			    ok: function () {
   			    	return;
   			    }
   			});
    		d.show();
    		return;
        }
    	 $("#pay-pro-con2").css({
             display: "block"
         });
         $("#pay-pro-con1").css({
             display: "none"
         });
         $(".registor ul li").eq(1).addClass("bac0277bd ");
         $(".registor ul li").eq(0).removeClass("bac0277bd");
         $(".registor ul li").eq(0).addClass("bac0277");
    })
    $("#next3").on("click",function(){
    	
         $("#next3").attr("disabled","disabled");  
	   	 $.post("${ctx}/api/insure/buyInsurance",$("#doInsureForm").serializeArray(),function(result){
	   		if(result.msg == 'success'){
	   			  console.log(result.data);
	   			  var getData = result.data;
	   			  $("#orderNo").val(getData.billNo);
	   			  $("#chargeBillNoShow").html(getData.billNo);
	   			  $("#getPremium").val(getData.premium);
	   			  $("#premiumShow").html(getData.amount); 
	   			  $("#insureNameShow").html(getData.product);
	              $("#pay-pro-con4").css({
	                  display: "block"
	              });
	              $("#pay-pro-con3").css({
	                  display: "none"
	              });
	              $(".registor ul li").eq(3).addClass("bac0277bd");
	              $(".registor ul li").eq(2).removeClass("bac0277bd");
	              $(".registor ul li").eq(1).removeClass("bac0277");
	              $(".registor ul li").eq(1).removeClass("bac0277bd");
	              $(".registor ul li").eq(2).addClass("bac0277");
	          
	   		}else if(result.msg == 'reapt'){
	   			var d = dialog({
	   			    title: '提示信息',
	   			    content: '您已投保，请查看我的保单！',
	   			    cancel: false,
	   				okValue: '确定',
	   				width: 280,
	   			    ok: function () {}
	   			});
	   			d.show();
	   		}else{
	   			var d = dialog({
	   			    title: '提示信息',
	   			    content: '系统出现异常！',
	   			    cancel: false,
	   				okValue: '确定',
	   				width: 280,
	   			    ok: function () {}
	   			});
	   			d.show();
	   		}
	   	
	   	});  
    });
   
    //支付验证 
    function validate(){
    	$("#bankmobileError").css({display:"none"})
    	$("#mobilePhone").css({border: "1px solid #d3d3d3"})
    	$("#mobilePhone").attr("placeholder","");
    	$("#mobilePhone").css({color:"black"})
    	if ($("#next4").attr("flag") == "1"){
    		var result = false;
    		 $.ajax({
    	    	    type: "POST",   
    	    	    url: "${ctx}/api/insure/validateBuy",
    	    	    dataType:"json",
    	    	    async: false,
    	    	    success : function(data) {
    	    	    	if(data.msg == '1'){
    	    	    		result = true;
    	    	    	}
    	    	    }
    		  });  
    		 if(result){
		    		var d = dialog({
		   			    title: '提示信息',
		   			    content: '您已支付成功，请不要重复支付！',
		   			    cancel: false,
		   				okValue: '确定',
		   				width: 280,
		   			    ok: function () {
		   			    	$("#back5").trigger("click");
		   			    }
		   			});
		    		d.show();
		    		return false;
    		 }
    	}
    	var orderNo = $("#orderNo").val();
    	var premium = $("#getPremium").val();
    	var bankCode = $("#bankCode").val();
    	var mobilephone = $.trim($("#mobilePhone").val());
    	
    	if(mobilephone == "" || mobilephone == "请输入预留手机号"){
    		$("#mobilePhone").css({border:"1px solid red"})
    		$("#mobilePhone").val("请输入预留手机号");
    		$("#mobilePhone").css({ color:'#aaa' })
            $("#mobilePhone").attr("placeholder","请输入预留手机号");
    		return false;
    	}else{
    		 var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
    		 if (!isMobile.test(mobilephone) || mobilephone.length != 11) {
              	$("#mobilePhone").css({border: "1px solid red"})
              	$("#bankmobileError").css({display:"block"})
      	       /*  $("#mobilePhone").val("");
                $("#mobilePhone").attr("placeholder","请输入正确预留手机号");
                $("#mobilePhone").focus();   */
            	return false;
              }
    	}
    	if(bankCode == ""){
    		$("#errorMessage").css({
    			"display":"block"
    		})
    		return false;
    	} 
    	$("#next4").attr("flag", "1");
    	  $(".registor ul li").eq(3).removeClass("bac0277bd");
    	  $(".registor ul li").eq(3).addClass("bac0277");
    	  $(".registor ul li").eq(4).addClass("baclast");
          $(".registor ul li").eq(2).removeClass("bac0277"); 	
    	$("#popup").fadeIn();
    }
    function getCheckMobile(){
    	var mobilephone = $.trim($("#mobilePhone").val());
    	var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
    	 if (isMobile.test(mobilephone) && mobilephone.length == 11) {  
           	 $("#mobilePhone").css({border: "1px solid #d3d3d3"})
           	 $("#bankmobileError").css({display:"none"})
           }
    }
    // 已完成支付 点击跳转 
    $("#back5").on("click",function(){
        $("#popup").fadeOut();
        var orderNo = $("#orderNo").val();
        $.ajax({
        	type:"POST",
        	url:"${ctx}/api/insure/getPolicyInfos",
        	data:{"orderNo":orderNo},
        	dataType:"json",
        	success:function(data){
        		console.log(data);
        		if(data.msg == '0'){//支付成功返回 
        			var obj = data.resultData;
        		     $("#showPolicyInfo").css({display:"block"})
        		     $("#payPolicyNo").html(obj.policyNo);
        		     $("#payProduct").html(obj.product);
        		     $("#payOrderNo").html(obj.policyNo);
        		     $("#payPremium").html(formatMoney(obj.premium,2)+"元");
        		     $("#payAmount").html(formatMoney(obj.insuredAmount,2)+"元");
        		     $("#amountInfo").html(formatMoney(obj.insuredAmount,2));
        		     $("#payUseTime").html(data.validFrom+"至"+data.validTo);
        		     $("#payHolderName").html(obj.policyHolderInfo.holderName);
        		     if(obj.policyHolderInfo.holderType == 1){
        		    	 $("#payHolderType").html("企业 ");
        		     }else{
        		    	 $("#payHolderType").html("个人");
        		     }
        		     if(obj.policyHolderInfo.idType == 1){
        		    	 $("#payIdType").html("组织机构代码证 ");
        		     }else{
        		    	 $("#payIdType").html("身份证");
        		     }
        		     $("#payIdNo").html(obj.policyHolderInfo.idNo);
        		     $("#payBuyer").html(obj.insuredName);
        		     if(obj.payMode == 1){
        		     	$("#payType").html("银信证支付 ");
        		     }
        		     $("#payInsureName").html(obj.insurerInfo.insurername);
        		     $("#payInsureHotline").html(obj.insurerInfo.hotline);
        		     $("#payInsureAddress").html(obj.insurerInfo.address);
        			
        		}else if(data.msg == '1'){// 支付失败返回 
        			$("#showPolicyInfo").css({display:"none"})
        			var d = dialog({
    	   			    title: '提示信息',
    	   			    content: '支付失败！',
    	   			    cancel: false,
    	   				okValue: '确定',
    	   				width: 280,
    	   			    ok: function () {}
    	   			});
    	   			d.show();
        		}else{//系统异常 
        			$("#showPolicyInfo").css({display:"none"})
        			var d = dialog({
    	   			    title: '提示信息',
    	   			    content: '系统出现异常！',
    	   			    cancel: false,
    	   				okValue: '确定',
    	   				width: 280,
    	   			    ok: function () {}
    	   			});
    	   			d.show();
        		}
        	}
        	
        });
        
        $("#content5").css({
            display: "block"
        });
        $("#pay-pro-con4").css({
            display: "none"
        });
        $(".registor ul li").eq(4).addClass("bac0277bd");
        $(".registor ul li").eq(4).find("span").addClass("back0277bd1");
        $(".registor ul li").eq(3).removeClass("bac0277bd");
        $(".registor ul li").eq(3).find("span").removeClass("back0277bd1");
    })
    
     function formatMoney(s, n) // s:传入的float数字 ，n:希望返回小数点几位
	{
		n = n > 0 && n <= 20 ? n : 2;
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
		t = "";
		for (i = 0; i < l.length; i++) {
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
		}
		return t.split("").reverse().join("") + "." + r;
	} 
    
    function downloadFile(){
    	    var fileName =  $("#fileNamedown").val();
			window.location.href = "${ctx}/api/insure/downLoad?fileName="+fileName;
    }
    
    </script>

</html>