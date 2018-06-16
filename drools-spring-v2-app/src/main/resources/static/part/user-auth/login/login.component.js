'use strict';

angular.module('userAuth.login')
	.component('myLogin', {
		templateUrl: '/part/user-auth/login/login.template.html',
		controller: function(UserAuthService, $rootScope, $state,$window) {
			this.send = () => {
				UserAuthService.logIn(this.user).then(
					(response) => {
						$rootScope.user = response.data;
						$state.go('home.patients');
					},
					() => {
						this.status = 'Wrong email/password.';
					});
			};
		}
	});
