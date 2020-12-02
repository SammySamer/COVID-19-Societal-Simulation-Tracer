import java.net.*;
import java.io.*;

public class CovidServer {
    
    private ServerSocket serverS;
    private Socket cSocket;
    private DataInputStream in;

    private int serverPort = 8080;

    public CovidServer() {
        
        try {
            serverS = new ServerSocket(serverPort);
            System.out.println("Server started, now waiting for client..."); 

            cSocket = serverS.accept();
            System.out.println("Client connected!"); 

            in = new DataInputStream(new BufferedInputStream(cSocket.getInputStream()));

            String clientInput = "";                            //timestamp, x, y, status
            InetAddress clientAddr = cSocket.getInetAddress();  //ID of User
            //final string: clientAddr + ", " + clientInput

            //once it gets the string "Close.", it'll start closing the server, otherwise keep running
            while (!clientInput.equals("Close.")) {
                
                try { 
                    clientInput = in.readUTF(); 
                    
                    //NOBNA
                    //need to output to file here instead of print
                    System.out.println(clientInput); 
                } 

                catch(IOException i) { 
                    System.out.println(i); 
                } 

                //close all connections 
                cSocket.close(); 
                serverS.close(); 
                in.close(); 

            }
        }

        catch (IOException i) {
            System.out.println(i);
        }
    }
}
