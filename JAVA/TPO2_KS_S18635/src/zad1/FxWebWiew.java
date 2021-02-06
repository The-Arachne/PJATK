package zad1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FxWebWiew extends JFXPanel {
	String xd;
	WebEngine engine;
	public FxWebWiew(String coutnry) {
		 WebView view = new WebView();
		xd=coutnry;
		Platform.runLater(new Runnable() {
	        @Override public void run() {
	           
	            engine = view.getEngine();
	            engine.load(xd);
	            
	        }
	      });
		this.setScene(new Scene(view));
	}
	
	
	
}
