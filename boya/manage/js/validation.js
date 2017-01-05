(function($){

	$.form = {
		title : "添加",
		checkSelect : function(targetId, msg) {
			var target = $(targetId);
			if (target.get(0).selectedIndex <= 0) {
				jAlert(msg, this.title);
				return false;
			}
			return true;

		},
		
		checkIsNull : function(targetId, msg) {
			var targetValue = $(targetId).val();
			if (targetValue == "") {
				jAlert(msg, this.title);
				return false;
			}
			return true;
		}
		
	}
	
})(jQuery);