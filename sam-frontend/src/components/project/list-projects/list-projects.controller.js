angular.module("sam").controller('ListProjectsController', function ($scope, $state, ProjectFactory, ObjectiveFactory) {

    $scope.clearNavigationElements();
    $scope.addNavigationElement({stateName: 'list-projects', viewName : "Projetos"});

    getProjects = function () {
        ProjectFactory.getProjects().then(function (result) {
            $scope.projects = result.data;
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });
    };

    $scope.deleteObjective = function (idObjective, idProject) {
        ObjectiveFactory.deleteObjective(idObjective, idProject).then(function (result) {
            getProjects();
        }, function errorCallback(response) {
            console.log("Erro na request de project!", response);
        });
    };

    $scope.deleteProject = function (id) {
        console.log('projeto removido com sucesso!');
        ProjectFactory.deleteProject(id).then(function () {
            $scope.goTo('list-projects');
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });
    };

    getProjects();
});