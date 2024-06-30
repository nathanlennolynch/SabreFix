(function() {
	var weaponapp = angular.module('weaponapp');
	
	weaponapp.controller('orderSearchController', function($scope, $http, $location) {
		$scope.getAllWeapons = function() {
			$scope.showSpinner = true
			$http.get("http://localhost:8080/api/repair_tickets")
			.then(function(response) {
				$scope.weapons = response.data;
				$scope.showSpinner = false
				console.log('number of oreders: ' + $scope.weapons.length)
			}, function(response) {
				console.log('error http GET orders: ' + response.status)
			})
		}

		$scope.goToUpdateOrder = function(weaponId) {
			console.log("go to update view: " + weaponId)
			$location.path('/updateOrder/' + weaponId)
		}
		$scope.goToRepairOrder = function(weaponId) {
			console.log("go to update view: " + weaponId)
			$location.path('/repairOrder/' + weaponId)
		}
	})
})()