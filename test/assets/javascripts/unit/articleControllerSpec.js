'use strict';

describe('articleCtrl', function() {
  var scope, ctrl, backend;

  // module() is used to load Angular modules that you want to test
  beforeEach(module('bitspokeApp'));

  beforeEach(inject(function($httpBackend, $rootScope, $controller) { 
    backend = $httpBackend;
    backend.expectGET('/assets/articles.json')
           .respond([{title:'title', author:'author', epoch:431276831, summary:'summary', content:'content'}]);

    scope = $rootScope.$new();
    ctrl = $controller('articleCtrl', {$scope: scope});
  }));


  // inject() is used to inject arguments of all given functions

  it('should have no articles on init', inject(function($controller) {    
    expect(scope.articlesIsEmpty()).toBe(true);    
  }));


  it('should have "list" state on init', inject(function($controller) {    
    expect(scope.state).toBe('list');
  }));


  it('should switch state and class', inject(function($controller) {    
    expect(scope.getClassByState('list')).toBe('active');    
    scope.setState('add');
    expect(scope.getClassByState('add')).toBe('active');
  }));


  it('should get articles from backend', inject(function($controller) {  
    backend.flush();  
    expect(scope.articlesIsEmpty()).toBe(false);
    expect(scope.articles.length).toBe(1);
  }));


  it('should save an article', inject(function($controller) {
    scope.save({title:'a new article'});
    expect(scope.articles.length).toBe(1);
  }));
});