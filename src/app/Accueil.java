package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;
import com.sun.java.swing.plaf.windows.resources.windows;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Point;

public class Accueil {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil window = new Accueil();
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
	public Accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(128, 0, 0));
		frame.setBounds(100, 100, 931, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton btnAuth = new JButton("Entrer");
		btnAuth.setVerifyInputWhenFocusTarget(false);
		btnAuth.setBackground(new Color(255, 165, 0));
		btnAuth.setBorder(null);
		btnAuth.setFont(new Font("Arial", Font.BOLD, 30));
		btnAuth.setForeground(new Color(128, 0, 0));
		btnAuth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// acces a la page admin
				Login lg = new Login();
				lg.logU();
				frame.setVisible(false);
			}
		});
		btnAuth.setBounds(331, 256, 243, 107);
		frame.getContentPane().add(btnAuth);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(0, 48, 434, 63);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("GESTION H\u00D4PITAL");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_7.setForeground(new Color(255, 140, 0));
		lblNewLabel_7.setBounds(10, 0, 401, 58);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\OUSSEINI\\Documents\\java_workspace\\gestion_hospital\\images\\icons8_multiply_32px_1.png"));
		lblNewLabel.setBounds(889, 10, 32, 41);
		frame.getContentPane().add(lblNewLabel);
	}
}
