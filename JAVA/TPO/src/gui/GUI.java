package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class GUI {
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frmTpousugiSieciowe = new JFrame();
		frmTpousugiSieciowe.setTitle("TPO_uslugi_sieciowe");
		frmTpousugiSieciowe.setResizable(false);
		frmTpousugiSieciowe.setAlwaysOnTop(true);
		frmTpousugiSieciowe.setBounds(100, 100, 313, 135);
		frmTpousugiSieciowe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTpousugiSieciowe.getContentPane().setLayout(null);
		
		JTextField var_miasto = new JTextField();
		var_miasto.setBounds(106, 11, 111, 20);
		frmTpousugiSieciowe.getContentPane().add(var_miasto);
		var_miasto.setColumns(10);
		
		JTextField var_panstwo = new JTextField();
		var_panstwo.setBounds(106, 42, 111, 20);
		frmTpousugiSieciowe.getContentPane().add(var_panstwo);
		var_panstwo.setColumns(10);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.setBounds(227, 11, 67, 51);
		frmTpousugiSieciowe.getContentPane().add(btnNewButton);
		
		JTextField txtMiasto = new JTextField();
		txtMiasto.setText("Miasto");
		txtMiasto.setEditable(false);
		txtMiasto.setBounds(10, 11, 86, 20);
		frmTpousugiSieciowe.getContentPane().add(txtMiasto);
		txtMiasto.setColumns(10);
		
		JTextField txtPanstwo = new JTextField();
		txtPanstwo.setEditable(false);
		txtPanstwo.setText("Panstwo");
		txtPanstwo.setBounds(10, 42, 86, 20);
		frmTpousugiSieciowe.getContentPane().add(txtPanstwo);
		txtPanstwo.setColumns(10);
		
		JTextField txtKursPanstwaPowyzej = new JTextField();
		txtKursPanstwaPowyzej.setEditable(false);
		txtKursPanstwaPowyzej.setHorizontalAlignment(SwingConstants.CENTER);
		txtKursPanstwaPowyzej.setText("NazwaKodowa waluty");
		txtKursPanstwaPowyzej.setBounds(10, 73, 127, 20);
		frmTpousugiSieciowe.getContentPane().add(txtKursPanstwaPowyzej);
		txtKursPanstwaPowyzej.setColumns(10);
		
		JTextField textField = new JTextField();
		textField.setBounds(147, 73, 147, 20);
		frmTpousugiSieciowe.getContentPane().add(textField);
		textField.setColumns(10);
		
		frmTpousugiSieciowe.setVisible(true);
	}
}
