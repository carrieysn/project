/**
 * 
 */
$(function(){
	if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
	    $('[placeholder]').focus(function() {
	        var input = $(this);
	        if (input.val() == input.attr('placeholder')) {
	            input.val('');
	            input.removeClass('placeholder');
	            input.css({color:"black"})
	        }else{
	        	if(input.val() != "" && input.val() != input.attr('placeholder')){
	        		input.css({color:"black"})
	    			input.removeClass('placeholder');
	    		}
	        }
	    }).blur(function() {
	        var input = $(this);
	        if (input.val() == '' || input.val() == input.attr('placeholder')) {
	        	input.css({color:'#aaa'})
	        	$('.placeholder').css({ color:'#aaa' })
	            input.addClass('placeholder');
	            input.val(input.attr('placeholder'));
	        }
	    }).blur();
	}
	function placeholderSupport() {
	    return 'placeholder' in document.createElement('input');
	}
	
	
})

   function initinput(){
	   $("#holderphoneError").css({display:"none"})
	   $("#holderemailError").css({display:"none"})
	   $("#holderName").attr("placeholder","");
	   $("#idNo").attr("placeholder","");
	   $("#contacts").attr("placeholder","");
	   $("#phone").attr("placeholder","");
	   $("#email").attr("placeholder","");
	   $("#holderName").css({color:"black"})
	   $("#idNo").css({color:"black"})
	   $("#contacts").css({color:"black"})
	   $("#phone").css({color:"black"})
	   $("#email").css({color:"black"})
   }
    function confirmInfo(){
	    initinput();
		var flag = true;
		var holderType = $("#holderType").val();
		var idType = $("#idType").val();
    	var holderNameValue = $.trim($("#holderName").val());
    	var idNoValue = $.trim($("#idNo").val());
    	var contactsValue = $.trim($("#contacts").val());
    	var phoneValue = $.trim($("#phone").val());
    	var emailValue = $.trim($("#email").val());
    	    if(holderType == "" || idType == ""){
    	    	$("#holderErrorFlag").css({
    	    		display:"block"
    	    	})
    	    	flag = false;
    	    }
    	    
        	if(holderNameValue == "" || holderNameValue == "请输入投保人"){
    	    	$("#holderName").css({
    	            border: "1px solid red"
    	        })
    	         $("#holderName").val("请输入投保人");
    	        $('#holderName').css({ color:'#aaa' })
    	        $("#holderName").attr("placeholder","请输入投保人");
    	    	//$("#holderName").focus();
    	    	flag = false;
        	}
        	if(filterSqlStr(holderNameValue) || holderNameValue == "非法字符"){
        		$("#holderName").css({
    	            border: "1px solid red"
    	        })
	        	 $("#holderName").val("非法字符");
        		 $('#holderName').css({ color:'#aaa' })
    	        $("#holderName").attr("placeholder","非法字符");
    	    	flag = false;
        	}
        	if(idNoValue == "" || idNoValue == "请输入证件号" ){
        		$("#idNo").css({
    	            border: "1px solid red"
    	        })
    	         $("#idNo").val("请输入证件号");
    	         $('#idNo').css({ color:'#aaa' })
    	        $("#idNo").attr("placeholder","请输入证件号");
    	    	//$("#idNo").focus();
    	    	flag = false;
        	}
        	if(filterSqlStr(idNoValue) || idNoValue == "非法字符"){
        		$("#idNo").css({
    	            border: "1px solid red"
    	        })
    	        $("#idNo").val("非法字符");
        		 $('#idNo').css({ color:'#aaa' })
	    	    $("#idNo").attr("placeholder","非法字符");
    	    	flag = false;
        	}
        	if(contactsValue == ""  || contactsValue == "请输入联系人" ){
        		$("#contacts").css({
    	            border: "1px solid red"
    	        })
    	         $("#contacts").val("请输入联系人");
    	         $('#contacts').css({ color:'#aaa' })
    	        $("#contacts").attr("placeholder","请输入联系人");
    	    	//$("#contacts").focus();
    	    	flag = false;
        	}
        	if(filterSqlStr(contactsValue) || contactsValue == "非法字符"){
        		$("#contacts").css({
    	            border: "1px solid red"
    	        })
    	        $("#contacts").val("非法字符");
        		 $('#contacts').css({ color:'#aaa' })
    	        $("#contacts").attr("placeholder","非法字符");
    	    	flag = false;
        	}
        	var mobile = $.trim($("#phone").val());  
        	
            if(mobile == "" || mobile == "请输入联系电话"){
            	
            	$("#phone").css({
    	            border: "1px solid red"
    	        })
    	        $("#phone").val("请输入联系电话");
    	        $('#phone').css({ color:'#aaa' })
    	        $("#phone").attr("placeholder","请输入联系电话");
    	    	//$("#phone").focus();
    	    	flag = false;
            }else{
            	if(!checkPhone()){
            		flag = false;
            	}
            }
            if(emailValue == "" || emailValue == "请输入邮箱"){
            	$("#email").css({
    	            border: "1px solid red"
    	        })
    	        $("#email").val("请输入邮箱");
    	         $('#email').css({ color:'#aaa' })
    	        $("#email").attr("placeholder","请输入邮箱");
    	    	//$("#email").focus();
    	    	flag = false;
            }else{
            	 if(!checkEmail()){
            		 flag = false;
       		     }
            }
            if(flag){
	            if(holderNameValue.length>50 || idNoValue.length>50 || contactsValue.length>5 || emailValue.lenght>50){
	            	flag = false;
	            	var d = dialog({
		   			    title: '警告',
		   			    content: '输入内容长度非法',
		   			    cancel: false,
		   				okValue: '确定',
		   				width: 280,
		   			    ok: function () {
		   			    	flag = false;
		   			    }
		   			});
		    		d.show();
		    		return flag;
	            }
            }
	    	var periodValue = $("#insurePeriod").val();
	    	var periodHtml = periodValue +'个月';
	    	$("#periodCofirm").html(periodHtml);
	    	var  premiumValue = $("#payMuch").val();
	    	$("#premiumConfirm").html(formatMoney(premiumValue,2));
	    	$('#bignum').html(NoToChinese(premiumValue)+"圆整");
	    	var  holderTypeValue = $("#holderType").val();
	    	if(holderTypeValue == '1'){
	    		$("#holderTypeConfirm").html('企业');
	    	}else{
	    		$("#holderTypeConfirm").html('个人');
	    	}
	    	$("#holderNameConfirm").html(holderNameValue);
	    	$("#holderNameConfirm").attr("title",holderNameValue);
	    	var idTypeValue = $("#idType").val();
	    	if(idTypeValue == '1'){
	    		$("#idTypeConfirm").html('机构代码');
	    	}else if(idTypeValue == '2'){
	    		$("#idTypeConfirm").html('身份证');
	    	}
	    	$("#idNoConfirm").html(idNoValue);
	    	$("#idNoConfirm").attr("title",idNoValue);
	    	$("#contactsConfirm").html(contactsValue);
	    	$("#contactsConfirm").attr("title",contactsValue);
	    	$("#phoneConfirm").html(phoneValue);
	    	$("#emailConfirm").html(emailValue);
	    	$("#emailConfirm").attr("title",emailValue);
	    	return flag;
    	
    }
    function filterSqlStr(value){
    	var sqlStr=sql_str().split(',');
    	var flag=false;
    	
    	for(var i=0;i<sqlStr.length;i++){
    		if(value.toLowerCase().indexOf(sqlStr[i])!=-1){
    			flag=true;
    			break;
    		}
    	}
    	return flag;
    }
    function sql_str(){
    	var str="and,delete,or,exec,insert,select,union,update,count,*,',join,>,<";
    	return str;
    }

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
    
	 $("#holderName").on("blur",function(){
	    	var holderNameValue = $("#holderName").val();
	    	var nameplaceValue = $("#holderName").attr("placeholder");
	    	if (holderNameValue == nameplaceValue &&  "" != holderNameValue) {
	        	$("#holderName").css({color:'#aaa'})
	        }
			 if(holderNameValue.length>0){
				 $("#holderName").css({
		            border: "1px solid  #d3d3d3"
		         })
			  }
		 }); 
	 
	 $("#idNo").on("blur",function(){
	    	var idNo = $("#idNo").val();
	    	var idplaceValue = $("#idNo").attr("placeholder");
	    	
	    	if ("" != idNo && idNo == idplaceValue) {
	        	$("#idNo").css({color:'#aaa'})
	        }
			 if(idNo.length>0){
				 $("#idNo").css({
		            border: "1px solid  #d3d3d3"
		         })
			  }
     }); 
	 
	 $("#contacts").on("blur",function(){
	    	var contacts = $("#contacts").val();
	    	var conplaceValue = $("#contacts").attr("placeholder");
	    	if ("" != contacts && contacts == conplaceValue) {
	        	$("#contacts").css({color:'#aaa'})
	        }
			 if(contacts.length>0){
				 $("#contacts").css({
		            border: "1px solid  #d3d3d3"
		         })
			  }
    }); 
	 $("#phone").on("blur",function(){
	    	var mobileVa = $.trim($("#phone").val());  
	    	var phoneplaceValue = $("#phone").attr("placeholder");
	    	
	    	if ("" != mobileVa && mobileVa == phoneplaceValue) {
	        	$("#phone").css({color:'#aaa'})
	        }
	    	 if(mobileVa.length>0 && mobileVa != ""){
	    		 if(!checkPhone()){
	    		   return false;
	    		 }
	    	 }
			 if(mobileVa.length>0){
				 $("#phone").css({
		            border: "1px solid  #d3d3d3"
		         })
			  }
    });
	 function checkPhone(){
		 var isflag  = true;
		 var mobile = $.trim($("#phone").val());  
		 var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;  
         var isPhone = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;
         if(mobile != "" && mobile != "请输入联系电话"){
			 if (mobile.substring(0, 1) == 1) {  
	             if (!isMobile.test(mobile) || mobile.length != 11) {  
	             	$("#phone").css({
	     	            border: "1px solid red"
	     	        })
	     	          $("#holderphoneError").css({display:"block"})
	     	       /*  $("#phone").val("");
	                 $("#phone").attr("placeholder","请输入正确电话号码");
	                 $("#phone").focus();  */
	                 isflag = false;  
	             }  
	         }else if (mobile.substring(0, 1) == 0) {//如果为0开头则验证固定电话号码    
	             if (!isPhone.test(mobile)) {  
	             	$("#phone").css({
	     	            border: "1px solid red"
	     	        })
	     	         $("#holderphoneError").css({display:"block"})
	     	        /* $("#phone").val("");
	                 $("#phone").attr("placeholder","请输入正确电话号码");
	                 $("#phone").focus();  */
	                 isflag = false;  
	             }  
	         }else{  //否则全部不通过  
	         	$("#phone").css({
	 	            border: "1px solid red"
	 	        })
	 	         $("#holderphoneError").css({display:"block"})
	 	        /*$("#phone").val("");
	         	$("#phone").attr("placeholder","请输入正确电话号码");
	            $("#phone").focus();*/
	            isflag = false;   
	         } 
         }
		 return isflag;
		 
	 }
    $("#email").on("blur",function(){
    	var email = $.trim($("#email").val());  
    	var emailplaceValue = $("#email").attr("placeholder");
    	
    	if ("" != email && email == emailplaceValue) {
        	$("#email").css({color:'#aaa'})
        	$('.placeholder').css({ color:'#aaa' })
            $("#email").addClass('placeholder');
        	$("#email").val(emailplaceValue);
        }
    	if(email != "" && email.length>0){
    		  if(!checkEmail()){
    			  return false;
    		  }
    	 }
		 if(email.length>0){
			 $("#email").css({
	            border: "1px solid  #d3d3d3"
	         })
		  }
    });
    function checkEmail(){
    	var ischeck = true;
    	var email = $.trim($("#email").val());  
        var isEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        if(email != "" && email != "请输入邮箱"){
        if (!isEmail.test(email)){
      	   $("#email").css({
 	            border: "1px solid red"
 	       })
 	          $("#holderemailError").css({display:"block"})
 	          /* $("#email").val("");
	           $("#email").attr("placeholder","请输入正确邮箱");
	    	   $("#email").focus();*/
	    	   ischeck = false;  
       }
      }
        return ischeck;
    }