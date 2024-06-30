(function() {
	var weaponapp = angular.module('weaponapp');
	
	weaponapp.controller('updateController', function($scope, $http, $routeParams, $location) {

        $scope.types = ['SWORD', 'AXE', 'MACE', 'STAFF']

        $scope.getWeaponsById = function() {
            $http.get("http://localhost:8080/api/weapons/" + $routeParams.weaponId)
            .then(function(response) {
                var weapons = response.data;
                if (weapons.length == 1) {
                    $scope.weapon = weapons[0]
                } else {
                    //error message
                }
            }, function(response) {
                console.log('error http GET weapons by id: ' + response.status)
            })
        
        }
        $scope.getWeaponsById()

        $scope.updateWeapon = function() {

			$http.put("http://localhost:8080/api/weapons", $scope.weapon)
			.then(function(response) {				
				$scope.updateStatus = 'Upgrade successful';			
			}, function(response) {
				$scope.updateStatus = 'error trying to update weapon'	
				console.log('error http PUT weapons: ' + response.status)
			})
		}
        $scope.deleteWeapon = function() {

			$http.delete("http://localhost:8080/api/weapons/" + $scope.weapon.id)

			.then(function(response) {				

				$scope.updateStatus = 'Purchase successful'

				$scope.disableUpdate = true

			}, function(response) {

				$scope.updateStatus = 'error trying to delete weapon'	

				console.log('error http DELETE weapons: ' + response.status);

			});

		}
        $scope.goToSearchView = function() {
            $location.path("/search")
        }
	})

})()