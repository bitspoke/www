'use strict';

angular.module('app')

.filter('trust', ['$sce', function ($sce) {
  return function (val) {
    return $sce.trustAsHtml(val);
  };
}])
;