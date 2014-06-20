"use strict";

describe("ArticleCtrl", function() {
  var scope;

  // module() is used to load Angular modules that you want to test
  beforeEach(function() {
    module("bitspokeApp");
    inject(function($controller) {
      scope = {};
      $controller("ArticleCtrl", {$scope: scope}); // new ArticleCtrl(scope);
    });
  });


  // inject() is used to inject arguments of all given functions

  it("should have no articles on init", inject(function($controller) {    
    expect(scope.articlesIsEmpty()).toBe(true);    
  }));


  it("should know when can save article", inject(function($controller) {    
    expect(scope.canSaveArticle({"title":"this can be saved"})).toBe(true);
    expect(scope.canSaveArticle({"content":"since it has no title then it cannot be saved"})).toBe(false);
  }));


  it("should save an article", inject(function($controller) {
    scope.save({"title":"a new article"});
    expect(scope.articles.length).toBe(1);
  }));
});