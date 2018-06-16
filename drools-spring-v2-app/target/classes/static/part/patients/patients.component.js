'use strict';

angular.module('patients')
	.component('myPatients', {
		templateUrl: '/part/patients/patients.template.html',
		controller: function($stateParams,PatientsService,$window,$rootScope,MedicineService) {

			PatientsService.getAll(this.placeType)
				.then( (response) => {
					this.patients = response.data;
				}, () => {
					this.patients = null;
				});
			this.patient ={}
			MedicineService.getAll()
			.then( (response) => {
				this.medicines = response.data;
				
			}, () => {
				this.medicines = null;
			}); 
			
			this.choosePatient = (patient) => {
				$rootScope.patient = patient;
				$window.location.href = '#!/symptoms'
			}
			
			this.send = () => {
				PatientsService.add(this.patient)
				.then( (response) => {
					this.patients.push(response.data)
					this.status = "Successful";
				}, () => {
					this.status = "Error";
				});
				
			}
			
			this.changeStatus = (choice,medicine) =>{
				if(this.patient.alergicMedicines == undefined)
					this.patient.alergicMedicines = [];
				
				if(choice == true)
					this.patient.alergicMedicines.push(medicine);
				else{
					var med = medicine;
					this.patient.alergicMedicines = this.patient.alergicMedicines.filter(function(el){
						return el != med;
					});
				}
			}
			
			
			this.deletePatient = (patient) =>{
				
				PatientsService.deletePatient(patient)
				.then( (response) => {
					var pac = response.data;
					this.patients= this.patients.filter(function(el){
						return el.id != pac.id;
					});
				}, () => {
					alert("Pacient ne moze biti obrisan")
				});
			}

		}
	});