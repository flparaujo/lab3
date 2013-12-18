package models;

import java.util.*;

public class GradeCurricular {
	
	private LeitorDeDisciplinas leitorDeDisciplinas;
	private List<Disciplina> disciplinas;
	
	//CREATOR: grade curricular é feita de disciplinas
	public GradeCurricular() {
		disciplinas = new ArrayList<Disciplina>();
		leitorDeDisciplinas = new LeitorDeDisciplinas();
		geraDisciplinas();
	}
		
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public Disciplina getDisciplina(String nome) {
		for(Disciplina disciplina: disciplinas) {
			if(disciplina.getNome().equals(nome))
				return disciplina;
		}
		return null;
	}

	private void geraDisciplinas() {
		List<Disciplina> preRequisitos;
		for(String info : leitorDeDisciplinas.getInformacoesDasDisciplinas()) {
			preRequisitos = new ArrayList<Disciplina>();
			String nome = info.split("-")[0];
			int numeroDeCreditos = leitorDeDisciplinas.getNumeroDeCreditosDeDisciplina(nome);
			for(String nomeDePreRequisto : leitorDeDisciplinas.getNomesDosPreRequisitosDeDisciplina(nome)) {
				preRequisitos.add(new Disciplina(nomeDePreRequisto, 
						leitorDeDisciplinas.getNumeroDeCreditosDeDisciplina(nomeDePreRequisto)));
			}
			Disciplina disciplina = new Disciplina(nome, numeroDeCreditos, preRequisitos);
			disciplinas.add(disciplina);
		}
	}
}
