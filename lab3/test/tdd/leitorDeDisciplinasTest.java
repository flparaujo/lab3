package tdd;

import static org.junit.Assert.*;

import models.LeitorDeDisciplinas;

import org.junit.Before;
import org.junit.Test;

public class leitorDeDisciplinasTest {

	private LeitorDeDisciplinas leitorDeDisciplinas;

	@Before
	public void setUp() throws Exception {
		leitorDeDisciplinas = new LeitorDeDisciplinas();
	}

	@Test
	public void deveMostrarInformacoesDeDisciplinasDoArquivoOrdenadas() {
		assertEquals("Administracao-4", leitorDeDisciplinas.getInformacoesDasDisciplinas()[0]);
		assertEquals("Compiladores-4", leitorDeDisciplinas.getInformacoesDasDisciplinas()[11]);
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
