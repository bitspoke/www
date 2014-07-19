'use strict';

angular.module('app')

.controller('articleCtrl', ['$scope', '$http', 'lzstringSrv', function ($scope, $http, lzstringSrv) {


  $scope.alerts = [];

  var handleError = function(data) {
    $scope.alerts.push({type: 'danger', msg: data});
  };

  $scope.closeAlert = function(index) {
    $scope.alerts.splice(index, 1);
  };


  var id = function(article) {
    return article['_id']['$oid'];
  };


  $scope.list = function() {
    $scope.state = 'listing';
    $http.get('/articles')
      .success(function(data) {
        angular.forEach(data, function(item) {
          item.summary = lzstringSrv.decompress(item.summary);
        });
        $scope.articles = data;
      })
      .error(handleError);
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
        .error(handleError);
    }
    else if ($scope.state === 'updating') {
      $http.put('/articles/' + id(article))
        .success(function() {
          $scope.list();
        })
        .error(handleError);
    }
  };


  $scope.read = function(article) {
    $scope.state = 'reading';
    $http.get('/articles/' + id(article))
      .success(function(data) {
        data.content = lzstringSrv.decompress(data.content);
        data.summary = lzstringSrv.decompress(data.summary);
        $scope.article = data;
      })
      .error(handleError);
  };



  $scope.updating = function(article) {
    $scope.read(article);
    $scope.state = 'updating';
  };



  $scope.delete = function(article) {
    $http.delete('/articles/'+ id(article))
      .success(function() {
        $scope.list();
      })
      .error(handleError);
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


  // start in 'listing' state
  $scope.list();

}])
;
