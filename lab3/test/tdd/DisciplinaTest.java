package tdd;

import static org.junit.Assert.*;
import models.Disciplina;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes para a classe Disciplina
 *
 */
public class DisciplinaTest {
	
	private Disciplina disciplina;

	@Before
	public void setUp() {
		disciplina = new Disciplina("Programacao 1", 4);
	}

	@Test
	public void testaConstrutor() {
		assertEquals("Programacao 1", disciplina.getNome());
		assertEquals(4, disciplina.getNumeroDeCreditos());
		
	}
	
}
