/**
 * Created by mendelssohn on 21/06/17.
 */
angular.module("sam").controller('QuestionsMetricsController', function ($scope, $rootScope, $state, $stateParams, MetricFactory, QuestionFactory) {

    /**
     * Get all questions
     */

    QuestionFactory.getQuestions().then(function (result) {
        $scope.questions = result.data;
    }).catch(function (result) {
        //TODO ver como tratar esse erro.
    });

    /**
     * Get all metrics
     */

    MetricFactory.getMetrics().then(function (result) {
        $scope.metrics = result.data;
    }).catch(function (result) {
        //TODO ver como os erros serão tratados.
    });
});
