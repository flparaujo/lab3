package models;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//PURE FABRICATION: a classe LeitorDeDisciplinas nao representa algo do dominio do problema, serve pra manter a alta coesao da GradeCurricular.
/**
 * Classe responsavel por gerenciar informacoes sobre disciplinas, contidas em um arquivo.
 * 
 * 
 * @version 1.0
 */
public class LeitorDeDisciplinas {
	
	private Map<Integer, Disciplina> disciplinas;
	private static LeitorDeDisciplinas instancia;
	
	private LeitorDeDisciplinas() {
		disciplinas = new HashMap<Integer, Disciplina>();
		lerArquivo();
	}
	
	private void lerArquivo () {
		File fXmlFile = new File("disciplinas-do-curso.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("cadeira");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				criaDisciplina(nNode);
			}
		}
	}
	
	private void criaDisciplina (Node nNode) {
		List<Disciplina> requisitos = new ArrayList<Disciplina>();
		Element cadeira = (Element) nNode;
		NodeList preRequisitos = cadeira.getElementsByTagName("id");
		for (int i = 0; i < preRequisitos.getLength(); i++) {
			requisitos.add(disciplinas.get(Integer.parseInt(preRequisitos.item(i).getTextContent())));
		}
		Disciplina disciplina = new Disciplina(cadeira.getAttribute("nome"), 
				Integer.parseInt(cadeira.getElementsByTagName("creditos").item(0).getTextContent()),
				requisitos,
				Integer.parseInt(cadeira.getElementsByTagName("dificuldade").item(0).getTextContent()));
		disciplinas.put(Integer.parseInt(cadeira.getAttribute("id")), disciplina);
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
	public List<Disciplina> getInformacoesDasDisciplinas() {
		List<Disciplina> informacoes = new ArrayList<Disciplina>();
		for (int i = 1; i <= disciplinas.size(); i++) {
			informacoes.add(disciplinas.get(i));
		}
		return informacoes;
	}

}
