var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/SpringBootCRUDApp',
    CLIENTE_SERVICE_API : 'http://localhost:8080/SpringBootCRUDApp/api/cliente/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/lista',
                controller:'ClienteController',
                controllerAs:'ctrl',
                resolve: {
                    clientes: function ($q, ClienteService) {
                        console.log('Lendo todos clientes...');
                        var deferred = $q.defer();
                        ClienteService.loadAllClientes().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

