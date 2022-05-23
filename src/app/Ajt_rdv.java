package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import com.sun.org.apache.xpath.internal.operations.And;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ajt_rdv {

	private JFrame frame;
	private JTable table;
	private JTextField txtDetail;
	private JTextField txtRe;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void Rdv() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajt_rdv window = new Ajt_rdv();
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
	public Ajt_rdv() {
		initialize();
		connect();
		table_load();
		loadP();
		loadM();
		
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtDate;
	
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
	
	public void loadP()
	{
		try 
    	{
	    pst = con.prepareStatement("select * from patient");
	    rs = pst.executeQuery();
	    while(rs.next())
	    {
	    	comboBox.addItem(rs.getString("idPatient"));
	    }
	    
    	} 
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
    	} 
	}
	 
	
	public void loadM()
	{
		try 
    	{
	    pst = con.prepareStatement("select * from medecin");
	    rs = pst.executeQuery();
	    while(rs.next())
	    {
	    	comboBox_1.addItem(rs.getString("idMedecin"));
	    }
	    
    	} 
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
    	} 
	}
	
	
	
	public void table_load()
    {
    	try 
    	{
	    pst = con.prepareStatement("select * from rendezVous");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
    	} 
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
    	} 
    }
	
	public static boolean isValidDate( String date ) {              // jj/mm/aaaa    jj/mm/aa
        String regExp = "^\\d\\d/\\d\\d/\\d\\d(\\d\\d)?$";
        // ou String redExp = "^[0-9]{2}/[0-9]{2}/([0-9]{2})?[0-9]{2}$";
        return date.matches( regExp );
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 931, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 140, 0));
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 0, 931, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rendez vous");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setBounds(138, 23, 215, 57);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(44, 146, 45, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Detail");
		lblNewLabel_1_1.setForeground(new Color(255, 140, 0));
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(44, 206, 101, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("IdPatient");
		lblNewLabel_1_2.setForeground(new Color(255, 140, 0));
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_2.setToolTipText("IdMedecin");
		lblNewLabel_1_2.setBounds(44, 271, 101, 48);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("idMedecin");
		lblNewLabel_1_3.setForeground(new Color(255, 140, 0));
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(44, 336, 101, 38);
		panel.add(lblNewLabel_1_3);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				
				
				String dateR, detail, idP, idM;
				
				dateR = txtDate.getText();
				detail = txtDetail.getText();
				idP = comboBox.getSelectedItem().toString();
				idM = comboBox_1.getSelectedItem().toString();
				
				
				if (isValidDate(dateR)) {
					try {
						
						pst = con.prepareStatement("insert into rendezvous(dateRV,detailsRV,idPatient,idMedecin)values(?,?,?,?)");
						pst.setString(1, dateR);
						pst.setString(2, detail);
						pst.setString(3, idP);
						pst.setString(4, idM);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Insertion reussie !!!");
						table_load();
							           
						//dateChooser.setDateFormatString("");
						txtDetail.setText("");
						comboBox.setToolTipText("");
						comboBox_1.setToolTipText("");
					   }
				 
						catch (SQLException e1) 
					        {
							e1.printStackTrace();
					        }
				}
				else {
					JOptionPane.showMessageDialog(null, "Le format de date ne correspond pas !!");
				}
					 
					
				
				 
			}
		});
		btnAjouter.setIcon(null);
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAjouter.setBackground(new Color(0, 153, 51));
		btnAjouter.setBorder(null);
		btnAjouter.setBounds(24, 449, 123, 48);
		panel.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dateR, detail, idP, idM, idR;
				
				dateR = txtDate.getText();
				detail = txtDetail.getText();
				idP = comboBox.getSelectedItem().toString();
				idM = comboBox_1.getSelectedItem().toString();
				idR = txtRe.getText();

				
				if (isValidDate(dateR)) {
					try {
						
						//update utilisateurs set identifiant=?, nom=?, prenom=?, mot_de_passe=?, type_utilisateur=? where idUtilisateurs = ?
						
						pst = con.prepareStatement("update rendezvous set dateRV=?, detailsRV=?, idPatient=?, idMedecin=? where idRV = ?");
						pst.setString(1, dateR);
						pst.setString(2, detail);
						pst.setString(3, idP);
						pst.setString(4, idM);
						pst.setString(5, idR);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modification reussie !!!");
						table_load();
							           
						//dateChooser.setDateFormatString("");
						txtDate.setText("");
						txtDetail.setText("");
						comboBox.setToolTipText("");
						comboBox_1.setToolTipText("");
						txtDate.requestFocus();
					   }
				 
						catch (SQLException e1) 
					        {
							e1.printStackTrace();
					        }
				}
				else {
					JOptionPane.showMessageDialog(null, "Le format de date ne correspond pas !!");
				}

				
				
			}
		});
		btnModifier.setIcon(null);
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnModifier.setBorder(null);
		btnModifier.setBackground(new Color(255, 140, 0));
		btnModifier.setBounds(171, 449, 123, 48);
		panel.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Re;
				Re = txtRe.getText();
				try {
					
					//update utilisateurs set identifiant=?, nom=?, prenom=?, mot_de_passe=?, type_utilisateur=? where idUtilisateurs = ?
					
					pst = con.prepareStatement("delete from rendezvous where idRV = ?");
					pst.setString(1, Re);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Suppression reussie !!!");
					table_load();
						           
					//dateChooser.setDateFormatString("");
					txtDate.setText("");
					txtDetail.setText("");
					comboBox.setToolTipText("");
					comboBox_1.setToolTipText("");
					txtDate.requestFocus();
				   }
			 
					catch (SQLException e1) 
				        {
						e1.printStackTrace();
				        }
				
			}
		});
		btnSupprimer.setIcon(null);
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupprimer.setBorder(null);
		btnSupprimer.setBackground(new Color(255, 0, 0));
		btnSupprimer.setBounds(325, 449, 162, 48);
		panel.add(btnSupprimer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(497, 71, 419, 410);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtDetail = new JTextField();
		txtDetail.setBounds(204, 218, 260, 19);
		panel.add(txtDetail);
		txtDetail.setColumns(10);
		
		txtRe = new JTextField();
		txtRe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = txtRe.getText();

		                pst = con.prepareStatement("select dateRv,detailsRV,idPatient,idMedecin from rendezvous where idRV = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		            	
		              
		                String dateR = rs.getString(1);
		                String detail = rs.getString(2);
		                String idpa = rs.getString(3);
		                String idmed = rs.getString(4);

		                txtDate.setText(dateR);
		                txtDetail.setText(detail);
		                comboBox.setToolTipText(idpa);
		                comboBox_1.setToolTipText(idmed);
		                
		            }   
		            else
		            {
		            	 txtDate.setText("");
			             txtDetail.setText("");
			             comboBox.setToolTipText("");
			             comboBox_1.setToolTipText("");
		                
		                 
		            }
		        } 
			 catch (SQLException ex) {
		           
		        }
			}
		});
		txtRe.setBounds(597, 527, 277, 38);
		panel.add(txtRe);
		txtRe.setColumns(10);
		
		JLabel lblRecherche = new JLabel("Recherche");
		lblRecherche.setForeground(new Color(255, 140, 0));
		lblRecherche.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblRecherche.setBounds(419, 510, 155, 57);
		panel.add(lblRecherche);
		
		comboBox = new JComboBox();
		comboBox.setBounds(204, 287, 260, 21);
		panel.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(204, 347, 260, 21);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Dashboard_secretaire retD = new Dashboard_secretaire();
				retD.Secretaire();
				frame.setVisible(false);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("./images\\icons8_go_back_24px.png"));
		lblNewLabel_2.setBounds(10, 10, 51, 70);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("./images\\icons8_multiply_32px_1.png"));
		lblNewLabel_3.setBounds(872, 10, 32, 35);
		panel.add(lblNewLabel_3);
		
		txtDate = new JTextField();
		txtDate.setBounds(204, 145, 260, 19);
		panel.add(txtDate);
		txtDate.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("format jj/mm/aaaa");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(204, 161, 149, 19);
		panel.add(lblNewLabel_4);
	}
}
