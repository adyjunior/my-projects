function questionController($scope, $http) {
	
	$scope.url = 'http://www.w3schools.com/website/Customers_JSON.php';
	
    $scope.getQuestionList = function () {
        var url = $scope.url;

        //var config = {params: {page: $scope.pageToGet}};

        $http.get(url)
            .success(function (data) {
                $scope.questionsPaginated = data;
            })
            .error(function () {
            	//alert('Erro');
            });
    }
	
    $scope.getQuestionList();
	
}
