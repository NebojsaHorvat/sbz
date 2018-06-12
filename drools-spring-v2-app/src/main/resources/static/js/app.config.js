'use strict';

angular.module('diagnostic')
	.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state({
				name: 'home',
				url: '/',
				component: 'myHome'
			})
			
			.state({
				name: 'userAuth',
				url: '/user-auth',
				component: 'myUserAuth'
			})
			.state({
				name: 'home.patients',
				url: 'patients',
				component: 'myPatients'
			})
			.state({
				name: 'home.registration',
				url: 'registration',
				component: 'myRegistration'
			})
			
			.state({
				name: 'home.symptoms',
				url: 'symptoms',
				component: 'mySymptoms'
			})
			
			.state({
				name: 'error',
				url: '/error',
				template: '<h1>Error 404</h1>'
			});

		$urlRouterProvider
			.when('', '/')
			.otherwise('/error');
	})
	.run(function($rootScope, UserAuthService) {
		UserAuthService.getUser().then(
			(response) => {
				$rootScope.user = response.data;
			},
			() => {
				$rootScope.user = null;
			});
	});
