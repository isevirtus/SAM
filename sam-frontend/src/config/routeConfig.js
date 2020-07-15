angular.module('sam').config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $urlRouterProvider.otherwise("/list-project");

    $stateProvider

        .state('principal-project', {
            url: '/principal-project/:idProject',
            templateUrl: 'src/components/project/principal-project/principal-project.html',
            controller: 'PrincipalProject'
        })

        .state('create-project', {

            url: '/create-project',
            templateUrl: 'src/components/project/create-edit-project/create-edit-project.html',
            controller: 'CreateProjectController'

        })

        .state('list-projects', {

            url: '/list-project',
            templateUrl: 'src/components/project/list-projects/list-projects.html',
            controller: 'ListProjectsController'

        })

        .state('edit-project', {

            url: '/edit-project/:idProject',
            templateUrl: 'src/components/project/create-edit-project/create-edit-project.html',
            controller: 'CreateProjectController'
        })

        .state('list-objectives', {

            url: '/list-objectives',
            templateUrl: 'src/components/objective/list-objectives/list-objectives.html',
            controller: 'ListObjectiveController'

        })

        .state('create-objective', {

            url: '/create-objective/:idProject',
            templateUrl: 'src/components/objective/create-edit-objective/create-edit-objective.html',
            controller: 'CreateObjectiveController'

        })

        .state('edit-objective', {

            url: '/edit-objective/:idObjective',
            templateUrl: 'src/components/objective/create-edit-objective/create-edit-objective.html',
            controller: 'CreateObjectiveController'
        })

        .state('view-question-metric', {
            url: '/view-question-metric',
            templateUrl: 'src/components/question-metric/question-metric.html',
            controller: 'QuestionsMetricsController',
            params : {
                idProject : null,
                idObjective : null,
                idQuestion : null
            }
        })

        .state('create-metric',{

            url: '/create-metric',
            templateUrl: 'src/components/metric/create-edit-metric/create-edit-metric.html',
            controller: 'CreateMetricController',
            params: {
                idProject: null,
                idObjective: null,
                idQuestion: null
            }

        })

        .state('edit-metric', {

            url: '/edit-metric/:idMetric',
            templateUrl: 'src/components/metric/create-edit-metric/create-edit-metric.html',
            controller: 'CreateMetricController',
            params: {
                idProject: null,
                idObjective: null,
                idQuestion: null
            }

        })

        .state('list-metrics', {

            url: '/list-metrics',
            templateUrl: 'src/components/metric/list-metrics/list-metrics.html',
            controller: 'ListMetricsController'

        })

        .state('create-question', {

            url: '/create-question',
            templateUrl: 'src/components/question/create-edit-question/create-edit-question.html',
            controller: 'CreateQuestionController',
            params: {
                idProject: null,
                idObjective: null
            }
        })

        .state('edit-question', {

            url: '/edit-question/:idQuestion',
            templateUrl: 'src/components/question/create-edit-question/create-edit-question.html',
            controller: 'CreateQuestionController',
            params: {
                idProject: null,
                idObjective: null
            }

        })

        .state('list-questions', {

            url: '/list-questions',
            templateUrl: 'src/components/question/list-metrics/list-metrics.html',
            controller: 'ListMetricsController'

        })

        .state('network', {

            url: '/network/:idObjective',
            templateUrl: 'src/components/project/network/network.html',
            controller: 'NetworkController'

        })

        .state('login', {

            url: '/login',
            templateUrl: 'src/components/login/login.html',
            controller: 'LoginCtrl',
            controllerAs: "loginCtrl"

        })
});