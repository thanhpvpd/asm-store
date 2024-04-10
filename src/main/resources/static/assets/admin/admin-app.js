var app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/product", {
        templateUrl: "/assets/admin/product/index.html",
        controller: "product-ctrl"
    })
    .when("/authorize", {
        templateUrl: "/assets/admin/authority/index.html",
        controller: "authority-ctrl"
    })
    .otherwise({
        template: "<h1 class='text-center mt-5'>Shopping cart Administration</h1>"
    });
});