/**
 *
 *  @author Kachniarz Sebastian S18635
 *
 */

package zad1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main {
	  public static void main(String[] args) {
	    JFrame frmTpousugiSieciowe = new JFrame();
		frmTpousugiSieciowe.setTitle("TPO_uslugi_sieciowe");
		frmTpousugiSieciowe.setResizable(false);
		frmTpousugiSieciowe.setAlwaysOnTop(true);
		frmTpousugiSieciowe.setBounds(100, 100, 313, 135);
		frmTpousugiSieciowe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTpousugiSieciowe.getContentPane().setLayout(null);
		
		JTextField var_city = new JTextField("Warsaw");
		var_city.setBounds(106, 11, 111, 20);
		frmTpousugiSieciowe.getContentPane().add(var_city);
		var_city.setColumns(10);
		
		JTextField var_country = new JTextField("Poland");
		var_country.setBounds(106, 42, 111, 20);
		frmTpousugiSieciowe.getContentPane().add(var_country);
		var_country.setColumns(10);
		
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
		txtKursPanstwaPowyzej.setText("Przelicz na(kodWaluty)");
		txtKursPanstwaPowyzej.setBounds(10, 73, 127, 20);
		frmTpousugiSieciowe.getContentPane().add(txtKursPanstwaPowyzej);
		txtKursPanstwaPowyzej.setColumns(10);
		
		JTextField textField = new JTextField("USD");
		textField.setBounds(147, 73, 147, 20);
		frmTpousugiSieciowe.getContentPane().add(textField);
		textField.setColumns(10);
		
		frmTpousugiSieciowe.setVisible(true);
		
		btnNewButton.addActionListener(e->{
			tworz(var_country.getText(),var_city.getText(),textField.getText());
			frmTpousugiSieciowe.setVisible(false);
		});
	  }

	  private static void tworz(String coutnry, String city, String text3) {
		Service s = new Service(coutnry);
		String weatherJson = s.getWeather(city);
		Double rate1 = s.getRateFor(text3);
		Double rate2 = s.getNBPRate();
		System.out.println(weatherJson);
		System.out.println(rate1);
		System.out.println(rate2);
		
		JFrame frmTpousugiSieciowe = new JFrame();
		frmTpousugiSieciowe.setResizable(false);
		frmTpousugiSieciowe.setBounds(100, 100, 490, 207);
		frmTpousugiSieciowe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTpousugiSieciowe.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 464, 121);
		frmTpousugiSieciowe.getContentPane().add(textArea);
		textArea.setText(weatherJson+"\n"+rate1+"\n"+rate2);
		
		JButton btnNewButton = new JButton("Wiki");
		btnNewButton.addActionListener(e->{
			new FxWebWiew("https://en.wikipedia.org/wiki/Great_Britain"+coutnry);
		});
		btnNewButton.setBounds(10, 143, 152, 23);
		frmTpousugiSieciowe.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("One MORE time");
		btnNewButton_1.addActionListener(e->{
			frmTpousugiSieciowe.dispose();
			main(null);
		});
		btnNewButton_1.setBounds(322, 143, 152, 23);
		frmTpousugiSieciowe.getContentPane().add(btnNewButton_1);
		
		frmTpousugiSieciowe.setVisible(true);
	}
}
