'use strict';

angular.module('medicine')
	.component('myMedicine', {
		templateUrl: '/part/medicine/medicine.template.html',
		controller: function($stateParams,DiseaseService,$rootScope,MedicineService) {
			this.status = ""
//			DiseaseService.getAllDiseases()
//			.then( (response) => {
//				this.diseases = response.data;
//				
//			}, () => {
//				this.diseases = null;
//			}); 
//			
			this.send = () => {
				
				MedicineService.add(this.newMedicine)
				.then( (response) => {
					this.status = "Success";
					
				}, () => {
					this.status = "Error";
				});
			}
			
		}
	});