/**
 * Created by mendelssohn on 19/06/17.
 */
angular.module('sam').factory('NetworkFactory', function ($window, $http, $state, Connection) {

    var myIp = Connection.baseUrl;

    function _queryNetwork(idNetwork) {
        return $http.get(myIp + '/network/query/' + idNetwork);
    }

    return {
        queryNetwork: _queryNetwork
    }
});