package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um periodo de curso.
 * 
 * @author Felipe Araujo de Andrade, Franklin Wesley Bastos.
 * @version 1.0
 */
public class Periodo {
	
	//CREATOR: classe Periodo registra objetos do tipo Disciplina
	private List<Disciplina> disciplinas;
	
	/**
	 * Constante que representa o numero minimo de creditos que um periodo deve conter.
	 */
	public static final int MINIMO_DE_CREDITOS = 14;
	
	/**
	 * Constante que representa o numermo maximo de creditos que um periodo pode conter.
	 */
	public static final int MAXIMO_DE_CREDITOS = 28;
	
	/**
	 * Construtor de Periodo.
	 */
	public Periodo() {
		disciplinas = new ArrayList<Disciplina>();
	}
	
	/**
	 * Recupera a lista das disciplinas alocadas para o periodo.
	 * @return a lista de disciplinas do periodo.
	 */
	public List<Disciplina> disciplinasAlocadas() {
		return this.disciplinas;
	}

	public void adicionaDisciplina(String nome, int numeroDeCreditos) {
		disciplinas.add(new Disciplina(nome, numeroDeCreditos));
	}
	
	public void adicionaDisciplina(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos) {
		disciplinas.add(new Disciplina(nome, numeroDeCreditos, preRequisitos));
	}

	/**
	 * Obtem o numero de creditos do periodo.
	 * @return o numero de creditos do periodo.
	 */
	public int getNumeroDeCreditos() {
		int total = 0;
		for(Disciplina disciplina : disciplinas)
			total += disciplina.getNumeroDeCreditos();
		return total;
	}
	
}
