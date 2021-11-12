package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;
import java.awt.Font;

public class FrameAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;
	private int posicao = 0;
	private int indexSelecionado;

	public FrameAlunos() {

		setTitle("Cadastro de Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setBounds(10, 16, 65, 14);
		contentPane.add(lblMatricula);

		txtMatricula = new JTextField();
		txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMatricula.setBounds(75, 8, 105, 31);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 57, 52, 14);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setColumns(10);
		txtNome.setBounds(75, 50, 105, 29);
		contentPane.add(txtNome);

		JLabel lblPeriodo = new JLabel("Período");
		lblPeriodo.setBounds(10, 92, 52, 14);
		contentPane.add(lblPeriodo);

		JComboBox comboPeriodo = new JComboBox();
		DefaultComboBoxModel<String> modelPeriodo = new DefaultComboBoxModel<String>();
		for (Periodo p : Periodo.values()) {
			modelPeriodo.addElement(p.getDescricao());
		}
		comboPeriodo.setModel(modelPeriodo);
		comboPeriodo.setBounds(75, 90, 105, 31);
		contentPane.add(comboPeriodo);

		JButton btnSalvarAluno = new JButton("Salvar Aluno");
		btnSalvarAluno.setBounds(10, 141, 170, 52);
		contentPane.add(btnSalvarAluno);

		JLabel lblListaDeAlunos = new JLabel("Lista de Alunos:");
		lblListaDeAlunos.setBounds(231, 8, 105, 14);
		contentPane.add(lblListaDeAlunos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 25, 154, 217);
		contentPane.add(scrollPane);

		JList listAlunos = new JList();
		DefaultListModel<String> listaModel = new DefaultListModel<String>();
		listAlunos.setModel(listaModel);
		scrollPane.setViewportView(listAlunos);

		JButton btnExibirAlunos = new JButton("Exibir Alunos");
		btnExibirAlunos.setBounds(10, 204, 170, 38);
		contentPane.add(btnExibirAlunos);

		AlunoRepository turma = new AlunoRepository(3);

		btnSalvarAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Aluno aluno = new Aluno();
				aluno.setMatricula(txtMatricula.getText());
				aluno.setNome(txtNome.getText());
				turma.gravar(aluno, posicao);
				aluno.setPeriodo(determinarPeriodo(comboPeriodo.getSelectedIndex()));
				posicao++;

				// Adicionar o nome do aluno ao model da lista
				listaModel.addElement(aluno.getNome());

				if (posicao == turma.getTamanho()) {
					btnSalvarAluno.setEnabled(false);
					JOptionPane.showMessageDialog(null, "A turma está cheia!");
				}
			}
		});

		btnExibirAlunos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (Aluno aluno : turma.listarTodos()) {
					System.out.println(aluno.getMatricula());
					System.out.println(aluno.getNome());
					System.out.println(aluno.getPeriodo());
					System.out.println("==============================");
				}
			}
		});
		
		
		listAlunos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				indexSelecionado = listAlunos.getSelectedIndex();
				
				Aluno aluno = turma.listarAluno(indexSelecionado);
	
				txtNome.setText(aluno.getNome()); 
				txtMatricula.setText(aluno.getMatricula());
				comboPeriodo.setSelectedIndex(aluno.getPeriodo().ordinal());
			}
		});

	}

	private Periodo determinarPeriodo(int periodoSelecionado) {

		if (periodoSelecionado == 0) {
			return Periodo.MANHA;
		} else if (periodoSelecionado == 1) {
			return Periodo.TARDE;
		} else if (periodoSelecionado == 2) {
			return Periodo.NOITE;
		} else {
			return Periodo.SABADO;
		}
	}
	
}
