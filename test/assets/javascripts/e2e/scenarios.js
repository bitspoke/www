'use strict';

describe('Article list view', function() {
  
  beforeEach(function() {
    browser.get('gui/articles');
  });

  it("should list 3 articles", function() {
    var articleList = element.all(by.repeater('article in articles'));
    expect(articleList.count()).toBe(3);
  });
});