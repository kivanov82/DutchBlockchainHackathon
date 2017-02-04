'use strict';

hackathonControllers.controller('LoginCtrl', ['$scope', 'auth',
    function ($scope, auth) {
        $scope.submit = function () {
            if (!$scope.username || !$scope.password) {
                $scope.loginButtonClass = "error";
                $scope.loginMessageClass = "show error";
                return;
            }
            var postData = {
                "username": $scope.username,
                "password": $scope.password
            };

            auth.authenticate(postData, function (authenticated) {
                if (authenticated) {
                    $scope.loginButtonClass = "success";
                    $scope.loginMessageClass = "show success";
                } else {
                    $scope.loginButtonClass = "error";
                    $scope.loginMessageClass = "show error";
                }
            });
        };
    }]);

hackathonControllers.controller('LogoutCtrl', ['$scope', 'auth',
    function ($scope, auth) {
        $scope.logout = auth.clear;
    }]);
