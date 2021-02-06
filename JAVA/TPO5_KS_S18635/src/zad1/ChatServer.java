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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ChatServer {

   ServerSocketChannel ssc = null;
     ExecutorService excServ = Executors.newCachedThreadPool();
   List<BencClient> chats = new ArrayList<>();

    Selector selector = null;
   String host;
  int port;
    public ChatServer(String host, int port) {
    	 this.port = port;
        this.host = host;
    }
    public void startServer()  {
        System.out.println("Server started");
        try {
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);

            ssc.socket().bind(new InetSocketAddress(host, port));
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch(Exception exc) {
            exc.printStackTrace();
         }
        Runnable runThisShit = () -> serviceConnections();
        excServ.execute(runThisShit);
    }


    public void stopServer() throws IOException {
    	System.out.println("\nServer stopped");
        ssc.close();
        excServ.shutdown();
    }
    boolean isRunning = true;
    private ByteBuffer bbuf = ByteBuffer.allocate(1024);
    Charset charset  = Charset.forName("ISO-8859-2");
    private StringBuffer reqString = new StringBuffer();
    private void serviceConnections() {

        while (isRunning) {
            try {
                selector.select();
                Set keys = selector.selectedKeys();
                Iterator iterator = keys.iterator();
                
                while (iterator.hasNext()) {
                    SelectionKey key = (SelectionKey) iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        SocketChannel cc = ssc.accept();
                        cc.configureBlocking(false);
                        cc.register(selector, SelectionKey.OP_READ);
                        continue;
                    }
                    if (key.isReadable()) {
                        SocketChannel cc = (SocketChannel) key.channel();
                        serviceRequest(cc);
                        continue;
                    }
                }
            } catch (Exception exc) {exc.printStackTrace();
                continue;
            }
        }
    }





   

    
    StringBuffer odpowiedzBogow = new StringBuffer();
    protected void serviceRequest(SocketChannel reqFromClient) {
        if (!reqFromClient.isOpen()) return;

        reqString.setLength(0);
        bbuf.clear();
        try {
            readLoop:
            while (true) {
                int n = reqFromClient.read(bbuf);
                if (n > 0) {
                    bbuf.flip();
                    CharBuffer cbuf = charset.decode(bbuf);
                    while(cbuf.hasRemaining()) {
                        char c = cbuf.get();
                        if (c == '\r' || c == '\n') break readLoop;
                        reqString.append(c);
                    }
                }
            }


            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
            String now = format.format(System.currentTimeMillis());

            String id = reqString.toString().split(" ")[0];

            if(reqString.toString().contains("login")) {
                chats.add(new BencClient(id));
                for (BencClient chat : chats) {
                    chat.sb.append(id + " logged in\n");
                }
                writeResp(reqFromClient, "\r");
                writeToLog(now + " " + id + " logged in");
            } else if(reqString.toString().contains("logout")){
                BencClient currentClient = null;
                for(BencClient chat : chats) {
                    chat.sb.append(id + " logged out\n");
                    if(chat.id.equals(id))
                        currentClient = chat;
                }

                writeResp(reqFromClient, currentClient.sb.toString() + "\r");
                writeToLog(now + " " + id + " logged out");
            } else {
                String message = reqString.toString().replaceAll(id + " ", "");
                for (BencClient chat : chats) {
                    chat.sb.append(id + ": " +  message + "\n");
                }
                writeResp(reqFromClient, "\r");
                writeToLog(now + " " + id + ": " + message);
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    private void writeResp(SocketChannel sc, String addMsg) throws IOException {
        odpowiedzBogow.setLength(0);
        odpowiedzBogow.append(addMsg);
        odpowiedzBogow.append('\n');
        ByteBuffer buf = charset.encode(CharBuffer.wrap(odpowiedzBogow));
        sc.write(buf);
    }
    StringBuffer serverLog = new StringBuffer();
    private void writeToLog(String message){
        serverLog.append(message + "\n");
    }

    int delay = 2;
    public String getServerLog() {
        Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), delay, TimeUnit.SECONDS);
        return serverLog.toString();
    }
}

