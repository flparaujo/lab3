package models;

import java.util.List;

public class Disciplina {
	
	// INFORMATION EXPERT: Disciplinas tem a responsabilidade de saber seus pre-requisitos.	
	private String nome;
	private int numeroDeCreditos;
	private String[] preRequisitos;

	public Disciplina(String nome, int numeroDeCreditos, String[] preRequisitos) {
		this.nome = nome;
		this.numeroDeCreditos = numeroDeCreditos;
		this.preRequisitos =  preRequisitos;
	}

	public String getNome() {
		return this.nome;
	}

	public int getNumeroDeCreditos() {
		return this.numeroDeCreditos;
	}
	
	public String[] getPreRequisitos() {
		return this.preRequisitos;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Disciplina)) {
			return false;
		}
		return ((Disciplina) obj).getNome().equals(nome) 
				&& ((Disciplina) obj).getNumeroDeCreditos() == numeroDeCreditos;
	}

}
