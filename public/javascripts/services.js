'use strict';

angular.module('app')

.service('lzstringSrv', function() {
  return {

    compress: function(clearText) {
      var len1 = clearText.length;
      console.log('Uncompressed content is ' + len1 + ' bytes');
      var cypherText = LZString.compressToBase64(clearText);
      var len2 = cypherText.length;
      var ratio = Math.round((len2 / len1) * 100);
      console.log('Compressed content is ' + len2 + ' bytes. Ratio is ' + ratio + '%');
      return cypherText;
    },

    decompress: function(clearText) {
      return LZString.decompressFromBase64(clearText);
    }
  };
})
;