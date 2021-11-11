package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;
	private int posicao = 0;

	public FrameAlunos() {

		setTitle("Cadastro de Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setBounds(10, 11, 65, 14);
		contentPane.add(lblMatricula);

		txtMatricula = new JTextField();
		txtMatricula.setBounds(75, 8, 86, 20);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 39, 52, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(75, 36, 86, 20);
		contentPane.add(txtNome);

		JLabel lblPeriodo = new JLabel("Período");
		lblPeriodo.setBounds(10, 71, 52, 14);
		contentPane.add(lblPeriodo);

		JComboBox comboPeriodo = new JComboBox();
		DefaultComboBoxModel<String> modelPeriodo = new DefaultComboBoxModel<String>();
		for (Periodo p : Periodo.values()) {
			modelPeriodo.addElement(p.getDescricao());
		}
		comboPeriodo.setModel(modelPeriodo);
		comboPeriodo.setBounds(75, 67, 86, 22);
		contentPane.add(comboPeriodo);

		JButton btnSalvarAluno = new JButton("Salvar Aluno");
		btnSalvarAluno.setBounds(35, 110, 126, 52);
		contentPane.add(btnSalvarAluno);

		JLabel lblListaDeAlunos = new JLabel("Lista de Alunos:");
		lblListaDeAlunos.setBounds(187, 11, 105, 14);
		contentPane.add(lblListaDeAlunos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 26, 153, 188);
		contentPane.add(scrollPane);

		JList listAlunos = new JList();
		DefaultListModel<String> listaModel = new DefaultListModel<String>();
		listAlunos.setModel(listaModel);
		scrollPane.setViewportView(listAlunos);
		
		JButton btnExibirAlunos = new JButton("Exibir Alunos");
		btnExibirAlunos.setBounds(35, 176, 126, 38);
		contentPane.add(btnExibirAlunos);

		AlunoRepository turma = new AlunoRepository(3);

		btnSalvarAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Aluno aluno = new Aluno();
				aluno.setMatricula(txtMatricula.getText());
				aluno.setNome(txtNome.getText());
				turma.gravar(aluno, posicao);
				posicao++;
				
				//Adicionar o nome do aluno ao model da lista
			
				listaModel.addElement(aluno.getNome());
			}
		});
		
		btnExibirAlunos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(Aluno aluno : turma.listarTodos()) {
					System.out.println(aluno.getMatricula());
					System.out.println(aluno.getNome());
					System.out.println("==============================");
				}
				
				
			}
		});

	}
}
