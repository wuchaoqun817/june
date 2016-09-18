/*service list  由service 与后台进行交互*/
var baseService=angular.module("baseServcie",[]);


baseService.factory('requestService', function($http) {
	//查询菜单栏
	var queryMenu = function(postData) {
		return $http.post('/pmsys/queryMenu', postData);
	}
	return {
		queryMenu : function(postData) {
			return queryMenu(postData);
		}
	}
})

//app.factory('userListService', ['$http', function($http){
//	var doRequest=function(username,path){
//		return $http({
//			method:"GET",
//			url:'users.json'
//		});
//	}
//	return{
//		userList:function(username){
//		return doRequest(username,'userList');
//	}
//	}
//}])