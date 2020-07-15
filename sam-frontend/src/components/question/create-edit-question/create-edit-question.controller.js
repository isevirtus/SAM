angular.module("sam").controller('CreateQuestionController', function ($scope, $rootScope, $state, $stateParams, MetricFactory, QuestionFactory) {

    /**
     * CreateQuestionController scope variables
     * */


    $scope.idObjective = $stateParams.idObjective;
    $scope.idProject = $stateParams.idProject;
    $scope.idQuestion = $stateParams.idQuestion;
    $scope.currentVariationFactors = [];

    $scope.question = {};


    getMetrics = function () {
        MetricFactory.getMetrics().then(function (result) {
            $scope.metrics = result.data;
            if(!!$stateParams.idQuestion){
                $scope.isEdit = true;
                QuestionFactory.getQuestion($stateParams.idQuestion).then(function (result) {
                    $scope.question = result.data;
                    angular.forEach($scope.question.metrics, function (metric, index) {
                        angular.forEach($scope.metrics, function (metric2, index2) {
                            if (metric.id === metric2.id) {
                                console.log(metric);
                                $scope.currentVariationFactors[index2] = true;
                            }
                        });
                    });
                }, function (result) {
                    //TODO ver como os erros serão tratados.
                });
            }
        });
    };

    getMetrics();

    /**
     * Convert metric in variantion factores
     */

    $scope.variationFactorsSetup = function () {
        $scope.question.metrics = [];

        angular.forEach($scope.currentVariationFactors, function (question, index) {
            var checkedVariationFactor = $scope.metrics[index];
            if (question) {
                $scope.question.metrics.push(checkedVariationFactor);
            }
        })
    };

    /**
     * create metric
     * @param metric
     */

    $scope.saveMetric = function (metric) {
        MetricFactory.createMetric(metric).then(function (result) {
            $scope.currentMetric = {};
            getMetrics();
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });
    };

    /**
     * Delete metric
     * @param id
     */

    $scope.deleteMetric= function (id) {
        for(i = 0; i < $scope.question.metrics.length; i++){
            if($scope.question.metrics[i].id === id){
                $scope.question.metrics.splice(i, 1);
            }
        }
    };

    /**
     * Create question
     * @param question
     */

    $scope.saveQuestion = function (question) {
        QuestionFactory.createQuestion(question).then(function () {
            if($stateParams.idObjective){
                $state.go("edit-objective", {idObjective: $stateParams.idObjective});
            }else if($stateParams.idProject){
                $state.go("create-objective", {idProject : $stateParams.idProject});
            }

        }, function (result) {
            //TODO ver como o erro dessa requisição será tratado.
            console.log(result);
        });
    };
});