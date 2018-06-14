'use strict';

angular.module('core.disease')
	.service('DiseaseService', function($http) {
		this.getAllSymptoms = () => {
			return $http.get('/api/diseases/symptoms');
		};
		this.getListOfDiseases = (symptomTypesList,id) => {
			return $http.post('/api/diseases/reasoning',symptomTypesList);
		};
		this.getAllDiseases = () => {
			return $http.get('/api/diseases');
		};
		this.getDiseaseSymptoms = (name) => {
			return $http.get('/api/diseases/symptoms/'+name);
		};
		this.diagnoseDisease = ( disease ) => {
			return $http.post('/api/diseases/diagnose',disease);
		};
	});
