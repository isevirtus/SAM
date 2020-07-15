angular.module('sam').controller('CreateProjectController', function ($scope, $state, $stateParams, ProjectFactory, $mdToast) {

    $scope.clearNavigationElements();
    $scope.addNavigationElement({stateName: 'list-projects', viewName: 'PROJECTS'});


    /**
     * CreateProjectController scope variables
     * */
    $scope.projectType = [
        {
            name: 'Protótipo'
        },
        {
            name: 'Produto'
        }
    ];



    $scope.objectsOfStudy = ['objectOfStudy1', 'objectOfStudy2'];

    $scope.purposes = ['purpose1', 'purpose2'];

    $scope.methodologyType = ['Scrum', 'Kanban', 'RUP', 'XP', 'OUTRO'];

    $scope.isEditEnabled = true;

    if (!!$stateParams.idProject) {
        $scope.showEditBtn = true;
        $scope.isEditEnabled = false;
        var idProject = $stateParams.idProject;

        ProjectFactory.getProject(idProject).then(function (result) {
            $scope.project = result.data;
            $scope.addNavigationElement({viewName: result.data.name});
            $scope.addNavigationElement({viewName: 'DETAILS'});
        }).catch(function (result) {
            //TODO ver como tratar o erro.
            console.log(result);
        });
    } else {
        $scope.addNavigationElement({viewName: 'NEW_PROJECT'});
        $scope.project={};
        $scope.project.objectives=[];
    }

    /**
     * Make form editable
     */

    $scope.setEnableEdit = function (isEditEnabled) {
        $scope.isEditEnabled = isEditEnabled;
    };

    $scope.addObjective = function(){
        $scope.project.objectives.push({});
    };


    /*
     $scope.showToast= function () {
     mdToast.show(
     $mdToast.simple()
     .textContent('Simple Toast!')
     .position('top center').parent($document[0].querySelector('#toaster')
     .hideDelay(3000)
     );

     };

     */

    /**
     * create project
     * @param project
     */

    $scope.createProject = function (project) {
        ProjectFactory.createProject(project).then(function (result) {
            $state.go('list-projects');
        }).catch(function (result) {
            //TODO ver como tratar o erro.
            console.log(result);
        });
    };

    /**
     * edit project
     * @param project
     */

    $scope.editProject = function (project) {
        ProjectFactory.editProject(project).then(function () {
            $state.go('list-projects');
        }, function (result) {
            //TODO ver como os erros serão tratados.
            console.log(result);
        });
    };
});