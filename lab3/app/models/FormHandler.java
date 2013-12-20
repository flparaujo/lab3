package models;

import play.data.validation.Constraints.*;

public class FormHandler {
	
	@Required
	private String nomeDisciplina;
	
	public void setTeste(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	

}
