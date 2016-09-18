///*app.js主要是做前段路由，怎么跳转*/
var baseDirective = angular.module("baseDirective", []);
baseDirective.directive('treeMenu', // 命名使用驼峰式命名，使用时采用tree-menu
[ '$compile', function($compile) {
	return {
		restrict : 'EA', // E = Element, A = Attribute
		templateUrl : '/pmsys/tpls/menuChirdren.html',
		replace : false,

		scope : {
			data : '=?',// “@”：传递一个字符串作为属性的值；“=”：使用父作用域中的一个属性，绑定数据到指令的属性中(对象或者字符串)；“&”：使用父作用域中的一个函数(方法),可以在指令中调用
			id : '@?',
			pid : '@?',
			pvalue : '@?',
			showname : '@?',
			icon:'@?',
			url:'@?',
			isstandard : '@'
		},
		link : function(scope, element, attrs, leafController) {
			// angular.forEach(scope.data, function(data){
			// console.log("scope.data:"+data.menuParentId);
			// });

			// 创建节点数据的方法：
			function createTreeData(id, pid, pvalue) {
				var newData = [];			
				angular.forEach(scope.data, function(item, index) {
					if (item.menuParentId == pvalue) {
						var children = createTreeData(id, pid, item.id);
						if (children && children.length > 0) {
							item.children = children;
						}
						newData.push(item);
					}
				});
				return newData;
			}	
			if (!scope.isstandard) {
				scope.data = createTreeData(scope.id, scope.pid, scope.pvalue);
			}
			// 此处是关键，调用入口处，需要找到index
			scope.toogle = function(index) {
				scope.data[index]["isopen"] = !scope.data[index]["isopen"];
			}
		}
	};
} ]);