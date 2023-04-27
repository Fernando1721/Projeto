package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.DAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(146, 137, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(146, 174, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(222, 136, 121, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(222, 171, 121, 20);
		contentPane.add(txtSenha);
		
		JLabel lblNewLabel_4 = new JLabel("Caso não tenha um login");
		lblNewLabel_4.setBounds(135, 217, 160, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Cadastre-se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnNewButton.setBounds(292, 212, 113, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/Img/logoGame.png")));
		lblNewLabel_2.setBounds(240, 42, 72, 72);
		contentPane.add(lblNewLabel_2);
		
		lblLogin = new JButton("Login");
		lblLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		lblLogin.setBounds(240, 260, 89, 23);
		contentPane.add(lblLogin);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(469, 293, 48, 48);
		contentPane.add(lblStatus);
	}// fim do construtor
	
	DAO dao = new DAO();
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton lblLogin;
	private JLabel lblStatus;
	
	
	/**
	 * Método usado para verificar o status do servidor
	 */

	private void status() {
		try {
			// Abrir a conexão
			Connection con = dao.conectar();
			if (con == null) {
				// escolher a imagem database_off
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dbon.png")));
			}
			// Não Esquecer de Fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}
	
	   private void logar() {

			String capturaSenha = new String(txtSenha.getPassword());

			// validação
			if (txtLogin.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insira o login");
				txtLogin.requestFocus();
			} else if (txtSenha.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Insira a senha");
				txtSenha.requestFocus();
			} else {

				String read = "select * from usuarios where login=? and senha=md5(?)";
				try {

					Connection con = dao.conectar();

					PreparedStatement pst = con.prepareStatement(read);

					pst.setString(1, txtLogin.getText());
					pst.setString(2, capturaSenha);

					ResultSet rs = pst.executeQuery();
					
					JOptionPane.showMessageDialog(null, "Logado");
					
					Principal principal = new Principal();
					
					principal.setVisible(true);
	                                
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	   
	   public void cadastrar(){
		   TelaCadastro telacadastro = new TelaCadastro();
		   telacadastro.setVisible(true);
		   
	   }
	
	
	
}
