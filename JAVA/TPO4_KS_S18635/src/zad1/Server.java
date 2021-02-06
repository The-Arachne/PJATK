/**
 *
 *  @author Kachniarz Sebastian S18635
 *
 */

package zad1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Server {

	
	HashMap<SocketChannel,StringBuilder>userLogs;
	ServerSocketChannel serverChannel;
	Selector selector;
	SelectionKey sscKey;
	boolean czyDziala;
	Thread a=null;
	public Server(String host, int port) throws IOException {
		userLogs=new HashMap<>();
		serverChannel=ServerSocketChannel.open();
		serverChannel.socket().bind(new InetSocketAddress(host, port));
		serverChannel.configureBlocking(false);
		selector=selector.open();
		sscKey=serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void startServer() {
		czyDziala=true;
		a=new Thread(()->{
			while(czyDziala){
				try {
					selector.select();
					Set keys=selector.selectedKeys();
					
					Iterator iter=keys.iterator();
					while(iter.hasNext()){
						SelectionKey key=(SelectionKey) iter.next();
						iter.remove();
						
						if(key.isAcceptable()){
							SocketChannel cc=serverChannel.accept();
							cc.configureBlocking(false);
							cc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
							
							continue;
						}
						if(key.isReadable()){
							SocketChannel cc=(SocketChannel) key.channel();
							serviceRequest(cc);
							continue;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});	
		a.start();
	}
	private String msg[] =
		{ "Ok", "Invalid request" };
	private Charset charset  = Charset.forName("UTF-8");
	private final int BSIZE = 1024;
	private ByteBuffer bbuf = ByteBuffer.allocate(BSIZE);
	private StringBuffer reqString = new StringBuffer();
	Time time=new Time();
	String log="";
	private void serviceRequest(SocketChannel sc) {
	    if (!sc.isOpen()) return;

	    reqString.setLength(0);
	    bbuf.clear();
	    try {
	      readLoop:                    
	      while (true) {               
	        int n = sc.read(bbuf);    
	        if (n > 0) {
	          bbuf.flip();
	          CharBuffer cbuf = charset.decode(bbuf);
	          while(cbuf.hasRemaining()) {
	            char c = cbuf.get();
	            if (c == '\r' || c == '\n') break readLoop;
	            else
	            reqString.append(c);
	          }
	        }
	      }
	      String[] req = reqString.toString().split(" ");
	      String cmd = req[0];
//	      System.out.println("REQUEST:\n"+reqString.toString());
	      String kto="";
	      if(userLogs.containsKey(sc))
	    	  kto=userLogs.get(sc).toString().split(" ")[1];
	      if (cmd.equals("bye")) { 
	    	  log+=kto+" logged out at "+LocalTime.now()+"\n";
	    	  userLogs.get(sc).append("\n=== "+kto+" log end ===\n");
	    	  if(req[1]!=null){
	    		  writeResp(sc,0,userLogs.get(sc).toString());
	    		  sc.close();
	    		  sc.socket().close();
	    	  }else{
	    		  writeResp(sc, 0, "logged out");          
		          sc.close();                      
		          sc.socket().close();
	    	  }
	    	  userLogs.remove(sc);
	          
	      }
	      else if (cmd.equals("login")) {
	        if(req[1]!=null){
	        	//users.put(sc, req[1]);
	        	userLogs.put(sc, new StringBuilder());
	        	userLogs.get(sc).append("=== "+req[1]+" log start ===\n");
	        	userLogs.get(sc).append("logged in\n");
	        	log+=req[1]+" logged in at "+LocalTime.now()+"\n";
	        	writeResp(sc, 0, "logged in");
	        }else
	        	writeResp(sc, 1, "invalid nick");
	      }
	      else if (cmd.contains("-"))  {
	    	if(req[0]!=null && req[1]!=null){
	    		String resp=time.passed(req[0], req[1]);
	    		//userLogs.get(sc).append("Request: "+req[0]+" "+req[1]+"\n");
	    		userLogs.get(sc).append("Result:\n"+resp);
	    		log+=kto+" request at "+LocalTime.now()+": \""+req[0]+" "+req[1]+"\""+"\n";
		        writeResp(sc, 0, resp);
	    	}else
	    		writeResp(sc, 1, "sth is missing");
	        
	        
	      }
	      else writeResp(sc, 1, "invalid request bitch");             

	    } catch (Exception exc) {                  
	        exc.printStackTrace();
	        try { sc.close();
	              sc.socket().close();
	        } catch (Exception e) {}
	    }
	  }
	  private StringBuffer remsg = new StringBuffer(); 

	  private void writeResp(SocketChannel sc, int rc, String addMsg)throws IOException {
	    remsg.setLength(0);
	    remsg.append(rc);
	    remsg.append(' ');
	    remsg.append(msg[rc]);
	    remsg.append('\n');
	    if (addMsg != null) {
	      remsg.append(addMsg);
	      //remsg.append('\n');
	    }
	    ByteBuffer buf = charset.encode(CharBuffer.wrap(remsg));
	    sc.write(buf);
	  }


	public void stopServer() {
		czyDziala=false;
		a.interrupt();
	}

	public char[] getServerLog() {

		return log.toCharArray();
	}
	
}
