'use strict';

angular.module('symptoms')
	.component('mySymptoms', {
		templateUrl: '/part/symptoms/symptoms.template.html',
		controller: function($stateParams,DiseaseService) {

			DiseaseService.getAllSymptoms(this.placeType)
				.then( (response) => {
					this.symptoms = response.data;
				}, () => {
					this.symptoms = null;
				});
//
//			this.order = null;
//			this.isReverse = true;
//			this.orderBy = (order) => {
//				this.isReverse = (this.order === order) ? !this.isReverse : false;
//				this.order = order;
//			};
		}
	});