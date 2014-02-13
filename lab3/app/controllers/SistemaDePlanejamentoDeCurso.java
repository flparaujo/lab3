package controllers;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;


import models.*;

/**
 * Classe que representa o sistema de planejamento de curso.
 *
 */
public class SistemaDePlanejamentoDeCurso {
	
	private List<Periodo> periodos;
	private GradeCurricular grade;
	private List<Disciplina> handlerDisciplinas;
	
	/**
	 * Constroi um sistema de planejamento de curso, com o primeiro periodo predefinido.
	 */
	public SistemaDePlanejamentoDeCurso() {
		this.periodos = new ArrayList <Periodo>();
		this.grade = new GradeCurricular();
		primeiroPeriodo(); 
		handlerDisciplinas = new ArrayList<Disciplina>();
	}
	
	public int dificuldadeDoPeriodo (int indicePeriodo) {
		return getPeriodo(indicePeriodo).getDificuldadeDoPeriodo();
	}
	
	/**
	 * Obtem uma lista de todas as disciplinas do curso.
	 * @return uma lista com as disciplinas do curso.
	 */
	public List<Disciplina> listaDisciplinasDoCurso() {
		return grade.getDisciplinas();
	}
	
	/**
	 * Obtem um periodo da lista de periodos adicionados.
	 * @param i O indice do periodo (0 para o primeiro, 1 para o segundo e assim por diante).
	 * @return o periodo de indice i.
	 */
	public Periodo getPeriodo(int i) {
		return periodos.get(i);
	}
	
	/**
	 * Obtem uma lista das disciplinas de um periodo.
	 * @param indicePeriodo O indice do periodo.
	 * @return a lista das disciplinas do periodo.
	 */
	public List<Disciplina> getDisciplinasDoPeriodo(int indicePeriodo) {
		return getPeriodo(indicePeriodo).disciplinasAlocadas();
	}
	
	
	/**
	 * Obtem o numero de creditos de um periodo.
	 * @param indicePeriodo O indice do periodo.
	 * @return o numero de creditos do periodo.
	 */
	public int numeroDeCreditosDoPeriodo(int indicePeriodo) {
		return getPeriodo(indicePeriodo).getNumeroDeCreditos(); 
	}
	
	
	/**
	 * Obtem uma lista de todos os periodos criados.
	 * @return a lista de todos os periodos criados.
	 */
	public List<Periodo> getPeriodos() {
		return this.periodos;
	}
	
	//CREATOR: classe SistemaDePlanejamentoDeCurso registra objetos do tipo Periodo
	/**
	 * Adiciona um novo periodo.
	 */
	public void adicionaPeriodo() {
		periodos.add(new Periodo());
	}
	
	/**
	 * Adiciona uma disciplina a um periodo.
	 * @param indicePeriodo O indice do periodo.
	 * @param nome O nome da disciplina.
	 * @param numeroDeCreditos O numero de creditos da disciplina.
	 * @throws AlocacaoInvalidaException 
	 * @throws LimiteDeCreditosExcedidoException
	 */
	public void adicionaDisciplinaAoPeriodo(int indicePeriodo, String nome, int numeroDeCreditos) 
			throws AlocacaoInvalidaException, LimiteDeCreditosExcedidoException {
		if(grade.getDisciplina(nome) != null) {
			adicionarDisciplinaSePreRequisitosSatisfeitos(indicePeriodo, nome, numeroDeCreditos, 
					grade.getDisciplina(nome).getPreRequisitos());
			grade.retiraDisciplina(nome);
		}
	}
	
	/**
	 * Obtem uma disciplina da grade curricular.
	 * @param nome O nome da disciplina.
	 * @return a disciplina, se for encontrada na grade. Se nao, retorna null.
	 */
	public Disciplina getDisciplinaDaGrade(String nome) {
		return grade.getDisciplina(nome);
	}
	
	/**
	 * Verifica se a soma dos creditos de um periodo esta abaixo do limite minimo de creditos.
	 * @param idPeriodo O indice do periodo.
	 * @return true se o periodo esta abaixo do limite minimo de cretidos, false caso contrario.
	 */
	public boolean periodoComCreditosAbaixoDoLimiteMinimo(int idPeriodo) {
		return getPeriodo(idPeriodo).abaixoDoLimiteMinimoDeCreditos();
	}

	/**
	 * 
	 * @param nomeDaDisciplina
	 */
	public void devolveDisciplinaParaGrade(String nomeDaDisciplina) {
		int indicePeriodo = indicePeriodoDeDisciplina(nomeDaDisciplina);
		Disciplina aRemover = getPeriodo(indicePeriodo).getDisciplina(nomeDaDisciplina);
		grade.adicionaDisciplina(nomeDaDisciplina);
		handlerDisciplinas.add(aRemover);
		for(int i = indicePeriodo+1; i < periodos.size(); i++) {
			for(Disciplina disciplina : periodos.get(i).disciplinasAlocadas()) {
				if(disciplina.getPreRequisitos().contains(aRemover))
					devolveDisciplinaParaGrade(disciplina.getNome());
			}
		}
	}
	
	/**
	 * Obtem o indice do periodo, na lista de periodos, ao qual uma disciplina de nome dado 
	 * foi alocada.
	 * @param nome O nome da disciplina.
	 * @return o indice do periodo em que a disciplina esta. Se a disciplina de nome dado
	 * nao esta alocada em periodo algum, retorna -1.
	 */
	public int indicePeriodoDeDisciplina(String nome) {
		for(int i = 0; i < periodos.size(); i++)
			if(periodos.get(i).getDisciplina(nome) != null)
				return i;
		return -1;
	}
	
	/**
	 * Remove disciplinas desalocadas.
	 */
	public void removeDisciplinasDesalocadas() {
		for(Disciplina disciplina : handlerDisciplinas) {
			removeDisciplina(disciplina.getNome());
		}
	}
	
	/**
	 * Obtem as disciplinas alocadas a partir do segundo periodo.
	 * @return as disciplinas alocadas a partir do segundo periodo.
	 */
	public List<Disciplina> getDisciplinasAlocadas() {
		List<Disciplina> alocadas = new ArrayList<Disciplina>();
		for(int i = 1; i < periodos.size(); i++) {
			if(! periodos.get(i).disciplinasAlocadas().isEmpty()) {
				alocadas.addAll(periodos.get(i).disciplinasAlocadas());
			}
		}
		return alocadas;
	}
	
	private void adicionarDisciplinaSePreRequisitosSatisfeitos(int indicePeriodo, String nome, 
			int numeroDeCreditos, List<Disciplina> preRequisitos) throws AlocacaoInvalidaException, LimiteDeCreditosExcedidoException {
		if (! preRequisitosSatisfeitos(indicePeriodo, preRequisitos)) {	
			throw new AlocacaoInvalidaException();
		}
		getPeriodo(indicePeriodo).adicionaDisciplina(nome, numeroDeCreditos, preRequisitos);
	}
	
	private void removeDisciplina(String nomeDaDisciplina) {
		for(int i = 0; i < periodos.size(); i++)
			for(Disciplina disciplina : periodos.get(i).disciplinasAlocadas())
				if(disciplina.getNome().equals(nomeDaDisciplina)) {
					periodos.get(i).removeDisciplina(nomeDaDisciplina);
					return;
				}
		
	}
	
	private void primeiroPeriodo() {
		getPeriodos().add(new Periodo());
		alocaDisciplinasDoPrimeiroPeriodo();
	}
	
	private void alocaDisciplinasDoPrimeiroPeriodo() {
		String[] nomesDasDisciplinas = {"Calculo Dif. e Int. 1", "Algebra Vet. e Geom. Analitica", 
				"Lab. de Programacao 1", "Programacao 1", 
		"Introducao a Computacao"};
		
		for(String nome : nomesDasDisciplinas) {
			int numeroDeCreditos = grade.getDisciplina(nome).getNumeroDeCreditos();
			try {
				getPeriodos().get(0).adicionaDisciplina(nome, numeroDeCreditos);
			} catch (LimiteDeCreditosExcedidoException e) {
				e.printStackTrace();
			}
			grade.retiraDisciplina(nome);
		}	
	}
	
	private boolean preRequisitosSatisfeitos(int indicePeriodo, List<Disciplina> preRequisitos) {
		int contPreRequisitos = 0;
		if (!preRequisitos.isEmpty()) {
			for(int i = 0; i < indicePeriodo; i++) {
				for(Disciplina disciplinaPreRequisito : preRequisitos) {
					if(periodos.get(i).disciplinasAlocadas().contains(disciplinaPreRequisito)) {
						contPreRequisitos ++;
					}
				}
			}
		} else {
			return true;
		}
		return contPreRequisitos == preRequisitos.size();
	}
	
}