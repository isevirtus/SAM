/**
 * Created by mendelssohn on 26/06/17.
 */
angular.module('sam').directive('draggable', ['$rootScope', function ($rootScope) {
        return function ($scope, element) {
            var el = element[0];
            el.draggable = true;

            el.addEventListener(
                'dragstart',
                function(e) {
                    $rootScope.idItem = this.id;
                    e.dataTransfer.setData('text', this.id);
                    e.dataTransfer.effectAllowed = 'move';

                    this.classList.add('drag');
                    return false;
                },
                false
            );

            el.addEventListener(
                'dragend',
                function(e) {
                    this.classList.remove('drag');
                    return false;
                },
                false
            );
        }
    }])
    .directive('droppable', ['$rootScope', function ($rootScope) {
    return {
        scope: {
            drop: '&',
            bin: '='
        },
        link: function(scope, element) {
            // again we need the native object
            var el = element[0];

            el.addEventListener(
                'dragover',
                function(e) {
                    e.dataTransfer.dropEffect = 'move';
                    // allows us to drop
                    if (e.preventDefault) e.preventDefault();

                    idItem = $rootScope.idItem;

                    typeOfSrc = idItem.replace(/[0-9]/g, '');
                    typeOfDest = this.id.replace(/[0-9]/g, '');

                    if (typeOfSrc !== typeOfDest) {
                        this.classList.add('over');
                    }
                    return false;
                },
                false
            );

            el.addEventListener(
                'dragenter',
                function(e) {
                    idItem = $rootScope.idItem;

                    typeOfSrc = idItem.replace(/[0-9]/g, '');
                    typeOfDest = this.id.replace(/[0-9]/g, '');

                    if (typeOfSrc !== typeOfDest) {
                        this.classList.add('over');
                    }
                    return false;
                },
                false
            );

            el.addEventListener(
                'dragleave',
                function(e) {
                    this.classList.remove('over');
                    return false;
                },
                false
            );

            el.addEventListener(
                'drop',
                function(e) {
                    // Stops some browsers from redirecting.
                    if (e.preventDefault) e.preventDefault();
                    if (e.stopPropagation) e.stopPropagation();

                    idItem = $rootScope.idItem;

                    typeOfSrc = idItem.replace(/[0-9]/g, '');
                    typeOfDest = this.id.replace(/[0-9]/g, '');

                    if (typeOfSrc !== typeOfDest) {
                        this.classList.remove('over');
                    }

                    return false;
                },
                false
            );
        }
    }
}]);
