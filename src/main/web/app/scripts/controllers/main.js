'use strict';

/**
 * @ngdoc function
 * @name youShouldRememberMeUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the youShouldRememberMeUiApp
 */
angular.module('youShouldRememberMeUiApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
