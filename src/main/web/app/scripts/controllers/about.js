'use strict';

/**
 * @ngdoc function
 * @name youShouldRememberMeUiApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the youShouldRememberMeUiApp
 */
angular.module('youShouldRememberMeUiApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
