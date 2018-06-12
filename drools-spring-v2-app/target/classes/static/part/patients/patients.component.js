'use strict';

angular.module('patients')
	.component('myPatients', {
		templateUrl: '/part/patients/patients.template.html',
		controller: function($stateParams,PatientsService) {

			PatientsService.getAll(this.placeType)
				.then( (response) => {
					this.patients = response.data;
				}, () => {
					this.patients = null;
				});

			this.order = null;
			this.isReverse = true;
			this.orderBy = (order) => {
				this.isReverse = (this.order === order) ? !this.isReverse : false;
				this.order = order;
			};
		}
	});