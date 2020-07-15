angular.module('sam').service("HTTPInterceptor").config(function ($httpProvider) {
    var httpInterceptor = function ($location, $q) {
        return {
            request: function (config) {
                config.headers = config.headers || {};
                config.headers['Access-Control-Allow-Origin'] = "*";

                return config;
            },

            responseError: function (response) {
                return $q.reject(response);
            }
        }
    }

    $httpProvider.interceptors.push(httpInterceptor);
});