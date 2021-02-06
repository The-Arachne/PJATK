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

public class ChatClient {

    SocketChannel channel;
    StringBuilder odpowiedzBogow = new StringBuilder();
    String host;
    String id;
    int port;
    public ChatClient(String host, int port, String id) {
        this.host = host;
        this.id = id;
        this.port = port;
    }
    public void login() {
        try {
            odpowiedzBogow.append("=== " + id + " chat view\n" );
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(host, port));
            while (!channel.finishConnect()) {
                try { Thread.sleep(150); } catch(Exception exc) { return; }
            }
            send(id + " login");
        } catch (Exception e) {e.printStackTrace();}
    }
    //stage 2 beka z typa
    ByteBuffer inBuf = ByteBuffer.allocate(1024);
    Charset charset  = Charset.forName("ISO-8859-2");
    StringBuffer request = new StringBuffer();

    public void send(String req) {
        StringBuffer result = new StringBuffer();
        try {
            request.setLength(0);
            if(!req.contains(id))
                request.append(id + " ");
            request.append(req);
            request.append('\n');

            ByteBuffer buf = charset.encode(CharBuffer.wrap(request));//kodowanie bo czm nie z wykladu
            channel.write(buf);

            if(req.contains("-"))
                odpowiedzBogow.append("\nRequest: " + req + "\n");
            while (true) {
                inBuf.clear();
                int read = channel.read(inBuf);
                if (read == 0) 
                    continue;
                else if (read == -1) {
                    channel.close();
                    break;}
                else {
                    inBuf.flip();
                    CharBuffer outBuff = charset.decode(inBuf);
                    //ok wszysktko w porzadku
                    while(outBuff.hasRemaining()) {
                        char c = outBuff.get();
                        if (c == '\r') break;
                        result.append(c);
                    }
                    break;}
            }
        } catch(Exception e){e.printStackTrace();}
        odpowiedzBogow.append(result.toString());
        //thasts it
    }
    public String getChatView() {
        return odpowiedzBogow.toString();
    }
    public void logout() throws IOException {
        send(  id+ " logout");
    }
}