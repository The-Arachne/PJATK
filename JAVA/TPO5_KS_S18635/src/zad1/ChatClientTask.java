/**
 *
 *  @author Kachniarz Sebastian S18635
 *
 */

package zad1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ChatClientTask extends FutureTask<ChatClient> {
    
	//zejfajny konstruktor
	public ChatClientTask(Callable<ChatClient> callable) {
        super(callable);
    }

    public static ChatClientTask create(ChatClient chatKlient, List<String> messages, int wait) {
       
    	return new ChatClientTask(() ->{
            chatKlient.login();
            if(wait != 0)
               Thread.sleep(wait);
            for (String message : messages){
                chatKlient.send(chatKlient.id + " " + message);
                if(wait != 0)
                    Thread.sleep(wait);
            }
            chatKlient.logout();
            if(wait != 0)
                Thread.sleep(wait);
            return chatKlient;
        });
    }


    public ChatClient getClient() {
        try {
            return this.get();
        } catch (Exception e) {
            return null;
        }
    }
}
