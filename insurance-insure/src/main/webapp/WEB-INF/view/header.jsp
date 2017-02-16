<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit"/>
    <title>Insert title here</title>
	<script  type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/html5shiv.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css"/>
    <script src="${ctx}/static/js/artdialog/js/dialog-plus-min.js"></script>
    <script src="${ctx}/static/js/artdialog/js/doc.js"></script>
    <link rel="stylesheet" href="${ctx}/static/js/artdialog/css/ui-dialog.css" />
    <link rel="stylesheet" href="${ctx}/static/js/artdialog/css/doc.css" />
    
    <%-- <link rel="stylesheet" href="${ctx}/static/js/boxy/css/boxy.css" type="text/css" />
    <script type="text/javascript" src="${ctx}/static/js/boxy/js/jquery.boxy.js"></script> --%>
    
</head>
<body>
       <header>
            <div class="header-con">
                <img src="${ctx}/static/images/logo.png" alt="" class="header-logo" />
                <c:if test="${user != null}">
                <span id="confirmLogout">退出</span>
                </c:if>
                <span class="showInfoBut">我的保单</span>
                <span class="downLoadBut"><input type="hidden" id="fileNamedown" value="insurecert-generator.zip"  class="fileName-down"/>生成器下载</span>
                <c:if test="${user != null}">
                  <span>您好！${user.userAccount}</span>
                  <input type="hidden" id="loginName" value="${user.userAccount}"/>
                </c:if>
                <c:if test="${user == null }">
                  <span class="loginBut">登录</span>
                </c:if>
               
            </div>
        </header>
      
     <div class="modal modal-small modal-performance"  id="login" style="display:none;">	
        <div class="modal-wrapper">
            <div class="modal-title modal-title--tips">
                <p><a href="javascript:void(0)" class="modal-close" data-action="close">X</a>登录</p>                
            </div>
          
            <div class="modal-content addressee-content ">
                <div class="input-field">
                  <label for="" class="lineup">账号:</label>
                  <input type="text" name="" class="userAccount" id="userAccount" maxlength="16" />
                  <span class="input-fb input-fb--tips"></span>
                </div>
                <div class="input-field">
                  <label for="" class="lineup">密码:</label>
                  <input type="password" name=""  id="password"  maxlength="12" class="userAccount" >
               <!--    <input type="password" name=""  id="password"  maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" > -->
                  <span class="input-fb input-fb--tips"></span>
                </div>  
               <label class="errorMsg" style="display:none;font-size:12px;color:red;text-align:center;"></label>         
            </div>
          
            <div class="modal-footer txtCenter">
            	<a class="loginbtn" onclick="login();" id="loginbut">登录</a>&nbsp;&nbsp;<a class="loginbtn" onclick="closelogin();" >取消</a> 
            	
            </div>
        </div>
        
    </div>
      
</body>
<script type="text/javascript">
        $("#confirmLogout").on("click",function(){
        	var logoutDialog = dialog({
    		    title: '提示',
    		    content: '确定退出吗？',
    		    okValue: '确定',
    		    width: 280,
    		    ok: function () {
    		    	window.location.href = "${ctx}/loginOut";
    		        return false;
    		    },
    		    cancelValue: '取消',
    		    cancel: function () {}
    		});
        	/* art.dialog({
        	    width: 460
        	}); */
    		logoutDialog.show();
        	
        })
		
		/* $("#confirmLogout").click(function() {
			Boxy.confirm("确认退出吗?", function() { window.location.href = "${ctx}/loginOut";  }, {title: "提示信息"});
			return false;
		}); */
			
		$(".userAccount").keydown(function(e){
			var curKey = e.which;
			if(curKey == 13){
		    login();
			return false;
			} 
		})
		
		$(".downLoadBut").on("click",function(){
			
			var account = $("#loginName").val();
			if(account == "" || account == undefined){
				$("#login").show();
				return;
			}
			 var fileName =  $("#fileNamedown").val();
			window.location.href = "${ctx}/api/insure/downLoad?fileName="+fileName;
			/* var fileName = $(".fileName-down").val();
			$.ajax({
				type:"POST",
				url:"${ctx}/api/insure/downLoad",
				data:{"fileName":fileName},
				dataType:"json",
				success:function(data){
					if(data != null && data.msg == '1'){
						$("#login").show();
					}
				}
				
			}); */
			
		});
		$(".showInfoBut").on("click",function(){
			var account = $("#loginName").val();
			if(account == "" || account == undefined){
				$("#login").show();
				return;
			}
			window.location.href = "${ctx}/api/insure/showPolicyInfo";
		})
	
	 $(".loginBut").on("click",function(){
     	$(".errorMsg").html("");
     	$(".errorMsg").css("display","none");
     	$("#userAccount").val("");
     	$("#password").val("");
     	$("#login").show();
     	
     })
     $(".modal-close").on("click",function(){
     	
     	$("#login").hide();
     })
     function closelogin(){
			$("#login").hide();
	 }
     function checkLogin(){
			var flag = false;
			var userAccount = $.trim($("#userAccount").val());
	     	var password = $.trim($("#password").val());
			if(userAccount == ""){
	     		$(".errorMsg").html("账号不能为空！");
	     		$(".errorMsg").css("display","block");
	     		$("#userAccount").focus();
	     		flag = true;
	     		return flag;
	     	}
	     	if(password == ""){
	     		$(".errorMsg").html("密码不能为空！");
	     		$(".errorMsg").css("display","block");
	     		$("#password").focus();
	     		flag = true;
	     		return flag;
	     	}
	     	if(userAccount == "" && password == ""){
	     		$(".errorMsg").html("账号或密码不能为空！");
	     		$(".errorMsg").css("display","block");
	     		$("#userAccount").focus();
	     		flag = true;
	     		return flag;
	     	}
	     	if(userAccount.length>16){
	     		$(".errorMsg").html("账号长度需在16个字符之内！");
	     		$(".errorMsg").css("display","block");
	     		$("#userAccount").focus();
	     		flag = true;
	     		return flag;
	     	}
	    	if(password.length>12){
	     		$(".errorMsg").html("密码长度需在12个字符之内！");
	     		$(".errorMsg").css("display","block");
	     		$("#password").focus();
	     		flag = true;
	     		return flag;
	     	}
	     	return flag;
			
     }
     function login(){
     	var userAccount = $("#userAccount").val();
     	var password = $("#password").val();
     	var checkflag = checkLogin();
     	if(!checkflag){
	     	$.ajax({
	     		type:"POST",
	     	    url:"${ctx}/login",
	     		data:{"userName":userAccount,"password":password},
	     		dataType:"json",
	     		success:function(data){
	     			console.log(data);
	     			if(data.msg == '0'){
	     				$("#login").hide();
	     				window.location.href="${ctx}";
	     				$(".errorMsg").html("");
	     		     	$(".errorMsg").css("display","none");
	     		     	$("#userAccount").val("");
	     		     	$("#password").val("");
	     			}else{
	     				$(".errorMsg").html("账号或密码错误 ！");
	             		$(".errorMsg").css("display","block");
	             		$("#userAccount").focus();
	     			}
	     		}
	     	});
	     	
     	}
     	
     }
 
</script>
</html>