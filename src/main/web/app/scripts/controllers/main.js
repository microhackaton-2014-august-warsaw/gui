'use strict';

/**
 * @ngdoc function
 * @name youShouldRememberMeUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the youShouldRememberMeUiApp
 */
angular.module('youShouldRememberMeUiApp')
        .controller('MainCtrl', function ($scope, $resource, $routeParams) {
            var link = $resource('/rest/link/:url', {}, {
                put: {method: 'PUT', headers: {'Content-Type': 'application/vnd.gui.v1+json'},
                    responseType: 'application/vnd.gui.v1+json'},
                post: {method: 'POST', headers: {'Content-Type': 'application/vnd.gui.v1+json'},
                    responseType: 'application/vnd.gui.v1+json'}
            });

            if ($routeParams.id) {
                $scope.personToMatch = link.post({'url': $routeParams.id}, '{}');
            }

            $scope.linkRequest = {
                twitter: '',
                facebook: '',
                googleplus: '',
                name: ''
            };

            $scope.generate = function() {
                link.put({'url': ''}, $scope.linkRequest, function(data){
                    console.log('ok, response:');
                    console.log(data);
                },
                function() {
                    console.log('bad :(');
                });
            };
        });
