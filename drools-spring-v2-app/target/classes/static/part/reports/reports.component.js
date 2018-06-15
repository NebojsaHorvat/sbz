'use strict';

angular.module('reports')
	.component('myReports', {
		templateUrl: '/part/reports/reports.template.html',
		controller: function($stateParams,$window,$rootScope,PatientsService) {

			
			this.chronical = () => {
				PatientsService.getChronical()
				.then( (response) => {
					this.patients = response.data;
				}, () => {
					this.patients = null;
				});
			}
			
			this.addiction = () => {
				PatientsService.getAddiction()
				.then( (response) => {
					this.patients = response.data;
				}, () => {
				});
			}
			
			this.immunity = () => {
				PatientsService.getImmunity()
				.then( (response) => {
					this.patients = response.data;
				}, () => {
					this.patients = null;
				});
			}
			

		}
	});