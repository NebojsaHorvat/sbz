'use strict';

angular.module('patients')
	.component('myPatients', {
		templateUrl: '/part/patients/patients.template.html',
		controller: function($stateParams,PatientsService,$window,$rootScope) {

			PatientsService.getAll(this.placeType)
				.then( (response) => {
					this.patients = response.data;
				}, () => {
					this.patients = null;
				});
			
			this.choosePatient = (patient) => {
				$rootScope.patient = patient;
				$window.location.href = '#!/symptoms'
			}

		}
	});