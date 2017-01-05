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
		getData : function(url, data, source) {
			$.ajax({
				url : url,
				data : data,
				type : "post",
				success : function(data, textStatus) {
					$.readXML.writeHtml(data, this.data, this.url, source);
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
		writeHtml : function(data, param, url, source) {
			var itemPageSize_i = $(data).find("itemPageSize").text();
			var itemCount_i = $(data).find("itemCount").text();
			var pageIndex_i = $(data).find("pageIndex").text();
			var str = "";
			var i_i = 1;
			$(data).find("article").each(function() {
				var typeId_i = $.readXML.getXMLFiledValue(this, "typeId");								  
				var id_i = $.readXML.getXMLFiledValue(this, "id");
				var subject_s = $.readXML.getXMLFiledValue(this, "subject");
				var columnName_s = $.readXML.getXMLFiledValue(this, "columnName");
				var regTime_d = $.readXML.getXMLFiledValue(this,"regTime");
				var author_s = $.readXML.getXMLFiledValue(this, "author");
				var isHot_i = $.readXML.getXMLFiledValue(this, "isHot");
				var isTop_i = $.readXML.getXMLFiledValue(this, "isTop");
				var statistics_i = $.readXML.getXMLFiledValue(this, "statistics");
				var status_s = "";
				var isTop_s = "";
				var isHot_s = "";
				var action_s = "<a href=\"#\" onclick=\"window.open('editArticles.asp?id="+id_i+"');\">编辑</a>&nbsp;|&nbsp;<a href=\"deleteArticles.asp?typeId="+typeId_i+"&id="+id_i+"\">删除</a>";
				if (isHot_i == 0){
					isHot_s = "是";
				}else{
					isHot_s = "否";
				}
				if (isTop_i == 0){
					isTop_s = "是";
				}else{
					isTop_s = "否";
				}
					
				str += "<tr><td>" + id_i + "</td>";
				str += "<td class=\"subject\"><a href=\"#\" onclick=\"window.open('editArticles.asp?id="+id_i+"&source="+source+"');\" title=\""+subject_s+"\">" + subject_s + "</a></td>";
				str += "<td>" + columnName_s + "</td>";
				str += "<td>" + isTop_s + "</td>";
				str += "<td>" + isHot_s + "</td>";
				str += "<td>" + statistics_i + "</td>";
				str += "<td>" + regTime_d + "</td>";
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
	$.articles = {
		
		getArticlesList : function(data,url){
			if(typeof url == "undefined" || url == "")
				url = "queryArtilces.asp";
			$.util.ajax(url,data,$.articles.setArticlesList);	
		},
		
		setArticlesList : function(data){
			var columnId = $("#articleList").attr("column") || 0;
			var id = $("#articleList").attr("isId") || 0;
			var str = "<select id=\"articleId\" name=\"articleId\"><option value=\"0\">--请选择分类--</option>"; 
			$(data).find("article").each(function() {
				var id_i = $.readXML.getXMLFiledValue(this, "id");
				var username_s = $.readXML.getXMLFiledValue(this, "subject");
				var selected = "";
				if(columnId == id_i){
					selected = "selected=\"selected\"";
				}
				if(id_i != id)
					str += "<option "+selected+" value=\""+id_i+"\">"+username_s+"</option>";
			});
			str += "</select>";
			
			$("#articleList").empty().html(str);

		},
		
		validation : function(){
			$.form.title = "添加文章";
			if(!$.form.checkIsNull("#subject","文章标题不能为空")) return false;
			if(!$.form.checkSelect("#columnId","请选择文章所属栏目")) return false;
			else $("#columnName").val($("#columnId option:selected").text());
//			if(!$.form.checkIsNull("#source","文章来源不能为空")) return false;
//			if(!$.form.checkIsNull("#author","文章作者不能为空")) return false;
			if(!$.form.checkIsNull("#created","文章添加时间不能为空")) return false;
			// if(!$.form.checkIsNull("#summary","文章摘要不能为空")) return false;
			if(!$.form.checkIsNull("#content","文章内容不能为空")) return false;
			// alert($("#columnName").val());
			return true;
		},
		
		search : function(){
			var subject = $("#subject").val();
			var columnId = $("#columnId").val();
			var url = "queryArticles.asp";
			var searchParam_s = "typeId=1&subject="+subject+"&columnId="+columnId+"&pageIndex=1";
			$.readXML.getData(url, searchParam_s);
		}
	};
})(jQuery);

