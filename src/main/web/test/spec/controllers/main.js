'use strict';

describe('Controller: MainCtrl', function () {

  // load the controller's module
  beforeEach(module('youShouldRememberMeUiApp'));

  var MainCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MainCtrl = $controller('MainCtrl', {
      $scope: scope
    });
  }));

  it('should attach a linkRequest to the scope', function () {
    expect(scope.linkRequest.twitter).toBe('');
    expect(scope.linkRequest.facebook).toBe('');
    expect(scope.linkRequest.googleplus).toBe('');
  });
});
