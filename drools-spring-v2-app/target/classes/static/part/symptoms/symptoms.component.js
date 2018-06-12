'use strict';

angular.module('symptoms')
	.component('mySymptoms', {
		templateUrl: '/part/symptoms/symptoms.template.html',
		controller: function($stateParams,DiseaseService) {

			DiseaseService.getAllSymptoms(this.placeType)
				.then( (response) => {
					this.symptoms = response.data;
					
					this.symptoms = this.symptoms.filter(function(el){
						return ( !el.startsWith("I_") ) && (!el.includes('TEMPERATURA'));
					}); 
					
				}, () => {
					this.symptoms = null;
				}); 
			this.symptomList = [];
			
			this.changeStatus = (choice,symptom) =>{
				
				if(choice == true){
					this.symptomList.push(symptom);
					//alert(this.symptomList);
				}
				else{
					//alert(symptom +  " " + "false");
					var sy = symptom;
					this.symptomList = this.symptomList.filter(function(el){
						return el !== sy;
					});
					//alert(this.symptomList);
				}
					
			}
			
			this.changeTemperature = () =>{
				//alert(this.temperature)
				if(this.temperature == undefined && this.temperature == null){
					this.symptomList = this.symptomList.filter(function(el){
						return !el.includes('TEMPERATURA');
					});
				}
				
			}
			
			this.mostLikely = () =>{
				alert("normal");
			}
			
			
			this.mostLikelyList = () =>{
				// Ovde izbacujem sve prethodne simptome temperature
				this.symptomList = this.symptomList.filter(function(el){
					return !el.includes('TEMPERATURA');
				});
				if(this.temperature > 38  )
					this.symptomList.push("TEMPERATURA_38");
				if(this.temperature >= 40 && this.temperature <=41 )
					this.symptomList.push("TEMPERATURA_40_41");

				alert(this.symptomList)
//				DiseaseService.getAllSymptoms(this.placeType)
//				.then( (response) => {
//					this.symptoms = response.data;
//					
//					this.symptoms = this.symptoms.filter(function(el){
//						return !el.startsWith("I_");
//					}); 
//					
//				}, () => {
//					this.symptoms = null;
//				}); 
			
			}
		}
	});