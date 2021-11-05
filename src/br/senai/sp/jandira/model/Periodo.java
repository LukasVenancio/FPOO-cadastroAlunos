package br.senai.sp.jandira.model;

public enum Periodo {

	MANHA("Manhã"), TARDE("Tarde"), NOITE("Note");

	private String descricao;

	private Periodo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
