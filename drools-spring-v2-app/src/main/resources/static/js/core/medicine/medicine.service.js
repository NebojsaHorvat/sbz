'use strict';

angular.module('core.medicine')
	.service('MedicineService', function($http) {
		this.getAll = () => {
			return $http.get('/api/medicine');
		};
		this.getOne = (id) => {
			return $http.get('/api/medicine/'+id);
		};
		this.add = (data) => {
			return $http.post('/api/medicine/', data);
		};
		this.checkAlergies = (patientId,medicineId) => {
			return $http.get('/api/medicine/alergies/'+patientId+'/'+medicineId);
			
		};
		
	});