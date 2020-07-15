angular.module("sam").controller("AppController", function($rootScope, $state, $scope, $translate){

    $translate.use('pt');

    $scope.navigationElements = [];

    $scope.addNavigationElement = function(element){
        $scope.navigationElements.push(element);
    };

    $scope.clearNavigationElements = function(){
        $scope.navigationElements = [];
    };

    $rootScope.$on('$stateChangeSuccess', function(ev, to, toParams, from, fromParams) {
        $rootScope.previousState = from.name;
        $rootScope.currentState = to.name;
    });
});

