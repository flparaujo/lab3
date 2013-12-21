package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.naming.LimitExceededException;

import exceptios.AlocacaoInvalidaException;


import models.Disciplina;
import models.GradeCurricular;
import models.Periodo;

public class SistemaDePlanejamentoDeCurso {
	
	private List<Periodo> periodos;
	private GradeCurricular grade;
	
	public SistemaDePlanejamentoDeCurso() {
		this.periodos = new ArrayList <Periodo>();
		this.grade = new GradeCurricular();
		primeiroPeriodo(); //Obs.: So chame apos criar a grade e antes de criar outro periodo!!
	}
	
	// Grande Curricular
	public List<Disciplina> listaDisciplinasDoCurso() {
		return grade.getDisciplinas();
	}
	
	public Periodo getPeriodo(int i) {
		return periodos.get(i);
	}
	
	public List<Disciplina> getDisciplinasDoPeriodo(int indicePeriodo) {
		return getPeriodo(indicePeriodo).disciplinasAlocadas();
	}
	
	private void adicionarDisciplinaSePreRequisitosSatisfeitos(int indicePeriodo, String nome, int numeroDeCreditos, 
			List<Disciplina> preRequisitos) throws AlocacaoInvalidaException, LimitExceededException {
		if (! preRequisitosSatisfeitos(indicePeriodo, preRequisitos)) {	
			throw new AlocacaoInvalidaException("Nao pode alocar " + nome + " ao "+ (indicePeriodo+1) 
					+ "º periodo. " + "Ha pre-requisito(s) nao satisfeito(s).");
		}
		getPeriodo(indicePeriodo).adicionaDisciplina(nome, numeroDeCreditos, preRequisitos);
	}
	
	public int numeroDeCreditosDoPeriodo(int indicePeriodo) {
		return periodos.get(indicePeriodo).getNumeroDeCreditos(); 
	}
	
	// 1º Periodo
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
			} catch (LimitExceededException e) {
				e.printStackTrace();
			}
			grade.pegaDisciplina(nome);
		}	
	}
	
	// Todos os periodos
	public List<Periodo> getPeriodos() {
		return this.periodos;
	}
	
	//CREATOR: classe SistemaDePlanejamentoDeCurso registra objetos do tipo Periodo
	public void adicionaPeriodo() {
		periodos.add(new Periodo());
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

	public void adicionaDisciplinaAoPeriodo(int indicePeriodo, String nome, int numeroDeCreditos) 
			throws AlocacaoInvalidaException, LimitExceededException {
		if(grade.getDisciplina(nome) != null) {
			adicionarDisciplinaSePreRequisitosSatisfeitos(indicePeriodo, nome, numeroDeCreditos, 
					grade.getDisciplina(nome).getPreRequisitos());
				grade.pegaDisciplina(nome);
		}
	}

	public Disciplina getDisciplinaDaGrade(String nome) {
		return grade.getDisciplina(nome);
	}
	
	public boolean periodoComCreditosAbaixoDoLimiteMinimo(int idPeriodo) {
		return getPeriodo(idPeriodo).abaixoDoLimiteMinimoDeCreditos();
	}
	
}