package models;

import java.util.*;

public class GradeCurricular {
	
	private List<Disciplina> disciplinas;
	//CREATOR: grade curricular é feita de disciplinas
	public GradeCurricular() {
		disciplinas = new ArrayList<Disciplina>();
		leDisciplinasDeArquivo();
	}
		
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public Disciplina pegaDisciplina(String nome) {
		for(Disciplina disciplina: disciplinas) {
			if(disciplina.getNome().equals(nome))
				return disciplinas.remove(disciplinas.indexOf(disciplina));
		}
		return null;
	}

	public void leDisciplinasDeArquivo () {
		// fazer ele ler disciplinas de uma arquivo
	}
}
