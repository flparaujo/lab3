package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.GradeCurricular;
import models.Periodo;

//CREATOR: classe SistemaDePlanejamentoDeCurso registra objetos do tipo Periodo
public class SistemaDePlanejamentoDeCurso {
	
	private List<Periodo> periodos;
	private Periodo periodoAtual;
	private GradeCurricular grade;
	
	public SistemaDePlanejamentoDeCurso() {
		this.periodos = new ArrayList <Periodo>();
		primeiroPeriodo();
		this.periodoAtual = periodos.get(periodos.size() - 1);
		this.grade = new GradeCurricular();
	}
	
	// Grande Curricular
	public GradeCurricular getGradeCurricular () {
		return this.grade;
	}
	
	// Periodo atual
	public Periodo getPeriodoAtual () {
		return this.periodoAtual;
	}
	
	public List<Disciplina> getDisciplinasDoPeriodoAtual() {
		return getPeriodoAtual().disciplinasAlocadas();
	}
	
	public void adicionaDisciplinaAoPeriodoAtual(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos) {
		if ((numeroDeCreditosDoPeriodoAtual() + numeroDeCreditos) < 12) {
			// mostrar q o numero minimo de creditos naum foi atingigo
			adicionarDisciplinaSePreRequisitosSatisfefitos(nome, numeroDeCreditos, preRequisitos);
		} else if ((numeroDeCreditosDoPeriodoAtual() + numeroDeCreditos)<= 28) {
			adicionarDisciplinaSePreRequisitosSatisfefitos(nome, numeroDeCreditos, preRequisitos);
		} else {
			// mostra messagem q o numero maximo foi atingido
		}
	}
	
	private void adicionarDisciplinaSePreRequisitosSatisfefitos(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos) {
		if(preRequisitosSatisfeitos(preRequisitos)) {
			getPeriodoAtual().adicionaDisciplina(nome, numeroDeCreditos, preRequisitos);
		} else {
			// mostrar q pre-requisitos naum foram satisfeitos
		}
	}
	
	public int numeroDeCreditosDoPeriodoAtual() {
		return getPeriodoAtual().getNumeroDeCreditos(); 
	}
	
	// Periodos anteriores
	public List<Periodo> getPeriodosAnteriores () {
		List<Periodo> result = new ArrayList<>();
		for (int i = 0; i < getPeriodos().size()-1; i++) {
			result.add(periodos.get(i));
		}
		return result;
	}
	
	// 1º Periodo
	private void primeiroPeriodo () {
		getPeriodos().add(new Periodo());
		alocaDisciplinasDoPrimeiroPeriodo();
	}
	
	private void alocaDisciplinasDoPrimeiroPeriodo() {
		String[] informacoesDasDisciplinas = {"Calculo 1-4", "Algebra Vet. e Geo. Analitica-4", 
				"Lab. de Programacao 1-4", "Programacao 1-4", 
				"Introducao a Computacao-4"};
		
		for(String informacao : informacoesDasDisciplinas) {
			String nome = informacao.split("-")[0];
			int numeroDeCreditos = Integer.parseInt(informacao.split("-")[1]);
			getPeriodos().get(0).adicionaDisciplina(nome, numeroDeCreditos);
		}	
	}
	
	// Todos os periodos
	public List<Periodo> getPeriodos() {
		return this.periodos;
	}
	
	public void adicionaPeriodo() {
		Periodo newPeriodo= new Periodo();
		getPeriodos().add(newPeriodo);
		periodoAtual = newPeriodo;
	}

	public int numeroDeCreditosDoPeriodo(int i) {
		return getPeriodos().get(i-1).getNumeroDeCreditos();
	}
	
	private boolean preRequisitosSatisfeitos(List<Disciplina> preRequisito) {
		boolean result = false;
		for(Periodo periodo : getPeriodos()) {
			if (result != true) {
				result = periodo.preRequisitosSatisfeitos(preRequisito);
			} else {
				break;
			}
		}
		return result;
	}
}