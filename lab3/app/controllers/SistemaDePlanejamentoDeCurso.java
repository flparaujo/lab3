package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.Periodo;

//CREATOR: classe SistemaDePlanejamentoDeCurso registra objetos do tipo Periodo
public class SistemaDePlanejamentoDeCurso {
	
	private List<Periodo> periodos;
	
	public SistemaDePlanejamentoDeCurso() {
		periodos = new ArrayList <Periodo>();
		primeiroPeriodo();
	}
	
	private void primeiroPeriodo () {
		periodos.add(new Periodo());
		alocaDisciplinasDoPrimeiroPeriodo();
	}
	
	public List<Disciplina> getDisciplinasDoPeriodo(int i) {
		return periodos.get(i-1).disciplinasAlocadas();
	}
	
	private void alocaDisciplinasDoPrimeiroPeriodo() {
		String[] informacoesDasDisciplinas = {"Calculo 1-4", "Algebra Vet. e Geo. Analitica-4", 
				"Lab. de Programacao 1-4", "Programacao 1-4", 
				"Introducao a Computacao-4"};
		
		for(String informacao : informacoesDasDisciplinas) {
			String nome = informacao.split("-")[0];
			int numeroDeCreditos = Integer.parseInt(informacao.split("-")[1]);
			periodos.get(0).adicionaDisciplina(nome, numeroDeCreditos, null);
		}	
	}

	public int numeroDeCreditosDoPeriodo(int i) {
		return periodos.get(i-1).getNumeroDeCreditos();
	}
}