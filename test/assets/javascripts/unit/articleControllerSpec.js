'use strict';

// https://docs.angularjs.org/api/ngMock/service/$httpBackend

describe('articleCtrl', function() {

  var scope, ctrl, backend;


  beforeEach(module('app'));


  beforeEach(inject(function($httpBackend, $rootScope, $controller) { 
    backend = $httpBackend;
    backend.expectGET('/articles').respond([
      {
        _id: {'$oid': '53bf100e3004eb66c3f7e5fe'},
        title:' Christmas Programming',
        author: 'paolo',
        _date: {'$date': '2014-07-11T20:16:06.977Z'},
        summary: 'HIUwbiBOAEA2CWBrE0AuB7aB3S9Uo0wFsBXAYwAtoz0ATEIA',
        content: 'HIUwbiBOAEA2CWBrEBnaAXA9tA7pe6IGm2AtgK4DGAFtJZgCYhAAAA=='
      }
    ]);

    scope = $rootScope.$new();
    ctrl = $controller('articleCtrl', {$scope: scope});
  }));


  // inject() is used to inject arguments of all given functions

  it('should have "listing" state on init', function() {
    expect(scope.state).toBe('listing');
  });


  it('should get articles from backend', function() { 
    backend.flush();  
    expect(scope.articles.length).toBe(1);
  });


  it('should create an article', function() {
    scope.create({
      'title': 'title',
      'summary': 'summary',
      'content': 'content'
    });
    expect(scope.articles.length).toBe(1);
  });
});