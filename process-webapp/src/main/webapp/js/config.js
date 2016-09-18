/*app.js主要是做前段路由，怎么跳转*/

var processApp=angular.module('process_app', ['ui.router','baseController','baseServcie','baseDirective','baseFilter']);
processApp.config(function ($stateProvider, $urlRouterProvider) {
	 $urlRouterProvider.otherwise('/druid');
	    $stateProvider.state('druid',{
	        url:'/druid',
	        views:{
	        	'content':{
	        		templateUrl:"/pmsys/tpls/druid.html",
	        		//controller:'homeCtrl'
	        	},'menu':{
	        		templateUrl:'/pmsys/tpls/menu.html',
	        		controller:'index_tree_controller'
	        	}
	        }
	    }).state('email',{
	        url:'/email',
	        views:{
	        	'content':{
	        		templateUrl:"/pmsys/tpls/email.html",
	        		//controller:'homeCtrl'
	        	},'menu':{
	        		templateUrl:'/pmsys/tpls/menu.html',
	        		controller:'index_tree_controller'
	        	}
	        }
	    })

	});