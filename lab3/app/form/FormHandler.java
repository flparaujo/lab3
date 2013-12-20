package form;

import play.data.validation.Constraints.*;

public class FormHandler {
	
	@Required
	private String inputNameDisciplina;
	
	public void setInputNameDisciplina(String inputNameDisciplina) {
		this.inputNameDisciplina = inputNameDisciplina;
	}
	
	public String getInputNameDisciplina() {
		return inputNameDisciplina;
	}
	

}
