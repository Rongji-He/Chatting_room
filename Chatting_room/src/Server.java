import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Server host
 *
 *
 */


public class Server {

    private ServerSocket server;

    private List<PrintWriter> boardCast ;
    public Server() {


        try {

            System.out.println("Initializing server...");
            server=new ServerSocket(7007);
            boardCast = new LinkedList<>();
            System.out.println("Server is ready.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){
        try {
            while (true) {
                System.out.println("Server is listening");
                Socket socket = server.accept();

                System.out.println("One client has connected.");

                ClientHandler ch = new ClientHandler(socket, boardCast);
                Thread thread = new Thread(ch);
                thread.start();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Server server= new Server();
        server.start();
    }
}
