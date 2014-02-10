package tdd;

import static org.junit.Assert.*;

import models.LeitorDeDisciplinas;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes para a classe LeitorDeDisciplinas
 *
 */
public class LeitorDeDisciplinasTest {

	private LeitorDeDisciplinas leitorDeDisciplinas;

	@Before
	public void setUp() {
		leitorDeDisciplinas = LeitorDeDisciplinas.getInstance();
	}
	
	@Test
	public void deveMostrarNomeDePreRequisitoDeDisciplina() {
		assertEquals("Algebra Vet. e Geom. Analitica", 
				leitorDeDisciplinas.getNomesDosPreRequisitosDeDisciplina("Algebra Linear").get(0));
	}
	
	@Test
	public void deveMostrarNumeroDeCreditosDeDisciplina() {
		assertEquals(4, leitorDeDisciplinas.getNumeroDeCreditosDeDisciplina("Programacao 1"));
		assertEquals(2, leitorDeDisciplinas.getNumeroDeCreditosDeDisciplina("Paradigmas de " +
				"Linguagens de Programacao"));
	}

}
