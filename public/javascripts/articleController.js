'use strict';

var bitspokeApp = angular.module('bitspokeApp', []);

bitspokeApp.controller('articleCtrl', ['$scope', '$http', function ($scope, $http) {
  
  $scope.articles = [];

  $scope.state = 'list';

  $http.get('/assets/articles.json').success(function(data) {
    $scope.articles = data;
  });  

  $scope.getClassByState = function(s) {
    return $scope.state === s ? 'active' : '';
  }

  $scope.setState = function(s) {
    $scope.state = s;
  }

  $scope.articlesIsEmpty = function() {
    return $scope.articles.length == 0;
  }

  $scope.save = function(article) {
    $scope.articles.push(article);
    $scope.article = null;    
  }
}]);