<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>银信证付款成功</title>
  <meta name="format-detection" content="telephone=no">
  <meta name="viewport" content="width=device-width,initial-scale=1.0, user-scalable=no">
  <link rel="stylesheet" href="../css/base.css">
</head>

<body style=" ">
  <div class="nav">
    <span class="arrow fistPageGoBack" data-index="true"><em></em></span>
    
    <h1 class=""><p class="brand">银信证</p></h1>
  </div>
  <div class="nav-wrap"></div>
  <div class="error-promotion-tip"></div>
  <div class="grid" style="background: #f2f2f2;margin-top: 0; width: 100%; overflow: hidden">
      
      <div class="orderInfo">
          <div class="" style="color: #1b8a1d;margin-top: 20px;font-size: 36px;font-weight: bold;line-height: 50px;text-align: center;">支付成功</div> 
          <div style="text-align: center;padding-bottom: 15px;"><span id="intervalTime">0</span>秒后<a href-="javascript:submit()">返回商户</a></div>        
      </div>

      <!--form start-->
      <ul class="form-wrap">
          <form action="${returnUrl}" autocomplete="off" method="post" id="returnForm" class="errorMsgRequest">
              <!--交易信息 start-->
              <input type="hidden" name="responseData" value='${encryptResponse.responseData}'>            
              <input type="hidden" name="sign" value="${encryptResponse.sign}">           
          </form>
      </ul>
      <!--form end-->
  </div>

  <script>
  var i = 5; 
  var intervalid = setInterval("fun()", 1000); 
  document.getElementById("intervalTime").innerHTML = i; 
  function fun() { 
    i--; 
    if (i == 0) { 
      submit();
      clearInterval(intervalid); 
    } 
    document.getElementById("intervalTime").innerHTML = i; 
  } 
  
  function submit(){
    document.getElementById('returnForm').submit();
  }
  </script>
</body>
</html>