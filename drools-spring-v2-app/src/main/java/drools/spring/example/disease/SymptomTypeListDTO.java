package drools.spring.example.disease;

import java.util.ArrayList;
import java.util.List;

import drools.spring.example.symptom.SymptomType;

public class SymptomTypeListDTO {

	private ArrayList<SymptomType> symptomTypes;
	
	private int id ;
	
	public SymptomTypeListDTO() {}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public ArrayList<SymptomType> getSymptomTypes() {
		return symptomTypes;
	}


	public void setSymptomTypes(ArrayList<SymptomType> symptomTypes) {
		this.symptomTypes = symptomTypes;
	}





}
