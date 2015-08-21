/**
 * Created by breezzo on 08.08.15.
 */

localStorage.setItem('contextPath', '/iss');
localStorage.setItem('staticRoot', '/static')

angular.module('issApplication', []);
angular.module('iss', ['ngResource', 'issApplication']);