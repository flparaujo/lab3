package models;

import java.io.*;
import java.util.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

//PURE FABRICATION: a classe LeitorDeDisciplinas nao representa algo do dominio do problema, serve pra manter a alta coesao da GradeCurricular.
/**
 * Classe responsavel por gerenciar informacoes sobre disciplinas, contidas em um arquivo.
 * 
 * 
 * @version 1.0
 */
public class LeitorDeDisciplinas {
	
	private Map<Integer, Disciplina> disciplinas;
	private Map<String, List<String>> mapa;
	private static LeitorDeDisciplinas instancia;
	
	private LeitorDeDisciplinas() {
		mapa = new HashMap<String, List<String>>();
		disciplinas = new HashMap<Integer, Disciplina>();
		try {
			carregaDisciplinasDoArquivo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked"})
	private void lerArquivo () {
		Document doc = null;
        SAXBuilder builder = new SAXBuilder();
        try {
              doc = builder.build("disciplinas-do-curso.xml");
        } catch (Exception e) {
              e.printStackTrace();
        }           
        List<Element> lista = doc.getRootElement().getChildren();
        for (Element e: lista ){
        	disciplinas.put(Integer.parseInt(e.getAttributeValue("id")), criaDisciplina(e));
        }           
	}
	
	private Disciplina criaDisciplina (Element e) {
		List<Disciplina> requisitos = new ArrayList<Disciplina>();
		String[] r = e.getAttributeValue("requisitos").split("");
		for (int i = 0; i < r.length; i++) {
			requisitos.add(disciplinas.get(r[i]));
		}
		Disciplina disciplina = new Disciplina(e.getAttributeValue("name"), Integer.parseInt(e.getChildText("creditos")), requisitos);
		return disciplina;
	}
	
	private void carregaDisciplinasDoArquivo() throws IOException {
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader("disciplinas-do-curso.txt"));
			String line;
			while ((line = inputStream.readLine()) != null) {
				String[] dados = line.split(", ");
				mapa.put(dados[0]+"-"+dados[1], new ArrayList<String> ());
				String[] nomesDosPreRequisitos = dados[6].split("-");
				for(String nomeDoPreRequisito : nomesDosPreRequisitos) {
					mapa.get(dados[0]+"-"+dados[1]).add(nomeDoPreRequisito);
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	} 
	
	/**
	 * Obtem uma unica instancia deste leitor de disciplinas.
	 * @return o leitor de disciplinas instanciado.
	 */
	//SINGLETON: Essa classe deve ser instanciada uma unica vez
	public static LeitorDeDisciplinas getInstance() {
		if(instancia == null) {
			instancia = new LeitorDeDisciplinas();
		}
		return instancia;
	}
	
	/**
	 * Obtem as informacoes basicas de todas disciplinas do arquivo.
	 * Cada informacao eh apresentada no formato "nome da disciplina-numero de creditos".
	 * @return um array de strings, onde cada string contem informacoes basicas de uma disciplina.
	 */
	public String[] getInformacoesDasDisciplinas() {
		String[] informacoes = mapa.keySet().toArray(new String[mapa.keySet().size()]);
		return informacoes;
	}
	
	/**
	 * Obtem uma lista dos nomes dos pre-requisitos de uma disciplina.
	 * @param nomeDaDisciplina O nome da disciplina da qual se quer obter os nomes dos pre-requisitos.
	 * @return a lista de strings, que sao os nomes dos pre-requisitos.
	 */
	public List<String> getNomesDosPreRequisitosDeDisciplina(String nomeDaDisciplina) {
		for(String informacaoDeDisciplina : mapa.keySet()) {
			if(informacaoDeDisciplina.split("-")[0].equals(nomeDaDisciplina))
				return mapa.get(informacaoDeDisciplina);
		}
		return null;
	}
	
	/**
	 * Obtem o total de creditos de uma disciplina do arquivo.
	 * @param nomeDaDisciplina O nome da disciplina.
	 * @return a quantidade de creditos da disciplina.
	 */
	public int getNumeroDeCreditosDeDisciplina(String nomeDaDisciplina) {
		for(int i = 0; i < getInformacoesDasDisciplinas().length; i++) {
			if(getInformacoesDasDisciplinas()[i].split("-")[0].equals(nomeDaDisciplina))
				return Integer.parseInt(getInformacoesDasDisciplinas()[i].split("-")[1]);
		}
		return 0;
	}

}
