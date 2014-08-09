'use strict';

/**
 * @ngdoc function
 * @name youShouldRememberMeUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the youShouldRememberMeUiApp
 */
var LinkModalCtrl = function ($scope, $modalInstance, link, $location) {

    $scope.link = $location.absUrl() + link;

    $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};

angular.module('youShouldRememberMeUiApp')
        .controller('MainCtrl', function ($scope, $resource, $routeParams, $modal, $timeout) {
            var link = $resource('/rest/link/:url', {}, {
                put: {method: 'PUT', headers: {'Content-Type': 'application/vnd.gui.v1+json'},
                    responseType: 'application/vnd.gui.v1+json'},
                post: {method: 'POST', headers: {'Content-Type': 'application/vnd.gui.v1+json'},
                    responseType: 'application/vnd.gui.v1+json'}
            });

            var pull = $resource('/rest/results/pull', {}, {
                pull: {method: 'POST', headers: {'Content-Type': 'application/vnd.gui.v1+json'},
                    responseType: 'application/vnd.gui.v1+json'}
            });

            if ($routeParams.id) {
                $scope.personToMatch = link.post({'url': $routeParams.id}, '{}');
            }

            $scope.link = '';
            $scope.pairId = '';

            $scope.results = [];

            $scope.linkRequest = {
                twitter: '',
                facebook: '',
                googleplus: '',
                name: '',
                rss: ''
            };

            $scope.generate = function () {
                link.put({'url': ''}, $scope.linkRequest, function (data) {
                            console.log('ok, response:');
                            console.log(data);
                            $scope.link = data.string;

                            if (!$scope.personToMatch) {
                                showLink();
                            } else {
                                link.put({'url': 'pair'}, {celebrityId: data.string, peasantId: $scope.personToMatch.id},
                                        function (data) {
                                            console.log('ok, response from pair:');
                                            console.log(data);
                                            $scope.pairId = data.string;
                                            $scope.sendToMatcher({
                                                pairId: data.string,
                                                peasant: $scope.personToMatch,
                                                celebrity: $scope.linkRequest
                                            });
                                            $scope.startPullingResponse();
                                        },
                                        function () {
                                            console.log('bad pair :(');
                                        });
                            }
                        },
                        function () {
                            console.log('bad :(');
                        });


            };

            $scope.sendToMatcher = function() {
                //todo
            };

            $scope.startPullingResponse = function() {
                pull.pull({}, {'id': $scope.pairId}, function(pulledData){
                    if (pulledData.result) {
                        console.log('gut gut gut');
                        $scope.results.push(pulledData.result);
                    }
                }, function() {
                    console.log('Pulling error');
                });

                $timeout($scope.startPullingResponse, 1000);
            };

            var showLink = function () {
                $modal.open({
                    templateUrl: 'modalWithLink.html',
                    controller: LinkModalCtrl,
                    size: 'lg',
                    resolve: {
                        link: function () {
                            return $scope.link;
                        }
                    }
                });
            };
        });
