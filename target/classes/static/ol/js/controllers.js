var app = angular.module("ebolasafe");


//Patients
app.controller('PatientsController', function($scope, $http) {
	$scope.patients = [];
	$http.get('/rest/patients').success(function(data, status, headers, config) {
		console.log("Hope You Can be seen??");
		console.log(JSON.stringify(data));
		$scope.patients = data;
	});
	
	$scope.searchpatient = function(){
	
	var url = '/rest/patients/'+$scope.phoneNumber;
	$scope.patient = {};
	$http.get(url).success(function(data, status, headers, config){
	console.log("can you be seen?");
	console.log(JSON.stringify(data));
	$scope.patient = data;
	});
    };
	
});

app.controller('CreatePatientController', function($scope, $http, $location) {
	$scope.setPatient = function () {
		var patient = { name : $scope.name, username : $scope.username, phoneNumber : $scope.phoneNumber, password : $scope.password, email : $scope.email, country : $scope.country, town : $scope.town};
		console.log($scope.name);
		$http.post('/rest/patients', patient).success(function(data, status, headers, config) {
            console.log(status);
            $location.path('/patients');
        });
    };
});

app.controller('LocationController', function($scope, $http,$location) {
    $scope.location = [];
	$http.get('/rest/location').success(function(data, status, headers, config) {
        console.log(JSON.stringify(data));
        $scope.location = data;
    });
	
});

app.controller('CreateLocationController', function($scope, $http, $route,$location) {

	$scope.setLocation = function(){
		var location = { country: $scope.country, town:$scope.town };
	$http.post('/rest/location', location).success(function(data, status, headers, config) {
        console.log(status);
       
    });
	};
    
});

app.controller('SymptomsController', function($rootScope,$scope, $http, $route,$location) {
    
	$scope.symptoms = [];
    $http.get('/rest/symptoms/').success(function(data, status, headers, config) {
        console.log(JSON.stringify(data));
        $scope.symptoms = data;
    });
});

app.controller('CreateSymptomController', function($scope,$http,$route,$location){
	$scope.setSymptom = function(){
		$scope.date = new Date();
		var symptom = { symptom: $scope.symptomname, date_added:$scope.date };
	$http.post('/rest/symptoms', symptom).success(function(data, status, headers, config) {
        console.log(status);
       
    });
	};
});

app.controller('PatientSymptomsController', function($rootScope,$scope, $http, $route,$location) {
    
	$scope.symptomspatient = [];
	$http.get('/rest/symptoms/symp_patient').success(function(data, status, headers, config) {
    	console.log(JSON.stringify(data));
        $scope.symptomspatient = data;
    });
    
 });

app.controller('IllPatientSymptomsController', function($rootScope,$scope, $http, $route,$location) {
    
	$scope.illpatientsymptoms = [];
	$http.get('/rest/illpatient/symptoms/').success(function(data, status, headers, config) {
    	console.log(JSON.stringify(data));
        $scope.illpatientsymptoms = data;
    });
    
 });
 
app.controller('CreatePatientSymptomController', function($scope,$http,$route,$location){
	$scope.setPatientSymptom = function(){
		$scope.date = new Date();
		var patientsymptom = { symptom: $scope.symptom, date_added:$scope.date, username:$scope.username, phone:$scope.phone };
	$http.post('/rest/symptoms/symp_patient', patientsymptom).success(function(data, status, headers, config) {
        console.log(status);
       
    });
	};
});

app.controller('TemperatureController', function($rootScope,$scope, $http, $route,$location) {
    
	$scope.temperature = [];
	$http.get('/rest/temperature').success(function(data, status, headers, config) {
    	console.log(JSON.stringify(data));
        $scope.temperature = data;
    });
});

app.controller('CreateTemperatureController', function($scope,$http,$route,$location){
	$scope.setTemperature = function(){
		$scope.date = new Date();
		var temp = { phone:$scope.phone,_timeRes:$scope.time,temp: $scope.temp, date_taken:$scope.date, username:$scope.username };
	$http.post('/rest/temperature', temp).success(function(data, status, headers, config) {
        console.log(status);
       
    });
	};
});

app.controller('MessageController', function($rootScope,$scope, $http, $route,$location) {
    
	$scope.messages = [];
	$http.get('/rest/messages').success(function(data, status, headers, config) {
    	console.log(JSON.stringify(data));
        $scope.messages = data;
    });
});

app.controller('IllpatientController', function($rootScope,$scope, $http, $route,$location) {
    
	$scope.illpatients = [];
	$http.get('/rest/illpatient').success(function(data, status, headers, config) {
    	console.log(JSON.stringify(data));
        $scope.illpatients = data;
    });
    
    $scope.searchpatient = function () {
    	var url = '/rest/illpatient/'+$scope.phoneNumber;
        $http.get(url).success(function(data, status, headers, config) {
        $scope.illpatient = {};
        $scope.illpatient = data;
        console.log(JSON.stringify(data));
        
        });
    };
});


/*
 $scope.savepatientSymptoms = function() {
    	 console.log(poref);
    	 $rootScope.poUrlSelf = poref;
    	 $scope.phone='';
    	 $scope.username = '';
    	 $scope.symptom = '';
    	 $scope.date_created = new Date();
    	 $http.post('rest/symptoms/symp_patient',{params {username:$scope.username, phone:$scope.phone, symptom:$scope.symptom, date_created:$scope.date_created}}).success(function(data, status, headers, config) {
     		console.log(data);
     		$location.path('/symptoms/symp_patient');
    	 });
    };

app.controller('SearchPatientController', function($scope, $http, $location) {
    $scope.patient = [];
    $scope.phoneNumber = '';
    $scope.catalogShown = false;

    $scope.execQuery = function () {
        $http.get('/rest/patients', {params: {phone: $scope.phoneNumber}}).success(function(data, status, headers, config) {
            $scope.patient = data;
            console.log(data);
            $scope.catalogShown = true;
        });
    };
});

app.controller('UpdatePatientController', function($scope, $http, $location) {
	var url = '/rest/patients/+$scope.phoneNumber';
    $scope.patient = [];
    $scope.phoneNumber = '';
    $scope.name='';
    $scope.username = '';
      
    $scope.setPatient = function (selectedPatient) {
        
	$http.post('/rest/patients', {patients:selectedPatient}).success(function(data, status, headers, config) {
	    $scope.patient = data;
	    $scope.phoneNumber = data.phoneNumber;
	    console.log(data);
	   	 });
	   	 };

app.controller('POSController', function($rootScope,$scope, $http, $route,$location) {
    
	$scope.pos = [];
	$scope.poUrlSelf = '';
    $http.get('/rest/pos/local').success(function(data, status, headers, config) {
    	console.log(JSON.stringify(data));
        $scope.pos = data;
    });
    
    $scope.viewplant = function(plantref) {
   	 console.log(plantref);
   	 $rootScope.planturlSelf = plantref;
   	 $http.post(plantref).success(function(data, status, headers, config) {
    		console.log(data);
    		$location.path('/plants/show');
   	 });
   };
    
    $scope.follow = function(link) {
        console.log(link);
        console.log(link.method);
        if (link.method == 'POST') {
        	$http.post(link.href).success(function(data, status, headers, config) {
            		console.log(data);
            		$route.reload();});
        } else if (link.method == 'DELETE') {
        	if (link.rel == 'updatePHR') {
            	console.log($route);
            	$rootScope.pourl = link.href;
            	$location.path('/phrs/update');
            }else {
            		$http.delete(link.href).success(function(data, status, headers, config) {
            		console.log(data);
                	$route.reload();}); 
            }
        }
    };
});

app.controller('CustomersController', function($scope, $http) {
	$scope.customers = [];
	$http.get('/rest/customers').success(function(data, status, headers, config) {
		console.log(JSON.stringify(data));
		$scope.customers = data;
	});
	
});
*/