var aGManage = angular.module('gManage', []);
var path = "/smartscreen";
/**
 * manageRouteConfig 路由config
 */
function manageRouteConfig($routeProvider){
	$routeProvider.
		when('/manage.html', {//设备列表
	      controller: 'facilityList',
	      templateUrl: 'facility-list.html'
	    }).
		when('/manage/1', {//显示列表
	      controller: 'exhibitionList',
	      templateUrl: 'exhibition.html'
	    }).
	    when('/exhibition/add/:equipid/:id', {//显示模板
	      controller: 'exhibitionAdd',
	      templateUrl: 'exhibition-add.html'
	    }).
	    when('/exhibition/:equipid/screen/:screenS/:id/:handle', {//显示设置
	      controller: 'exhibitionScreen',
	      templateUrl: 'exhibition-set.html'
	    }).
	    when('/setting/add', {//显示设置
	      controller: 'settingAdd',
	      templateUrl: 'setting.html'
	    }).
	    otherwise({
	      redirectTo: '/manage.html'
	    });
}

function tansformDataConfig ($httpProvider) { 
	// Use x-www-form-urlencoded Content-Type 
	$httpProvider . defaults . headers . post [ 'Content-Type' ] = 'application/x-www-form-urlencoded;charset=utf-8' ; 
	/** 
	* The workhorse; converts an object to x-www-form-urlencoded serialization. 
	* @param {Object} obj 
	* @return {String} 
	*/ 
	var param = function ( obj ) { 
	var query = '' , name , value , fullSubName , subName , subValue , innerObj , i ; 
	for ( name in obj ) { 
	value = obj [ name ] ; 
	if ( value instanceof Array ) { 
	for ( i = 0 ; i < value . length ; ++ i ) { 
	subValue = value [ i ] ; 
	fullSubName = name + '[' + i + ']' ; 
	innerObj = { } ; 
	innerObj [ fullSubName ] = subValue ; 
	query += param ( innerObj ) + '&' ; 
	} 
	} 
	else if ( value instanceof Object ) { 
	for ( subName in value ) { 
	subValue = value [ subName ] ; 
	fullSubName = name + '[' + subName + ']' ; 
	innerObj = { } ; 
	innerObj [ fullSubName ] = subValue ; 
	query += param ( innerObj ) + '&' ; 
	} 
	} 
	else if ( value !== undefined && value !== null ) 
	query += encodeURIComponent ( name ) + '=' + encodeURIComponent ( value ) + '&' ; 
	} 
	return query . length ? query . substr ( 0 , query . length - 1 ) : query ; 
	} ; 
	// Override $http service's default transformRequest 
	$httpProvider . defaults . transformRequest = [ function ( data ) { 
	return angular . isObject ( data ) && String ( data ) !== '[object File]' ? param ( data ) : data ; 
	} ] ; 
}

aGManage.config(tansformDataConfig);
aGManage.config(manageRouteConfig);



/**
 * settingAdd 显示设置
 */
aGManage.controller('settingAdd',function($scope, $http, $routeParams){
	$scope.model=area;
	$scope.settingAddSubmit = function(){
		var data = {
			'id' 					: $scope.id,
			'code' 				: $scope.code,
			'name' 				: $scope.name,
			'area.id' 			: $scope.area,
			'photo' 				: $scope.photo,
			'category' 			: $scope.category,
			'width' 				: $scope.width,
			'height' 				: $scope.height,
			'startTime' 		: $scope.startTime,
			'closeTime' 		: $scope.closeTime,
			'description' 		: $scope.description
		};
		var config = {
			'method' : 'POST',
			'url' : path+'/equipInfo/edit',
			'data' : data
		};
		$http(config).success(function(data,status,headers,config){
			window.location.href = path+"/views/manage.html";
		}).error(function(data,status,headers,config){
			alert(data.msg);
		});
	};
});
/**
 * exhibitionScreen 显示设置
 */
aGManage.controller('exhibitionScreen', function($scope, $http, $routeParams, $location){
	var url, data, config;
	$scope.screenS = screenTemplate[$routeParams.screenS];
	if($routeParams.handle == 'add'){//设置提交路径
		$scope.handle = '添加';
		
	}else{
		$scope.handle = '修改';
		
	}
	$scope.st = $scope.screenS[$routeParams.id];
	$scope.save = function () {
		if($routeParams.handle == 'add'){//设置提交路径
			url = path + '/content/add';
		}else{
			url = path + '/content/edit';
		}
		data = {//设置提交的数据
				'equip.id': $routeParams.id,
				'name': $scope.name,
				'category': $routeParams.screenS === 'cross' ? 0 : 1,
				'label': $scope.label,
				'time': $scope.time,
				'type': $scope.type,
				'crs[0].resourceInfo.mode':$routeParams.temp,
				'crs[0].resourceInfo.pic': 'pic.jpg',
				'crs[0].resourceInfo.video': 'video.jpg',
				
	//			'audioUrl':$scope.time,
	//			'videoUrl':$scope.time,
	//			'pic':$scope.pic
		};
		config = {//设置提交信息
			'method' 	: 'POST',
			'url' 		: url,
			'data' 		: data
		};
		
		$http(config)
		.success(function () {
			window.location.href = path + "/views/manger.html#/manager/1";
		});
	}
});
/**
 * exhibitionAdd 选屏
 */
aGManage.controller('exhibitionAdd', function($scope, $http, $routeParams){
	$scope.id = $routeParams.id;
	$scope.equipId = $routeParams.equipid;
	$scope.handle = ($routeParams.id - 0) ?  'edit' : 'add';
	$scope.screenS = 'cross';
	$scope.tempCross = '1';
	$scope.verticalCross = '1';
	$scope.$watch('screenS', function(v){//v：是当前选择的value值，书上和网上说$watch会有性能问题
		if(v == 'cross'){
			$scope.crossShow = true;
			$scope.$watch('tempCross', function(v){
				$scope.temp = v;
			});
		}else{
			$scope.crossShow = false;
			$scope.$watch('verticalCross', function(v){
				$scope.temp = v;
			});
		}
		$scope.verticalShow = !$scope.crossShow;
    });
});
/**
 * screenTemplate 屏幕模板
 */
var screenTemplate = {
	'cross' 	: {
		'1' : {
			'tempClass'			: 'g_show_area_cross',
			'pictureClass' 		: '',
			'show': false
		},
		'2' : {
			'tempClass'			: 'g_show_area_cross',
			'pictureClass' 		: '',
			'show': true
		}
	},
	'vertical' 	: {
		'1' : {
			'tempClass'			: 'g_show_area_vertical',
			'pictureClass' 		: '',
			'show': false
		},
		'3' : {
			'tempClass'			: 'g_show_area_vertical',
			'pictureClass' 		: 'g_show_area_vertical_t',
			'show': true
		},
		'4' : {
			'tempClass'			: 'g_show_area_vertical',
			'pictureClass' 		: 'g_show_area_vertical_m',
			'show': true
		},
		'5' : {
			'tempClass'			: 'g_show_area_vertical',
			'pictureClass' 		: 'g_show_area_vertical_b',
			'show': true
		}
	}
};


/**
 * contentList 显示数据列表，目前是假数据，需要ajax返回
 
var contentList = [
	{
		'id' 			: 4,
		'name' 			: '第一个',
		'playCount' 	: 10,//轮播数量
		'place' 		: 'abc',//位置
		'playType' 		: 1,//1为随机，2为顺序
		'list' 			: [
			{
				'id' 				: 1,
				'showlevel' 		: 1,//序号
				'lable' 				: 'aa',
				'status' 			: true
			},
			{
				'id' 				: 2,
				'showlevel' 		: 2,//序号
				'lable' 				: 'bb',
				'status' 			: true
			},
			{
				'id' 				: 3,
				'showlevel' 		: 3,//序号
				'lable' 				: 'cc',
				'status' 			: true
			}
		]
	},
	{
		'id' 			: 1,
		'name' 			: '第二个',
		'playCount' 	: 3,//轮播数量
		'place' 		: 'abc',//位置
		'playType' 		: 1,//1为随机，2为顺序
		'list' 			: [
			{
				'id' 				: 1,
				'showlevel' 		: 1,//序号
				'lable' 			: 'aa',
				'status' 			: true
			},
			{
				'id' 				: 2,
				'showlevel' 		: 2,//序号
				'lable' 				: 'bb',
				'status' 			: true
			},
			{
				'id' 				: 3,
				'showlevel' 		: 3,//序号
				'lable' 				: 'cc',
				'status' 			: true
			}
		]
	},
	{
		'id' 			: 2,
		'name' 			: '第三个',
		'playCount' 	: 2,//轮播数量
		'place' 		: 'abc',//位置
		'playType' 		: 2,//1为随机，2为顺序
		'list' 			: [
			{
				'id' 				: 1,
				'showlevel' 		: 1,//序号
				'lable' 				: 'aa',
				'status' 			: true
			},
			{
				'id' 				: 2,
				'showlevel' 		: 2,//序号
				'lable' 				: 'bb',
				'status' 			: true
			},
			{
				'id' 				: 3,
				'showlevel' 		: 3,//序号
				'lable' 				: 'cc',
				'status' 			: true
			}
		]
	},
	{
		'id' 			: 3,
		'name' 			: '第四个',
		'playCount' 	: 1,//轮播数量
		'place' 		: 'abc',//位置
		'playType' 		: 2,//1为随机，2为顺序
		'list' 			: [
			{
				'id' 				: 1,
				'showlevel' 		: 1,//序号
				'lable' 				: 'aa',
				'status' 			: true
			},
			{
				'id' 				: 2,
				'showlevel' 		: 2,//序号
				'lable' 				: 'bb',
				'status' 			: true
			},
			{
				'id' 				: 3,
				'showlevel' 		: 3,//序号
				'lable' 				: 'cc',
				'status' 			: true
			}
		]
	}
];
*/

/**
 * exhibitionList 显示列表
 */
aGManage.controller('exhibitionList', function($scope, $http){

	$http.get(path+"/content/list").success(function(data){
//		area = data;
		$scope.exhibitions = data;
		$scope.model = data; 
	});
	
	$scope.getListByEquip=function (){
		$http.post(path+'/content/query',{'equipId':$scope.contentList.id}).success(function(data){
			$scope.exhibitions = data;
		});
	};
	
	$scope.setStatus = function(status,id){
		if(status == 0){
			status=1; //启用
		}else{
			status=0; //暂停
		}
		var param = {"id":id,"status":status,"operate":"changeStatus"};
//		$http.post('../content/edit',param).success(function(data){
//			
//			$scope.model = equipList;
//			$scope.contentList = newValue;
//			$scope.getListByEquip();
//		});
		updateContent(path+'/content/edit',param);
	};
	function updateContent(url,data){
		$http.post(url,data).success(function(data){
			$scope.getListByEquip();
		});
	}
	
	$scope.setRising = function(iNum){//上升
		if(iNum == 1)return;
		var url=path+'/content/edit';
		var showlevel = iNum-1;
//		alert($scope.exhibitions[iNum-1].id);
		var data = {'id':$scope.exhibitions[iNum-1].id,'showlevel':showlevel,'description':$scope.exhibitions[iNum-2].id,'operate':'rising'}
		updateContent(url,data);
		var iNum1 = $scope.exhibitions[iNum-1],
        	iNum2 = $scope.exhibitions[iNum-2],
        	iSort1 = $scope.exhibitions[iNum-1].showlevel,
        	iSort2 = $scope.exhibitions[iNum-2].showlevel;
        $scope.exhibitions[iNum-2].showlevel = iSort1;
        $scope.exhibitions[iNum-1].showlevel = iSort2;
        $scope.exhibitions.splice((iNum-2), 2, iNum1, iNum2);
	}	
	$scope.setFalling = function(iNum){//下降
		if(iNum >= $scope.exhibitions.length)return;
		var url=path+'/content/edit';
		var showlevel = iNum+1;
//		alert($scope.exhibitions[iNum-1].id);
		var data = {'id':$scope.exhibitions[iNum-1].id,'showlevel':showlevel,'description':$scope.exhibitions[iNum-2].id,'operate':'falling'}
		updateContent(url,data);
        var iNum1 = $scope.exhibitions[iNum-1],
        	iNum2 = $scope.exhibitions[iNum],
        	iSort1 = $scope.exhibitions[iNum-1].showlevel,
        	iSort2 = $scope.exhibitions[iNum].showlevel;
        $scope.exhibitions[iNum].showlevel = iSort1;
        $scope.exhibitions[iNum-1].showlevel = iSort2;
        $scope.exhibitions.splice((iNum-1), 2, iNum2, iNum1);
	};
});

var area = []

/**
 * facilityList 设备列表
 */
aGManage.controller('facilityList', function($scope, $http){
	/**
	 * $scope.facility 设备管理列表 目前是假数据，需要ajax返回
	 */
	$http.get(path+"/equipInfo/getArealist").success(function(data){
		area = data;
		$scope.contentList = data;
		$scope.model = data; 
	});
//	$scope.select1 = area;
	$http.get(path+"/equipInfo/list").success(function(data){
		$scope.facilities=data;
	});
	
	
//	$scope.$watch('select1',function(){
//		
//		var data={'area.id':$scope.select1};
//		var config = {
//				'method' : 'POST',
//				'url' : '../equipInfo/query',
//				'data' : data
//			};
//		$http(config).success(function(data){
//			$scope.facilities=data;
//		});
////		$scope.select1 = area;
////		
////		console.log(newValue);
//	});
	$scope.queryList=function(){
		$http.post(path+"/equipInfo/query",{'area.id':$scope.contentList.id}).success(function(data){
//			$scope.contentList=area;
			$scope.facilities=data;
//			$scope.model = area; 
		});
	};
	/**$scope.$watch('area', function(){
		
		$scope.facilities = $scope.contentList.list;//设置列表
	});*/
});
/**
 * menu 菜单
 */
aGManage.controller('menu', function($scope, $http){//设置菜单
	/**
	 * $scope.menus 菜单数组
	 */
	$scope.menus = [
		{
			'name' 		: '设备管理',
			'classN' 	: 'z-on',
			'url' 		: 'manage/0'
		},
		{
			'name' 		: '显示管理',
			'classN' 	: '',
			'url' 		: 'manage/1'
		}
	];

	/**
	 * $scope.tabSwitch 页面切换
	 * @param  {number} iNum 当前序号
	 */
	$scope.tabSwitch = function(iNum){
		if($scope.menus[iNum].classN == ''){
			var len = $scope.menus.length;
			for(var i = 0; i < len; i++){
				if(i == iNum){
					$scope.menus[i]['classN'] = 'z-on';
				}else{
					$scope.menus[i]['classN'] = '';
				}
			}
		}
	};
});