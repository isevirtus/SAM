angular.module('sam').factory('MetricFactory', function ($window, $http, $state, Connection) {

    var myIp = Connection.baseUrl;

    /**
     * get all metrics
     * @return {*}
     * @private
     */
    function  _getMetrics() {
        return $http.get(myIp + '/project/metric/list');
    }

    /**
     * get especific metric
     * @param idMetric
     * @return {*}
     * @private
     */
    function _getMetric(idMetric) {
        return $http.get(myIp + '/project/metric/' + idMetric);
    }

    /**
     * create metric
     * @param metric
     * @return {*}
     * @private
     */
    function _createMetric(metric) {
        return $http.post(myIp + '/project/metric/create', metric);
    }

    /**
     * delete metric
     * @param idMetric
     * @return {boolean|*}
     * @private
     */
    function _deleteMetric(idMetric) {
        return $http.delete(myIp + '/project/metric/delete/' + idMetric);
    }

    return{
        getMetrics: _getMetrics,
        getMetric: _getMetric,
        createMetric: _createMetric,
        deleteMetric: _deleteMetric
    }
});