(function() {
	var weaponapp = angular.module('weaponapp');
	
	weaponapp.controller('searchController', function($scope, $http, $location) {
		$scope.getAllWeapons = function() {
			$scope.showSpinner = true
			$http.get("http://localhost:8080/api/weapons")
			.then(function(response) {
				$scope.weapons = response.data;
				$scope.showSpinner = false
				console.log('number of weapons: ' + $scope.weapons.length)
			}, function(response) {
				console.log('error http GET weapons: ' + response.status)
			})
		}

		$scope.goToUpdateView = function(weaponId) {
			console.log("go to update view: " + weaponId)
			$location.path('/update/' + weaponId)
		}
		
	})
})()