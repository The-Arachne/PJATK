package proj;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	TxtParser txtpars=null;
	ArrayList<Perceptron> perceptrons=null;
	public GUI(TxtParser txtpars, ArrayList<Perceptron> perceptrons) {
		this.perceptrons=perceptrons;
		this.txtpars=txtpars;
		initialize();
		for(Perceptron pr:perceptrons)
			System.out.println(pr);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 426, 263);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		textField = new JTextField();
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Okejka");
		panel.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		frame.setVisible(true);
		btnNewButton.addActionListener(e->{
			System.out.println();
			String result="";
			String latin=txtpars.getLatinoAlphabet(textArea.getText());
			//System.out.println(latin);
			ArrayList<Double>tmp=txtpars.countLetters(latin,null).list;
			//System.out.println(tmp);
			double max=0;
			for(Perceptron xd:perceptrons){
				double bec=xd.CheckAnswer(tmp);
				System.out.println(xd.name+" | "+bec );
				if(bec>max){
					max=bec;
					result=xd.name;
				}
			}
			textField.setText("");
			textField.setText(result);
		});
	}

}
