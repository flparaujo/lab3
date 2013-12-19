package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um periodo de curso.
 * 
 * @author Felipe Araujo de Andrade, Franklin Wesley Bastos.
 * @version 1.1
 */
public class Periodo {
	
	//CREATOR: classe Periodo registra objetos do tipo Disciplina
	private List<Disciplina> disciplinas;
	private boolean ultrapassaLimiteMaximo;
	
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

	/**
	 * Adiciona uma disciplina a este periodo.
	 * @param nome O nome da disciplina a ser adicionada.
	 * @param numeroDeCreditos O numero de creditos da disciplina a ser adicionada.
	 */
	public void adicionaDisciplina(String nome, int numeroDeCreditos) {
		adicionaDisciplina(nome, numeroDeCreditos, new ArrayList<Disciplina>());
	}
	
	/**
	 * Adiciona uma disciplina a este periodo.
	 * @param nome O nome da disciplina.
	 * @param numeroDeCreditos O numero de creditos da disciplina.
	 * @param preRequisitos A lista contendo as disciplinas pre-requisito da disciplina a ser adicionada.
	 */
	public void adicionaDisciplina(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos) {
		if ((getNumeroDeCreditos() + numeroDeCreditos) <= Periodo.MAXIMO_DE_CREDITOS) {
			disciplinas.add(new Disciplina(nome, numeroDeCreditos, preRequisitos));
			ultrapassaLimiteMaximo = false;
		} else {
			ultrapassaLimiteMaximo = true;
		}
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

	/**
	 * @return
	 */
	public boolean acimaDoLimiteMaximoDeCreditos() {
		return this.ultrapassaLimiteMaximo;
	}

	public boolean abaixoDoLimiteMinimoDeCreditos() {
		return getNumeroDeCreditos() < Periodo.MINIMO_DE_CREDITOS;
	}
}
