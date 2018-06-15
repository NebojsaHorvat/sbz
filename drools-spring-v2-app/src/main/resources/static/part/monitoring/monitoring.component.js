'use strict';

angular.module('monitoring')
	.component('myMonitoring', {
		templateUrl: '/part/monitoring/monitoring.template.html',
		controller: function($stateParams,$window,$rootScope,MonitoringService) {

			
			this.oxygenLvl = () => {
				MonitoringService.getHeartBeat()
				.then( (response) => {
					this.results = response.data;
				}, () => {
					this.results = null;
				});
			}
			
			this.heartMonitoring = () => {
				MonitoringService.getOxygen()
				.then( (response) => {
					this.results = response.data;
				}, () => {
					this.results = null;
				});
			}
			
			this.dialisa = () => {
				MonitoringService.getDialisa()
				.then( (response) => {
					this.results = response.data;
				}, () => {
					this.results = null;
				});
			}
			

		}
	});