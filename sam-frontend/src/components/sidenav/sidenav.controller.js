angular.module("sam").controller('SidenavController', function ($scope, $rootScope, $state, $stateParams, ProjectFactory) {

    /**
     * get all projects
     */
    getProjects = function () {
        ProjectFactory.getProjects().then(function (result) {
            $scope.projects = result.data;
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });
    };

    getProjects();


});