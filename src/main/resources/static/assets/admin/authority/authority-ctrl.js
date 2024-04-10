app.controller("authority-ctrl", function($scope, $http) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];

	$scope.initalize = function() {
		// load all role
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
		})

		// load staffs and directors
		$http.get("/rest/accounts?admin=true").then(resp => {
			$scope.admins = resp.data;
		})

		// load authorities of staffs and directors4
		$http.get("/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
			console.log($scope.authorities)
		}).catch(error => {
			console.log("error", error);
			if(error.status == 403){
				alert("Tài Khoản của bạn không đủ thẩm quyền");
			}
		})
	}

	$scope.authority_of = function(acc, role) {
		if ($scope.authorities) {
			return $scope.authorities.find(ur =>
				ur.account.username == acc.username &&
				ur.role.id == role.id
			);
		}
	};
	
	$scope.authority_changed = function(acc, role){
		var authority = $scope.authority_of(acc, role);
		
		if(authority){
			// thu hoi
			$scope.revoke_authority(authority);
		}else{
			// cap quyen
			authority = {account: acc, role: role};
			$scope.grant_authority(authority);
		}
	}
	
	$scope.grant_authority = function(authority){
		$http.post("/rest/authorities", authority).then(resp => {
			$scope.authorities.push(resp.data);
			alert("Cấp quyền thành công");
		}).catch(error => {
			console.log("error", error);
			if(error.status == 403){
				alert("Tài Khoản của bạn không đủ thẩm quyền");
			}else{
				alert("Cấp quyền thất bại");
			}
		})
	}
	
	$scope.revoke_authority = function(authority){
		$http.delete("/rest/authorities/" + authority.id).then(resp => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index, 1);
			alert("Thu hồi thành công");
		}).catch(error => {
			console.log("error", error);
			if(error.status == 403){
				alert("Tài Khoản của bạn không đủ thẩm quyền");
			}else{
				alert("Thu hồi thất bại");
			}
		})
	}

	$scope.initalize();
});