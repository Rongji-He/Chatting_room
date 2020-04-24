import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server host
 *
 *
 */


public class Server {

    private ServerSocket server;
    public Server() {


        try {
            System.out.println();
            server=new ServerSocket(7007);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){
        try {
            System.out.println("Server is listening");
            Socket socket = server.accept();

            System.out.println("Received one client connection.");

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);


            System.out.println(br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Socket accept(){

        return null;
    }
    public static void main(String[] args) {
        Server server= new Server();
        server.start();
    }
}
