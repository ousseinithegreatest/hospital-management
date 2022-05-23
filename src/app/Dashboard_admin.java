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

public class Dashboard_admin {

	private JFrame frame;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	/**
	 * Launch the application.
	 */
	public static void Admin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_admin window = new Dashboard_admin();
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
	public Dashboard_admin() {
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
		
		JButton btnNewButton = new JButton("Ajouter utilisateur");
		btnNewButton.setForeground(new Color(255, 165, 0));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ajout_utilisateur u = new Ajout_utilisateur();
				//u.ajt_u();
				Ajout_utilisateurs ajout = new Ajout_utilisateurs();
				
				ajout.user();
				frame.setVisible(false);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("./images/icons8_team_40px_1.png"));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(359, 116, 247, 62);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSecretaire = new JButton("Patient");
		btnSecretaire.setForeground(new Color(255, 165, 0));
		btnSecretaire.setBorder(null);
		btnSecretaire.setBackground(new Color(128, 0, 0));
		btnSecretaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Detail_patient dpat = new Detail_patient();
				dpat.DPatient();
				frame.setVisible(false);
				
			}
		});
		btnSecretaire.setIcon(new ImageIcon("./images\\icons8_woman_profile_40px_1.png"));
		btnSecretaire.setFont(new Font("Arial", Font.BOLD, 20));
		btnSecretaire.setBounds(359, 205, 247, 62);
		frame.getContentPane().add(btnSecretaire);
		
		JButton btnDocteurs = new JButton("Docteurs");
		btnDocteurs.setForeground(new Color(255, 165, 0));
		btnDocteurs.setBorder(null);
		btnDocteurs.setBackground(new Color(128, 0, 0));
		btnDocteurs.setIcon(new ImageIcon("./images\\icons8_medical_doctor_40px.png"));
		btnDocteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajout_docteurs dd = new Ajout_docteurs();
				dd.doc();
				frame.setVisible(false);
				
			}
		});
		btnDocteurs.setFont(new Font("Arial", Font.BOLD, 20));
		btnDocteurs.setBounds(359, 296, 247, 62);
		frame.getContentPane().add(btnDocteurs);
		
		JButton btnRendezVous = new JButton("Rendez vous");
		btnRendezVous.setForeground(new Color(255, 165, 0));
		btnRendezVous.setBorder(null);
		btnRendezVous.setBackground(new Color(128, 0, 0));
		btnRendezVous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RDV rdv = new RDV();
				rdv.rdvD();
				frame.setVisible(false);
			}
		});
		btnRendezVous.setIcon(new ImageIcon("./images\\icons8_bulleted_list_40px.png"));
		btnRendezVous.setFont(new Font("Arial", Font.BOLD, 20));
		btnRendezVous.setBounds(359, 382, 247, 62);
		frame.getContentPane().add(btnRendezVous);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Déconnecté");
				//System.exit(0);
				Login logA = new Login();
				logA.logU();
				frame.setVisible(false);
				
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("./images\\icons8_shutdown_48px.png"));
		lblNewLabel_2.setBounds(848, 20, 48, 62);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 165, 0));
		panel.setBounds(200, 87, 561, 463);
		frame.getContentPane().add(panel);
	}

	public void setVisble(boolean b) {
		// TODO Auto-generated method stub
		
	}



}
