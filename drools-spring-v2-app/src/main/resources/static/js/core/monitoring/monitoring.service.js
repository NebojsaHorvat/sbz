'use strict';

angular.module('core.monitoring')
	.service('MonitoringService', function($http) {
		this.getHeartBeat = () => {
			return $http.get('/api/monitoring/heartBeat');
		};
		this.getOxygen = () => {
			return $http.get('/api/monitoring/oxygen');
		};
		this.getDialisa = () => {
			return $http.get('/api/monitoring/dialisa');
		};
		
	});