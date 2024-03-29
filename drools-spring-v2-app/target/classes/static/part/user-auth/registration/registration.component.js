'use strict';

angular.module('registration')
	.component('myRegistration', {
		templateUrl: '/part/user-auth/registration/registration.template.html',
		controller: function(UserAuthService) {
			this.send = () => {
				if(this.user.password !== this.user.passwordAgain)
				{
					this.status = 'Passwords don\'t match';
					return;
				}
				UserAuthService.register(this.user).then(
					() => {
						this.status = 'Registered successfully. Please confirm your email';
					},
					() => {
						this.status = 'Error';
					});
			};
		}
	});
