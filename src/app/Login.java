package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	private JFrame frmSindentifier;
	private JTextField txtIdent;
	

	/**
	 * Launch the application.
	 */
	public static void logU() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmSindentifier.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPasswordField txtPasswd;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSindentifier = new JFrame();
		frmSindentifier.setUndecorated(true);
		frmSindentifier.setTitle("S'indentifier");
		frmSindentifier.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmSindentifier.getContentPane().setForeground(Color.WHITE);
		frmSindentifier.setResizable(false);
		frmSindentifier.getContentPane().setBackground(new Color(128, 0, 0));
		frmSindentifier.setBounds(100, 100, 931, 600);
		frmSindentifier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSindentifier.getContentPane().setLayout(null);
		frmSindentifier.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(659, 10, 152, 86);
		frmSindentifier.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./images\\icons8_customer_50px.png"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(507, 162, 50, 68);
		frmSindentifier.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("./images\\icons8_lock_52px.png"));
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(499, 258, 58, 68);
		frmSindentifier.getContentPane().add(lblNewLabel_1_1);
		
		txtIdent = new JTextField();
		txtIdent.setForeground(new Color(255, 165, 0));
		txtIdent.setCaretColor(new Color(255, 165, 0));
		txtIdent.setSelectedTextColor(new Color(255, 165, 0));
		txtIdent.setBorder(null);
		txtIdent.setBackground(new Color(128, 0, 0));
		txtIdent.setBounds(563, 179, 344, 40);
		frmSindentifier.getContentPane().add(txtIdent);
		txtIdent.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setFocusTraversalKeysEnabled(false);
		comboBox.setForeground(new Color(128, 0, 0));
		comboBox.setBorder(null);
		comboBox.setBackground(new Color(255, 165, 0));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 25));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"admin", "secretaire"}));
		comboBox.setBounds(730, 378, 177, 40);
		frmSindentifier.getContentPane().add(comboBox);
		
		JButton btnConnection = new JButton("Connexion");
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
					
			try {
				String query = "select * from utilisateurs where identifiant=? and mot_de_passe=? and type_utilisateur =?";
				con = DriverManager.getConnection("jdbc:mysql://localhost/gestionhopital", "root", "");
				pst = con.prepareStatement(query);
				pst.setString(1, txtIdent.getText());
				pst.setString(2, txtPasswd.getText());
				pst.setString(3, String.valueOf(comboBox.getSelectedItem()));
				rs = pst.executeQuery();
				
				if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Identifiant et mot de passe acceptés! Bienvenue "+rs.getString("prenom")+" "+rs.getString("nom"));
				if(comboBox.getSelectedIndex()==0) {
					Dashboard_admin ad = new Dashboard_admin();
					ad.Admin();
					frmSindentifier.setVisible(false);
				}else{	
					
					Dashboard_secretaire sec = new Dashboard_secretaire();
					sec.Secretaire();
					frmSindentifier.setVisible(false);
					}
				}
				else {
					
				JOptionPane.showMessageDialog(null, "identifiant ou mot de passe incorrect ");}
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
				
			}

			private void setVisble(boolean b) {
				// TODO Auto-generated method stub
				
			}
		});
		btnConnection.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConnection.setBorder(null);
		btnConnection.setBackground(new Color(255, 165, 0));
		btnConnection.setForeground(new Color(128, 0, 0));
		btnConnection.setBounds(631, 447, 180, 50);
		frmSindentifier.getContentPane().add(btnConnection);
		
		txtPasswd = new JPasswordField();
		txtPasswd.setCaretColor(new Color(255, 165, 0));
		txtPasswd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPasswd.setForeground(new Color(255, 165, 0));
		txtPasswd.setBorder(null);
		txtPasswd.setBackground(new Color(128, 0, 0));
		txtPasswd.setBounds(563, 279, 344, 40);
		frmSindentifier.getContentPane().add(txtPasswd);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 165, 0));
		separator_1.setBackground(new Color(255, 140, 0));
		separator_1.setBounds(563, 329, 340, 2);
		frmSindentifier.getContentPane().add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 165, 0));
		separator.setBackground(new Color(255, 140, 0));
		separator.setBounds(563, 228, 340, 2);
		frmSindentifier.getContentPane().add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("./images\\log_bck.png"));
		lblNewLabel_2.setBounds(0, 0, 497, 600);
		frmSindentifier.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("./images\\icons8_multiply_32px_1.png"));
		lblNewLabel_3.setBounds(889, 10, 32, 32);
		frmSindentifier.getContentPane().add(lblNewLabel_3);
	}
}
