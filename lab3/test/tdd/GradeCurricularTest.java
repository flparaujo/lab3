package tdd;

import static org.junit.Assert.*;

import models.Disciplina;
import models.GradeCurricular;

import org.junit.Before;
import org.junit.Test;

public class GradeCurricularTest {

	private GradeCurricular gradeCurricular;

	@Before
	public void setUp() throws Exception {
		gradeCurricular = new GradeCurricular();
	}

	@Test
	public void deveConterDisciplina() {
		assertTrue(gradeCurricular.getDisciplinas().contains(new Disciplina("Estruturas" +
				" de Dados e Algoritmos", 4)));
		assertTrue(gradeCurricular.getDisciplinas().contains(new Disciplina("Sistemas de " +
				"Informacao 1", 4)));
		assertTrue(gradeCurricular.getDisciplinas().contains(new Disciplina("Lab. Organ. " +
				"e Arquit. de Computadores", 4)));
	}
	
	@Test
	public void deveFornecerDisciplina() {
		assertEquals(new Disciplina("Teoria da Computacao", 4), gradeCurricular.
				getDisciplina("Teoria da Computacao"));
	}

}
