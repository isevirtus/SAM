angular.module('sam').factory('QuestionFactory', function ($http, $state, Connection) {

    var myIp = Connection.baseUrl;

    /**
     * get all questions
     * @return {*}
     * @private
     */
    function _getQuestions(){
        return $http.get(Connection.baseUrl + '/question/list');
    }

    /**
     * get especific question
     * @param idQuestion
     * @return {*}
     * @private
     */
    function _getQuestion(idQuestion) {
        return $http.get(myIp + '/question/get-question/' + idQuestion);
    }

    /**
     * create question
     * @param question
     * @return {*}
     * @private
     */
    function _createQuestion(question) {
        return $http.post(Connection.baseUrl + "/question/create", question);
    }

    /**
     * Delete question
     * @param idQuestion
     * @return {boolean|*}
     * @private
     */
    function _deleteQuestion(idQuestion) {
        return $http.delete(myIp + '/question/delete/' + idQuestion);
    }

    return{
        getQuestions: _getQuestions,
        createQuestion : _createQuestion,
        deleteQuestion: _deleteQuestion,
        getQuestion: _getQuestion
    }

});