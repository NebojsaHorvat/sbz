'use strict';

angular.module('diseases')
	.component('myDiseases', {
		templateUrl: '/part/diseases/disease.template.html',
		controller: function($stateParams,DiseaseService,$rootScope,$window) {
			
			DiseaseService.getAllDiseases()
			.then( (response) => {
				this.diseases = response.data;
				
			}, () => {
				this.diseases = null;
			}); 
			
			this.displayDisease = (disease) => {
				this.disease = disease;
				DiseaseService.getDiseaseSymptoms(disease)
				.then( (response) => {
					this.symptoms = response.data;
					
				}, () => {
					this.symptoms = null;
				});
			}
			
			this.diagnoseDisease = (disease) => {
				if($rootScope.patient == undefined){
					alert("Must indentify patient first");
					return;
				}
				var diseaseDat = {
						'diseaseType' : disease,
						'patient' : $rootScope.patient
				}
				//disease.patient = $rootScope.patient;
				
				DiseaseService.diagnoseDisease( diseaseDat )
				.then( (response) => {
					$rootScope.disease = response.data;
					$window.location.href = '#!/medicine'
				}, () => {
					$rootScope.disease = undefined;
					alert("Error");
				}); 
			}
		}
	});