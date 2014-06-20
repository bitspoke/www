"use strict";

var bitspokeApp = angular.module("bitspokeApp", []);

bitspokeApp.controller("ArticleCtrl", function ($scope) {

  $scope.articles = [];

  $scope.articlesIsEmpty = function() {
    return $scope.articles.length == 0;
  }

  $scope.canSaveArticle = function(article) {
    return angular.isObject(article) && angular.isString(article.title)
  }

  $scope.save = function(article) {
    $scope.articles.push(article);
    $scope.article = null;    
  }
});