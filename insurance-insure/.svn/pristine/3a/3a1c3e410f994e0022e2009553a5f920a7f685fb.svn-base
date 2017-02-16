/* 
* @Author: anchen
* @Date:   2015-12-16 13:41:06
* @Last Modified by:   anchen
* @Last Modified time: 2015-12-16 13:52:24
*/

$(document).ready(function(){


    var windowWidth = window.innerWidth;
    var windowHeight = window.innerHeight;
    var footerUlHeight = $("footer ul").width();
    $("#popup").css({
        width: windowWidth,
        height: windowHeight
    });


    $(".bank-btn").click(function(){
    	$("#bankCode").val($(this).attr("id"));
    	$("#errorMessage").css({
			"display":"none"
		})
    	//alert($("#bankCode").val());
        $(this).siblings('span').find("span").css({
            backgroundColor: "#ffffff"
        })
        $(this).find("span").css({
            backgroundColor: "#000000"
        })
        $("#next4").css({
            backgroundColor: "#ffbe21"
        })
        /*$("#next4").click(function(event){
            event.stopPropagation();
            $(".popup").fadeIn();
        })*/
    })

    $(".closepop").click(function(){
        $("#popup").fadeOut();
    })
});