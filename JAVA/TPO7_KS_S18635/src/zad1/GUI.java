package zad1;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;

public class GUI {
	JFrame frame;
	JTextArea textArea;
	JButton btnNewButton;
	JTextArea textArea_1;
	
	public GUI() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		panel_4.add(textArea);
		
		btnNewButton = new JButton("SEND mssg");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		panel_4.add(btnNewButton, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GRAY);
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_4.add(panel_6, BorderLayout.WEST);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		JScrollPane jp=new JScrollPane(textArea_1);
		panel.add(jp, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

}
