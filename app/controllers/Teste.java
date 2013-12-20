package controllers;

import play.data.validation.Constraints.*;

public class Teste {
	
	@Required
	public String teste = "oi";
	
	public static void adcionaDisciplina(Teste t) {
		
	}
	
	public String getTeste() {
		return teste;
	}
	

}
