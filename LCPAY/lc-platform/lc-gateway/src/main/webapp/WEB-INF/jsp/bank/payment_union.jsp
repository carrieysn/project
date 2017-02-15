<%--
  Created by IntelliJ IDEA.
  User: sweet
  Date: 16-9-20
  Time: 上午10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width,height=device-height">
    <title>银信证支付</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/component/aui-artDialog-5024803/css/ui-dialog.css" />">
</head>
<body>
<div class="header">
    <div class="container v-middle">
        <img src="<c:url value="/resources/images/logo.png" />" class="">
    </div>
</div>
<div class="page-container">
    <div class="forminfoWrap">
        <div class="container clear">
            <div class="fl col-md-9 col-xs-12">
                <h3 class="orderinfo">订单信息</h3>
                <div class="f-info">
                    <ul>
                        <li>收款方：${merchantName}</li>
                        <li class="min-hide">订单号：${preLcDto.getOrderId()}</li>
                        <li>商品名称：${preLcDto.getOrderContent()}</li>
                        <li class="min-hide">交易金额：${amount}元</li>
                        <li class="min-hide">银信证规则：出票再收款</li>
                        <li class="min-show"><a href="javascript:void(0);" class="btn btn-link">展开更多>></a></li>
                    </ul>
                </div>
            </div>
            <div class="fl col-md-3 col-xs-12 f-other v-middle">
                <div class="color-blue ">
                    <font size="60">${amount.substring(0,amount.indexOf("."))}</font>.${amount.substring(amount.indexOf(".")+1)}元
                </div>
                <p class="p-block">出票再收款</p>
                <p class="p-block" id="timeoutContent">剩余开证时间<span id="timeout"></span>分钟</p>
            </div>
        </div>
    </div>
    <div class="paymentWrap">
        <div class="container">
            <ul class="nav nav-tabs clear">
                <li id="tabCredit" class="active">
                    <a href="#credit" data-toggle='tab'>信用卡</a>
                </li>
                <li id="tabDeposit">
                    <c:if test="${depositMobileNoDisplay == null}">
                        <c:set var="tabName" value="deposit"/>
                    </c:if>
                    <c:if test="${depositMobileNoDisplay != null}">
                        <c:set var="tabName" value="deposit2"/>
                    </c:if>

                    <a href="#${tabName}" data-toggle='tab'>储蓄卡</a>
                </li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane fade active in" id="credit">
                    <form class="form-horizontal" action="<c:url value="/bank/unionpay/credit" />" method="post">
                        <div class="form-group">
                            <label for="" class="col-sm-2 control-label">卡号</label>
                            <div class="col-sm-10">
                                <input type="tel" class="form-control accountNo" name="accountNo" value="${creditAccountNoDisplay}" placeholder='请输入信用卡号' pattern="^(\d{4} |\*{4} ){3}\d{3,4}$" data-error="请输入信用卡号" maxlength="23" required>
                            </div>
                            <div class="col-sm-10 col-sm-offset-2">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10 help-block credit-help">
                                此服务由银联提供
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-2 control-label">
                                有效期
                                <i class="glyphicon glyphicon-info-sign" data-toggle='tooltip' data-placement='top' title='请填写卡号下面的有效期' id='tooltip-indate'></i>

                            </label>
                            <div class="col-sm-10">
                                <%--<input type="text" class="form-control form_datetime" name="expiryDate" data-date-format='mm/yyyy' placeholder='有效期' data-error="请选择信用卡有效期" required>--%>
                                <input type="tel" class="form-control on-indate" name="expiryDate" placeholder='月份/年份' maxlength="5" data-required-error="请填写信用卡有效期" data-expiry="true" required>
                            </div>
                            <div class="col-sm-10 col-sm-offset-2">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-2 control-label">信用卡验证码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="cvv2" placeholder='卡背后末三位数字' maxlength="3" pattern="^\d{3}$" data-error="请输入三位数字验证码" required>
                            </div>
                            <div class="col-sm-10 col-sm-offset-2">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="" class="col-sm-2 control-label">手机号码</label>
                            <div class="col-sm-10">
                                <input type="tel" class="form-control" name="mobileNo" value="${creditMobileNoDisplay}" placeholder='此卡在银行预留的手机号码' pattern="^\d{3}(\d{4}|\*{4})\d{4}$" data-error="请输入正确的手机号码" required>
                            </div>
                            <div class="col-sm-10 col-sm-offset-2">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-sm-2 control-label">短信验证码</label>
                            <div class="col-sm-10">
                                <div class="form-inline">
                                    <input type="tel" name="smsCode" class="form-control" placeholder='发送至您手机的六位短信验证码' maxlength="6" pattern="^\d{6}$" data-error="请输入短信验证码" required>

                                    <button class="form-control sendSms" data-cardType="credit">获取验证码</button>
                                </div>
                            </div>
                            <div class="col-sm-10 col-sm-offset-2">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10 checkbox">
                                <label>
                                    <input type="checkbox" name="remember" checked>
                                    保存至常用信用卡，并同意<a href="_b" target="_blank">《常用卡支付服务协议》</a>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="lcId" value="${preLcDto.getLcId()}"/>

                            <div class="col-sm-offset-2 col-sm-10">
                                <input type="submit" class="btn btn-primary" value="付款">
                            </div>
                        </div>
                    </form>
                </div>

                <div class="tab-pane" id="deposit">
                    <form action="<c:url value="/bank/unionpay/opencard" />" method="post">
                        <div class="form-group">
                            <label for="" class="control-label">请输入要付款的银联卡号</label>
                            <input type="tel" class="form-control accountNo" name="accountNo" placeholder='请输入银联卡号' pattern="^(\d{4} ){3}\d{3,4}( \d{0,3})?$" data-error="请输入银联卡号" maxlength="23" required/>
                        </div>
                        <div class="help-block with-errors"></div>

                        <%--<p class="help-block ">--%>
                        <%--XXXX XXXX XXXX XXXX XXXX--%>
                        <%--</p>--%>
                        <div class="form-group">
                            <img src="<c:url value="/resources/images/payment.png" />" class="banklogo" alt="">
                            <p class="help-block">由银联在线支付提供安全支付服务，保障您的资金安全</p>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="lcId" value="${preLcDto.getLcId()}"/>
                            <input type="submit" class="btn btn-primary" value="下一步">
                        </div>
                    </form>
                </div>

                <div class="tab-pane" id="deposit2">
                    <form action="<c:url value="/bank/unionpay/deposit" />" method="post">
                        <div class="form-inline">
                            <div class="form-group">
                                <label>储蓄卡卡号</label>
                            </div>
                            <div class="form-group">
                                <select name="accountNo" id="accountNo" class="form-control">
                                    <option>
                                        ${depositAccountNoDisplay} 储蓄卡
                                    </option>
                                </select>
                            </div>
                            <a href="#deposit" class="btn btn-link" data-toggle='tab'>更改</a>
                        </div>
                        <div class="form-group">
                            <img src="<c:url value="/resources/images/payment.png" />" class="banklogo" alt="">
                            <p class="help-block">由银联在线支付提供安全支付服务，保障您的资金安全</p>
                        </div>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="" class="control-label title-width">手机号码</label>
                            </div>
                            <div class="form-group">
                                <%--<input type="tel" class="form-control" name="mobileNo" value="" placeholder='此卡在银行预留的手机号码' pattern="^\d{11}$" data-error="请输入正确的手机号码" required>--%>
                                <label>${depositMobileNoDisplay}</label>
                            </div>
                            <div class="col-sm-10 col-sm-offset-2">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="">短信验证码</label>
                            </div>
                            <div class="form-group">
                                <input type="tel" name="smsCode" class="form-control" placeholder='发送至您手机的六位短信验证码' maxlength="6" pattern="^\d{6}$" required>
                                <button class="form-control button btn-success hack-getCode sendSms" data-cardType="deposit">获取验证码</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="lcId" value="${preLcDto.getLcId()}"/>
                            <input type="submit" class="btn btn-primary" value="确认支付">
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>


<script src='<c:url value="/resources/js/util/common.js" />'></script>
<script src='<c:url value="/resources/js/jquery-1.12.1.min.js" />'></script>
<script src='<c:url value="/resources/js/bootstrap.min.js" />'></script>
<script src='<c:url value="/resources/component/bootstrap-validator-0.11.5/validator.min.js" />'></script>
<script src='<c:url value="/resources/component/aui-artDialog-5024803/dist/dialog-min.js" />'></script>
<script>
    $.fn.validator.Constructor.VALIDATORS.expiry = function (el) {
        var value = el.val() && el.val().trim();

        if (value != undefined && value.length > 0) {
            var len = value.length;
            var month = value.substring(0, Math.min(2, len));

            if (!/^(0[0-9])|(1[0-2])$/.test(month)) {
                return "月份填写错误";
            }

            if (len > 3) {
                var year = value.substring(3)

                if (!/^\d{2}$/.test(year)) {
                    return "年份填写错误";
                }
            }
        }
    }

    //银行卡号格式
    $('.accountNo').on('input propertychange change', function (e) {
        var target = e.srcElement || e.target;
        var accountNo = $(target).val();

        if (accountNo != undefined && accountNo.length > 0) {
            accountNo = accountNo.replace(/\s/g, '');
            var pettyAccountNo = accountNo.replace(/(\S{4})/g, "$1 ").trim();
            $(target).val(pettyAccountNo);
        }
    }).trigger('change');

    //信用卡有效期
    $('input[name="expiryDate"]').on('input propertychange change', function (e) {
        var target = e.srcElement || e.target;
        var expiryDate = $(target).val();

        //有效期格式化
        if (expiryDate != undefined && expiryDate.length > 0) {
            expiryDate = expiryDate.replace(/[\s|\/]/g, '');
            var pettyExpiryDate = expiryDate.replace(/(\d{2})(\/)?(\d{2})?/g, "$1\/$3").trim();
            $(target).val(pettyExpiryDate);
//            console.log(pettyExpiryDate);
        }
    }).on('keydown', function (event) {
        var target = event.srcElement || event.target;
        var keyCode = event.keyCode || event.which;

        if (8 == keyCode) {
            var expiryDate = $(target).val();
            var splitIndex = expiryDate.indexOf('/');
            if (splitIndex >= expiryDate.length - 1) {
                $(target).val(expiryDate.substring(0, splitIndex - 1));

                return false;
            }
        }
    });

    //展开更多click事件...
    $('.min-show').on('click', function (e) {
        var ele = e.srcElement || e.target;
        $('.min-hide').show();
        $(ele).hide();
    })

    //“有效期”说明提示初始化
    $('#tooltip-indate').tooltip()

    $('form').validator().on('submit', function (e) {
        if (e.isDefaultPrevented()) {
            // handle the invalid form...
        } else {
            // everything looks good!

            //提交表单前修改内容
            var accountElements = $(':input[name=accountNo]:visible');
            $.map(accountElements, function (dom, i) {
                var ele = $(dom);
                if (ele.is('select')) {
                    return;
                }
                var pettyAccountNo = ele.val().replace(/\s/g, '');
                ele.val(pettyAccountNo);
            });
            return true;
        }
    });
</script>
<script>
    var alert = function (message) {
        var d = dialog({
            content: message
        });
        d.show();
        setTimeout(function () {
            d.close().remove();
        }, 2000);

    };

    // 计算开证有效时间
    var timeout = new Date(${preLcDto.getOpenValidTime().getTime()});
    var now = new Date(${now.getTime()});
    var second = (timeout.getTime() - now.getTime()) / 1000;
    var handle = function () {
        var minute = Math.ceil(second / 60);
        $('#timeout').text(minute);
        second--;

        if (minute <= 0) {
            $('#timeoutContent').text('开证时间已过');
            $('form :input').attr("disabled", "disabled");
            $('button').attr("disabled", "disabled");
        } else {
            setTimeout(handle, 1000);
        }
    };

    //设置开证时间定时器
    if (!isNaN(second)) {
        setTimeout(handle, 0);
    }

    //验证码逻辑
    var sendSmsHandler = function (mobileObj, smsObj) {
        var apiUrl = '<c:url value="/bank/unionpay/sendVerifyCode" />'
        var sms = $(smsObj);
        var regex = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])(\d{8}|\*{4}\d{4})$/;

        var handler = function () {
            var ele = $(this);
            var timeout = 30;
            var mobileEle = $(mobileObj).filter(':visible');
            var mobileNo = mobileEle.val();
//            var accountNo = $(':input[name=accountNo]:visible').val().replace(/\s/g, '');
            var lcId = $('input[name=lcId]').val();
            var cardType = ele.attr('data-cardType');

            var target = $(this);
            var rawSmsText = target.text();
            var timeOutHandler = function () {
                timeout--;
                target.text("重新发送(" + timeout + ")");
                target.attr("disabled", "disabled");
                mobileEle.attr("readonly", "readonly");

                if (timeout <= 0) {
                    target.text(rawSmsText);
                    target.removeAttr('disabled');
                    mobileEle.removeAttr('readonly');
                } else {
                    setTimeout(timeOutHandler, 1000);
                }
            };

            if (regex.test(mobileNo) || cardType === 'deposit') {
                target.attr("disabled", "disabled");

                //发送验证码
                $.ajax({
                    type: 'post',
                    url: apiUrl,
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify({
                        mobileNo: mobileNo,
                        cardType: cardType,
                        lcId: lcId
                    }),
                    success: function (_body) {
                        if (_body.success) {
                            console.log("send sms success");

                            //启用输入框
                            sms.removeAttr('disabled');
                            sms.val('');
                            //计时
                            setTimeout(timeOutHandler, 0);
                        } else {
                            alert(_body.message);
                            target.removeAttr('disabled');
                        }
                    },
                    error: function () {
                        console.log('error');
                        target.removeAttr('disabled');
                    }
                });

            } else {
                alert('请输入正确的手机号码');
            }

            return false;
        }

        return handler;
    }

    //绑定验证码按钮
    $('.sendSms').on('click', sendSmsHandler($('input[name=mobileNo]'), $('.sendSms').prev('input')));

    //储蓄卡开卡成功时打开付款页面
    var tabId = getQueryString('tab'); // 'tabDeposit';
    $(function () {
        //打开储蓄卡页面
        if (tabId != undefined && tabId.length > 0) {
            $('#' + tabId).addClass('active').siblings('li').removeClass('active');
            $('#deposit2').addClass('active in').siblings('div.tab-pane').removeClass('active').removeClass('in');
        }
    });
</script>
</body>
</html>