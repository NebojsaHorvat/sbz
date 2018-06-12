'use strict';

angular.module('core.disease')
	.service('DiseaseService', function($http) {
		this.getAllSymptoms = () => {
			return $http.get('/api/disease/symptoms');
		};
	});
