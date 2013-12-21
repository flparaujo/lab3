package models;

import java.util.*;

/**
 * Classe que representa a grade curricular do curso.
 * 
 * @author Felipe Araujo de Andrade
 * @version 1.2
 */
public class GradeCurricular {
	
	private LeitorDeDisciplinas leitorDeDisciplinas;
	private List<Disciplina> disciplinas;
	
	/**
	 * Construtor da grade curricular.
	 */
	public GradeCurricular() {
		disciplinas = new ArrayList<Disciplina>();
		leitorDeDisciplinas = LeitorDeDisciplinas.getInstance();
		geraDisciplinas();
	}
		
	/**
	 * Obtem a lista contendo todas as disciplinas desta grade curricular.
	 * @return uma lista com as disciplinas da grade.
	 */
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	/**
	 * Obtem uma disciplina qualquer da grade curricular.
	 * @param nome O nome da disciplina a ser obtida.
	 * @return A disciplina, se estiver na grade. Caso contrário, retorna null.
	 */
	public Disciplina getDisciplina(String nome) {
		for(Disciplina disciplina: disciplinas) {
			if(disciplina.getNome().equals(nome))
				return disciplina;
		}
		return null;
	}
	
	/**
	 * Obtém uma disciplina removendo-a da grade curricular.
	 * @param nome O nome da disciplina.
	 * @return a disciplina.
	 */
	public Disciplina pegaDisciplina(String nome) {
		for(Disciplina disciplina : disciplinas) {
			if(disciplina.getNome().equals(nome)) {
				return disciplinas.remove(disciplinas.indexOf(disciplina));
			}
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
				//CREATOR: grade curricular é feita de disciplinas
				preRequisitos.add(new Disciplina(nomeDePreRequisto, leitorDeDisciplinas.
						getNumeroDeCreditosDeDisciplina(nomeDePreRequisto)));
			}
			Disciplina disciplina = new Disciplina(nome, numeroDeCreditos, preRequisitos);
			disciplinas.add(disciplina);
		}
	}
}
