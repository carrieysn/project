/* 
* @Author: anchen
* @Date:   2015-12-02 14:48:22
* @Last Modified by:   anchen
* @Last Modified time: 2015-12-04 15:58:02
*/

var userAgent = navigator.userAgent,   
rMsie = /(msie\s|trident.*rv:)([\w.]+)/;   
rFirefox = /(firefox)\/([\w.]+)/;   
rOpera = /(opera).+version\/([\w.]+)/;   
rChrome = /(chrome)\/([\w.]+)/;  
rSafari = /version\/([\w.]+).*(safari)/;  
var browser;  
var version;  
var ua = userAgent.toLowerCase();  
function uaMatch(ua){  
  var match = rMsie.exec(ua);  
  if(match != null){  
    return { browser : "IE", version : match[2] || "0" };  
  }  
  var match = rFirefox.exec(ua);  
  if (match != null) {  
    return { browser : match[1] || "", version : match[2] || "0" };  
  }  
  var match = rOpera.exec(ua);  
  if (match != null) {  
    return { browser : match[1] || "", version : match[2] || "0" };  
  }  
  var match = rChrome.exec(ua);  
  if (match != null) {  
    return { browser : match[1] || "", version : match[2] || "0" };  
  }  
  var match = rSafari.exec(ua);  
  if (match != null) {  
    return { browser : match[2] || "", version : match[1] || "0" };  
  }  
  if (match != null) {  
    return { browser : "", version : "0" };  
  }  
}  
var browserMatch = uaMatch(userAgent.toLowerCase());  
if (browserMatch.browser){  
  browser = browserMatch.browser;  
  version = browserMatch.version;  
}  
$(document).ready(function(){

    if(browser == "IE") {
        $(".registor ul li span").css({
            top: -4+"px"

        })
    }
    var windowWidth = window.screen.width;
    var windowHeight = window.screen.height;
    var footerUlHeight = $("footer ul").width();
    $("#popup").css({
        width: windowWidth,
        height: windowHeight
    });
    var footerUlHeight = $("footer ul").width();
    $("footer ul").css({
        marginLeft: windowWidth/2 - footerUlHeight/2
    });
    $(".pay-pro-con1 div span").on("click",function(){
        var index = $(this).index();
        var preiod = $(this).next().attr("id");
        if($(this).attr("class") == 'addcls'){
        	$("#insurePeriod").val("");
        }else{
        	$("#insurePeriod").val(preiod);
        	 $("#errorDiv").css({
      			display:"none"
      		});
        }
        $(this).toggleClass('addcls');
        $(this).siblings('span').removeClass('addcls');
    })
    $("#payMuch").keyup(function(){
        var reg = /^(\d)*$/;
        if (!reg.test($("#payMuch").val())) {
        	$("#payMuch").css({
	            border: "1px solid red"
	        })
	         $("#payMuch").val("");
	        $("#payMuch").attr("placeholder","请输入整数数字");
            $("#next1").css({
                backgroundColor: "#d6d6d6"
            })
            $(".pay-much").html(null);
        }else if (Math.floor($("#payMuch").val())<10000) {
            $(this).css({
                border: "1px solid red"
            });
            $(".pay-num").css({
                color:"red"
            })
            $("#next1").css({
                backgroundColor: "#d6d6d6"
            })
            var data = NoToChinese($("#payMuch").val());
            $(".pay-much").html(data)
        }else if(Math.floor($("#payMuch").val())>=10000){
            $(this).css({
                border: "1px solid #d3d3d3"
            });
            $(".pay-num").css({
                color:"#d3d3d3"
            })
            $("#next1").css({
                backgroundColor: "rgb(255, 190, 33)"
            })
            var data = NoToChinese($("#payMuch").val());
            $(".pay-much").html(data)
        }
        
    });

    $("#next2").mouseover(function(){
        if ($(this).css("backgroundColor") == "rgb(255, 190, 33)") {
            $(this).css({
                background: "#ecb020"
            })
        }
    });
    $("#next2").mouseout(function(){
        $(this).css({
            background: "#ffbe21",
            color: "black"
        });
        console.log($(this).css("backgroundColor"));
    });
    $("#next3").mouseover(function(){
        if ($(this).css("backgroundColor") == "rgb(255, 190, 33)") {
            $(this).css({
                background: "#ecb020"
            });
            console.log($(this).css("backgroundColor"));
        }
    });
    $("#next3").mouseout(function(){
        $(this).css({
            background: "#ffbe21",
            color: "black"
        });
        console.log($(this).css("backgroundColor"));
    });
    $("#next4").mouseover(function(){
        if ($(this).css("backgroundColor") == "rgb(255, 190, 33)") {
            $(this).css({
                background: "#ecb020"
            });
            console.log($(this).css("backgroundColor"));
        }
    });
    $("#next4").mouseout(function(){
        $(this).css({
            background: "#ffbe21",
            color: "black"
        });
    });
 
    
    $("#next2").on("click",function(){
		    var check = confirmInfo();
	        if(check){
	        	var holderNameValue = $.trim(filterscript($("#holderName").val()));
	        	var idNoValue = $.trim(filterscript($("#idNo").val()));
	        	var contactsValue = $.trim(filterscript($("#contacts").val()));
	        	$("#holderName").val(holderNameValue);
		    	$("#idNo").val(idNoValue);
		    	$("#contacts").val(contactsValue);
	        	$("#next3").removeAttr("disabled");
	            $("#pay-pro-con3").css({
	                display: "block"
	            });
	            $("#pay-pro-con2").css({
	                display: "none"
	            });
	            $(".registor ul li").eq(0).removeClass("bac0277");
	            $(".registor ul li").eq(2).addClass("bac0277bd");
	            $(".registor ul li").eq(1).addClass("bac0277");
	        }
    	
    })
     function filterscript(s) { 
	    var pattern = new RegExp("[%--`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");//格式 RegExp("[在中间定义特殊过滤字符]")
	    var rs = ""; 
	    for (var i = 0; i < s.length; i++) { 
	     rs = rs+s.substr(i, 1).replace(pattern, ''); 
	    }
	    return rs;
    }
   
    $("#go").on("click",function(){
        $("#popup").fadeOut();
    })
    $("#close-bd").on("click",function(){
        $("#popup").fadeOut();
    })
    $("#back2").on("click",function(){
        $("#pay-pro-con1").css({
            display: "block"
        });
        $("#pay-pro-con2").css({
            display: "none"
        });
        $(".registor ul li").eq(0).removeClass("bac0277");
        $(".registor ul li").eq(0).addClass("bac0277bd");
        $(".registor ul li").eq(1).removeClass("bac0277bd");
        
    })
    $("#back3").on("click",function(){
        $("#pay-pro-con2").css({
            display: "block"
        });
        $("#pay-pro-con3").css({
            display: "none"
        });
        $(".registor ul li").eq(0).addClass("bac0277");
        $(".registor ul li").eq(1).removeClass("bac0277");
        $(".registor ul li").eq(1).addClass("bac0277bd");
        $(".registor ul li").eq(2).removeClass("bac0277bd");
    })
    $("#back4").on("click",function(){
        $("#pay-pro-con3").css({
            display: "block"
        });
        $("#pay-pro-con4").css({
            display: "none"
        });
        $(".registor ul li").eq(2).addClass("bac0277bd");
        $(".registor ul li").eq(3).removeClass("bac0277bd");
    })
    $(".bank-btn").on("click",function(){
    	
    	if($(this).find("span").attr("class").indexOf('back-chosecolor') != -1){
    		$("#bankCode").val("");
    	}else{
    		$("#bankCode").val($(this).attr("id"));
    		$("#errorMessage").css({
    			"display":"none"
    		})
    	}
        $(this).find("span").toggleClass('back-chosecolor');
        $(this).siblings('span').find('span').removeClass('back-chosecolor');
    })
   
});
$("#paypart100span").on("click",function(){
    $("#IDcardUl li").css({
        display: "none"
    })
    $("#IDcardUl li").eq(1).css({
        display: "block",
        backgroundColor: "#dddddd"
    })
    var preiod = $(this).next().attr("id");
    if($(this).attr("class") == 'addcls'){
    	 $("#holderType").val("");
    	 $("#idType").val("");
    }else{
    	 $("#holderType").val(preiod);
    	 $("#idType").val(preiod);
    	 $("#holderErrorFlag").css({
    			display:"none"
    	 })
    }
    $(this).toggleClass('addcls');
    $(this).siblings('span').removeClass('addcls');
})
$("#paypart10span").on("click",function(){
    $("#IDcardUl li").css({
        display: "none"
    })
    $("#IDcardUl li").eq(2).css({
        display: "block",
        backgroundColor: "#dddddd"
    })
    var preiod = $(this).next().attr("id");
   if($(this).attr("class") == 'addcls'){
	   	$("#holderType").val("");
	   	$("#idType").val("");
   }else{
	    $("#holderType").val(preiod);
	    $("#idType").val(preiod);
	    $("#holderErrorFlag").css({
			display:"none"
		})
   }
    $(this).toggleClass('addcls');
    $(this).siblings('span').removeClass('addcls');
})
$("#li1").on("click",function(){
    $("#paypart10span").removeClass('addcls')
    $("#paypart100span").addClass('addcls')
})
$("#li2").on("click",function(){
    $("#paypart10span").addClass('addcls')
    $("#paypart100span").removeClass('addcls')
})
function NoToChinese(num) { 
if (!/^\d*(\.\d*)?$/.test(num)) { alert("Number is wrong!"); return "Number is wrong!"; } 
var AA = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); 
var BB = new Array("", "拾", "佰", "仟", "萬", "億", "点", ""); 
var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = ""; 
for (var i = a[0].length - 1; i >= 0; i--) { 
switch (k) { 
case 0: re = BB[7] + re; break; 
case 4: if (!new RegExp("0{4}\\d{" + (a[0].length - i - 1) + "}$").test(a[0])) 
re = BB[4] + re; break; 
case 8: re = BB[5] + re; BB[7] = BB[5]; k = 0; break; 
} 
if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0) re = AA[0] + re; 
if (a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k % 4] + re; k++; 
} 

if (a.length > 1) //加上小数部分(如果有小数部分) 
{ 
re += BB[6]; 
for (var i = 0; i < a[1].length; i++) re += AA[a[1].charAt(i)]; 
}
console.log(re); 
return re; 
} 
