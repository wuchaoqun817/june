/*controller.js */
var baseController = angular.module('baseController',[]);

baseController.controller('header_controller', [ '$scope', '$http',
		function($scope, $http) {
			var scope = $scope;

			$http.post('/pmsys/getUser').success(function(data) {
				if (!data) {
					window.location.href = "/pmsys/login";
					location.href = "/pmsys/login"; // 这里改成"#!/listUser"也可以
					return;
				}
				scope.userName = data.userName;
			})
			/**
			 * 退出登录
			 */
			scope.loginOut = function() {
				$http.post('/pmsys/logOut').success(function(data) {

				});
			};

		} ]);

baseController.controller('index_tree_controller', [
		'$scope',
		'requestService','$http',
		function($scope, requestService,$http) {
			var postData = {};
			/**
			 * 获取菜单
			 * */
			requestService.queryMenu(postData).success(
					function(data) {
						$scope.treeData =data;
					});

			
				
		} ])
