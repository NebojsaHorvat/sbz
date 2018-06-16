'use strict';

angular.module('reports')
	.component('myReports', {
		templateUrl: '/part/reports/reports.template.html',
		controller: function($stateParams,$window,$rootScope,PatientsService) {

			
			this.chronical = () => {
				PatientsService.getChronical()
				.then( (response) => {
					this.results = response.data.message;
					this.results = this.results.split(",").join("\n")
				}, () => {
					this.results = null;
				});
			}
			
			this.addiction = () => {
				PatientsService.getAddiction()
				.then( (response) => {
					this.results = response.data.message;
					this.results = this.results.split(",").join("\n")
				}, () => {
					this.results = null;
				});
			}
			
			this.immunity = () => {
				PatientsService.getImmunity()
				.then( (response) => {
					this.results = response.data.message;
					this.results = this.results.split(",").join("\n")
				}, () => {
					this.results = null;
				});
			}
			

		}
	});