package adminClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminClientView {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminClientView window = new AdminClientView();
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
	public AdminClientView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setBounds(100, 100, 450, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPanelAdministratora = new JLabel("Panel Administratora");
		lblPanelAdministratora.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelAdministratora.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPanelAdministratora.setBounds(20, 11, 385, 50);
		frame.getContentPane().add(lblPanelAdministratora);
		
		JLabel lblNewLabel = new JLabel("Dodaj u\u017Cytkownika");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(108, 78, 205, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(105, 127, 33, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(162, 127, 100, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHaso.setHorizontalAlignment(SwingConstants.CENTER);
		lblHaso.setBounds(91, 164, 60, 14);
		frame.getContentPane().add(lblHaso);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 158, 100, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblDodajUytkownika = new JLabel("Dodaj u\u017Cytkownika do grupy");
		lblDodajUytkownika.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDodajUytkownika.setBounds(108, 240, 209, 38);
		frame.getContentPane().add(lblDodajUytkownika);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(105, 289, 46, 26);
		frame.getContentPane().add(lblLogin);
		
		textField_1 = new JTextField();
		textField_1.setBounds(162, 294, 100, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNazwaGrupy = new JLabel("Nazwa grupy");
		lblNazwaGrupy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNazwaGrupy.setBounds(52, 320, 86, 26);
		frame.getContentPane().add(lblNazwaGrupy);
		
		textField_2 = new JTextField();
		textField_2.setBounds(162, 325, 100, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Zatwierdz u\u017Cytkownika");
		btnNewButton.setBounds(123, 189, 171, 32);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Dodaj u\u017Cytkownika do grupy");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(123, 376, 171, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		
	}
}
