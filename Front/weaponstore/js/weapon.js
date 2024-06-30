(function(){
    var weaponapp = angular.module('weaponapp', ['ngRoute'])
    weaponapp.config(function($routeProvider) {
        $routeProvider

        .when("/search", {
            templateUrl : "search.html",
            controller: "searchController"

        })
        .when("/update/:weaponId", {
            templateUrl : "update.html",
            controller : "updateController"
        })
        .when("/create", {
            templateUrl : "create.html",
            controller : "createController"
        })
        .when("/stack", {
            templateUrl : "stack.html"
        })
        .when("/repair", {
            templateUrl : "repair.html",
            controller : "repairController"
        })
        .when("/orderSearch", {
            templateUrl : "orderSearch.html",
            controller : "orderSearchController"
        })
        .when("/repairOrder/:weaponId", {
            templateUrl : "repairOrder.html",
            controller : "orderSearchController"
        })
        .when("/updateOrder/:weaponId", {
            templateUrl : "updateOrder.html",
            controller : "updateOrderController"
        })
        .when("/login", {
            templateUrl : "login.html",
            controller : "passController"
        })
        .otherwise({
            templateUrl : "main.html"
        })

 

    })

 

})()