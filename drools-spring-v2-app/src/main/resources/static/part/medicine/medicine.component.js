'use strict';

angular.module('medicine')
	.component('myMedicine', {
		templateUrl: '/part/medicine/medicine.template.html',
		controller: function($stateParams,DiseaseService,$rootScope) {
			
//			DiseaseService.getAllDiseases()
//			.then( (response) => {
//				this.diseases = response.data;
//				
//			}, () => {
//				this.diseases = null;
//			}); 
//			
//			this.displayDisease = (disease) => {
//				this.disease = disease;
//				DiseaseService.getDiseaseSymptoms(disease)
//				.then( (response) => {
//					this.symptoms = response.data;
//					
//				}, () => {
//					this.symptoms = null;
//				});
//			}
			
		}
	});