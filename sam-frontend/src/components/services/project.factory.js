angular.module('sam').factory('ProjectFactory', function ($window, $http, $state, Connection) {

    var myIp = Connection.baseUrl;

    /**
     * get all projects
     * @return {*}
     * @private
     */
    function _getProjects() {
        return $http.get(myIp + '/project/list');
    }

    /**
     * get especific projetc
     * @param idProject
     * @return {*}
     * @private
     */
    function _getProject(idProject) {
        return $http.get(Connection.baseUrl + '/project/get-project/' + idProject);
    }

    /**
     * cretae project
     * @param project
     * @return {*}
     * @private
     */
    function _createProject(project) {
        return $http.post(myIp + '/project/create', project);
    }

    /**
     * Delete project
     * @param idProject
     * @return {boolean|*}
     * @private
     */
    function _deleteProject(idProject) {
        return $http.delete(myIp + '/project/delete/' + idProject);
    }

    /**
     * edit especific project
     * @param project
     * @return {*}
     * @private
     */
    function _editProject(project) {
        return $http.put(myIp + '/project/update', project);
    }



    return{
        getProjects: _getProjects,
        getProject: _getProject,
        createProject: _createProject,
        deleteProject: _deleteProject,
        editProject: _editProject,
    }
});
