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
		assertEquals(new Disciplina("Calculo 1", 4, null), sistema.getDisciplinasDoPeriodo(1).get(0));
		assertEquals(new Disciplina("Algebra Vet. e Geo. Analitica", 4, null), 
				sistema.getDisciplinasDoPeriodo(1).get(1));
		assertEquals(new Disciplina("Lab. de Programacao 1", 4, null), 
				sistema.getDisciplinasDoPeriodo(1).get(2));
		assertEquals(new Disciplina("Programacao 1", 4, null), sistema.getDisciplinasDoPeriodo(1).get(3));
		assertEquals(new Disciplina("Introducao a Computacao", 4, null), 
				sistema.getDisciplinasDoPeriodo(1).get(4));
	}
	
	@Test
	public void deveAdicionarDisciplinaNoPeriodo() {
		sistema.adicionaPeriodo();
		List<Disciplina> prerequisitoCalculo2 = new ArrayList<Disciplina>();
		sistema.adicionaDisciplinaAoPeriodo(2, "Calculo 2", 4, prerequisitoCalculo2);
		//assertEquals("Calculo 2", sistema.);
	}

}
