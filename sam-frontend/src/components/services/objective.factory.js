angular.module('sam').factory('ObjectiveFactory', function ($window, $http, $state, Connection) {

    var myIp = Connection.baseUrl;

    /**
     * get all objectives
     * @return {*}
     * @private
     */
    function _getObjectives() {
        return $http.get(myIp + '/project/objective/list');
    }

    /**
     * create objective
     * @param objective
     * @param idProject
     * @return {*}
     * @private
     */
    function _createObjective(objective, idProject) {
        return $http.post(myIp + '/project/objective/create/' + idProject, objective);
    }

    /**
     * delete objective
     * @param idObjective
     * @param idProject
     * @return {boolean|*}
     * @private
     */
    function _deleteObjective(idObjective, idProject) {
        return $http.delete(myIp + '/project/objective/delete/' + idProject + '/' + idObjective);
    }

    /**
     * edit objective
     * @param objective
     * @return {*}
     * @private
     */
    function _editObjective(objective) {
        return $http.put(myIp + '/project/objective/update', objective);
    }

    /**
     * get especific objective
     * @param idObjective
     * @return {*}
     * @private
     */
    function _getObjective(idObjective) {
        return $http.get(myIp + '/project/objective/get-objective/' + idObjective);
    }

    return {
        getObjectives: _getObjectives,
        createObjective: _createObjective,
        deleteObjective: _deleteObjective,
        editObjective: _editObjective,
        getObjective: _getObjective
    }

});