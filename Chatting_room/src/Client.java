import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Client host
 *
 *
 */


public class Client {
    private Socket socket;

    public Client()  {
        //use ipconfig in cmd to acquire IP address
        try {
            System.out.println("Connecting to Server...");
            socket = new Socket("localhost",7007);
        } catch (IOException e) {
            System.out.println("Error");
        }

        System.out.println("Clint has connected to Server");
    }

    public void start(){
        try {
            OutputStream os =socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw,true);

            pw.println("hello!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Client client = new Client();
    }
}
