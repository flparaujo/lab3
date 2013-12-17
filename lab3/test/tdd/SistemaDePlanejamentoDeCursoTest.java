package tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;

import org.junit.Before;
import org.junit.Test;

import controllers.SistemaDePlanejamentoDeCurso;

public class SistemaDePlanejamentoDeCursoTest {

	private SistemaDePlanejamentoDeCurso sistema;

	@Before
	public void setUp() throws Exception {
	   sistema = new SistemaDePlanejamentoDeCurso();
	}

	@Test
	public void deveMostrarDisciplinasDoPrimeiroPeriodo() {
		assertEquals(20, sistema.numeroDeCreditosDoPeriodo(1));
		assertEquals(new Disciplina("Calculo 1", 4), sistema.getDisciplinasDoPeriodoAtual().get(0));
		assertEquals(new Disciplina("Algebra Vet. e Geo. Analitica", 4), 
				sistema.getDisciplinasDoPeriodoAtual().get(1));
		assertEquals(new Disciplina("Lab. de Programacao 1", 4), 
				sistema.getDisciplinasDoPeriodoAtual().get(2));
		assertEquals(new Disciplina("Programacao 1", 4), sistema.getDisciplinasDoPeriodoAtual().get(3));
		assertEquals(new Disciplina("Introducao a Computacao", 4), 
				sistema.getDisciplinasDoPeriodoAtual().get(4));
	}
	
	@Test
	public void deveAdicionarDisciplinaNoPeriodoAtual() {
		sistema.adicionaPeriodo();
		
		List<Disciplina> requisito = new ArrayList<>();
		requisito.add(new Disciplina("Calculo 1", 4));
		sistema.adicionaDisciplinaAoPeriodoAtual("Calculo 2", 4, requisito);
		
		List<Disciplina> calculo2 = new ArrayList<>();
		calculo2.add(new Disciplina("Calculo 2", 4, requisito));
		assertEquals(calculo2, sistema.getDisciplinasDoPeriodoAtual());
	}

	@Test
	public void deveMostrarTodasAsDisciplinasDaGrade () {
		List<Disciplina> disciplinas = new ArrayList<>();
		//adicionar elementos a disciplinas
		assertEquals(sistema.getGradeCurricular().getDisciplinas(), disciplinas);
	}
}
