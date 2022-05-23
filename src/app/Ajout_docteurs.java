package app;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
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

public class Ajout_docteurs {

	private JFrame frame;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtTel;
	private JTextField txtSpecial;
	private JTextField idRe;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void doc() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajout_docteurs window = new Ajout_docteurs();
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
	public Ajout_docteurs() {
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
	    pst = con.prepareStatement("select * from medecin");
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
		frame.getContentPane().setBackground(new Color(128, 0, 0));
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 931, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setForeground(new Color(255, 140, 0));
		lblNom.setFont(new Font("Arial", Font.BOLD, 18));
		lblNom.setBounds(10, 153, 90, 24);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setForeground(new Color(255, 140, 0));
		lblPrenom.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrenom.setBounds(10, 211, 90, 24);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setForeground(new Color(255, 140, 0));
		lblTelephone.setFont(new Font("Arial", Font.BOLD, 18));
		lblTelephone.setBounds(10, 276, 109, 24);
		frame.getContentPane().add(lblTelephone);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setForeground(new Color(255, 140, 0));
		lblAdresse.setFont(new Font("Arial", Font.BOLD, 18));
		lblAdresse.setBounds(10, 342, 90, 24);
		frame.getContentPane().add(lblAdresse);
		
		JLabel lblDocteurs = new JLabel("Docteurs");
		lblDocteurs.setForeground(new Color(255, 140, 0));
		lblDocteurs.setFont(new Font("Arial", Font.BOLD, 22));
		lblDocteurs.setBounds(208, 39, 132, 24);
		frame.getContentPane().add(lblDocteurs);
		
		JLabel lblRecherhce = new JLabel("Recherhce");
		lblRecherhce.setForeground(Color.WHITE);
		lblRecherhce.setFont(new Font("Arial", Font.BOLD, 18));
		lblRecherhce.setBounds(493, 482, 132, 24);
		frame.getContentPane().add(lblRecherhce);
		
		txtNom = new JTextField();
		txtNom.setBounds(124, 158, 216, 19);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(124, 216, 216, 19);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(129, 281, 211, 19);
		frame.getContentPane().add(txtTel);
		txtTel.setColumns(10);
		
		txtSpecial = new JTextField();
		txtSpecial.setBounds(129, 347, 211, 19);
		frame.getContentPane().add(txtSpecial);
		txtSpecial.setColumns(10);
		
		idRe = new JTextField();
		idRe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = idRe.getText();

		                pst = con.prepareStatement("select nom,prenom,telephone,specialiste from medecin where idMedecin = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		            	
		              
		                String nom = rs.getString(1);
		                String prenom = rs.getString(2);
		                String tel = rs.getString(3);
		                String special = rs.getString(4);
		                
		                

		                txtNom.setText(nom);
		                txtPrenom.setText(prenom);
		                txtTel.setText(tel);
		                txtSpecial.setText(special);
		                
		            }   
		            else
		            {
		                txtNom.setText("");
		                txtPrenom.setText("");
		                txtTel.setText("");
		                txtSpecial.setText("");
		                
		                 
		            }
		            


		        } 
			
			 catch (SQLException ex) {
		           
		        }
				
			}
		});
		idRe.setBounds(605, 479, 238, 35);
		frame.getContentPane().add(idRe);
		idRe.setColumns(10);
		
		table = new JTable();
		table.setBounds(436, 81, 468, 357);
		frame.getContentPane().add(table);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom, prenom, tel, specia;
				nom = txtNom.getText();
				prenom = txtPrenom.getText();
				tel = txtTel.getText();
				specia = txtSpecial.getText();
				
				
				
				 try {
						pst = con.prepareStatement("insert into medecin(nom,prenom,telephone,specialiste)values(?,?,?,?)");
						pst.setString(1, nom);
						pst.setString(2, prenom);
						pst.setString(3, tel);
						pst.setString(4, specia);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Insertion reussie !!!");
						table_load();
							           
						txtNom.setText("");
						txtPrenom.setText("");
						txtTel.setText("");
						txtSpecial.setText("");
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
			}
		});
		btnAjouter.setBackground(new Color(0, 128, 0));
		btnAjouter.setBorder(null);
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.setBounds(10, 417, 109, 45);
		frame.getContentPane().add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom, prenom, tel, specia, idR;
				nom = txtNom.getText();
				prenom = txtPrenom.getText();
				tel = txtTel.getText();
				specia = txtSpecial.getText();
				idR = idRe.getText();
				
				
				
				 try {
						pst = con.prepareStatement("update medecin set nom=?, prenom=?, telephone=?, specialiste=? where idMedecin = ?");
						pst.setString(1, nom);
						pst.setString(2, prenom);
						pst.setString(3, tel);
						pst.setString(4, specia);
						pst.setString(5, idR);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modification reussie !!!");
						table_load();
							           
						txtNom.setText("");
						txtPrenom.setText("");
						txtTel.setText("");
						txtSpecial.setText("");
						txtNom.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
			}
		});
		btnModifier.setBackground(new Color(255, 140, 0));
		btnModifier.setBorder(null);
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModifier.setBounds(141, 417, 109, 45);
		frame.getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String idR;

				idR = idRe.getText();
				
				
				
				 try {
						pst = con.prepareStatement("delete from medecin where idMedecin = ?");
						pst.setString(1, idR);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Suppressin reussie !!!");
						table_load();
							           
						txtNom.setText("");
						txtPrenom.setText("");
						txtTel.setText("");
						txtSpecial.setText("");
						txtNom.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
			}
		});
		btnSupprimer.setBackground(new Color(255, 0, 0));
		btnSupprimer.setBorder(null);
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupprimer.setBounds(260, 417, 109, 45);
		frame.getContentPane().add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard_admin retad = new Dashboard_admin();
				retad.Admin();
				frame.setVisible(false);
			}
		});
		lblNewLabel.setIcon(new ImageIcon("./images\\icons8_go_back_24px.png"));
		lblNewLabel.setBounds(10, 24, 35, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("./images\\icons8_multiply_32px_1.png"));
		lblNewLabel_1.setBounds(869, 10, 35, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}

}
