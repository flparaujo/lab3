package tdd;

import static org.junit.Assert.*;

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
		assertEquals(new Disciplina("Calculo Dif. e Int. 1", 4), 
				sistema.getDisciplinasDoPeriodoAtual().get(0));
		assertEquals(new Disciplina("Algebra Vet. e Geom. Analitica", 4), 
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
		sistema.adicionaDisciplinaAoPeriodoAtual("Calculo Dif. e Int. 2", 4);
		
		assertTrue(sistema.getDisciplinasDoPeriodoAtual().contains(new Disciplina("Calculo " +
				"Dif. e Int. 2", 4)));
	}

	@Test
	public void naoDeveAlocarDisciplinaAoPeriodoAtual() {
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodoAtual("Banco de Dados 1", 4);
		assertFalse(sistema.getDisciplinasDoPeriodoAtual().contains(new 
				Disciplina("Banco de Dados 1", 4)));
	}
	
	@Test
	public void deveMostrarCreditosDeDisciplinaDeQualquerPeriodo() {
		assertEquals(2, sistema.getDisciplinaDaGrade("Teoria dos Grafos").getNumeroDeCreditos());
	}
}
