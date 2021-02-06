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
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

	SocketChannel channel;
	String host;
	int port;
	String id;
	public Client(String host, int port, String id) {
		this.host=host;
		this.port=port;
		this.id=id;
	}

	public void connect() {
		try {
			channel=SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress(host, port));
			//System.out.println("Lacze sie.");
			while(!channel.finishConnect()){
				//System.out.print(".");
				//Thread.sleep(100);
			}
			//System.out.println("Polaczono");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Charset charset  = Charset.forName("UTF-8");
	  private ByteBuffer inBuf = ByteBuffer.allocateDirect(1024);
	  private Matcher matchCode =
	                 Pattern.compile("(0 Ok)|(1 Invalid request)").matcher("");
	public String send(String string) {
		
		if(!channel.isOpen() || !channel.isConnected())
			connect();
		
		CharBuffer cbuf=CharBuffer.wrap(string+"\n");
		ByteBuffer outBuf=charset.encode(cbuf);
		try {
			channel.write(outBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer result=new StringBuffer();
	      while (true) {
	        inBuf.clear();
	        int readBytes;
			try {
				readBytes = channel.read(inBuf);
				if (readBytes == 0) {
			          try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			          continue;
			        }
			        else if (readBytes == -1) {
			          channel.close();
			          break;
			        }
			        else {
			          inBuf.flip();
			          cbuf = charset.decode(inBuf);
			          result.append(cbuf);
			          
			         // System.out.println("SERVER RESPONSE:\n"+result.toString());
			          matchCode.reset(cbuf);
			          if (matchCode.find()){
			        	  break;
			          }
			        }
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	      }
		
		
		return result.substring(5);
	}
	
	
	
}
