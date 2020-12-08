package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.VacinaController;
import model.vo.Vacina;
import java.awt.Font;


public class TelaCadastroVacina extends JFrame {

	private JPanel contentPane;
	private JTextField textPesquisador;
	private JTextField textFieldPaisOrigem;
	private JComboBox cbEstagioPesquisa;;
	private DatePickerSettings dateSettings;
	private DatePicker DataInicioPesquisa;
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroVacina frame = new TelaCadastroVacina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroVacina() {
		setTitle("Cadastro de Vacinas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Pesquisador");
		lblNome.setBounds(86, 11, 79, 14);
		contentPane.add(lblNome);
		
		textPesquisador = new JTextField();
		textPesquisador.setBounds(85, 29, 217, 25);
		contentPane.add(textPesquisador);
		textPesquisador.setColumns(10);
		
		JLabel lblPaisOrigem = new JLabel("Pa\u00EDs de Origem");
		lblPaisOrigem.setBounds(85, 65, 102, 14);
		contentPane.add(lblPaisOrigem);
		
		textFieldPaisOrigem = new JTextField();
		textFieldPaisOrigem.setBounds(85, 79, 217, 25);
		contentPane.add(textFieldPaisOrigem);
		textFieldPaisOrigem.setColumns(10);
		
		JLabel lblEstagioPesquisa = new JLabel("Est\u00E1gio da Pesquisa");
		lblEstagioPesquisa.setBounds(86, 173, 126, 14);
		contentPane.add(lblEstagioPesquisa);
		
		ArrayList<String> EstagioPesquisa = obterEstagioPesquisa();
		cbEstagioPesquisa = new JComboBox(EstagioPesquisa.toArray());
		cbEstagioPesquisa.setBounds(86, 188, 216, 25);
		contentPane.add(cbEstagioPesquisa);
		
		JLabel lblDataInicioPesquisa = new JLabel("Data de In\u00EDcio da Pesquisa");
		lblDataInicioPesquisa.setBounds(85, 115, 163, 14);
		contentPane.add(lblDataInicioPesquisa);
		
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
			
		DataInicioPesquisa = new DatePicker(dateSettings);
		DataInicioPesquisa.getComponentDateTextField().setEditable(false);
		DataInicioPesquisa.setBounds(85, 130, 217, 25);
		contentPane.add(DataInicioPesquisa);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnCadastrar.setBackground(Color.GREEN);
		btnCadastrar.setForeground(Color.BLACK);
		btnCadastrar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
			Vacina vacina = new Vacina ();
			
			vacina.setPaisOrigem(textFieldPaisOrigem.getText());
			vacina.setDataInicioPesquisa(DataInicioPesquisa.getDate());
			//vacina.setPesquisador(textPesquisador.getText());
				
			VacinaController vacinaController = new VacinaController();
			JOptionPane.showMessageDialog(null, vacinaController.cadastrarVacina(vacina));
		}
		});
		btnCadastrar.setBounds(61, 280, 126, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setForeground(Color.BLACK);
		btnSair.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Passou pelo botão sair");
			}
		});
		btnSair.setBounds(197, 280, 105, 23);
		contentPane.add(btnSair);
	}

	private ArrayList<String> obterEstagioPesquisa() {
		ArrayList<String> EstagioPesquisa = new ArrayList<String>();
		EstagioPesquisa.add("");
		EstagioPesquisa.add("1 - Inicial");
		EstagioPesquisa.add("2 - Testes");
		EstagioPesquisa.add("3 - Aplicação em massa");
		return EstagioPesquisa;
	}
}
