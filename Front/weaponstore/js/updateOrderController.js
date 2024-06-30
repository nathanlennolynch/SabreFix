(function() {
	var weaponapp = angular.module('weaponapp');
	
	weaponapp.controller('updateOrderController', function($scope, $http, $routeParams, $location) {

        $scope.types = ['SWORD', 'AXE', 'MACE', 'STAFF']

        $scope.getOrderById = function() {
            $http.get("http://localhost:8080/api/repair_tickets/" + $routeParams.weaponId)
            .then(function(response) {
                var weapons = response.data;
                if (weapons.length == 1) {
                    $scope.weapon = weapons[0]
                } else {
                    //error message
                }
            }, function(response) {
                console.log('error http GET orders by id: ' + response.status)
            })
        
        }
        $scope.getOrderById()

        $scope.updateOrder = function() {

			$http.put("http://localhost:8080/api/repair_tickets", $scope.weapon)
			.then(function(response) {				
				$scope.updateStatus = 'update successful';			
			}, function(response) {
				$scope.updateStatus = 'error trying to update order'	
				console.log('error http PUT orders: ' + response.status)
			})
		}
        $scope.deleteOrder = function() {

			$http.delete("http://localhost:8080/api/repair_tickets/" + $scope.weapon.id)

			.then(function(response) {				

				$scope.updateStatus = 'Delete successful'

				$scope.disableUpdate = true

			}, function(response) {

				$scope.updateStatus = 'error trying to delete order'	

				console.log('error http DELETE orders: ' + response.status);

			});

		}
        $scope.goToOrderView = function() {
            $location.path("/orderSearch")
        }
	})

})()