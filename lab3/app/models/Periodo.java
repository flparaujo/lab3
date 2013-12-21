package models;

import java.util.ArrayList;
import java.util.List;

import javax.naming.LimitExceededException;

/**
 * Classe que representa um periodo de curso.
 * 
 * @author Felipe Araujo de Andrade, Franklin Wesley Bastos.
 * @version 1.1
 */
public class Periodo {

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

	/**
	 * Adiciona uma disciplina a este periodo.
	 * @param nome O nome da disciplina a ser adicionada.
	 * @param numeroDeCreditos O numero de creditos da disciplina a ser adicionada.
	 * @throws LimitExceededException quando a tentativa de adicionar a disciplina ultrapassa o limite maximo de creditos.
	 */
	public void adicionaDisciplina(String nome, int numeroDeCreditos) throws LimitExceededException {
		//CREATOR: classe Periodo registra objetos do tipo Disciplina
		adicionaDisciplina(nome, numeroDeCreditos, new ArrayList<Disciplina>());
	}
	
	/**
	 * Adiciona uma disciplina a este periodo.
	 * @param nome O nome da disciplina.
	 * @param numeroDeCreditos O numero de creditos da disciplina.
	 * @param preRequisitos A lista contendo as disciplinas pre-requisito da disciplina a ser adicionada.
	 * @return true se a disciplina foi adicionada, false caso contrario.
	 * @throws LimitExceededException quando a tentativa de adicionar a disciplina ultrapassa o limite maximo de creditos.
	 */
	public boolean adicionaDisciplina(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos) 
			throws LimitExceededException {
		//CREATOR: classe Periodo registra objetos do tipo Disciplina
		Disciplina disciplina = new Disciplina(nome, numeroDeCreditos, preRequisitos);
		if (!disciplinas.contains(disciplina)) {
			if ((getNumeroDeCreditos() + numeroDeCreditos) > MAXIMO_DE_CREDITOS) {
				throw new LimitExceededException("Nao foi possivel alocar " + nome + ". Limite maximo eh " +
						"de "+MAXIMO_DE_CREDITOS+" creditos!");
			}
			return disciplinas.add(disciplina);
		}
		return false;
	}

	/**
	 * Obtem o numero de creditos do periodo.
	 * @return o numero de creditos do periodo.
	 */
	public int getNumeroDeCreditos() {
	    //INFORMATION EXPERT: Casse Periodo contem disciplinas, que sao o atributo necessario para o calculo do total de creditos
		int total = 0;
		for(Disciplina disciplina : disciplinas)
			total += disciplina.getNumeroDeCreditos();
		return total;
	}

	public boolean abaixoDoLimiteMinimoDeCreditos() {
		return getNumeroDeCreditos() < Periodo.MINIMO_DE_CREDITOS;
	}
}
