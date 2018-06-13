'use strict';

angular.module('diseases')
	.component('myDiseases', {
		templateUrl: '/part/diseases/disease.template.html',
		controller: function($stateParams,DiseaseService,$rootScope) {
			
			DiseaseService.getAllDiseases()
			.then( (response) => {
				this.diseases = response.data;
				
			}, () => {
				this.diseases = null;
			}); 
			
		}
	});