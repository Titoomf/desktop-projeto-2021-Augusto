package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.google.protobuf.TextFormat.ParseException;

import controller.PessoaController;
import model.vo.Pessoa;

public class TelaCadastroPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textInstituicao;
	private JCheckBox chkPesquisador;
	private JComboBox cbSexo;
	private JFrame frame;
	private JFormattedTextField txtCPF;
	private JCheckBox chkVoluntario;
	private JComboBox cbReacao;
	private JCheckBox chkPublicoGeral;
	private DatePicker DataNascimento;
	private DatePicker DataVacinacao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPessoa frame = new TelaCadastroPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroPessoa() throws java.text.ParseException, ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroPessoa.class.getResource("/icons/icons8-nave-espacial-80.png")));
		setTitle("Cadastro de Pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 488);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("Nome");
			
		JLabel lblCPF = new JLabel("CPF");
		
		MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
		
		txtCPF = new JFormattedTextField(mascaraCpf);
			
		JLabel lblSexo = new JLabel("Sexo");
				
		ArrayList<String> sexo = obterSexo();
		cbSexo = new JComboBox(sexo.toArray());
		
		ArrayList<String> reacao = obterReacao();
		cbReacao = new JComboBox(reacao.toArray());
		cbReacao.setModel(new DefaultComboBoxModel(new String[] {"--- Selecione ----", "Grave"}));
		cbReacao.setEnabled(false);
		cbReacao.setEditable(true);
		
		textNome = new JTextField();
		textNome.setColumns(10);

		chkPesquisador = new JCheckBox("Pesquisador");
		chkPesquisador.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chkPesquisador.isSelected()) {
				textInstituicao.setEnabled(true);
			}else {
				textInstituicao.setEnabled(false);
			}
		}
});
	
	JLabel lblDataNascimento = new JLabel("Data de Nascimento");
	
	DatePickerSettings dateSettings = new DatePickerSettings();
	dateSettings.setAllowKeyboardEditing(false);
	
	DataNascimento = new DatePicker();
	DataNascimento.getComponentDateTextField().setEditable(false);

	JLabel lblDataVacinacao = new JLabel("Data da Vacinação");
		
	DataVacinacao = new DatePicker();
	DataVacinacao.getComponentDateTextField().setEditable(false);
	
	textInstituicao = new JTextField();
	textInstituicao.setEnabled(false);
	textInstituicao.setColumns(10);
	
	JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o");

	chkPublicoGeral = new JCheckBox("P\u00FAblico em Geral");
	chkPublicoGeral.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(chkPublicoGeral.isSelected()) {
				cbReacao.setEnabled(true);
			}else {
				cbReacao.setEnabled(false);
			}
		}
	});

	chkVoluntario = new JCheckBox("Volunt\u00E1rio");
	chkVoluntario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(chkVoluntario.isSelected()) {
				cbReacao.setEnabled(true);
			}else {
				cbReacao.setEnabled(false);
			}
		}
	});

	
	JButton btnCadastrar = new JButton("Cadastrar");
	btnCadastrar.setForeground(new Color(0, 0, 128));
	btnCadastrar.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			Pessoa pessoa = new Pessoa();
			String cpf = txtCPF.getText().replace("-", "").replace(".", "");
		
			pessoa.setNome(textNome.getText());
			pessoa.setDataNascimento(DataNascimento.getDate());
			pessoa.setSexo((String)cbSexo.getSelectedItem());
			pessoa.setDataVacinacao(DataVacinacao.getDate());
			pessoa.setCpf(txtCPF.getText().replace("-", "").replace(".", ""));
			
			PessoaController pessoaController = new PessoaController();
			JOptionPane.showMessageDialog(null, pessoaController.cadastrarPessoa(pessoa));
		}
		
	});
	
	JButton btnSair = new JButton("Sair");
	btnSair.setForeground(new Color(0, 0, 128));
	btnSair.addMouseListener(new MouseAdapter() {		
		public void mouseClicked(MouseEvent arg0) {
			JOptionPane.showMessageDialog(null, "Passou pelo botão sair");
		}
	});
		
	JLabel lblReacao = new JLabel("Reação");
	GroupLayout gl_contentPane = new GroupLayout(contentPane);
	gl_contentPane.setHorizontalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(94)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addComponent(textNome, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addComponent(txtCPF, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblCPF, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblDataNascimento, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addComponent(DataNascimento, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblSexo, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addComponent(cbSexo, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(chkVoluntario, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addGap(2)
						.addComponent(chkPublicoGeral, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblReacao, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addComponent(cbReacao, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblDataVacinacao, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addComponent(DataVacinacao, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addComponent(chkPesquisador)
					.addComponent(lblInstituicao, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addComponent(textInstituicao, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addGap(28)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
	);
	gl_contentPane.setVerticalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(17)
				.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(3)
				.addComponent(textNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addGap(7)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(13)
						.addComponent(txtCPF, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblCPF, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
				.addGap(6)
				.addComponent(lblDataNascimento, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(1)
				.addComponent(DataNascimento, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addGap(6)
				.addComponent(lblSexo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addComponent(cbSexo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addGap(7)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(chkVoluntario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addComponent(chkPublicoGeral, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addGap(7)
				.addComponent(lblReacao, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(1)
				.addComponent(cbReacao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addGap(8)
				.addComponent(lblDataVacinacao, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(1)
				.addComponent(DataVacinacao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addGap(7)
				.addComponent(chkPesquisador, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addGap(1)
				.addComponent(lblInstituicao, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(1)
				.addComponent(textInstituicao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addGap(19)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
	);
	contentPane.setLayout(gl_contentPane);
	}
	
	private ArrayList<String> obterSexo() {
		ArrayList<String> sexo = new ArrayList<String>();
		sexo.add("");
		sexo.add("Masculino");
		sexo.add("Feminino");
		return sexo;
	}
	
	private ArrayList<String> obterReacao() {
		ArrayList<String> reacao = new ArrayList<String>();
		reacao.add("");
		reacao.add("1 - PÉSSIMO");
		reacao.add("2 - RUIM");
		reacao.add("3- REGULAR");
		reacao.add("4 - BOM");
		reacao.add("5 - ÓTIMO");

		return reacao;
	}
}
	
	
