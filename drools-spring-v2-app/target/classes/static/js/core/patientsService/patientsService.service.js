'use strict';

angular.module('core.patients')
	.service('PatientsService', function($http) {
		
		this.getAll = () => {
			return $http.get('/api/patients');
		};
		this.getOne = (id) => {
			return $http.get('/api/patients/'+id);
		};
	});