package models;

import java.util.ArrayList;
import java.util.List;

//CREATOR: classe Periodo registra objetos do tipo Disciplina
public class Periodo {

	private List<Disciplina> disciplinas;
	
	public Periodo() {
		disciplinas = new ArrayList<Disciplina>();
	}
	
	public List<Disciplina> disciplinasAlocadas() {
		return disciplinas;
	}

	public void adicionaDisciplina(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos) {
		disciplinas.add(new Disciplina(nome, numeroDeCreditos, preRequisitos));
	}

	public int getNumeroDeCreditos() {
		int total = 0;
		for(Disciplina disciplina : disciplinas)
			total += disciplina.getNumeroDeCreditos();
		return total;
	}

}
