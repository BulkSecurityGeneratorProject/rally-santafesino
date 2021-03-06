(function() {
    'use strict';

    angular
        .module('rallyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('etapa-prueba', {
            parent: 'entity',
            url: '/etapa-prueba?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'EtapaPruebas'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/etapa-prueba/etapa-pruebas.html',
                    controller: 'EtapaPruebaController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('etapa-prueba-detail', {
            parent: 'etapa-prueba',
            url: '/etapa-prueba/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'EtapaPrueba'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/etapa-prueba/etapa-prueba-detail.html',
                    controller: 'EtapaPruebaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'EtapaPrueba', function($stateParams, EtapaPrueba) {
                    return EtapaPrueba.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'etapa-prueba',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('etapa-prueba-detail.edit', {
            parent: 'etapa-prueba-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/etapa-prueba/etapa-prueba-dialog.html',
                    controller: 'EtapaPruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EtapaPrueba', function(EtapaPrueba) {
                            return EtapaPrueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('etapa-prueba.new', {
            parent: 'etapa-prueba',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/etapa-prueba/etapa-prueba-dialog.html',
                    controller: 'EtapaPruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('etapa-prueba', null, { reload: 'etapa-prueba' });
                }, function() {
                    $state.go('etapa-prueba');
                });
            }]
        })
        .state('etapa-prueba.edit', {
            parent: 'etapa-prueba',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/etapa-prueba/etapa-prueba-dialog.html',
                    controller: 'EtapaPruebaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EtapaPrueba', function(EtapaPrueba) {
                            return EtapaPrueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('etapa-prueba', null, { reload: 'etapa-prueba' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('etapa-prueba.delete', {
            parent: 'etapa-prueba',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/etapa-prueba/etapa-prueba-delete-dialog.html',
                    controller: 'EtapaPruebaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['EtapaPrueba', function(EtapaPrueba) {
                            return EtapaPrueba.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('etapa-prueba', null, { reload: 'etapa-prueba' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
