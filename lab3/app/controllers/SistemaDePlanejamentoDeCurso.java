package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.GradeCurricular;
import models.Periodo;

public class SistemaDePlanejamentoDeCurso {
	
	private List<Periodo> periodos;
	private Periodo periodoAtual;
	private GradeCurricular grade;
	
	public SistemaDePlanejamentoDeCurso() {
		this.periodos = new ArrayList <Periodo>();
		this.grade = new GradeCurricular();
		primeiroPeriodo(); //Obs.: So chame apos criar a grade e antes de criar outro periodo!!
		this.periodoAtual = periodos.get(periodos.size() - 1);
	}
	
	// Grande Curricular
	public List<Disciplina> listaDisciplinasDoCurso() {
		return grade.getDisciplinas();
	}
	
	// Periodo atual
	public Periodo getPeriodoAtual() {
		return this.periodoAtual;
	}
	
	public List<Disciplina> getDisciplinasDoPeriodoAtual() {
		return getPeriodoAtual().disciplinasAlocadas();
	}
	
	public boolean getMinimoCreditosDoPeriodoAtual() {
		return periodoAtual.abaixoDoLimiteMinimoDeCreditos();
	}
	
	public boolean getMaximoCreditosDoPeriodoAtual() {
		return periodoAtual.acimaDoLimiteMaximoDeCreditos();
	}
	
	private boolean adicionarDisciplinaSePreRequisitosSatisfefitos(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos) {
		if (preRequisitosSatisfeitos(preRequisitos)) {	
			return getPeriodoAtual().adicionaDisciplina(nome, numeroDeCreditos, preRequisitos);
		} else {
			// mostrar q prerequisitos naum foram satisfeitos
		}
		return false;
	}
	
	public int numeroDeCreditosDoPeriodoAtual() {
		return getPeriodoAtual().getNumeroDeCreditos(); 
	}
	
	// Periodos anteriores
	public List<Periodo> getPeriodosAnteriores () {
		List<Periodo> result = new ArrayList<Periodo>();
		for (int i = 0; i < getPeriodos().size()-1; i++) {
			result.add(periodos.get(i));
		}
		return result;
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
			getPeriodos().get(0).adicionaDisciplina(nome, numeroDeCreditos);
			grade.removeDisciplina(nome);
		}	
	}
	
	// Todos os periodos
	public List<Periodo> getPeriodos() {
		return this.periodos;
	}
	
	//CREATOR: classe SistemaDePlanejamentoDeCurso registra objetos do tipo Periodo
	public void adicionaPeriodo() {
		Periodo novoPeriodo = new Periodo();
		if(!getPeriodoAtual().abaixoDoLimiteMinimoDeCreditos()) {
			getPeriodos().add(novoPeriodo);
			periodoAtual = novoPeriodo;
		}
	}

	public int numeroDeCreditosDoPeriodo(int i) {
		return getPeriodos().get(i-1).getNumeroDeCreditos();
	}
	
	private boolean preRequisitosSatisfeitos(List<Disciplina> preRequisitos) {
		boolean result = false;
		if (!preRequisitos.isEmpty()) {
			for(Periodo periodo : periodos) {
				for(Disciplina disciplina : preRequisitos) {
					if(periodo.disciplinasAlocadas().contains(disciplina)) {
						result = true;
					}
				}
			}
		} else {
			result = true;
		}
		return result;
	}

	public void adicionaDisciplinaAoPeriodoAtual(String nome, int numeroDeCreditos) {
		if(grade.getDisciplina(nome) != null) {
			if(adicionarDisciplinaSePreRequisitosSatisfefitos(nome, numeroDeCreditos, 
					grade.getDisciplina(nome).getPreRequisitos())) {
				grade.removeDisciplina(nome);
			}
		}
	}

	public Disciplina getDisciplinaDaGrade(String nome) {
		return grade.getDisciplina(nome);
	}
}