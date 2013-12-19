package tdd;

import static org.junit.Assert.*;
import models.Disciplina;
import models.Tipo;

import org.junit.Before;
import org.junit.Test;

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
		assertEquals(Tipo.OBRIGATORIA, disciplina.getTipo());
	}
	
	//@Test
	//public void 

}
