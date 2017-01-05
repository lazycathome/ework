/**
 * 显示列表
 */
(function($) {
	$.readXML = {
		data_Body : "#data_body",
		data_Page : "#data_page",
		today_Count : "#recordCount",
		tody_SuccessCount : "#recordSuccessCount",
		/**
		 * 提取数据
		 * 
		 * @param url
		 *            请求路径
		 * @param data
		 *            参数
		 */
		getData : function(url, data) {
			$.ajax({
				url : url,
				data : data,
				type : "post",
				success : function(data, textStatus) {

					$.readXML.writeHtml(data, this.data, this.url);
				},
				error : function(msg) {
					$.readXML.error(msg);
				}
			});
		},
		/**
		 * 格式化数据显示到页面上
		 * 
		 * @param data
		 *            数据
		 * @param 参数
		 * @param 路径
		 */
		writeHtml : function(data, param, url) {
			var itemPageSize_i = $(data).find("itemPageSize").text();
			var itemCount_i = $(data).find("itemCount").text();
			var pageIndex_i = $(data).find("pageIndex").text();
			var str = "";
			var i_i = 1;
			$(data).find("account").each(function() {
				var id_i = $.readXML.getXMLFiledValue(this, "id");
				var username_s = $.readXML.getXMLFiledValue(this, "username");
				var password_s = $.readXML.getXMLFiledValue(this, "password");
				var des_s = $.readXML.getXMLFiledValue(this, "description");
				var display_i = $.readXML.getXMLFiledValue(this, "display");
				var regtime_d = $.readXML.getXMLFiledValue(this, "regTime");
				var diaplay_s = "";
				var action_s = "<a href=\"editAccount.asp?id="+id_i+"\">修改</a>&nbsp;|&nbsp;<a href=\"deleteAccount.asp?id="+id_i+"\">删除</a>";
				if (display_i == 0){
					display_s = "显示";
				}else{
					display_s = "隐藏";
				}
					
				str += "<tr><td>" + id_i + "</td>";
				str += "<td>" + username_s + "</td>";
				str += "<td>" + password_s + "</td>";
				str += "<td>" + des_s + "</td>";
				str += "<td>" + regtime_d + "</td>";
				str += "<td>" + action_s + "</td></tr>";
			})
			//分页
			var dataBody = $($.readXML.data_Body);
			dataBody.empty().append(str);
			if (pageIndex_i <= 1) {
				$("#data_page").mypagination(itemCount_i, {
					perPage : itemPageSize_i,
					callback : function(page) {
						var params = param.split("=");
						var param1 = params[params.length - 1];
						var param_s = param.substring(0, param.length
										- param1.length);
						$.readXML.getData(url, (param_s + page));

					}
				});
			}
		},
		
		/**
		 * 提取xml文件的值。解析xml节点
		 */
		getXMLFiledValue : function(o, filed) {
			return $(o).find(filed).text();
		},
		
		/**
		 * 提取xml文件的值。解析xml节点
		 */
		getXMLFiledValue : function(o, filed) {
			return $(o).find(filed).text();
		},
		/**
		 * 提取xml文件的属性
		 */
		getXMLFiledAttr : function(o, filed, attr) {
			return $(o).find(filed).attr(attr);
		},

		error : function(msg) {
			jAlert("网络繁忙，请稍后再试...", "账户管理");
		}
	};
})(jQuery);



(function($){
	$.account = {
		
		validateion : function(){
			$.form.title = "添加栏目";
			if(!$.form.checkIsNull("#name","栏目名称不能为空")) return false;
			return true;
		}
		
	};
})(jQuery);

