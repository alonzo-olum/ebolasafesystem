var app = angular.module('app.charts', ['chart.js']);

app.controller('TempChartCtrl', function($scope, $http, $location) {
$scope.labels = ["Mon","Tue","Wed","Thur","Fri","Sat"];
$scope.temp_values = {};
$scope.temp = {};
var url = '/rest/temperature/' + '0723564000';
$http.get(url).success(function(data, status, headers, config) {
$scope.temp_values = ["30","31","32","33","34","35"];
//$scope.temp = $scope.temp_values.temp;
console.log(JSON.stringify($scope.temp_values));
});
    
});

//.controller('TempChartCtrl', function($scope, $http, $location) {$scope.labels = ["Mon"];$scope.temp_values = [];var url = '/rest/temperature/' + '0723564000';$http.get(url).success(function(data, status, headers, config) {$scope.temp_values = data;console.log(JSON.stringify($scope.temp_values));});});})