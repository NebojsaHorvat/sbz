'use strict';

angular.module('symptoms')
	.component('mySymptoms', {
		templateUrl: '/part/symptoms/symptoms.template.html',
		controller: function($stateParams,DiseaseService) {

			DiseaseService.getAllSymptoms(this.placeType)
				.then( (response) => {
					this.symptoms = response.data;
					
					this.symptoms = this.symptoms.filter(function(el){
						return !el.startsWith("I_");
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
					alert(this.symptomList);
				}
					
			}
			
			this.mostLikely = () =>{
				alert("normal");
			}
			
			this.mostLikelyList = () =>{
				alert("list");
			
			}
		}
	});