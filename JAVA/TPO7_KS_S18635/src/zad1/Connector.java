package zad1;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JOptionPane;
import javax.jms.*;


public class Connector {
	
	/*TODO
		-chcialem jeszcze dodac kolor dla kazdego uzytkownika (przekazywany w nick-u ale potem usuwany) dla kazdego uzytkownika inny 
			i z mozliwoscia wybrania(color picker)
		-
	 * */
	
	private static TopicSubscriber sub;
	public static void main(String[] args) throws Exception {
		
		Context cntx = new InitialContext();
        TopicConnectionFactory factory = (TopicConnectionFactory) cntx.lookup("ConnectionFactory");
        final Topic top = (Topic) cntx.lookup("topic1");
        TopicConnection conn = factory.createTopicConnection();
        final TopicSession ses = conn.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);
        final TopicPublisher publsh = ses.createPublisher(top);
        sub = ses.createSubscriber(top);

        GUI gui=new GUI();
        estabilishInput(gui);
        conn.start();
        
        String tmpUs = JOptionPane.showInputDialog(gui.frame, "Default: Anon", "Daj jakis nick",JOptionPane.QUESTION_MESSAGE);
        final String user=(tmpUs==null ||tmpUs.length()<1)?"Anon":tmpUs;
        gui.frame.setTitle("Chat: "+user);
        
        //ustawiamy listenera na przycisk
        gui.btnNewButton.addActionListener(e -> {
            String doWyslania = gui.textArea.getText();
            if (doWyslania.length() > 0) {
            	System.out.println(doWyslania);
                try {
                    TextMessage txtMss = ses.createTextMessage(doWyslania);
                    txtMss.setStringProperty("wysylajacy", user);
                    publsh.publish(txtMss);
                    gui.textArea.setText("");
                    System.out.println("Wyslano w swiat!");
                } catch (JMSException ex) {ex.printStackTrace();}
            }
        });
        
		
	}
	private static void estabilishInput(GUI gui) throws JMSException {
        sub.setMessageListener(e -> {
            try {
                TextMessage trescWiadomosci = (TextMessage) e;
                gui.textArea_1.append(trescWiadomosci.getStringProperty("wysylajacy") + ": " + trescWiadomosci.getText() + "\n");
            } catch (Exception ex) {ex.printStackTrace();}
        });
    }

}
