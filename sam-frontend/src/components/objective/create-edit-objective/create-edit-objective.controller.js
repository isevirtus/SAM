angular.module("sam").controller('CreateObjectiveController', function ($scope, $state, $stateParams, ObjectiveFactory, QuestionFactory) {

    QuestionFactory.getQuestions().then(function (result) {
        $scope.questions = result.data;
        if (!!$stateParams.idObjective) {
            $scope.isEdit = true;
            var idObjective = $stateParams.idObjective;

            ObjectiveFactory.getObjective(idObjective).then(function (result) {
                $scope.objective = result.data;

                angular.forEach($scope.objective.questions, function (question, index) {
                    angular.forEach($scope.questions, function (question2, index2) {
                        if (question.id === question2.id) {
                            console.log(question);
                            $scope.currentQuestion[index2] = true;
                        }
                    });
                });
            }, function (result) {
                //TODO ver como o erro dessa requisição será tratado.
                console.log(result);
            });
        }
    }, function (result) {
        //TODO ver como tratar esse erro.
    });

    /**
     * CreateObjectiveController scope variables
     * */

    $scope.currentQuestion = [];
    $scope.objective = {};
    $scope.idProject = $stateParams.idProject;
    $scope.idObjective = $stateParams.idObjective;

    $scope.questionsSetup = function () {
        $scope.objective.questions = [];
        angular.forEach($scope.currentQuestion, function (question, index) {
            var checkedQuestion = $scope.questions[index];
            if (question) {
                $scope.objective.questions.push(checkedQuestion);
            }
        })
    };

    /**
     * create objective
     * @param objective
     */
    $scope.createObjective = function (objective) {
        ObjectiveFactory.createObjective(objective, $stateParams.idProject).then(function () {
            $state.go("list-projects");
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });
    };

    /**
     * Edit objective
     * @param objective
     */
    $scope.editObjective = function (objective) {
        ObjectiveFactory.editObjective(objective).then(function () {
            $state.go("list-projects");
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });
    };
});