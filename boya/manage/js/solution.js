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
			$(data).find("solution").each(function() {
				var id_i = $.readXML.getXMLFiledValue(this, "id");
				var typeId_i = $.readXML.getXMLFiledValue(this, "typeId");
				var name_s = $.readXML.getXMLFiledValue(this, "solutionName");
				var columnId_i = $.readXML.getXMLFiledValue(this, "columnId");
				var columnName_s = $.readXML.getXMLFiledValue(this, "columnName");
				var times_d = $.readXML.getXMLFiledValue(this, "times");
				var money_s = $.readXML.getXMLFiledValue(this, "money");
				var price_s = $.readXML.getXMLFiledValue(this, "price");
				var isTop_i = $.readXML.getXMLFiledValue(this, "isTop");
				var type_s = $.readXML.getXMLFiledValue(this, "type");
				var expireTime_d = $.readXML.getXMLFiledValue(this, "expireTime");
				var action_s = "<a href=\"editSolution.asp?id="+id_i+"\">编辑</a>&nbsp;|&nbsp;<a href=\"deleteSolution.asp?typeId=2&id="+id_i+"\">删除</a>";
				if(typeId_i == 1){
					action_s = "<a href=\"editClinic.asp?id="+id_i+"\">编辑</a>&nbsp;|&nbsp;<a href=\"deleteSolution.asp?id="+id_i+"\">删除</a>";
				}
				var isTop_s = "否";
				if (isTop_i == 0){
					isTop_s = "是";
				}
					
				str += "<tr><td>" + id_i + "</td>";
				str += "<td>" + name_s + "</td>";
				str += "<td>" + columnName_s + "</td>";
				str += "<td>" + money_s + "</td>";
				if(typeId_i == 2)
					str += "<td>" + price_s + "</td>";
				str += "<td>" + isTop_s + "</td>";
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
	$.solution = {
		validateion : function(){
			$.form.title = "添加栏目";
			if(!$.form.checkIsNull("#name","栏目名称不能为空")) return false;
			return true;
		}
		
	};
})(jQuery);

