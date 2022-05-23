package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ajout_patients {

	private JFrame frame;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JTextField txtTel;
	private JTextField txtRe;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void patients() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajout_patients window = new Ajout_patients();
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
	public Ajout_patients() {
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
	    pst = con.prepareStatement("select * from patient");
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
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(128, 0, 0));
		frame.setBounds(100, 100, 931, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Patients");
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(316, 43, 118, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(27, 187, 113, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nom");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_2.setBounds(27, 123, 93, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adresse");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_3.setBounds(27, 271, 113, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("T\u00E9l\u00E9phone");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_4.setBounds(27, 356, 149, 36);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Recheche");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_5.setBounds(474, 518, 137, 30);
		frame.getContentPane().add(lblNewLabel_5);
		
		txtNom = new JTextField();
		txtNom.setBounds(163, 133, 271, 19);
		frame.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(163, 200, 271, 19);
		frame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(163, 281, 271, 19);
		frame.getContentPane().add(txtAdresse);
		txtAdresse.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(163, 369, 271, 19);
		frame.getContentPane().add(txtTel);
		txtTel.setColumns(10);
		
		txtRe = new JTextField();
		txtRe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = txtRe.getText();

		                pst = con.prepareStatement("select nom,prenom,telephone,adresse from patient where idPatient = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		            	
		              
		                String nom = rs.getString(1);
		                String prenom = rs.getString(2);
		                String tel = rs.getString(3);
		                String adresse = rs.getString(4);
		                
		                

		                txtNom.setText(nom);
		                txtPrenom.setText(prenom);
		                txtTel.setText(tel);
		                txtAdresse.setText(adresse);
		                
		            }   
		            else
		            {
		                txtNom.setText("");
		                txtPrenom.setText("");
		                txtTel.setText("");
		                txtAdresse.setText("");
		                
		                 
		            }
		            


		        } 
			
			 catch (SQLException ex) {
		           
		        }
			}
		});
		txtRe.setBounds(621, 519, 258, 29);
		frame.getContentPane().add(txtRe);
		txtRe.setColumns(10);
		
		table = new JTable();
		table.setBounds(474, 77, 433, 376);
		frame.getContentPane().add(table);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom, prenom, tel, adresse;
				nom = txtNom.getText();
				prenom = txtPrenom.getText();
				tel = txtTel.getText();
				adresse = txtAdresse.getText();
				
				
				
				 try {
						pst = con.prepareStatement("insert into patient(nom,prenom,telephone,adresse)values(?,?,?,?)");
						pst.setString(1, nom);
						pst.setString(2, prenom);
						pst.setString(3, tel);
						pst.setString(4, adresse);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Insertion reussie !!!");
						table_load();
							           
						txtNom.setText("");
						txtPrenom.setText("");
						txtTel.setText("");
						txtAdresse.setText("");
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
				
			}
			
		});
		btnAjouter.setIcon(null);
		btnAjouter.setBackground(new Color(0, 128, 0));
		btnAjouter.setBorder(null);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBounds(10, 439, 130, 48);
		frame.getContentPane().add(btnAjouter);
		
		JButton btnModiffier = new JButton("Modifier");
		btnModiffier.setIcon(null);
		btnModiffier.setBackground(new Color(255, 140, 0));
		btnModiffier.setBorder(null);
		btnModiffier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom, prenom, tel, adresse, idRe;
				nom = txtNom.getText();
				prenom = txtPrenom.getText();
				tel = txtTel.getText();
				adresse = txtAdresse.getText();
				idRe = txtRe.getText();
				
				
				
				 try {
						pst = con.prepareStatement("update patient set nom=?, prenom=?, telephone=?, adresse=? where idPatient = ?");
						pst.setString(1, nom);
						pst.setString(2, prenom);
						pst.setString(3, tel);
						pst.setString(4, adresse);
						pst.setString(5, idRe);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modification reussie !!!");
						table_load();
							           
						txtNom.setText("");
						txtPrenom.setText("");
						txtTel.setText("");
						txtAdresse.setText("");
						txtNom.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
			}
		});
		btnModiffier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModiffier.setForeground(Color.WHITE);
		btnModiffier.setBounds(163, 439, 130, 48);
		frame.getContentPane().add(btnModiffier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String idR;

				idR = txtRe.getText();
				
				
				
				 try {
						pst = con.prepareStatement("delete from patient where idPatient = ?");
						pst.setString(1, idR);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Suppressin reussie !!!");
						table_load();
							           
						txtNom.setText("");
						txtPrenom.setText("");
						txtTel.setText("");
						txtAdresse.setText("");
						txtNom.requestFocus();
					   }
				 
					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}
			}
		});
		btnSupprimer.setIcon(null);
		btnSupprimer.setBackground(new Color(255, 0, 0));
		btnSupprimer.setBorder(null);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setBounds(316, 439, 142, 48);
		frame.getContentPane().add(btnSupprimer);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard_secretaire ret = new Dashboard_secretaire();
				ret.Secretaire();
				frame.setVisible(false);
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon("./images\\icons8_go_back_24px.png"));
		lblNewLabel_6.setBounds(10, 10, 44, 36);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_7.setIcon(new ImageIcon("./images\\icons8_multiply_32px_1.png"));
		lblNewLabel_7.setBounds(863, 10, 44, 38);
		frame.getContentPane().add(lblNewLabel_7);
	}
}
