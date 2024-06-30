(function() {
	var weaponapp = angular.module('weaponapp');

	weaponapp.controller('createController', function($scope, $http) {			
		
		$scope.types = ['SWORD','AXE','MACE','STAFF'];
		
		$scope.createWeapon = function() {
			$http.post("http://localhost:8080/api/weapons", $scope.weapon)
			.then(function(response) {				
				$scope.createStatus = 'create successful';
				$scope.disableCreate = true;
			}, function(response) {
				$scope.createStatus = 'error trying to create weapon';	
				console.log('error http POST weapons: ' + response.status);
			});
		}
		
		$scope.clear = function() {
			$scope.weapon.name = '';
			$scope.weapon.level = '';
			$scope.weapon.type = '';
			$scope.disableCreate = false;
			$scope.createForm.$setUntouched();
			$scope.createForm.$setPristine();
		}
		
	});
	
})()