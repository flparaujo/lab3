package exceptios;

/**
 * Essa excecao eh lancada quando se tenta fazer uma alocacao nao permitida.
 * 
 * Exemplo: alocar disciplina sem todos os pre-requisitos satisfeitos.
 * 
 * @author Felipe Andrade.
 *
 */
public class AlocacaoInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cria uma nova AlocacaoInvalidaException.
	 * @param mensagemDeErro A mensagem da excecao.
	 */
	public AlocacaoInvalidaException(String mensagemDeErro) {
		super(mensagemDeErro);
	}
	

}
