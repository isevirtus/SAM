angular.module("sam").controller('PrincipalProject', function ($scope, $state, $stateParams, ProjectFactory) {
    $scope.clearNavigationElements();
    $scope.addNavigationElement({stateName: 'principal-project', viewName: 'PROJECTS'});

    $scope.idProject = $stateParams.idProject;

    if (!!$stateParams.idProject) {
        var idProject = $stateParams.idProject;
        ProjectFactory.getProject(idProject).then(function (result) {
            $scope.project = result.data;
            $scope.addNavigationElement({viewName: result.data.name});
        }).catch(function (result) {
            //TODO ver como tratar o erro.
            console.log(result);
        });
    } else {

    }
});
