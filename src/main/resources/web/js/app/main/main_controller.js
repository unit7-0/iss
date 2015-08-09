/**
 * Created by breezzo on 08.08.15.
 */

angular.module('iss').
    directive('imageLoad', function() {
        var contextPath = localStorage.getItem('contextPath');

        return {
            restrict: 'A',
            scope: {},
            templateUrl: contextPath + '/html/image_load/image_load_template.html',
            link: function(scope, element, attrs) {
                scope.model = {};
            },
            controller: ['$scope', function($scope) {
                $scope.loadImage = function(model) {
                    console.log('loadImage: ' + model);
                }
            }]
        };
    }).
    directive('albumView', function() {
        var contextPath = localStorage.getItem('contextPath');

        return {
            restrict: 'A',
            scope: {},
            templateUrl: contextPath + '/html/image_view/album_view_template.html',
            link: function(scope, element, attrs) {
                scope.model = {};
            },
            controller: ['$scope', function($scope) {

            }]
        };
    });