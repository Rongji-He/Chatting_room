import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final String host;

    public ClientHandler(Socket socket){
        this.socket = socket;
        host = this.socket.getInetAddress().getHostAddress();
    }
    @Override
    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String msg;
            while((msg = br.readLine())!=null){
                System.out.println(host+": "+msg);
            }
        } catch (IOException e) {

        }



    }


}
