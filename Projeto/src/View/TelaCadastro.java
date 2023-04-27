package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.DAO;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeExibi;
	private JPasswordField txtSenhaUsu;
	private JTextField txtLoginUsu;
	private JComboBox cboPerfil;
	private JButton btnCadastrarUsu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Senha");
		lblNewLabel.setBounds(37, 169, 75, 14);
		contentPane.add(lblNewLabel);
		
		txtNomeExibi = new JTextField();
		txtNomeExibi.setBounds(165, 78, 121, 20);
		contentPane.add(txtNomeExibi);
		txtNomeExibi.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(37, 126, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		txtSenhaUsu = new JPasswordField();
		txtSenhaUsu.setBounds(163, 165, 125, 20);
		contentPane.add(txtSenhaUsu);
		
		JLabel lblNewLabel_2 = new JLabel("Nome de Exibição");
		lblNewLabel_2.setBounds(37, 81, 129, 14);
		contentPane.add(lblNewLabel_2);
		
		txtLoginUsu = new JTextField();
		txtLoginUsu.setBounds(165, 123, 121, 20);
		contentPane.add(txtLoginUsu);
		txtLoginUsu.setColumns(10);
		
		cboPerfil = new JComboBox();
		cboPerfil.setVisible(false);
		cboPerfil.setEnabled(false);
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] {"Usuario"}));
		cboPerfil.setBounds(207, 209, 82, 22);
		
		contentPane.add(cboPerfil);
		
		btnCadastrarUsu = new JButton("Cadastrar");
		btnCadastrarUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();			}
		});
		btnCadastrarUsu.setBounds(316, 276, 102, 37);
		contentPane.add(btnCadastrarUsu);
		
		
		
	}
	
	DAO dao = new DAO();
	
	private void adicionarUsuario() {
		// validação
		// validação da Senha (captura segura)
		String capturarSenha = new String(txtSenhaUsu.getPassword());

		if (txtNomeExibi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome de exibição");
			txtNomeExibi.requestFocus();
		} else if (txtLoginUsu.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLoginUsu.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuário");
			cboPerfil.requestFocus();
		} else if (txtSenhaUsu.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtSenhaUsu.requestFocus();
		} else {
			// Lógica Principal
			String create = "insert into usuarios(usuario,login,senha,perfil)values(?,?,md5(?),?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir os ???? pelo conteúdo das caixas de texto
				pst.setString(1, txtNomeExibi.getText());
				pst.setString(2, txtLoginUsu.getText());
				pst.setString(3, capturarSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
				txtLoginUsu.setText(null);
				txtLoginUsu.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}
}
