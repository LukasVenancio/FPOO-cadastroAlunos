package br.senai.sp.jandira.repository;

import br.senai.sp.jandira.model.Aluno;

public class AlunoRepository {

	private Aluno[] turma;

	public AlunoRepository() {
		turma = new Aluno[32];
	}

	public AlunoRepository(int quantidadeDeAlunos) {
		turma = new Aluno[quantidadeDeAlunos];
	}

	public Aluno listarAluno(int posicao) {
		return turma[posicao];
	}

	public Aluno[] listarTodos() {
		return turma;
	}

	public void gravar(Aluno aluno, int posicao) {
		turma[posicao] = aluno;
	}
	
	public int getTamanho() {
		return turma.length;
	}
}
