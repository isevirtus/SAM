angular.module("sam").controller('ListMetricsController', function ($scope, $state,  MetricFactory) {


    $scope.metrics = [];

    getMetrics = function () {
        MetricFactory.getMetrics().then(function (metrics) {
            $scope.metrics = metrics;
        }, function (result) {
            //TODO ver como os erros serão tratados.
        });
    };

    getMetrics();


    //go to any state
    $scope.goTo = function(state) {
        $state.go(state);
    };


});
