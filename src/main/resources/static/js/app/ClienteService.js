'use strict';

angular.module('crudApp').factory('ClienteService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllClientes: loadAllClientes,
                getAllClientes: getAllClientes,
                getCliente: getCliente,
                createCliente: createCliente,
                updateCliente: updateCliente,
                removeCliente: removeCliente
            };

            return factory;

            function loadAllClientes() {
                console.log('Consultando todos os clientes');
                var deferred = $q.defer();
                $http.get(urls.CLIENTE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Pesquisou todos os clientes');
                            $localStorage.clientes = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erro ao pesquisar todos os clientes.');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllClientes(){
                return $localStorage.clientes;
            }

            function getCliente(id) {
                console.log('Consultando Cliente com id :'+id);
                var deferred = $q.defer();
                $http.get(urls.CLIENTE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('retornado com sucesso. Cliente id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao pesquisar o cliente id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createCliente(cliente) {
                console.log('Gravando Cliente');
                var deferred = $q.defer();
                $http.post(urls.CLIENTE_SERVICE_API, cliente)
                    .then(
                        function (response) {
                            loadAllClientes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Erro ao gravar Cliente : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateCliente(cliente, id) {
                console.log('Atualizando Cliente com id '+id);
                var deferred = $q.defer();
                $http.put(urls.CLIENTE_SERVICE_API + id, cliente)
                    .then(
                        function (response) {
                            loadAllClientes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao atualizar Cliente com id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeCliente(id) {
                console.log('Removendo cliente com id '+id);
                var deferred = $q.defer();
                $http.delete(urls.CLIENTE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllClientes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erro ao remover Cliente com id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
        }
    ]);