'use strict';

describe('lzstringSrv', function() {

  // service under test
  var lzstringSrv;

  // bootstrap angualar module
  beforeEach(module('app'));

  // inject service under test using angular underscore notation
  beforeEach(inject(function(_lzstringSrv_) {
    lzstringSrv = _lzstringSrv_;
  }));


  it('should compress/decompress strings', function() {
    expect(lzstringSrv.decompress(lzstringSrv.compress("Life is worth living"))).toBe("Life is worth living");
  });

});