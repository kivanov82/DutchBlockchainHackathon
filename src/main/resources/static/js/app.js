'use strict';

/* App Module */

var HackathonApp = angular.module('hackathonApp', [
    'ngRoute',
    'auth',
    'nvd3',
    'smart-table', //https://lorenzofox3.github.io/smart-table-website/
    'angularSpinners', //https://github.com/Chevtek/angular-spinners
    'hackathonControllers'
]);

HackathonApp.config(['$routeProvider', '$locationProvider', '$httpProvider',
    function ($routeProvider, $locationProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'partials/main.html',
            controller: 'MainCtrl'
        }).when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'LoginCtrl'
        }).otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

        $locationProvider.html5Mode(true);
    }]).run(function (auth) {
    // Initialize auth module with the home page and login/logout path
    // respectively
    auth.init('/', '/login', '/logout');
});

var hackathonControllers = angular.module('hackathonControllers', ['auth']);



