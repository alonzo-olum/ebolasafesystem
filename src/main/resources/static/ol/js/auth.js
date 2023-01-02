var app = angular.module("app.authenticate",[]);
app.controller("LoginController", function($scope, $http, $location, $rootScope) {
	var authenticate = function(credentials, callback ){
	
	var header = credentials ? {authorization: "Basic" + btoa (credentials.username+ ":" +credentials.password)}:{};
	
		$http.get("/rest/authentication", {headers:header})
			.success(function(data, status, headers, config) {
				console.log(data.roles);
				//$cookieStore.put("userRoles", data.roles);
				//$cookieStore.put("user", $scope.username);
				//$cookiestore.put("password", $scope.password);
					
				if(!angular.isDefined(data.username)){
				$rootScope.authenticated = true;
				}else{
				$rootScope.authenticated = false;
				}
				callback & callback();
				}).error(function(){
				$rootScope.authenticated = false;
				callback & callback();
				});
		
	};
	$scope.credentials = {};
	$scope.login = function () {
		
		//var code = window.btoa($scope.username + ":" + $scope.password);
		//console.log(code);
		//$rootScope.user = $scope.credentials.username;
		//$rootScope.password = $scope.credentials.password;
		//$rootScope.code = code;
		authenticate($scope.credentials, function(){
		if ($rootScope.authenticated) {
          $location.path("/");
          $scope.error = false;
        } else {
          $location.path("/login");
          $scope.error = true;
        }
		});
		
		$location.path("/");
		
	};
	
});