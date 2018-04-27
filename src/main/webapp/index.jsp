<html>
<head>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div ng-app="partApp" ng-controller="appCtrl">
    <table class="filter-table">
        <tr>
            <td>PN</td>
            <td > <input type="text" ng-model="number"></td>
        </tr>
        <tr>
            <td>Part Name</td>
            <td > <input type="text" ng-model="name"></td>
        </tr>
        <tr>
            <td>Vendor</td>
            <td > <input type="text" ng-model="vendor"></td>
        </tr>
        <tr>
            <td>Qty</td>
            <td > <input type="text" ng-model="qty"></td>
        </tr>
        <tr>
            <td>Shipped</td>
            <td>after <input type="date" ng-model="shippedAfter"> before  <input type="date" ng-model="shippedBefore"></td>
        </tr>
        <tr>
            <td>Received</td>
            <td>after  <input type="date" ng-model="receivedAfter"> before  <input type="date" ng-model="receivedBefore"></td>
        </tr>
    </table>

    <button class="filter" type="button" ng-click="filter()">Filter</button>

    <table border="1">
        <tr>
            <th><a href="#" ng-click="sortType = 'number'; sortReverse = !sortReverse">PN</a></th>
            <th><a href="#" ng-click="sortType = 'name'; sortReverse = !sortReverse">Part Name</a></th>
            <th><a href="#" ng-click="sortType = 'vendor'; sortReverse = !sortReverse">Vendor</a></th>
            <th><a href="#" ng-click="sortType = 'qty'; sortReverse = !sortReverse">Qty</a></th>
            <th><a href="#" ng-click="sortType = 'shipped'; sortReverse = !sortReverse">Shipped</a></th>
            <th><a href="#" ng-click="sortType = 'received'; sortReverse = !sortReverse">Received</a></th>
        </tr>
        <tr ng-repeat="part in parts | orderBy:sortType:sortReverse">
            <td>{{part.number}}</td>
            <td>{{part.name}}</td>
            <td>{{part.vendor}}</td>
            <td>{{part.qty}}</td>
            <td>{{part.shipped | date : 'longDate'}}</td>
            <td>{{part.received | date : 'longDate'}}</td>
        </tr>

    </table>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js"></script>
<script src="js/app.js"></script>
</body>
</html>
