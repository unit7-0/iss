/**
 * Created by breezzo on 09.08.15.
 */
angular.module('iss')
    .directive('mainNavbar', function() {
        var staticRoot = localStorage.getItem('staticRoot');

        return {
            restrict: 'A',
            scope: {},
            templateUrl: staticRoot + '/html/main/navbar_template.html',
            link: function(scope, element, attrs) {
                scope.model = {};
            },
            controller: ['$scope', function($scope) {
                $scope.search = function(model) {
                    console.log('search: ' + model.searchText);
                }
            }]
        };
    });