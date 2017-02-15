/**
 * jndi配置js库
 * 
 */
var JndiSetting = function(_targetObj) {
	this.$owner = _targetObj;
	this.$console = _targetObj.find("#console");
	this.init();
	
};

JndiSetting.prototype = {
		
		init: function() {
			var self = this;
			
			this.$owner.find("#btnStep1").click(function(){
				self.step1();
			});
			this.$owner.find("#btnFinish").click(function() {
				self.finish();
			});
			
			this.$owner.find("#databaseName").blur(function(){
				self.objOnblur('databaseName', '数据库名称不能为空');
			});
			this.$owner.find("#host").blur(function(){
				self.objOnblur('host', '主机名不能为空');
			});
			this.$owner.find("#port").blur(function(){
				self.objOnblur('port', '端口不能为空');
			});
			this.$owner.find("#userName").blur(function() {
				self.objOnblur('userName', '数据库用户名不能为空');
			});
			this.$owner.find("#password").blur(function(){
				self.objOnblur('password', '密码不能为空');
			});
			
			//启动comet4j
			try {
				JS.Engine.on({  
					jndisetting : function(data){//侦听输出日志通道 
						self.onJndisettingRecv(data);
	                }
		        });
		        JS.Engine.start('comet');
			} catch (e) {
				console.error("启动comet4j失败！error-->"+e.message);
			}
		},
		
		/**
		 * 第１步，即开始初始化
		 */
		step1: function() {
			//先检查连接正确与否
			if(!jndiSetting.checkInput()) {
				return false;
			}
			
			//检查连接
			if (!jndiSetting.testConn()) {
				alert("连接测试失败！请检查数据库信息！")
				return false;
			};
			//转向下一步界面
			jndiSetting.$owner.find('#step_1').hide();
			jndiSetting.$owner.find('#step_2').show();
			
			//开始初始化
			jndiSetting.startInit();
		},
		
		/**
		 * 完成，提示界面
		 */
		finish: function() {
			jndiSetting.$owner.find('#step_2').hide();
			jndiSetting.$owner.find('#step_3').show();
			//alert('配置成功！');
		},
		
		/**
		 * 开始初始化数据库脚本。
		 * @returns {Boolean}
		 */
		startInit: function() {
			jndiSetting.$owner.find("#btnFinish").attr("disabled","disabled");
			var params = jndiSetting.$owner.find("#frmDbinfo").serialize();
			$.ajax({
		           url: "appcfg.do?method=startInit&" + Math.random(),
		           type: 'POST',
		           data: params,
		           dataType: "json",
		           success: function(data){
		        	   console.log("------->"+JSON.stringify(data));
		           }
			});
		},
		
		objOnblur: function(objId, msg) {
			var self = this;
			var $obj = self.$owner.find("#"+objId);
			if ($obj.val() == "") {
				self.$owner.find("#tip_"+objId).html(msg);
			} else {
				self.$owner.find("#tip_"+objId).html("");
			}
			return false;
		},
		
		checkInput: function() {
			return check("databaseName", '数据库名称不能为空') &&
			check("host", '主机名不能为空') &&
			check("port", '端口不能为空') &&
			check("userName", '数据库用户名不能为空') &&
			check("password", '密码不能为空');
			function check(objId, msg) {
				var $obj = jndiSetting.$owner.find("#"+objId);
				if ($obj.val() == "") {
					jndiSetting.$owner.find("#tip_"+objId).html(msg);
					$obj.focus();
					return false;
				} else {
					jndiSetting.$owner.find("#tip_"+objId).html("");
					return true;
				}
			};
		},
		
		testConn: function() {
			var params = jndiSetting.$owner.find("#frmDbinfo").serialize();
			var checkOK = false;
			var self = this;
			$.ajax({
		           url: "appcfg.do?method=testConn&" + Math.random(),
		           type: 'POST',
		           data: params,
		           async: false,
		           dataType: "json",
		           success: function(data){
		        	   var re = self.json2obj(data);
		        	   if (re && re.success === true) {
		        		   checkOK = true;
		        	   }
		           }
			});
			return checkOK;
		},
		
		onJndisettingRecv: function(_data) {
			console.log("----------->>>>"+JSON.stringify(_data));
			var data = this.json2obj(_data);
			var msg = data.message;
			if (!msg) {
				return;
			}
			if (msg == "finish") {
				jndiSetting.$owner.find("#btnFinish").removeAttr("disabled");
				return;
			}
			var html = "";
			html = ".[<strong>"
			if(data.level == "WARN"){
				html += "警告</strong>] ";
				html = "<label class='warn'>"+html+msg+"</label>";
				infoStyle = "warn";
			}else if(data.level == "ERROR"){
				html += "错误</strong>] ";
				html = "<label class='error'>"+html+msg+"</label>";
				infoStyle = "error";
			}else{
				html += "信息</strong>] ";
				infoStyle = "info";
				html += msg;
			}
			html = '<p class="'+infoStyle+'">' + html + '</p>';
			$(html).appendTo(this.$console);
			this.$console.scrollTop(this.$console.get(0).scrollHeight);
		},
		
		json2obj: function(str){
			if(typeof str === "string"){
				window.__temp = undefined;
				try{
					eval("window.__temp = (" + str + ");");
				}catch(e){};
				return window.__temp;
			};
			return str;
		}
		
};


/*$("#console").width((function(){ 
	var _rootEl = document.compatMode=="CSS1Compat" ? document.documentElement : document.body;
	return _rootEl.clientWidth - 300;
})());
$("#console").height((function(){
	var _rootEl = document.compatMode=="CSS1Compat" ? document.documentElement : document.body;
	return _rootEl.clientHeight - 200;
})());*/

