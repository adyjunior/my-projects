<%@ include file="/utils/include-taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Start Bootstrap - SB Admin Version 2.0 Demo</title>

    <!-- Core CSS - Include with every page -->
    <link href="<util:url/>resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<util:url/>resources/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Page-Level Plugin CSS - Tables -->
    <link href="<util:url/>resources/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <!-- SB Admin CSS - Include with every page -->
    <link href="<util:url/>resources/css/sb-admin.css" rel="stylesheet">
    <script src="<util:url/>resources/js/angularjs/angular-1.2.0.js" type="text/javascript"></script>
    <script src="<util:url/>resources/js/application/modules/admin/perfil-controller.js"></script>
</head>
<body>
    <div id="wrapper">
	
		<jsp:include page="/template/include-topo.jsp" flush="true"/>

        <div id="page-wrapper" ng-app="" ng-controller="perfilController">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Perf&iacute;s do Sistema</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
								<div class="row">
								   <div class="col-sm-6">
								   </div>
								   <div class="col-sm-6">
								      <div><label style="float: right;">Busca:<input style="width: 16em;" type="search" class="form-control input-sm"></label></div>
								   </div>
								</div>
	                            <!-- /.row -->
	                            
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Rendering engine</th>
                                            <th>Browser</th>
                                            <th>Platform(s)</th>
                                            <th>Engine version</th>
                                            <th>CSS grade</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="perfil in perfisPaginated" class="odd gradeX">
                                            <td><button class="btn btn-info">Editar</button></td>
                                            <td>{{perfil.nome}}</td>
                                            <td>{{perfil.email}}</td>
                                            <td class="center">{{$index % 2 == 0 ? 'Par' : 'Impar'}}</td>
                                            <td><button class="btn btn-danger">Excluir</button></td>
                                        </tr>
                                    </tbody>
                                </table>
                                
								<div class="row">
								   <div class="col-sm-6">
								      <div style="padding-top: 8px;">Showing 1 to 10 of 29 entries</div>
								   </div>
								   <div class="col-sm-6">
								      <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
								         <ul class="pagination">
								            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="#">Previous</a></li>
								            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="#">1</a></li>
								            <li class="paginate_button " aria-controls="dataTables-example" tabindex="0"><a href="#">2</a></li>
								            <li class="paginate_button " aria-controls="dataTables-example" tabindex="0"><a href="#">3</a></li>
								            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="#">Next</a></li>
								         </ul>
								      </div>
								   </div>
								</div>
                            <!-- /.row -->
                                
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Core Scripts - Include with every page -->
    <script src="<util:url/>resources/js/jquery-1.10.2.js"></script>
    
    <script src="<util:url/>resources/js/bootstrap.min.js"></script>
    <script src="<util:url/>resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    

    <!-- Page-Level Plugin Scripts - Tables -->
    <!-- <script src="<util:url/>resources/js/plugins/dataTables/jquery.dataTables.js"></script> -->
    <!-- <script src="<util:url/>resources/js/plugins/dataTables/dataTables.bootstrap.js"></script> -->

    <!-- SB Admin Scripts - Include with every page -->
    <script src="<util:url/>resources/js/sb-admin.js"></script>
    

</body>

</html>
