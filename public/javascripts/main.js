"use strict";

var bitspokeApp = angular.module("bitspokeApp", []);

bitspokeApp.controller("ArticleCntrl", function($scope) {

    $scope.articles = [
        {"title": "title", "author": "author", "epoch": 431276831, "summary": "summary"},
        {"title": "title", "author": "author", "epoch": 431276831, "summary": "summary"},
        {"title": "title", "author": "author", "epoch": 431276831, "summary": "summary"}
    ];

    $scope.isEmpty = function() {
        $scope.article.length === 0
    }
});