"use strict";

describe("ArticleCtrl", function() {

    // module() is used to load Angular modules that you want to test
    beforeEach(module("bitspokeApp"));


    // inject() is used to inject arguments of all given functions

    it("should create 'articles' model with 3 articles", inject(function($controller) {
        var scope = {},
            ctrl = $controller("ArticleCtrl", {$scope: scope}); // new ArticleCtrl(scope);

        expect(scope.articles.length).toBe(3);
    }));
});