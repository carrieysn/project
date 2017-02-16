<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
        <meta charset="utf-8">
        <title>交还险</title>
        <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/tb/reset.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/header.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/tb/jhxcon.css" />
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <section class="pro-con">
            <h2><img src="${ctx}/static/images/jianjiao_03.png" alt="" /><a href="${ctx}">返回保险详情页</a></h2>
            <section class="pay-pro">
                <div class="registor">
                    <ul>
                        <li>录入投保信息</li>
                        <li>录入投保人信息</li>
                        <li>预览投保信息</li>
                        <li class="bac0277">支付保费</li>
                        <li class="baclast bac0277bd">完成投保</li>
                    </ul>
                </div>
            </section>
             <section class="content5" id="content5" style="display: block;">
                 <div class="small-logo"></div>
                 <div class="congra">恭喜您已投保成功！</div>
                 <div class="congra-mid">预缴保单号为:<span>${policyInfo.policyNo}</span></div>
                 <div class="congra-footer">您当前获得保额为<fmt:formatNumber value="${policyInfo.insuredAmount div 100.0}" pattern="#,#00.00#"/>的保障，对应的保费保额杠杆比为1:1。为了方便保险证管理，请下载<span>保险证生成器</span>安装使用</div>
                 <div class="infor-content">
                     <h2>保险单信息</h2>
                     <div class="infor-con-mid">
                         <div class="infor-con-mid1">
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>险种名称:</span>
                                         <strong>${policyInfo.product}</strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>预缴保单号:</span>
                                         <strong>${policyInfo.policyNo}</strong>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>预交保费:</span>
                                         <strong><fmt:formatNumber value="${policyInfo.premium div 100.0}" pattern="#,#00.00#"/>元</strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>保额:</span>
                                         <strong><fmt:formatNumber value="${policyInfo.insuredAmount div 100.0}" pattern="#,#00.00#"/>元</strong>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>预缴保单有效时间:</span>
                                         <strong><fmt:formatDate value="${policyInfo.validFrom}" pattern="yyyy-MM-dd HH:mm"/>至<fmt:formatDate value="${policyInfo.validTo}" pattern="yyyy-MM-dd HH:mm"/></strong>
                                     </div>
                                 </li>
                             </ul>
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>投保人名称:</span>
                                         <strong>${policyInfo.policyHolderInfo.holderName}</strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                        <span>投保人类型:</span>
                                        <c:if test="${policyInfo.policyHolderInfo.holderType == 1}">
                                	    	<strong>企业</strong>
                                        </c:if>
		                                <c:if test="${policyInfo.policyHolderInfo.holderType == 2}">
		                                	<strong>个人</strong>
		                                </c:if>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>证件类型:</span>
                                        <c:if test="${policyInfo.policyHolderInfo.idType == 1}">
                                			<strong>组织机构代码证</strong>
                                        </c:if>
                                        <c:if test="${policyInfo.policyHolderInfo.idType == 2}">
                                	       <strong>身份证</strong>
                                        </c:if>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>证件号码:</span>
                                         <strong>${policyInfo.policyHolderInfo.idNo}</strong>
                                     </div>
                                 </li>
                             </ul>
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>被保险人:</span>
                                         <strong>${policyInfo.insuredName}</strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>保费缴费方式:</span>
                                          <c:if test="${policyInfo.payMode == 1}">
                                           <strong>银信证</strong>
                                          </c:if>
                                     </div>
                                 </li>
                             </ul>
                             <ul class="infor-con-mid1-1">
                                 <li class="clearfix">
                                     <div class="infor-con-mid1-left">
                                         <span>保险人名称:</span>
                                         <strong>${policyInfo.insurerInfo.insurername}</strong>
                                     </div>
                                     <div  class="infor-con-mid1-right">
                                         <span>保险公司服务热线:</span>
                                         <strong>${policyInfo.insurerInfo.hotline}</strong>
                                     </div>
                                 </li>
                                 <li>
                                     <div class="infor-con-mid1-left">
                                         <span>保险人地址:</span>
                                         <strong>${policyInfo.insurerInfo.address}</strong>
                                     </div>
                                 </li>
                             </ul>
                         </div>
                     </div>
                 </div>
                 <div class="dowload">
                     <h2>下载保险证生成器</h2>
                     <div class="download-con">
                         <input type="hidden" id="fileNamedown" value="insurecert-generator.zip"/>
                         <dl>
                             <dt><img src="${ctx}/static/images/jhx07.png" alt="" /></dt>
                             <dd>
                                 <h3>互联网保险创新产品</h3>
                                 <div class="pace">让交易从此放心</div>
                                 <div class="down-btn">
                                     <button type="submit"  onclick="downloadFile();">下载保险证生成器</button>
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
            <ul class="clearfix" style="margin-left: 534px;">
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
       <%--  <div class="popup" id="popup">
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
        </div> --%>
    </body>
     <script type="text/javascript">
	    function downloadFile(){
		    var fileName =  $("#fileNamedown").val();
			window.location.href = "${ctx}/api/insure/downLoad?fileName="+fileName;
	    }
    </script>
</html>