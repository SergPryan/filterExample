app = angular.module('partApp',[]);

angular.isUndefinedOrNull = function(val) {
    return angular.isUndefined(val) || val === null
}

app.controller('appCtrl', function($scope,$http,$filter, Utils){
       $scope.parts = [];

    $scope.sortType     = 'number';
    $scope.sortReverse  = false;

       $scope.filter = function () {

           var request = '/part?';
           var name = $scope.name;
           var number = $scope.number;
           var vendor = $scope.vendor;
           var qty = $scope.qty;
           var receivedAfter = $filter('date')( $scope.receivedAfter,'yyyy-MM-dd');
           var receivedBefore = $filter('date')( $scope.receivedBefore,'yyyy-MM-dd');
           var shippedAfter = $filter('date')( $scope.shippedAfter,'yyyy-MM-dd');
           var shippedBefore = $filter('date')( $scope.shippedBefore,'yyyy-MM-dd');

           if(!Utils.isUndefinedOrNull(name)){
               request += 'name='+name+'&';
           }
           if(!Utils.isUndefinedOrNull(number)){
               request+='number='+number+'&'
           }
           if(!Utils.isUndefinedOrNull(vendor)){
               request+='vendor='+vendor+'&'
           }
           if(!Utils.isUndefinedOrNull(qty)){
               request+='qty='+qty+'&'
           }
           if(!Utils.isUndefinedOrNull(receivedAfter)){
               request+='receivedAfter='+receivedAfter+'&'
           }
           if(!Utils.isUndefinedOrNull(receivedBefore)){
               request+='receivedBefore='+receivedBefore+'&'
           }
           if(!Utils.isUndefinedOrNull(shippedAfter)){
               request+='shippedAfter='+shippedAfter+'&'
           }
           if(!Utils.isUndefinedOrNull(shippedBefore)){
               request+='shippedBefore='+shippedBefore+'&'
           }
           $http.get(request).then(function (response) {
               $scope.parts = response.data;
           })
       }

});

app.factory('Utils', function() {
    var service = {
        isUndefinedOrNull: function(obj) {
            return !angular.isDefined(obj) || obj==null;
        }

    }

    return service;
});