<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Cliente </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.cliente.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="cnome">Nome</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.cliente.nome" id="cnome" class="form-control input-sm" placeholder="Digite o nome" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="credito">Limite de Crédito</label>
	                        <div class="col-md-7">
	                        	<div class="input-group">
		                        	<div class="input-group-addon">R$</div>
		                            <input type="text" ng-model="ctrl.cliente.limiteCredito" id="credito" 
		                            	class="money form-control input-sm" placeholder="R$ limite de crédito." 
		                            	required />
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="riscoSelect"> Riscos: </label>
	                        <div class="col-md-7">
							    <select class="form-control" name="riscoSelect" id="riscoSelect" ng-model="ctrl.riscoSelect" >
					      		<option ng-repeat="risco in ctrl.riscos track by risco.tipo" {{ctrl.riscoSelect.tipo == risco.tipo ? 'selected' : ''}} value="{{risco.tipo}}">{{risco.desc}}</option>
							    </select>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-actions floatRight">
	                    	{{myForm.$invalid}}
	                        <input type="submit"  value="{{!ctrl.cliente.id ? 'Gravar' : 'Atualizar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Limpar Formulário</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Clientes </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NOME</th>
		                <th>CRÉDITO R$</th>
		                <th>RISCO</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="c in ctrl.getAllClientes()">
		                <td>{{c.id}}</td>
		                <td>{{c.nome}}</td>
		                <td><span class="money">{{c.limiteCredito}}</span></td>
		                <td>{{c.risco}}</td>
		                <td><button type="button" ng-click="ctrl.editCliente(c.id)" class="btn btn-success custom-width">Editar</button></td>
		                <td><button type="button" ng-click="ctrl.removeCliente(c.id)" class="btn btn-danger custom-width">Remover</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>