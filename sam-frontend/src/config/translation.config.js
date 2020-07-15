angular.module('sam').config(function($translateProvider){
    $translateProvider.useStaticFilesLoader({
        prefix: "assets/translations/locale-",
        suffix: ".json"
    });

    //$translateProvider.useSanitizeValueStrategy('sanitize');
});