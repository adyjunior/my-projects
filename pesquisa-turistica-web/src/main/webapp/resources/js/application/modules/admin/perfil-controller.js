function perfilController($scope, $http) {
	
	$scope.url = 'http://localhost:8080/pesquisa-turistica-web/perfil/lista.sp';
	
    $scope.getPerfisList = function () {
        var url = $scope.url;

        //var config = {params: {page: $scope.pageToGet}};

        $http.get(url)
            .success(function (data) {
                $scope.perfisPaginated = data;
            })
            .error(function () {
            	alert('Erro');
            });
    }
	
    $scope.getPerfisList();
	
}
