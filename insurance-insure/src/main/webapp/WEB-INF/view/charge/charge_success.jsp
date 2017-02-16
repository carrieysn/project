<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.11.3.min.js"></script>
        <style type="text/css">
            *{
                padding: 0;
                margin: 0;
            }
            .paysucceed {
                position: fixed;
                left: 50%;
                top: 50%;
                margin-left: -280px;
                margin-top: -120px; 
                width: 560px;
                height: 240px;
                border-radius: 7px;
            }
            .paysucceed .con {
                height: 132px;
                line-height: 132px;
                text-align: center;
                font-size: 25px;
            }
            .btnarea {
                position: relative;
                height: 100px;
            }
            .btnarea button {
                cursor: pointer;
                position: absolute;
                top: 50%;
                left: 50%;
                margin-left: -100px;
                margin-top: -20px;
                width: 200px;
                height: 40px;
                line-height: 40px;
                text-align: center;
                font-size: 18px;
                border: 1px solid #666666;
                outline: none;
                background: white;
            }
            .btnarea button a {
                color: black;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div class="paysucceed" id="paySucceed">
            <div class="con"> o(≥v≤)o恭喜！您已经充值成功！</div>
            <div class="btnarea">
                <button id="confirmBut">确定</button>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        var windowH = window.innerHeight;
        var windowW = window.innerWidth;
        
        
        $("#confirmBut").on("click",function(){
        
        	try {
        		if(window.opener){
           			 window.opener.callbackfun("success");
           	    }
			} catch (e) {
			}
        	
        	/* var userAgent = navigator.userAgent;
        	if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Presto") != -1) {
        	    window.location.replace("about:blank");
        	} else {
        		window.open('', '_self');
        	} */
        })
    
    </script>
</html>