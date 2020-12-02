import java.net.*;
import java.io.*;

public class CovidServer {
    
    private ServerSocket serverS;
    private Socket sSocket;
    private DataInputStream in;

    private int serverPort = 8080;

    public CovidServer() {
        
        try {
            serverS = new ServerSocket(serverPort);
            System.out.println("Server started, now waiting for client..."); 

            sSocket = serverS.accept();
            System.out.println("Client connected!"); 

            in = new DataInputStream(new BufferedInputStream(sSocket.getInputStream()));

            String clientInput = "";

            //once it gets "Close.", it'll start closing the server, otherwise keep running
            while (!clientInput.equals("Close.")) {
                
            }
        }

        catch (IOException i) {
            System.out.println(i);
        }
    }
}
