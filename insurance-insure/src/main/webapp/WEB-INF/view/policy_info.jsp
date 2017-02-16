
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <meta charset="utf-8">
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <title>交还险</title>
        <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/tb/reset.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/header.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/static/css/mypolicy.css" />
        <script type="text/javascript">
        $(function(){
        	$("#transTopageBut").on("click",function(){
        		window.location.href = "${ctx}/api/insure/transTobuyPage";
        	})
        })
        
        </script>
    </head>
    <body>
     <jsp:include page="header.jsp" flush="true"/>
        <section class="policycon">
            <div class="header">
                <h2><img src="${ctx}/static/images/jianjiao_03.png" alt="" /><a href="${ctx}">返回保险详情页</a></h2>
            </div>
            <c:if test="${policyInfo == null}">
		            <div class="policycon-infor" >
		            	<div class="policyconinfor" style="height:400px;padding-top:200px;text-align:center;">
		            	   <div style="color:#666666;line-height:54px;height:54px;">
			                  <span>您还没有投保，快去投保吧！</span>
			                </div>
			                <div style="padding:5px;">
	                               <button type="button" class="buyInsureBut" id="transTopageBut" >立即投保</button>
	                        </div>
                        </div>
		            </div>
            </c:if>
            <c:if test="${policyInfo != null}">
            <div class="policycon-infor">
                <h2>我的保单</h2>
                <div class="policyconinfor">
                    <ul class="part1 part">
                        <li>
                            <div class="left">
                                <span>险种名称:</span>
                                <strong>${policyInfo.product}</strong>
                            </div>
                            <div class="right">
                                <span>预缴保单号:</span>
                                <strong>${policyInfo.policyNo} </strong>
                            </div>
                        </li>
                        <li>
                            <div class="left">
                                <span>预交保费:</span>
                                <strong><fmt:formatNumber value="${policyInfo.premium}" pattern="#,#00.00#"/>元</strong>
                            </div>
                            <div class="right">
                                <span>保额:</span>
                                <strong><fmt:formatNumber value="${policyInfo.insuredAmount}" pattern="#,#00.00#"/>元</strong>
                            </div>
                        </li>
                        <li>
                            <div class="left">
                                <span>预缴保单有效时间:</span>
                                <strong><fmt:formatDate value="${policyInfo.validFrom}" pattern="yyyy-MM-dd HH:mm"/>至<fmt:formatDate value="${policyInfo.validTo}" pattern="yyyy-MM-dd HH:mm"/></strong>
                            </div>
                        </li>
                    </ul>
                    <ul class="part1 part">
                        <li>
                            <div class="left">
                                <span>投保人名称:</span>
                                <strong>${policyInfo.policyHolderInfo.holderName}</strong>
                            </div>
                            <div class="right">
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
                            <div class="left">
                                <span>证件类型:</span>
                                <c:if test="${policyInfo.policyHolderInfo.idType == 1}">
                                	<strong>组织机构代码证</strong>
                                </c:if>
                                <c:if test="${policyInfo.policyHolderInfo.idType == 2}">
                                	<strong>身份证</strong>
                                </c:if>
                               
                            </div>
                            <div class="right">
                                <span>证件号码:</span>
                                <strong title="${policyInfo.policyHolderInfo.idNo}">${policyInfo.policyHolderInfo.idNo}</strong>
                            </div>
                        </li>
                    </ul>
                    <ul class="part1 part">
                        <li>
                            <div class="left">
                                <span>被保险人:</span>
                                <strong title="${policyInfo.insuredName}">${policyInfo.insuredName}</strong>
                            </div>
                            <div class="right">
                                <span>保费缴费方式:</span>
                                <c:if test="${policyInfo.payMode == 1}">
                                <strong>银信证</strong>
                                </c:if>
                            </div>
                        </li>
                    </ul>
                    <ul class="part1 part part-last">
                        <li>
                            <div class="left">
                                <span>保险人名称:</span>
                                <strong title="${policyInfo.insurerInfo.insurername}">${policyInfo.insurerInfo.insurername}</strong>
                            </div>
                            <div class="right">
                                <span>保险公司服务热线:</span>
                                <strong>${policyInfo.insurerInfo.hotline}</strong>
                            </div>
                        </li>
                        <li>
                            <div class="left">
                                <span>保险人地址:</span>
                                <strong title="${policyInfo.insurerInfo.address}">${policyInfo.insurerInfo.address}</strong>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            </c:if>
        </section>
    </body>
</html>