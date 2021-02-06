import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Panel;
import java.awt.Color;
import java.awt.TextField;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;

public class GUI {

	private JFrame frame;
	private JTable table;
	private JTextField txtLivingCells;
	private JTextField txtDeadCells;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUI window = new GUI();
	}
	public GUI() {
		try {
			initialize();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	boolean[][] matryca = new boolean[47][90];
	private void initialize() throws InvocationTargetException, InterruptedException {
		frame = new JFrame("Średnio wydajna Jafka s18635");
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		
		TextField textField = new TextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		
		JButton btnStart = new JButton("start");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		
		JTextPane txtpnLifeSupportConditions = new JTextPane();
		txtpnLifeSupportConditions.setEditable(false);
		txtpnLifeSupportConditions.setText("Life support\r\nconditions");
		
		txtLivingCells = new JTextField();
		txtLivingCells.setEditable(false);
		txtLivingCells.setText("Living cells");
		txtLivingCells.setColumns(10);
		
		txtDeadCells = new JTextField();
		txtDeadCells.setEditable(false);
		txtDeadCells.setText("Dead cells");
		txtDeadCells.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("2, 3");
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("3");
		textField_3.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
								.addComponent(btnStart, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtpnLifeSupportConditions, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtLivingCells, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtDeadCells, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnLifeSupportConditions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(txtLivingCells, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtDeadCells, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		panel.setLayout(gl_panel);
		
		Panel panel_1 = new Panel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GRAY);
		panel_1.add(panel_5, BorderLayout.EAST);
		
		
		
		
		ModelTable model = new ModelTable(matryca);
		table = new JTable();
		table.setTableHeader(null);
		table.setModel(model);
		table.setDefaultRenderer(Boolean.class, new RenderTable(model));
		table.setCellSelectionEnabled(true);
		panel_1.add(table, BorderLayout.CENTER);
		
		frame.setVisible(true);

		
	    btnStart.addActionListener(e->{
	    	table.setCellSelectionEnabled(false);
	    	btnStart.setText((btnStart.getText().equals("start"))?"stop":"start");
	    });
	    model.fireTableDataChanged();
	    int[] i=new int[1];
	    MachinaGameOfLife advancer = new MachinaGameOfLife(matryca,this);
	    Thread xd=new Thread(()->{
		    while (true) {
		    	System.out.println("");
		    	if(btnStart.getText().equals("stop")){
		    		i[0]++;
		    		textField.setText("tura: "+i[0]);
		    		  matryca = advancer.przelicz(matryca);
				      try {
						SwingUtilities.invokeAndWait(() -> {
						    model.setNewMatryca(matryca);
						    table.repaint();
						  });
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				    }
		    	}
	    });
	    xd.start();
	}
	public ArrayList<Integer> getZyjace() {
		ArrayList<Integer>res=new ArrayList<>();
		Scanner scan=new Scanner(textField_2.getText().replaceAll(",", " "));
		while(scan.hasNext())
			res.add(Integer.parseInt(scan.next()));
		return res;
	}
	public ArrayList<Integer> getNiezyjace() {
		ArrayList<Integer>res=new ArrayList<>();
		Scanner scan=new Scanner(textField_3.getText().replaceAll(",", " "));
		while(scan.hasNext())
			res.add(Integer.parseInt(scan.next()));
		return res;
	}
}
