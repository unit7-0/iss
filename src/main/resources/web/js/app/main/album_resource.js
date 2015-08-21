angular.module('iss')
    .factory('Albums', ['$resource', function($resource) {
        var contextPath = localStorage.getItem('contextPath');

        return $resource(contextPath + '/rest/image/album/:actionName/:param', null, {
            create: {
                actionName: 'create',
                method: 'PUT',
                params: {
                    actionName: 'getByUser'
                }
            },
            getByUser: {
                method: 'GET',
                params: {
                    actionName: 'getByUser',
                    param: '55d6257a9d45b07a9f3e8d3c' // TODO
                }
            }
        });
    }]);