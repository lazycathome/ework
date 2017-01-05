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
			$(data).find("feedback").each(function() {
				var id_i = $.readXML.getXMLFiledValue(this, "id");
				//var typeId_i = $.readXML.getXMLFiledValue(this, "typeId");
				var title_s = $.readXML.getXMLFiledValue(this, "title");
				var username_s = $.readXML.getXMLFiledValue(this, "username");
				var createTime_d = $.readXML.getXMLFiledValue(this, "regTime");
				var lastTime_d = $.readXML.getXMLFiledValue(this, "lastTime");
				var reply_s = $.readXML.getXMLFiledValue(this, "reply");
				var author_s = $.readXML.getXMLFiledValue(this, "author");
				var tel_s = $.readXML.getXMLFiledValue(this, "tel");
				//var expireTime_d = $.readXML.getXMLFiledValue(this, "expireTime");
				var action_s = "<a href=\"replyFeedback.asp?id="+id_i+"\">回复</a>&nbsp;|&nbsp;<a href=\"deleteFeedback.asp?id="+id_i+"\">删除</a>";
				str += "<tr><td>" + id_i + "</td>";
				str += "<td>" + title_s + "</td>";
				str += "<td>" + username_s + "</td>";
				str += "<td>" + tel_s + "</td>";
				str += "<td>" + createTime_d + "</td>";
				str += "<td>" + reply_s + "</td>";
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
			jAlert("网络繁忙，请稍后再试...", "留言管理");
		}
	};
})(jQuery);



(function($){
	$.feedback = {
		validateion : function(){
			$.form.title = "留言板回复";
			if(!$.form.checkIsNull("#replyContent","回复留言不能为空")) return false;
			return true;
		}
		
	};
})(jQuery);

