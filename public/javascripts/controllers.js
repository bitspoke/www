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


  $scope.creating = function() {
    $scope.state = 'creating';
    $scope.article = {};
  };


  $scope.save = function(article) {
    var data = angular.copy(article);

    if (angular.isDefined(article.summary))
      data.summary = lzstringSrv.compress(article.summary);

    if (angular.isDefined(article.content))
      data.content = lzstringSrv.compress(article.content);

    if (angular.isDefined(article.marked))
      delete article.marked;

    if ($scope.state === 'creating') {
      $http.post('/articles', data)
        .success(function() {
          $scope.list();
        })
        .error(function(data) {
          $scope.alerts.push({type: 'danger', msg: data});
        });
    }
    else if ($scope.state === 'updating') {
      // TODO $http.put()
    }
  };


  $scope.read = function(article) {
    $scope.state = 'reading';
    $http.get('/articles/' + article['_id']['$oid'])
      .success(function(data) {
        data.content = lzstringSrv.decompress(data.content);
        data.summary = lzstringSrv.decompress(data.summary);
        $scope.article = data;
      })
      .error(function(data) {
        $scope.alerts.push({type: 'danger', msg: data});
      });
  };



  $scope.updating = function() {
    $scope.state = 'updating';
  };


  $scope.markdown = function(article) {
    if (angular.isDefined(article.content))
      article.marked = marked(article.content);
    else
      delete article.marked;
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
