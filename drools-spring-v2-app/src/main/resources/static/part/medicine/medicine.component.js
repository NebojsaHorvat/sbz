'use strict';

angular.module('medicine')
	.component('myMedicine', {
		templateUrl: '/part/medicine/medicine.template.html',
		controller: function($stateParams,DiseaseService,$rootScope,MedicineService) {
			MedicineService.getAll()
			.then( (response) => {
				this.medicines = response.data;
				
			}, () => {
				this.medicines = null;
			}); 
			
			this.send = () => {
				
				MedicineService.add(this.newMedicine)
				.then( (response) => {
					this.status = "Success";
					
				}, () => {
					this.status = "Error";
				});
			}
			this.checkAlergies = (medicine) =>{
				MedicineService.checkAlergies($rootScope.patient.id,medicine.id)
				.then( (response) =>{
					alert(response.data.message);
				},() =>{
					alert("Error")
				});
				
			}
			
			this.prescripe = (medicine) => {
				MedicineService.prescribe($rootScope.patient.id,medicine.id,$rootScope.disease.id)
				.then( (response) =>{
					alert("Lek uspesno prepisan");
				},() =>{
					alert("Error")
				});
			}
			this.deleteMedicine = (medicine ) =>{
				MedicineService.deleteMedicine(medicine.id)
				.then( (response) =>{
					var med = response.data;
					this.medicines= this.medicines.filter(function(el){
						return el.id != med.id;
					});
				},() =>{
					alert("Greska pri brisanju leka")
				});
			}
			
		}
	});