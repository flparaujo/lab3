package tdd;

import static org.junit.Assert.*;

import models.Disciplina;

import org.junit.Before;
import org.junit.Test;

import controllers.SistemaDePlanejamentoDeCurso;
import exceptions.AlocacaoInvalidaException;
import exceptions.LimiteDeCreditosExcedidoException;

/**
 * Testes para a classe SistemaDePlanejamentoDeCurso
 *
 */
public class SistemaDePlanejamentoDeCursoTest {

	private SistemaDePlanejamentoDeCurso sistema;

	@Before
	public void setUp() {
	   sistema = new SistemaDePlanejamentoDeCurso();
	}

	@Test
	public void deveMostrarDisciplinasDoPrimeiroPeriodo() {
		assertEquals(20, sistema.numeroDeCreditosDoPeriodo(0));
		assertEquals(new Disciplina("Calculo Dif. e Int. 1", 4), 
				sistema.getDisciplinasDoPeriodo(0).get(0));
		assertEquals(new Disciplina("Algebra Vet. e Geom. Analitica", 4), 
				sistema.getDisciplinasDoPeriodo(0).get(1));
		assertEquals(new Disciplina("Lab. de Programacao 1", 4), 
				sistema.getDisciplinasDoPeriodo(0).get(2));
		assertEquals(new Disciplina("Programacao 1", 4), sistema.getDisciplinasDoPeriodo(0).get(3));
		assertEquals(new Disciplina("Introducao a Computacao", 4), 
				sistema.getDisciplinasDoPeriodo(0).get(4));
	}
	
	@Test
	public void deveAdicionarDisciplinaEmPeriodo() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(1, "Calculo Dif. e Int. 2", 4);
	}

	@Test
	public void naoDeveAlocarDisciplinaAoPeriodo() throws Exception {
		sistema.adicionaPeriodo();
		try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Banco de Dados 1", 4);
			fail("Esperava excecao de nao poder alocar disciplina que tem " +
					"pre-requisito(s) nao satisfeito(s)");
		} catch (AlocacaoInvalidaException e) {
			assertEquals("Nao pode alocar essa disciplina ao periodo. Ha " +
					"pre-requisito(s) nao satisfeito(s).", e.getMessage());
		}
	}
	
	@Test
	public void naoPodeAlocarDisciplinaEmPeriodoAnteriorAoDoPreRequisito() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(2, "Gerencia da Informacao", 4);
	    try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Sistemas de Informacao 1", 4);
			fail("Esperava excecao de nao poder alocar disciplina que tem pre-requisito(s)" +
					" nao satisfeito(s)");
		} catch (AlocacaoInvalidaException e) {
			assertEquals("Nao pode alocar essa disciplina ao periodo. Ha " +
					"pre-requisito(s) nao satisfeito(s).", e.getMessage());
		}
	}
	
	@Test
	public void naoPodeAdicionarDisciplinaAcimaDoLimiteMaximoDeCreditos() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(1, "Algebra Linear", 4);
		sistema.adicionaDisciplinaAoPeriodo(1, "Administracao", 4);
		sistema.adicionaDisciplinaAoPeriodo(1, "Gerencia da Informacao", 4);
		sistema.adicionaDisciplinaAoPeriodo(1, "Futsal", 2);
		sistema.adicionaDisciplinaAoPeriodo(1, "Ingles", 4);
		sistema.adicionaDisciplinaAoPeriodo(1, "Fundamentos de Fisica Classica", 4);
		sistema.adicionaDisciplinaAoPeriodo(1, "Informatica e Sociedade", 2);
		sistema.adicionaDisciplinaAoPeriodo(1, "Empreendedorismo 1", 4);
		try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Expressao Grafica", 4);
			fail("Esperava excecao de nao poder alocar disciplina ultrapassando o " +
					"limite de 28 creditos");
		}
		catch(LimiteDeCreditosExcedidoException e) {
			assertEquals("Nao foi possivel alocar a disciplina. Limite maximo eh " +
					"de 28 creditos!", e.getMessage());
		}
	}
	
	@Test
	public void deveRemoverDisciplina() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(1, "Matematica Discreta", 4);
		sistema.adicionaDisciplinaAoPeriodo(1, "Teoria dos Grafos", 2);
		sistema.adicionaDisciplinaAoPeriodo(2, "Teoria da Computacao", 4);
		sistema.adicionaDisciplinaAoPeriodo(3, "Logica Matematica", 4);
		sistema.devolveDisciplinaParaGrade("Teoria dos Grafos");
		sistema.removeDisciplinasDesalocadas();
		assertEquals(null, sistema.getPeriodo(1).getDisciplina("Teoria dos Grafos"));
		assertEquals(null, sistema.getPeriodo(2).getDisciplina("Teoria da Computacao"));
		assertEquals(null, sistema.getPeriodo(3).getDisciplina("Logica Matematica"));
		assertEquals(new Disciplina("Teoria dos Grafos", 2), 
				sistema.getDisciplinaDaGrade("Teoria dos Grafos"));
		assertEquals(new Disciplina("Teoria da Computacao", 4), 
				sistema.getDisciplinaDaGrade("Teoria da Computacao"));
		assertEquals(new Disciplina("Logica Matematica", 4), 
				sistema.getDisciplinaDaGrade("Logica Matematica"));
	}
	
	@Test
	public void deveMostrarCreditosDeDisciplinaDeQualquerPeriodo() {
		assertEquals(2, sistema.getDisciplinaDaGrade("Teoria dos Grafos").getNumeroDeCreditos());
	}
}
