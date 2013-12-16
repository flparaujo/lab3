package models;

import java.util.*;

public class GradeCurricular {
	
	private List<Disciplina> disciplinas;
	
	public GradeCurricular() {
		disciplinas = new ArrayList<Disciplina>();
	}
	
	public void adicionaDisciplina(String nome, int numeroDeCreditos, String[] preRequisitos) {
		disciplinas.add(new Disciplina(nome, numeroDeCreditos, preRequisitos));
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

}
