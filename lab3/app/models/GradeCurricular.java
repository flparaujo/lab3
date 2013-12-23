package models;

import java.util.*;

/**
 * Classe que representa a grade curricular do curso.
 * 
 * 
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
		Collections.sort(disciplinas);
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
	public Disciplina retiraDisciplina(String nome) {
		for(Disciplina disciplina : disciplinas) {
			if(disciplina.getNome().equals(nome)) {
				return disciplinas.remove(disciplinas.indexOf(disciplina));
			}
		}
		return null;
	}
	
	public void adicionaDisciplina(String nome) {
		List<Disciplina> preRequisitos = new ArrayList<Disciplina>();
		int numeroDeCreditos = leitorDeDisciplinas.getNumeroDeCreditosDeDisciplina(nome);
		geraPreRequisitosDeDisciplina(nome, preRequisitos);
		disciplinas.add(new Disciplina(nome, numeroDeCreditos, preRequisitos));
	}
	
	private void geraDisciplinas() {
		for(String info : leitorDeDisciplinas.getInformacoesDasDisciplinas()) {
			String nome = info.split("-")[0];
			adicionaDisciplina(nome);
		}
	}
	
	private void geraPreRequisitosDeDisciplina(String nome,
			List<Disciplina> preRequisitos) {
		for(String nomeDePreRequisto : leitorDeDisciplinas.getNomesDosPreRequisitosDeDisciplina(nome)) {
			//CREATOR: grade curricular é feita de disciplinas
			preRequisitos.add(new Disciplina(nomeDePreRequisto, leitorDeDisciplinas.
					getNumeroDeCreditosDeDisciplina(nomeDePreRequisto)));
		}
	}
}
