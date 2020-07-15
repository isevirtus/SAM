angular.module("sam").controller('CreateMetricController', function ($scope, $state, $stateParams, MetricFactory) {

    /**
     * CreateMetricController scope variables
     * */
    $scope.currentMetric = {};
    $scope.idProject = $stateParams.idProject;
    $scope.idQuestion = $stateParams.idQuestion;
    $scope.idObjective = $stateParams.idObjective;

    if (!!$stateParams.idMetric) {
        $scope.isEdit = true;
        var idMetric = $stateParams.idMetric;
        MetricFactory.getMetric(idMetric).then(function (result) {
            $scope.currentMetric = result.data;
        });
    }
    /***
     * Create metric
     * @param metric
     */
    $scope.saveMetric = function (metric) {
        MetricFactory.createMetric(metric).then(function () {
            $scope.currentMetric = {};
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });

        goToStates();
    };

    function goToStates() {
        if ($stateParams.idQuestion !== null) {
            $state.go("edit-question", {idProject: $stateParams.idProject, idObjective : $stateParams.idObjective, idQuestion: $stateParams.idQuestion});
        } else if ($stateParams.idObjective !== null) {
            $state.go("create-question", {idObjective: $stateParams.idObjective, idQuestion: $stateParams.idQuestion});
        }else if ($stateParams.idProject !== null) {
            $state.go("create-question", {idProject: $stateParams.idProject, idObjective: $stateParams.idObjective, idQuestion: $stateParams.idQuestion});
        } else {
            $state.go("list-projects");
        }
    };

    $scope.$watch('currentVariationFactors', function () {
        $scope.currentMetric.variationFactors = [];
        angular.forEach($scope.currentVariationFactors, function (metric, index) {
            if (metric) {

                var variactionFactor = {
                    variationHypothesis: metric.variationHypothesis,
                    description: $scope.variationFactors[index].description
                };

                $scope.currentMetric.variationFactors.push(variactionFactor);
            }
        })
    }, true);
});