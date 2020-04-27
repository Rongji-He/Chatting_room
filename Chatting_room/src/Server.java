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

            System.out.println("Initializing server...");
            server=new ServerSocket(7007);
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
                ClientHandler ch = new ClientHandler(socket);
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
