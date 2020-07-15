angular.module('sam').controller('LoginCtrl', function ($scope, $rootScope, $state, $stateParams) {
    var self = this;

    function loginSuccess(response) {
        $scope.setCurrentUser(response.data.user);
        $state.go("users");
        $scope.deleteError();
    }

    self.logout = function () {
        $state.go('login');
    }

    self.login = function (credenciais) {
    };

    self.isAuthenticated = function () {
    }
});