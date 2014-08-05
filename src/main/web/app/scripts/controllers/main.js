'use strict';

/**
 * @ngdoc function
 * @name youShouldRememberMeUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the youShouldRememberMeUiApp
 */
angular.module('youShouldRememberMeUiApp')
        .controller('MainCtrl', function ($scope, $resource) {
            var link = $resource('/rest/link', {}, {
                link: {method: 'PUT', headers: {'Content-Type': 'application/vnd.gui.v1+json'},
                    responseType: 'application/vnd.gui.v1+json'}
            });

            $scope.linkRequest = {
                twitter: "",
                facebook: "",
                googleplus: ""
            };

            $scope.generate = function() {
                link.link({}, $scope.linkRequest, function(data){
                    console.log("ok, response:");
                    console.log(data);
                },
                function() {
                    console.log("bad :(");
                });
            }
        });
