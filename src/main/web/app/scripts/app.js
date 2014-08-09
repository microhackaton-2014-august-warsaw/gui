'use strict';

/**
 * @ngdoc overview
 * @name youShouldRememberMeUiApp
 * @description
 * # youShouldRememberMeUiApp
 *
 * Main module of the application.
 */
angular
        .module('youShouldRememberMeUiApp', [
            'ngAnimate',
            'ngCookies',
            'ngResource',
            'ngRoute',
            'ngSanitize',
            'ngTouch',
            'ui.bootstrap',
            'angular-websocket'
        ]).config(function (WebSocketProvider) {
            WebSocketProvider
                    .prefix('')
                    .uri('ws://echo.websocket.org/');
        })
        .config(function ($routeProvider) {
            $routeProvider
                    .when('/', {
                        templateUrl: 'views/main.html',
                        controller: 'MainCtrl'
                    })
                    .when('/:id', {
                        templateUrl: 'views/main.html',
                        controller: 'MainCtrl'
                    })
                    .when('/about', {
                        templateUrl: 'views/about.html',
                        controller: 'AboutCtrl'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
        });
