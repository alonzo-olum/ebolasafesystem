var app = angular.module('ebolasafe', ["ngRoute", "ngCookies"]);

app.config(function($routeProvider,$locationProvider, $httpProvider) {
	$routeProvider
		.when('/login', {
	      controller:'LoginController',
	      templateUrl:'ol/views/signin.html'
	    })
	    .when('/', {
	      templateUrl:'views/dashboard.html'
	    })
	    .when('/patients/list', {
	    	controller:'PatientsController',
	      	templateUrl:'ol/views/patients/list.html'
			      /*resolve: {
			    	  permission: function(RoleBasedAccessService, $route) {
			              return RoleBasedAccessService.permissionCheck(["ROLE_SENG","ROLE_WENG","ROLE_ADMIN"]);
			      			}
			      		}*/
	    })
	    .when('/patients/create', {
	    	controller: 'CreatePlantController',
	    	templateUrl: 'ol/views/plants/create.html',
	    	/*resolve: {
		    	  permission: function(RoleBasedAccessService, $route) {
		              return RoleBasedAccessService.permissionCheck(["ROLE_SENG","ROLE_WENG","ROLE_ADMIN"]);
		      			}
		      		}*/	    
	    })
	     .when('/location/list', {
	    	controller: 'LocationController',
          	templateUrl: 'ol/views/location/list.html'
          	/*
		      resolve: {
		    	  permission: function(RoleBasedAccessService, $route) {
		              return RoleBasedAccessService.permissionCheck(["ROLE_SENG","ROLE_WENG","ROLE_ADMIN"]);
		      			}*/
		      		}	    
	    })
	    .when('/location/create', {
	    	controller:'CreateLocationController',
	      	templateUrl:'ol/views/location/create.html'
	      		   
	      	 })
	     .when('/symptoms/list', {
	    	controller:'SymptomsPatientController',
	      	templateUrl:'ol/views/symptoms/list.html',
	      	/*
		      resolve: {
		    	  permission: function(RoleBasedAccessService, $route) {
		              return RoleBasedAccessService.permissionCheck(["ROLE_SENG","ROLE_WENG","ROLE_ADMIN"]);
		      			}*/
		      			   
		   })
		   .when('/symptoms/create',{
		   	controller:'CreateSymptomsController',
		   	templateUrl:'ol/views/symptoms/create.html'
		   	
		   })
		   .when('/symptomspatient/list', {
	    	controller:'PatientSymptomsController',
	      	templateUrl:'ol/views/symptomspatient/list.html',
	      			      			   
		   })
		   .when('/symptomspatient/create', {
	    	controller:'CreatePatientSymptomsController',
	      	templateUrl:'ol/views/symptomspatient/create.html',
	      			      			   
		   })
		    .when('/temperature/list', {
	    	controller:'TemperatureController',
	      	templateUrl:'ol/views/temperature/list.html',
	      			      			   
		   })
		   .when('/temperature/create', {
	    	controller:'CreateTemperatureController',
	      	templateUrl:'ol/views/temperature/create.html',
	      			      			   
		   })
		   .when('/messages/list', {
	    	controller:'MessageController',
	      	templateUrl:'ol/views/messages/list.html',
	      			      			   
		   })
		   .when('/illpatients/list', {
	    	controller:'IllpatientController',
	      	templateUrl:'ol/views/illpatients/list.html',
	      			      			   
		   })
		   .when('/illpatients/show', {
	    	controller:'IllpatientController',
	      	templateUrl:'ol/views/illpatients/show.html',
	      			      			   
		   })
		   .when('/temperature/line_chart', {
	    	controller: 'TempChartCtrl',
	    	templateUrl: 'ol/views/temperature/line_chart.html',
		        
	    });
	 
/*	    
	    
	    
	    .when('/plants/show', {
	    	controller:'PlantShowController',
	      	templateUrl:'views/plants/show.html',
		      resolve: {
		    	  permission: function(RoleBasedAccessService, $route) {
		              return RoleBasedAccessService.permissionCheck(["ROLE_SENG","ROLE_WENG","ROLE_ADMIN"]);
		      			}
		      		}	    
	    })
	    .when('/plants/search', {
	    	controller:'SearchPlantController',
	      	templateUrl:'views/plants/search.html',
		      resolve: {
		    	  permission: function(RoleBasedAccessService, $route) {
		              return RoleBasedAccessService.permissionCheck(["ROLE_SENG","ROLE_WENG","ROLE_ADMIN"]);
		      			}
		      		}	    
	    })
	     
	    .when('/customers', {
	    	controller: 'CustomersController',
	    	templateUrl: 'views/customers/list.html',
		      resolve: {
		    	  permission: function(RoleBasedAccessService, $route) {
		              return RoleBasedAccessService.permissionCheck(["ROLE_SENG","ROLE_WENG","ROLE_ADMIN"]);
		      			}
		      		}	    })
	    .when('/logout', {
        	redirectTo:'/login'
        })
	    .otherwise({
		      redirectTo:'/login'
		});
	
	$httpProvider.interceptors.push(function ($q, $rootScope, $location, $window) {
        return {
        	'request': function(config) {
        		var isRestCall = config.url.indexOf('/rest') == 0;
        		if (isRestCall) {
        			config.headers['Authorization'] = 'Basic ' + $rootScope.code;
        		}
        		return config || $q.when(config);
        	},
        	'responseError': function(rejection) {
        		var status = rejection.status;
        		var config = rejection.config;
        		var method = config.method;
        		var url = config.url;
      
        		if (status == 401) {
//        			$rootScope.logout();
        			$window.history.back();
        		} else {
        			$rootScope.error = method + " on " + url + " failed with status " + status;
        		}
              
        		return $q.reject(rejection);
				}
			};
		}
	);
});
	
app.run(function($rootScope, $location, $cookieStore) {
    var user = $cookieStore.get("user");
    var code = $cookieStore.get("code");
    if (user !== undefined && code !== undefined) {
        $rootScope.user = user;
        $rootScope.code = code;
    }

    $rootScope.logout = function() {
        delete $rootScope.user;
        delete $rootScope.code;

        $cookieStore.remove("user");
        $cookieStore.remove("code");
        $cookieStore.remove("userRoles");
        $location.path("/login");
    };
});

app.service("RoleBasedAccessService", function($rootScope, $location, $q,
        $cookieStore) {
    return {
        permissionCheck : function(roleCollection) {
            var deferred = $q.defer();
            var userRoles = $cookieStore.get("userRoles");

            console.log("-------------");
            console.log(roleCollection);
            console.log(userRoles);
            console.log("-------------");

            var matchingRoles = userRoles.filter(function(role) {
                return roleCollection.indexOf(role) != -1;
            });
            if (userRoles !== undefined && matchingRoles.length > 0)
                deferred.resolve();
            else {
                $location.path("/login");
                $rootScope.$on("$locationChangeSuccess",
                        function(next, current) {
                            deferred.resolve();
                        });
            }
            return deferred.promise;
        }
    };
});
*/
