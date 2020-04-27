import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class ClientHandler implements Runnable{

    private final Socket socket;
    private final String host;
    private PrintWriter pw;
    private  List<PrintWriter> boardCast ;

    public ClientHandler(Socket socket, List<PrintWriter> boardCast){
        this.socket = socket;
        host = this.socket.getInetAddress().getHostAddress();
        this.boardCast = boardCast;
    }
    @Override
    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            pw = new PrintWriter(bw, true);
            synchronized (boardCast){
                boardCast.add(pw);
                System.out.println(host+" joined the chat room. Currently "+boardCast.size()+" is in the room.");
            }


            String msg;

            while((msg = br.readLine())!=null){
                System.out.println(host+": "+msg);
                synchronized (boardCast){

                    for (PrintWriter printWriter : boardCast) {
                        printWriter.println(host + ": " + msg);
                    }
                }

            }
        } catch (IOException ignored) {

        }finally {
            synchronized (boardCast){
                boardCast.remove(pw);
                System.out.println(host+" left the chat room. Currently "+boardCast.size()+" is in the room.");

            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public PrintWriter getPw(){
        return this.pw;
    }

}
