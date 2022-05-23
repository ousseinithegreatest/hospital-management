package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard_secretaire {

	private JFrame frame;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void Secretaire() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_secretaire window = new Dashboard_secretaire();
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
	public Dashboard_secretaire() {
		initialize();
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
		
		JLabel lblNewLabel = new JLabel("TABLEAU DE BORD");
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(315, 20, 291, 62);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Patients");
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajout_patients ajp = new Ajout_patients();
				ajp.patients();
				frame.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon("./images/icons8_team_40px_1.png"));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(338, 154, 247, 62);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Déconnecté");
				Login logS = new Login();
				logS.logU();
				frame.setVisible(false);
				
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("./images\\icons8_shutdown_48px.png"));
		lblNewLabel_2.setBounds(848, 20, 48, 62);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 165, 0));
		panel.setBounds(215, 117, 492, 316);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnRendezVous = new JButton("Rendez vous");
		btnRendezVous.setForeground(new Color(255, 165, 0));
		btnRendezVous.setBorder(null);
		btnRendezVous.setBackground(new Color(128, 0, 0));
		btnRendezVous.setBounds(123, 175, 247, 62);
		panel.add(btnRendezVous);
		btnRendezVous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajt_rdv ajr = new Ajt_rdv();
				ajr.Rdv();
				frame.setVisible(false);
			}
		});
		btnRendezVous.setIcon(new ImageIcon("./images/icons8_bulleted_list_40px.png"));
		btnRendezVous.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("./images\\icons8_multiply_32px_1.png"));
		lblNewLabel_3.setBounds(859, 34, 37, 48);
		frame.getContentPane().add(lblNewLabel_3);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}



}
