/**
 * Created by breezzo on 09.08.15.
 */
angular.module('iss')
    .directive('mainNavbar', function() {
        var contextPath = localStorage.getItem('contextPath');

        return {
            restrict: 'A',
            scope: {},
            templateUrl: contextPath + '/html/main/navbar_template.html',
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