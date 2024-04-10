var app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {
    /*quản lý giỏ hàng*/
    $scope.cart = {
        items: [],
        add: function(id) {
            var idPro = id;
            var item = this.items.find(item => item.id == idPro);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get("/rest/products/" + idPro).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                });
            }
        },
        saveToLocalStorage: function() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        get count(){
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		get amount(){
			return this.items
				.map(item => item.qty * item.price)
				.reduce((total, qty) => total += qty, 0);
		},
		loadFormLocalStorage: function(){
			var json = localStorage.getItem("cart");
			this.items = json?JSON.parse(json): [];
		},
		remove: function(id){
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index,1);
			this.saveToLocalStorage();
		},
		clear: function(){
			this.items=[];
			this.saveToLocalStorage();
		}
		
    };
    
    $scope.cart.loadFormLocalStorage();
    
    $scope.order = {
		createDate: new Date(),
		address: "",
		account: {username: $("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item =>{
				return{
					product:{id: item.id},
					price: item.price,
					quantity: item.qty
				}
			})
		},
		purchase: function(){
			var order = angular.copy(this)
			console.log(order)
			$http.post("/rest/orders", order).then(resp =>{
				alert("Đặt hàng thành công");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng không công");
				console.log(error);
			})
		}
	}
});
