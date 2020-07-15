angular.module("sam").controller('ListObjectiveController', function ($scope, $state, ObjectiveFactory) {


    $scope.objectives = [];

    $scope.objectives = [
        {
            name: "Manuel",
            qualityFocus: "qualityFocus 1",
            variationFactors: "variationFactors 2"

        },

        {
            name: "Caio",
            qualityFocus: "qualityFocus 2",
            variationFactors: "variationFactors 2"

        },

        {
            name: "Mendel",
            qualityFocus: "qualityFocus 3",
            variationFactors: "variationFactors 3"

        }
    ];

    // getObjectives = function () {
    //
    //     console.log('oi');
    //     //reverse -> inverter a lista para ordenar cronologicamente
    //     ObjectiveFactory.getObjectives().then(function (result) {
    //         console.log("oi" + result);
    //         $scope.objectives = result.reverse();
    //     });
    // };
    //
    // getObjectives();
    //
    // $(document).ready(function() {
    //     $('[id^=detail-]').hide();
    //     $('.toggle').click(function() {
    //         $input = $( this );
    //         $target = $('#'+$input.attr('data-toggle'));
    //         $target.slideToggle();
    //     });
    // });

});