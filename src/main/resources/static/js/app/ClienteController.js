'use strict';

angular.module('crudApp').controller('ClienteController',
    ['ClienteService', '$scope',  function( ClienteService, $scope) {

        var self = this;
        self.cliente = {};
        self.clientes = [];
        self.riscos = [
            {tipo: 'A', desc: 'Risco A - taxa 0%'},
            {tipo: 'B', desc: 'Risco B - taxa 10%'},
            {tipo: 'C', desc: 'Risco C - taxa 20%'}
          ];
        
        self.riscoSelect = '';

        self.submit = submit;
        self.getAllClientes = getAllClientes;
        self.createCliente = createCliente;
        self.updateCliente = updateCliente;
        self.removeCliente = removeCliente;
        self.editCliente = editCliente;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function submit() {
            console.log('Submit...');
            if (self.cliente.id === undefined || self.cliente.id === null) {
                console.log('Salvando novo cliente:', self.cliente);
                createCliente(self.cliente);
            } else {
                updateCliente(self.cliente, self.cliente.id);
                console.log('Cliente atualizado com id: ', self.cliente.id);
            }
        }

        function createCliente(cliente) {
            console.log('Criando cadastro...');
            self.cliente.risco = self.riscoSelect;
            self.cliente.limiteCredito = self.cliente.limiteCredito.replace(/\D/g,'');
            console.log('Salvando novo cliente:', self.cliente);
            ClienteService.createCliente(cliente)
                .then(
                    function (response) {
                        console.log('Cliente gravado com sucesso');
                        self.successMessage = 'Cliente gravado com sucesso!';
                        self.errorMessage = '';
                        self.done = true;
                        self.cliente = {};
                        self.riscoSelect = "";
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Erro ao criar cliente');
                        self.errorMessage = 'Erro ao criar cliente: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateCliente(cliente, id){
            console.log('Atualizando cliente...');
            cliente.limiteCredito = String(cliente.limiteCredito).replace(/\D/g,'');
            cliente.risco = self.riscoSelect;
            ClienteService.updateCliente(cliente, id)
                .then(
                    function (response){
                        console.log('Cliente atualizado com sucesso');
                        self.successMessage='Cliente atualizado com sucesso!';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Erro ao atualizar o cliente');
                        self.errorMessage='Erro ao atualiar o cliente '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeCliente(id){
            console.log('Removendo o cliente id '+id);
            ClienteService.removeCliente(id)
                .then(
                    function(){
                        console.log('Cliente '+id + ' removido com sucesso');
                    },
                    function(errResponse){
                        console.error('Erro ao remover cliente '+id +', Erro :'+errResponse.data);
                    }
                );
        }


        function getAllClientes(){
            return ClienteService.getAllClientes();
        }

        function editCliente(id) {
            self.successMessage='';
            self.errorMessage='';
            ClienteService.getCliente(id).then(
                function (cliente) {
                    self.cliente = cliente;
                    self.riscoSelect = cliente.risco;
                },
                function (errResponse) {
                    console.error('Erro ao editar cliente ' + id + ', Erro :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.cliente={};
            self.riscoSelect = '';
            $scope.myForm.$setPristine(); //reset Form
        }
        
    }

    ]);