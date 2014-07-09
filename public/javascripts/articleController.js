'use strict';

var bitspokeApp = angular.module('bitspokeApp', []);

bitspokeApp.filter('unsafe', ['$sce', function ($sce) {
  return function (val) {
    return $sce.trustAsHtml(val);
  };
}]);

bitspokeApp.controller('articleCtrl', ['$scope', '$http', function ($scope, $http) {
  
  $scope.articles = [];

  $scope.state = 'list';

  $http.get('/articles').success(function(data) {
    $scope.articles = data;
  });  

  $scope.getClassByState = function(s) {
    return $scope.state === s ? 'active' : '';
  };

  $scope.setState = function(s) {
    $scope.state = s;
  };

  $scope.articlesIsEmpty = function() {
    return $scope.articles.length == 0;
  };

  $scope.save = function(article) {
    $http.post('/articles', article);
  };

  $scope.preview = function(article) {
    var md = '';
    if (angular.isDefined(article.title)) {
      md = md + '# ' + article.title + '\n';
    }
    if (angular.isDefined(article.content)) {
      md = md + article.content;
    }
    return marked(md);
  }
}]);