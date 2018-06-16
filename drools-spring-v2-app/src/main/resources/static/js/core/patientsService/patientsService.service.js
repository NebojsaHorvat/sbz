'use strict';

angular.module('core.patients')
	.service('PatientsService', function($http) {
		
		this.getAll = () => {
			return $http.get('/api/patients');
		};
		this.getOne = (id) => {
			return $http.get('/api/patients/'+id);
		};
		this.add = (patient) => {
			return $http.post('/api/patients',patient);
		};
		this.getChronical = () => {
			return $http.get('/api/patients/chronical');
		};
		this.getAddiction = () => {
			return $http.get('/api/patients/addiction');
		};
		this.getImmunity = () => {
			return $http.get('/api/patients/immunity');
		};
	});