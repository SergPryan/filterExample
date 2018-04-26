<html>
<body>

<div ng-app="partApp" ng-controller="appCtrl">
    <label> PN
        <input type="text" ng-model="number">
    </label> <br>

    <label> Part Name
        <input type="text" ng-model="name">
    </label> <br>

    <label> Vendor
        <input type="text" ng-model="vendor">
    </label> <br>

    <label> Qty
        <input type="text" ng-model="qty">
    </label> <br>
    Shipped <label> after
    <input type="date" ng-model="shippedAfter">
</label>
    <label> before
        <input type="date" ng-model="shippedBefore">
    </label> <br>
    Received <label> after
    <input type="date" ng-model="receivedAfter">
</label>
    <label> before
        <input type="date" ng-model="receivedBefore">
    </label> <br>

    <button type="button" ng-click="filter()">Filter</button>

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
            <td>{{part.shipped}}</td>
            <td>{{part.received}}</td>
        </tr>

    </table>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js"></script>
<script src="js/app.js"></script>
</body>
</html>
