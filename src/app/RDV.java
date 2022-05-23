package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RDV {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void rdvD() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RDV window = new RDV();
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
	public RDV() {
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
	    pst = con.prepareStatement("select * from rendezvous");
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
		
		JLabel lblNewLabel = new JLabel("Rendez-vous");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(392, 66, 156, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("./images\\icons8_multiply_32px_1.png"));
		lblNewLabel_1.setBounds(858, 26, 32, 32);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard_admin add1 = new Dashboard_admin();
				add1.Admin();
				
				frame.setVisible(false);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("./images\\icons8_go_back_24px.png"));
		lblNewLabel_2.setBounds(10, 26, 32, 32);
		frame.getContentPane().add(lblNewLabel_2);
		
		table = new JTable();
		table.setBounds(51, 119, 812, 404);
		frame.getContentPane().add(table);
	}
}
