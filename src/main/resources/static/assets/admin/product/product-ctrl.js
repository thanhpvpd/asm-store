app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.cates = [];
	$scope.pageCount = 0;

	// load thông tin từ server
	$scope.initialize = function() {
		// Load product
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);

			});
			$scope.pageCount = Math.ceil($scope.items.length / 8);
		});
		// Load categori
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		});

	};
	//phân trang
	$scope.begin = 0;
	$scope.currentPage = 1; // Khởi tạo currentPage là 1 ban đầu

	$scope.last = function() {
		$scope.begin = ($scope.pageCount - 1) * 8;
		$scope.currentPage = $scope.pageCount;
	};

	$scope.first = function() {
		$scope.begin = 0;
		$scope.currentPage = 1;
	};

	$scope.next = function() {
		if ($scope.begin < ($scope.pageCount - 1) * 8) {
			$scope.begin += 8;
			$scope.currentPage++; // Tăng currentPage lên mỗi khi chuyển trang
		}else{
			alert("Đang ở trang cuối cùng");
		}
	};

	$scope.prev = function() {
		if ($scope.begin > 0) {
			$scope.begin -= 8;
			$scope.currentPage--; // Giảm currentPage xuống mỗi khi chuyển trang
		}else{
			alert("Đang ở trang đầu");
		}
	};


	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(document).ready(function() {
			var $element = $('#nav-user-edition');
			var $element2 = $('#nav-user-list');
			var $element3 = $('#nav-user-edition-tab');
			var $element4 = $('#nav-user-list-tab');

			// Kiểm tra xem phần tử có chứa lớp 'show' và 'active' hay không
			var isActive = $element.hasClass('show') && $element.hasClass('active');
			var isActive2 = $element3.hasClass('active');

			if (isActive2) {
				$element3.removeClass('active');
				$element4.addClass('active');
			} else {
				$element4.removeClass('active');
				$element3.addClass('active');
			}

			if (isActive) { // Nếu có
				$element.removeClass('show');
				$element.removeClass('active');
				$element2.addClass('show');
				$element2.addClass('active');
			} else {
				$element.addClass('show');
				$element.addClass('active');
				$element2.removeClass('show');
				$element2.removeClass('active');

			}
		});
	}

	$scope.reset = function() {
		$scope.form = {
			name: "",
			createDate: new Date(),
			image: "cloud-upload.jpg",
			available: true
		};
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post("/rest/products", item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm");
			console.log("Error", error);
		})

	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put("/rest/products/" + item.id, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);
		})
	}

	$scope.delete = function() {
		var item = angular.copy($scope.form);
		$http.delete("/rest/products/" + item.id).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		})
	}

	$scope.upLoadImage = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/product', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 8,
		get items() {
			var start = this.page * this.size;
			return $scope.item.slice(start, start + this.size);
		}
	}

	$scope.initialize();

});

