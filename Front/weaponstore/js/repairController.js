(function() {
	var weaponapp = angular.module('weaponapp');

	weaponapp.controller('repairController', function($scope, $http, $location) {			
		
		$scope.types = ['SWORD','AXE','MACE','STAFF'];
		
		$scope.createRepairTicket = function() {
			$http.post("http://localhost:8080/api/repair_tickets", $scope.weapon)
			.then(function(response) {				
				$scope.createStatus = 'Order sent!';
				$scope.disableCreate = true;
			}, function(response) {
				$scope.createStatus = 'error trying to create repair ticket';	
				console.log('error http POST weapons: ' + response.status);
			});
		}
		
		$scope.clear = function() {
			$scope.weapon.name = '';
			$scope.weapon.level = '';
			$scope.weapon.type = '';
            $scope.weapon.phoneNumber = '';
			$scope.weapon.email = '';
            $scope.weapon.description = ''
			$scope.disableCreate = false;
			$scope.createForm.$setUntouched();
			$scope.createForm.$setPristine();
		}
	
	});
})()