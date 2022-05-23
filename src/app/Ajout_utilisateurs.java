package app;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.DebugGraphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ajout_utilisateurs {

	private JFrame frame;
	private JTextField txtIdent;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtMdp;
	private JTable table;
	private JTextField txtRecher;
	private JButton btnAjouter;

	/**
	 * Launch the application.
	 */
	public static void user() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajout_utilisateurs window = new Ajout_utilisateurs();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ajout_utilisateurs() {
		initialize();
		connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void connect() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/gestionhopital", "root", "");
		}
		catch(ClassNotFoundException ex) 
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	public void table_load()
    {
    	try 
    	{
	    pst = con.prepareStatement("select * from utilisateurs");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	} 
    	catch (SQLException e) 
    	 {
    		e.printStackTrace();
	  } 
    }


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(128, 0, 0));
		frame.setBounds(100, 100, 931, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 97, 90, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setForeground(new Color(255, 165, 0));
		lblNom.setFont(new Font("Arial", Font.BOLD, 18));
		lblNom.setBounds(10, 153, 90, 24);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(new Color(255, 165, 0));
		lblPrenom.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrenom.setBounds(10, 211, 90, 24);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setForeground(new Color(255, 165, 0));
		lblMotDePasse.setFont(new Font("Arial", Font.BOLD, 18));
		lblMotDePasse.setBounds(10, 274, 127, 24);
		frame.getContentPane().add(lblMotDePasse);
		
		JLabel lblTypeUtilisateur = new JLabel("Type utilisateur");
		lblTypeUtilisateur.setForeground(new Color(255, 165, 0));
		lblTypeUtilisateur.setFont(new Font("Arial", Font.BOLD, 18));
		lblTypeUtilisateur.setBounds(10, 343, 147, 24);
		frame.getContentPane().add(lblTypeUtilisateur);
		
		txtIdent = new JTextField();
		txtIdent.setBounds(173, 102, 201, 19);
		frame.getContentPane().add(txtIdent);
		txtIdent.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setBounds(173, 158, 201, 19);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(173, 216, 201, 19);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtMdp = new JTextField();
		txtMdp.setBounds(173, 279, 201, 19);
		frame.getContentPane().add(txtMdp);
		txtMdp.setColumns(10);
		
		final JComboBox txtType = new JComboBox();
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtType.setModel(new DefaultComboBoxModel(new String[] {"admin", "secretaire", "docteur"}));
		txtType.setBounds(173, 347, 197, 21);
		frame.getContentPane().add(txtType);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iden, nom, prenom, mot_de_passe, type_use;
				iden = txtIdent.getText();
				nom = txtNom.getText();
				prenom = txtPrenom.getText();
				mot_de_passe = txtMdp.getText();
				type_use = txtType.getSelectedItem().toString();
				
				
				
				 try {
						pst = con.prepareStatement("insert into utilisateurs(identifiant,nom,prenom,mot_de_passe,type_utilisateur)values(?,?,?,?,?)");
						pst.setString(1, iden);
						pst.setString(2, nom);
						pst.setString(3, prenom);
						pst.setString(4, mot_de_passe);
						pst.setString(5, type_use);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Insertion reussie !!!");
						table_load();
							           
						txtIdent.setText("");
						txtNom.setText("");
						txtPrenom.setText("");
						txtMdp.setText("");
						txtType.setToolTipText("");
						txtIdent.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
			}
		});
		btnAjouter.setBackground(new Color(0, 128, 0));
		btnAjouter.setBorder(null);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBounds(10, 428, 109, 53);
		frame.getContentPane().add(btnAjouter);
		
		JButton btnModiffier = new JButton("Modifier");
		btnModiffier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iden, nom, prenom, mot_de_passe, type_use, idR;
				iden = txtIdent.getText();
				nom = txtNom.getText();
				prenom = txtPrenom.getText();
				mot_de_passe = txtMdp.getText();
				type_use = txtType.getSelectedItem().toString();
				idR = txtRecher.getText();
				
				 try {
						//pst = con.prepareStatement("insert into utilisateurs(identifiant,nom,prenom,mot_de_passe,type_utilisateur)values(?,?,?,?,?)");
					 	pst = con.prepareStatement("update utilisateurs set identifiant=?, nom=?, prenom=?, mot_de_passe=?, type_utilisateur=? where idUtilisateurs = ?");
						pst.setString(1, iden);
						pst.setString(2, nom);
						pst.setString(3, prenom);
						pst.setString(4, mot_de_passe);
						pst.setString(5, type_use);
						pst.setString(6, idR);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modification reussie !!!");
						table_load();
							           
						txtIdent.setText("");
						txtNom.setText("");
						txtPrenom.setText("");
						txtMdp.setText("");
						txtType.setToolTipText("");
						txtIdent.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
			}
		});
		btnModiffier.setBorder(null);
		btnModiffier.setBackground(new Color(255, 140, 0));
		btnModiffier.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnModiffier.setForeground(Color.WHITE);
		btnModiffier.setBounds(143, 428, 109, 53);
		frame.getContentPane().add(btnModiffier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idR;
				
				idR = txtRecher.getText();
				
				 try {
						//pst = con.prepareStatement("insert into utilisateurs(identifiant,nom,prenom,mot_de_passe,type_utilisateur)values(?,?,?,?,?)");
					 	pst = con.prepareStatement("delete from utilisateurs where idUtilisateurs = ?");
						pst.setString(1, idR);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Suppresion reussie !!!");
						table_load();
							           
						txtIdent.setText("");
						txtNom.setText("");
						txtPrenom.setText("");
						txtMdp.setText("");
						txtType.setToolTipText("");
						txtIdent.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
			}
		});
		btnSupprimer.setBackground(new Color(255, 0, 0));
		btnSupprimer.setBorder(null);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setBounds(271, 428, 139, 53);
		frame.getContentPane().add(btnSupprimer);
		
		table = new JTable();
		table.setBounds(446, 72, 444, 374);
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel_1 = new JLabel("Utilisateurs");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setBounds(173, 32, 190, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Recherche");
		lblNewLabel_1_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(462, 500, 127, 24);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtRecher = new JTextField();
		txtRecher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = txtRecher.getText();

		                pst = con.prepareStatement("select identifiant,nom,prenom,mot_de_passe,type_utilisateur from utilisateurs where idUtilisateurs = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		            	
		              
		                String iden = rs.getString(1);
		                String nom = rs.getString(2);
		                String prenom = rs.getString(3);
		                String mot_de_passe = rs.getString(4);
		                String type_use = rs.getString(5);
		                
		                
		                txtIdent.setText(iden);
		                txtNom.setText(nom);
		                txtPrenom.setText(prenom);
		                txtMdp.setText(mot_de_passe);
		                txtType.setToolTipText(mot_de_passe);
		                
		            }   
		            else
		            {
		                txtIdent.setText("");
		                txtNom.setText("");
		                txtPrenom.setText("");
		                txtMdp.setText("");
		                txtType.setToolTipText("");
		                
		                 
		            }
		            


		        } 
			
			 catch (SQLException ex) {
		           
		        }
			}
		});
		txtRecher.setBounds(599, 496, 271, 40);
		frame.getContentPane().add(txtRecher);
		txtRecher.setColumns(10);
		

		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard_admin rea = new Dashboard_admin();
				rea.Admin();
				frame.setVisible(false);
				
			}
		});
		lblNewLabel_2.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		lblNewLabel_2.setIcon(new ImageIcon("./images\\icons8_go_back_24px.png"));
		lblNewLabel_2.setBounds(22, 10, 35, 46);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("./images\\icons8_multiply_32px_1.png"));
		lblNewLabel_3.setBounds(845, 10, 35, 46);
		frame.getContentPane().add(lblNewLabel_3);
		

	}
}
