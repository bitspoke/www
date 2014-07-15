'use strict';

angular.module('app')

.controller('articleCtrl', ['$scope', '$http', 'lzstringSrv', function ($scope, $http, lzstringSrv) {

  $scope.alerts = [];

  $scope.closeAlert = function(index) {
    $scope.alerts.splice(index, 1);
  };


  $scope.list = function() {
    $scope.state = 'listing';
    $http.get('/articles')
      .success(function(data) {
        angular.forEach(data, function(value) {
          value.summary = lzstringSrv.decompress(value.summary);
        });
        $scope.articles = data;
      })
      .error(function(data) {
        $scope.alerts.push({type: 'danger', msg: data});
      });
  };


  $scope.create = function(article) {
    var data = angular.copy(article);
    if (angular.isDefined(article.summary)) data.summary = lzstringSrv.compress(article.summary);
    if (angular.isDefined(article.content)) data.content = lzstringSrv.compress(article.content);

    $http.post('/articles', data)
      .success(function() {
        $scope.list();
      })
      .error(function(data) {
        $scope.alerts.push({type: 'danger', msg: data});
      });
  };


  $scope.read = function(id) {
    $scope.state = 'reading';
    $http.get('/articles/' + id)
      .success(function(data) {
        data.content = lzstringSrv.decompress(data.content);
        data.summary = lzstringSrv.decompress(data.summary);
        $scope.article = data;
      })
      .error(function(data) {
        $scope.alerts.push({type: 'danger', msg: data});
      });
  };



  $scope.marked = function(src) {
    if (angular.isDefined(src)) {
      return marked(src, {sanitize: "true"});
    }
  };


  // finally invoke list() to switch to 'listing' state
  $scope.list();

}])
;
