/**
 * Created by breezzo on 08.08.15.
 */

angular.module('iss').
    directive('imageLoad', function() {
        var staticRoot = localStorage.getItem('staticRoot');

        return {
            restrict: 'A',
            scope: {},
            templateUrl: staticRoot + '/html/image_load/image_load_template.html',
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
        var staticRoot = localStorage.getItem('staticRoot');

        return {
            restrict: 'A',
            scope: {},
            templateUrl: staticRoot + '/html/image_view/album_view_template.html',
            link: function(scope, element, attrs) {
                scope.load();
            },
            controller: ['$scope', 'Albums', function($scope, Albums) {

                $scope.load = function() {

                    Albums.getByUser(null, function(data) {
                        $scope.model = data;
                    }, function(response) {
                        alert(response);
                    });
                }
            }]
        };
    });