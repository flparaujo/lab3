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
		return this.disciplinas;
	}

	public void adicionaDisciplina(String nome, int numeroDeCreditos) {
		disciplinas.add(new Disciplina(nome, numeroDeCreditos));
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
	
	public boolean preRequisitosSatisfeitos(List<Disciplina> preRequisito) {
		boolean result = false;
		for(Disciplina disciplina: disciplinasAlocadas()) {
			if (preRequisito.contains(disciplina) || disciplina.getPreRequisitos() != null) {
				result = true;
			}
		}
		return result;
	}
}
