/**
 * Created by wyliuzhancai on 14-4-28.
 */

window.noTraceBrowse = true;   //不是无痕浏览
try{
    sessionStorage.setItem('aaa', '123');
}catch(err){
    noTraceBrowse = false;
}

function onBridgeReady(){
    WeixinJSBridge.call('hideOptionMenu');
}
if (typeof WeixinJSBridge == "undefined"){
    if( document.addEventListener ){
        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
    }else if (document.attachEvent){
        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
    }
}else{
    onBridgeReady();
}


var hasPop = false;
var Touch = (function(){
    if('ontouchstart' in  window){
        return {
            start : 'touchstart',
            end : 'touchend',
            move : 'touchmove'
        }
    }else if('onMSPointerDown' in window){
        return {
            start : 'MSPointerDown',
            end : 'MSPointerUp',
            move : 'MSPointerMove'
        }
    }else{
        return {
            start :'mousedown',
            end : 'mouseup',
            move : 'mousemove'
        }
    }
})(); 
 
var Pop = function (params, promotionInfo) {
    if(!(this instanceof Pop)){
        return new Pop(params, promotionInfo);
    }
    this.promotionInfo = promotionInfo? '<div class="error-promotion-tip">'+promotionInfo+'</div>' : '';
    this.options = params;
    this.init();
};
Pop.fn = Pop.prototype;
Pop.fn.init = function(){
    var options = this.options, self = this, id;
    this.height = $('body').height();
    if($('#J-pop-mark').length < 1){
        $('<div class="pop-mark" id="J-pop-mark"></div>').appendTo($('body'));
    };
    id = options.alert ? 'alert' : 'default';
    id = options.help ? 'help' : id;
    if($('#J-pop-'+id).length < 1){
        if(options.alert){
            $('<div class="pop J-pop-con" id="J-pop-'+id+'"><div class="pop-content">'+options.content+'</div>'+this.promotionInfo+'<div class="pop-handle"><span class="pop-btn" >'+options.alert+'</span></div></div>').appendTo($('body'));
        //帮助3列
        }else if(options.help){
            $('<div class="pop J-pop-con" id="J-pop-'+id+'"><div class="pop-content">'+options.content+'</div>'+this.promotionInfo+'<div class="pop-handle"><span class="pop-left">'+options.left+'</span><span class="pop-right">'+options.right+'</span><span class="pop-help">'+options.help+'</span></div></div>').appendTo($('body'));
        }else {
            $('<div class="pop J-pop-con" id="J-pop-'+id+'"><div class="pop-content">'+options.content+'</div>'+this.promotionInfo+'<div class="pop-handle"><span class="pop-left">'+options.left+'</span><span class="pop-right">'+options.right+'</span></div></div>').appendTo($('body'));
        }
    }else{
        var obj = $('#J-pop-'+id);
        obj.find('.pop-content').html(options.content);
        if(options.alert){
            obj.find('.pop-btn').html(options.alert);
        }else if(options.help){
            obj.find('.pop-help').html(options.help);
            obj.find('.pop-left').html(options.left);
            obj.find('.pop-right').html(options.right);
        }else{
            obj.find('.pop-left').html(options.left);
            obj.find('.pop-right').html(options.right);
        }
    }
    this.obj =  $('#J-pop-'+id);

    if(options.help){
         this.obj.addClass('pop-has-help')
    }else{
         this.obj.removeClass('pop-has-help')
    }
    //重新计算高度
    this.obj.css('margin-top', -(this.obj.height()-80)/2);

    this.show();
    $('.J-pop-con').on('click','.pop-btn',function(ev){
        ev.preventDefault();
        options.alertFn.call(self, 'pop-btn');
    });


    $('.J-pop-con').on('click','.pop-left',function(ev){
        ev.preventDefault();
        options.leftFn.call(self, 'pop-left');
    });
    $('.J-pop-con').on('click','.pop-right',function(ev){
        ev.preventDefault();
        options.rightFn.call(self, 'pop-right');
    });
     $('.J-pop-con').on('click','.pop-help',function(ev){
        ev.preventDefault();
        options.helpFn.call(self, 'pop-help');
    });

};
Pop.fn.show = function(){
    $('#J-pop-mark').show();
    this.obj.show().addClass('animate');

    //禁止选中
    $('input').prop('readonly',true);
    $('a').data('disabled',true);
};
Pop.fn.close = function(){
    $('.J-pop-con').off(Touch.end,'.pop-btn');
    $('.J-pop-con').off(Touch.end,'.pop-left');
    $('.J-pop-con').off(Touch.end,'.pop-right');

    $('#J-pop-mark').hide();
    this.obj.hide().removeClass('animate');

    setTimeout(function(){
       $('input').not('.J_inputReadonly').prop('readonly', false);  //返回锁定bug
        $('a').data('disabled',false);
    },500);
//    $('#J-pop').css('top','50%');
};



;(function($) {
    var o = $({});
    $.sub = function() {
        o.on.apply(o, arguments);
    };
    $.unsub = function() {
        o.off.apply(o, arguments);
    };

    $.pub = function() {
        o.trigger.apply(o, arguments);
    };
})(Zepto);


/*表单校验*/
;(function($){
    $.validator = {};
    /*格式化*/
    $.validator.format = {
        date : function (value) {
            var value = value.replace('/\//g','');
            return  value.length == 2 ? value+'/' : value.replace(/(\d{2})(?=\d)/g,'$1/');
        }
    }
    /*检查类型*/
    $.validator.check = {
        mobile : function (value) {
            // return /^0?1(3|4|5|7|8)\d{9}$/.test(value.replace(/\s*/g,''));
            return /^0?1(3|4|5|7|8).{9}$/.test(value.replace(/\s*/g,''));
        },
        bank : function (value) {
            var value = value.replace(/\s*/g,'');
            return value.length >= 14 && value.length <= 19 && /^[\d]+$/.test(value);
        },
        verifycode : function (value) {
            return /^\d{6}$/.test(value);
        },
        password : function (value) {
            return /^\d{6}$/.test(value);
        },
        num3 : function (value) {
            return /^\d{3}$/.test(value);
        },
        num4 : function (value) {
            return /^\d{4}$/.test(value);
        },
        day : function (value) {
//            return /^(0[1-9]|1[0-2])([0-2][1-9]|3[0-1])$/.test(value);
            return value.length == 4;
        },
        date : function (value) {
            return /^(0[1-9]|1[0-2])\d{2}$/.test(value.replace(/\//g,''));
        },
          identify : function (value) {
            // if(/^.*\*{10,}/.test(value)){
            //     return true;
            // }else{
            //     if(value.length == 15 || value.length == 18){
            //         return /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\d|3[0-1])\d{3}(\d|X)?$/i.test(value);
            //     }else{
            //         return false;
            //     }
            // }
            var certType = $('input[name="certType"]').val();

            switch(certType){
                case 'ID':  //身份证
                    if(/^.*\*{6,}/.test(value)){
                        return true;
                    }else{
                        if(value.length == 15 || value.length == 18){
                            return /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\d|3[0-1])\d{3}(\d|X)?$/i.test(value);
                        }else{
                            return false;
                        }
                    }
                break;
                case 'PA':  //护照
                    if(/^.*\*{3,}/.test(value)){
                        return true;
                    }else{
                        if(value.length >=6){
                            return /^\w+/g.test(value)
                        }else{
                            return false;
                        }
                    }
                break;
                case 'HO': //回乡证
                    if(/^.*\*{6,}/.test(value)){
                        return true;
                    }else{
                        if(value.length ==11 || value.length ==9){
                            return /^[a-zA-Z]\d+/g.test(value)
                        }else{
                            return false;
                        }
                    }
                break;
                 case 'TW': //台胞证
                    if(/^.*\*{3,}/.test(value)){
                        return true;
                    }else{
                        if(value.length ==8){
                            return /^\w+/g.test(value)
                        }else{
                            return false;
                        }
                    }
                break;
                default:
                    return true;
                break;
            }
        },
        name : function (value) {
            return value.length > 1 ? true : false;
        }
    }

    /*回车类型*/
    $.validator.backspace = {
        blank : function (value) {
            return value.replace(/(\s*$)/g,'');
        },
        date : function (value) {
            return value.replace(/(\/$)/g,'');
        }
    }

    /*时时校验*/
    $.validator.timely = {
        mobile : function (value) {
            return value.replace(/\s*/g,'').length == 11;
        }
    }

    function checkCard(card) {
        var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
                21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
                33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
                42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
                51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
                63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
               };

        if(card === '' || isCardNo(card) === false || checkProvince(card) === false || checkBirthday(card) === false || checkParity(card) === false)return false;
        return true;

        function isCardNo(card){
            var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
            return reg.test(card)
        };

        function checkProvince(card){
            var province = card.substr(0,2);

            return (vcity[province] != undefined)
        };

        function checkBirthday(card){
            var len = card.length;
            if(len == '15')
            {
                var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/; 
                var arr_data = card.match(re_fifteen);
                var year = arr_data[2];
                var month = arr_data[3];
                var day = arr_data[4];
                var birthday = new Date('19'+year+'/'+month+'/'+day);
                return verifyBirthday('19'+year,month,day,birthday);
            }
            if(len == '18')
            {
                var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
                var arr_data = card.match(re_eighteen);
                var year = arr_data[2];
                var month = arr_data[3];
                var day = arr_data[4];
                var birthday = new Date(year+'/'+month+'/'+day);
                return verifyBirthday(year,month,day,birthday);
            }
            return false;
        };

        function verifyBirthday(year,month,day,birthday){
            var now = new Date();
            var now_year = now.getFullYear();
            if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day)
            {
                var time = now_year - year;
                if(time >= 3 && time <= 100)
                {
                    return true;
                }
                return false;
            }
            return false;
        };
        function checkParity(card){
            card = changeFivteenToEighteen(card);
            var len = card.length;
            if(len == '18')
            {
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
                var cardTemp = 0, i, valnum; 
                for(i = 0; i < 17; i ++) 
                { 
                    cardTemp += card.substr(i, 1) * arrInt[i]; 
                } 
                valnum = arrCh[cardTemp % 11]; 
                if (valnum == card.substr(17, 1)) 
                {
                    return true;
                }
                return false;
            }
            return false;
        };

        function changeFivteenToEighteen(card){
            if(card.length == '15')
            {
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
                var cardTemp = 0, i;   
                card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
                for(i = 0; i < 17; i ++) 
                { 
                    cardTemp += card.substr(i, 1) * arrInt[i]; 
                } 
                card += arrCh[cardTemp % 11]; 
                return card;
            }
            return card;
        };
    };

})(Zepto);

;(function($){

    /*倒计时*/
    $.fn.remainTime = function(time,callback){
        var timer;
        this.clear = function(){
            clearInterval(timer);
        };
        return this.each(function(){
            var self = $(this),
                remainNo = time,
                timerFn = function(){
                    if(remainNo <= 1){
                        clearInterval(timer);
                        callback && callback();
                        return false;
                    };
                    remainNo--;
                    self.html(remainNo+'秒后重发');
                }
            timer = setInterval(timerFn,1000);
        });
    };

    ///*loading效果*/
    //$.loading = {
    //    img : '/wepay/images/loading.png?v=60',
    //    ready : function (){
    //        $('<div class="mark hidden" id="J-mark"></div><div class="mark mark-gray hidden" id="J-mark-gray"></div><div id="J-loading" class="hidden"><img src="'+this.img+'" /><div class="barWrap"><span class="barlittle bar1"></span><span class="barlittle bar2"></span><span class="barlittle bar3"></span><span class="barlittle bar4"></span><span class="barlittle bar5"></span></div></div>').appendTo($('body'));
    //    },
    //    show : function () {
    //        $('input').blur();
    //        $('#J-loading').addClass('loading');
    //        $('#J-mark,#J-loading').show();
    //    },
    //    close :function () {
    //        $('#J-loading').removeClass('loading');
    //        $('#J-mark,#J-loading').hide();
    //    }
    //};
    //$.loading.ready();

    //计时器
    $.time = function (fn,time){
        var timer = setTimeout(function(){
            fn();
            clearTimeout(timer);
        },time);
    };

    //密码输入样式
    $.fn.passwd = function (callback) {
        var width = $('.passwordWrap span').width();
        return this.each(function(){
            var self = $(this), passwdWrap = self.find('.passwordWrap'), passwdItem = passwdWrap.find('span'), passwdInput = self.find('.passwordInput'), format;
            format = function (iskey) {
                var i = 0,
                    max = 6,
                    val = passwdInput.val(),
                    len = val.length;
                for( ; i < max; i++){
                    if(i < len){
                        passwdItem.eq(i).find('em').html('<i></i>');
                    }else{
                        passwdItem.eq(i).find('em').html('');
                    }
                };
                if(len < max){
                    passwdInput.css({'left':width*(len+1)-width/2+'px'});
                }else if(len == max){
                    passwdInput.css({'left':width*max+'px'});
                }
                iskey && callback && callback(val,passwdInput.data('passed'));
            }
            passwdWrap.on('click',function(){
                passwdInput.focus();
                format(false);
            });
            passwdInput.on('keyup',function(){
                format(true);
            });
        });
    };

})(Zepto);

/*input 清空操作*/
;(function($){
    $.app = {};

    /*下一步按钮*/
    $.app.nextBtn = $('#J-next-btn');
    $.sub('next.status',function(_,status){
        if(!$.app.nextBtn.length)return;
        if(status){
            $.app.nextBtn
                .addClass('btn-actived')
                .data('enabled',true);
        }else{
            $.app.nextBtn
                .removeClass('btn-actived')
                .data('enabled',false);
        }
    });

    /*清空输入框*/
    $.sub('clearbtn.show',function(_,obj){
        if(!$(obj).data('open-clear')){
            $(obj).data('open-clear',true);
            $(obj).parent().find('.J-clear-btn').css('display','inline-block');
            $(obj).parent().find('.J-info-btn').hide();
        }
    });
    $.sub('clearbtn.hide',function(_,obj){
        $(obj).val('');
        $(obj).data('open-clear',false);
        $(obj).next('.J-clear-btn').hide();
        $(obj).parent().find('.J-info-btn').show();
        $(obj).parent().removeClass('error');
    });

    $(document).on(Touch.start,'.J-clear-btn',function(e){
        e.preventDefault();
        e.stopPropagation();
        var obj = $(this).prev('input');
        $.pub('clearbtn.hide',obj);

        //禁用下一步
        $.pub('next.status',false);
    });

    //stop default event
//    $(document).on(Touch.move,function(e){
//        e.preventDefault();
//    });

    //显示提示信息
    var isAnimate = false;
    window.addEventListener('load', function(){
        $(document).on(Touch.end,'.J-info-btn',function(e){
            if(isAnimate){
                return false;
            }
            isAnimate = true;
            $('input').blur()
                .prop('readonly',true);
            var obj = $('#'+$(this).data('show'));
            $('#J-mark-gray').show();
            obj.show();
            $.time(function(){
                obj.addClass('md-show');
            },80);
        });
    }, false)
    	
        
    $.sub('md.close',function(_,params){
        var obj = params['obj'];
        obj.removeClass('md-show');
        $.time(function(){
            $('#J-mark-gray').hide();
            obj.hide();
            $('input').not('.J_inputReadonly').prop('readonly',false);
            isAnimate = false;
        },300);
    });
    //close popbox
    $(document).on(Touch.end,'.md-close',function(e){
        var obj = $(this).parents('.md-modal');
        $.pub('md.close',{'obj':obj});
    });    

    /*监听回调事件*/
    var callback = function(params,status){
        var arr = params.split(',');
        $.each(arr,function(key,val){
            $.pub(val,status);
        });
    }

    $(document).on('input','input',function(e){
        var self = $(this),
            val = self.val(),
            dataFormat = self.data('format'),
            dataBackspace = self.data('backspace'),
            dataCallback = self.data('callback'),
            e = e || window.event;

        $.pub('clearbtn.show',self);

        //把值去空格
        // self.val(self.val().replace(/\s/g, ''));

        self.parent().removeClass('error');
        /*是否格式化*/
        if(dataFormat){
            var value = $.validator.format[dataFormat]
                ? $.validator.format[dataFormat](val)
                : val;
            $.validator.check[dataFormat](val)
                ? self.data('passed',true)
                : self.data('passed',false);
//            self.data('length',value.length);
            $.validator.format[dataFormat] && self.val(value);
        }else{
            self.data('passed',true);
        }

        //实时校验
        $.validator.timely[dataFormat] && $.validator.timely[dataFormat](val) && typeof(self.data('passed')) != 'undefined' && !self.data('passed') &&  self.parent().addClass('error');
        /*回调函数*/
         dataCallback && callback(dataCallback,self.data('passed'));
    });

    //wy+锁定  使用J_inputReadonly 过滤需要锁定的元素
    $('.J_inputReadonly').each(function(){
        $(this).data('passed', true);
    })

    $('#J-identify-input').not(".J_inputReadonly").on('keyup',function(e){
        var val = $(this).val();
        if(e.keyCode == 8 && /^.*\*{10,}/.test(val)){
            $(this).val('');
        };
    }).on('change',function(){
        var val = $(this).val();
        if(/^.*\*{10,}/.test(val)){
            $(this).val('');
        };
    })

    $(document).on('keyup','input',function(e){
        var self = $(this),
            val = self.val(),
            dataFormat = self.data('format'),
            dataBackspace = self.data('backspace'),
            dataCallback = self.data('callback'),
            e = e || window.event;

        if(val.length > 0) {
            /*删除的时候*/
            if(e.keyCode == 8 && dataBackspace){
                self.parent().removeClass('error');
                self.val($.validator.backspace[dataBackspace](val));
                dataCallback && callback(dataCallback,self.data('passed'));
                return false;
            };
            $.validator.check[dataFormat](val)
                ? self.data('passed',true)
                : self.data('passed',false);
            $.validator.format[dataFormat] && self.val(val);
        }else{
            $.pub('clearbtn.hide',self);
            self.data('passed',false);
        };
        if(self.hasClass('J_inputReadonly')){   //卡信息锁定
            self.data('passed',true);
        }
        /*回调函数*/
        dataCallback && callback(dataCallback,self.data('passed'));
    });

    $(document).on('focus','input',function(e){
        var self = $(this);
        if(self.hasClass('J_inputReadonly')){
            self.data('passed',true);
            self.parent().removeClass('error');
        }else{
            self.parent().removeClass('error');
            self.val() && $.pub('clearbtn.show',self);
        }

        // 交行提示显示bug
        // if(self.hasClass('J-check-bank-num')){
        //     self.siblings('.error-info.animate').data('info', '银行卡号格式错误');
        // }
    });
    $(document).on('blur','input',function(e){
            var self = $(this),
                sibling = self.siblings('.error-info.animate'),
                siblingHtml = '<em></em>'+sibling.data('info'),
                isBankNum = self.hasClass('J-check-bank-num')? true : false;
            if(self.hasClass('J_inputReadonly')){   //卡信息锁定
                self.data('passed',true);
                self.data('open-clear',false).next('.J-clear-btn').hide();
                self.parent().find('.J-info-btn').show();
            }else{
            	self.data('open-clear', false).next('.J-clear-btn').hide();
            	if (!isBankNum && typeof(self.data('passed')) != 'undefined' && !self.data('passed') && self.val()) {
            	    self.siblings('.error-info.animate').html(siblingHtml);
            	    self.parent().addClass('error').find('.J-clear-btn').show();
            	} else {
            	    //后端检查该银行卡是否支持

            	    if (isBankNum) {
                        var phoneText =  $('#modal-mobile .content'),
                        BCMText = '<p>交通银行不验证您的手机号码，您的手机号码将作为您在京东钱包的登陆账号。<p/>',
                        oldPhoneText = '<p>银行预留手机号是你在办理该银行卡时填写的手机号。</p><p>没有预留、手机号码已忘记、或者已停用可联系银行客服更新处理。</p>';

                        if($.validator.check.bank(self.val())){
                            var data = {
                                cardNum: self.val().replace(/\s/g, ''),
                                merchantNum: $('input[name="merchantNum"]').val()
                            };
                            var dataCallback = self.data('callback');
                            
                            $.ajax({
                                type: 'get',
                                url: '/wepay/ajax/checkByCardNum?t='+new Date().getTime(),
                                dataType: 'json',
                                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                                data: data,
                                success: function(data) {
                                    if (data.status != 'success') {
                                        self.siblings('.error-info.animate').html('<em></em>' + data.reason);
                                        self.siblings('.error-info.animate').data('info', data.reason);
                                        self.parent().addClass('error').find('.J-clear-btn').show();
                                        self.data('passed',false);
                                        self.data('checknum',false);
                                    }  else {
                                    	//是否是交通银行卡
                                        if(data.obj && data.obj.bankCodeEn == 'BCM' && data.obj.bankCardType =="CREDIT" && $('#J-mobile-input').length){
                                           //修改手机号提示
                                           phoneText.html(BCMText);
                                           $('#J-mobile-input').attr('placeholder', '');
                                        }else{
                                           phoneText.html(oldPhoneText);
                                           $('#J-mobile-input').attr('placeholder', '银行预留手机号');
                                        }
                                        self.parent().find('.J-info-btn').show();
                                        self.data('passed',true);
                                        self.data('checknum',true);
                                   }
                                dataCallback && callback(dataCallback,self.data('passed')); //银行卡检查
                                }
                            })
                        }else{
                            phoneText.html(oldPhoneText);
                            $('#J-mobile-input').attr('placeholder', '银行预留手机号');

                            self.siblings('.error-info.animate').html('<em></em>' + '银行卡号格式错误');
                            self.siblings('.error-info.animate').data('info', '银行卡号格式错误');
                        }
            	    } else {
            	        self.parent().find('.J-info-btn').show();
            	    }
            	}
            }    
    });
    $('form').on('submit',function(){
        var isPassed = true;
        $('.J-check-input').not(':disabled').each(function () {
            var empty = $(this).val() ? false : true, passed = $(this).data('passed');
            if (empty || (!passed && typeof(passed) != 'undefined')) {
                $(this).parent().addClass('error');
                isPassed = false;
                return false;
            }
        });
        if(isPassed){
            $.loading.show();
        }else{
            return false;
        }
    });

    /*选择银行卡*/
//    $('#J-card-template').on(Touch.move,function(e){
//        e.preventDefault();
//    });
//    $('#J-card-wrap').on(Touch.move,function(e){
//        e.stopPropagation();
//    });
    $(document).on(Touch.end,'#J-card-type li',function(){
        var self = $(this), obj = $('#'+self.data('id'));
        self.addClass('current')
            .siblings('li').removeClass('current');
        obj.show().siblings('ul').hide();
    });

})(Zepto);


var backFn = [];
;(function($){
    var quit = function(backurl){
        Pop({
            content : '是否要放弃本次付款？',
            left : '确定',
            right : '取消',
            leftFn : function () {
                window.location.href = backurl;
            },
            rightFn : function () {
                this.close();
            }
        });
    };


    backFn.push(function(){
        window.history.go(-1);
    });

    $('.nav').on('click','.goback',function(){
        backFn[0]();
    });
    $('.cancel').on(Touch.end,function(){
        var url = $(this).data('url'),
            clstag = $(this).data('clstag');
        $('input').blur();
        quit(url);
        $('#J-pop').find('.pop-left').attr('clstag',clstag);
    });

})(Zepto);

