import java.net.*;
import java.io.*;

public class CovidServer {
    
    //private ServerSocket serverS;
    //private Socket cSocket;

    //UDP
    DatagramSocket clientUDP;
    byte recBuffer[] = new byte[65535];
    DatagramPacket serverRec = null;

    private int serverPort = 8080;
    String fileName = "tracer.log";
    String str = "'\n'";

    boolean outputClosed = false;
    
	static BufferedWriter out;

    public CovidServer() {

        try {
            out = new BufferedWriter(new FileWriter(fileName, true));
        }

        catch (IOException i) {
            System.out.println(i);
        }

        try {
            clientUDP = new DatagramSocket(serverPort);
            clientUDP.setSoTimeout(15000);
            serverRec = new DatagramPacket(recBuffer, recBuffer.length);
            System.out.println("Server started, now waiting for client...");
            
            printTitle(fileName);
            clientUDP.receive(serverRec);
            System.out.println("Client online: they sent their first datagram!");

            while (true) {

                try {
                    try {
                        clientUDP.receive(serverRec);
                    }

                    catch (SocketTimeoutException s) {   
                        System.out.println("Client timed out after 15 seconds of idle time.");
                        out.close();
                        clientUDP.close();
                        System.exit(1);
                    }

                    //if output is to be closed
                    if (data(recBuffer).toString().equals("Close.")) {
                        System.out.println("Client sent their last datagram! Commencing server shutdown.");
                        out.close();
                        clientUDP.close();
                        System.exit(1);
                    }
                    
                    //if output is open and we received to close
                    else if (data(recBuffer).toString().equals("Close.") && outputClosed == false) {
                        out.close();
                        outputClosed = true;
                        System.out.println("Client finished simulation!");
                    }
        
                    InetAddress clientAddr = serverRec.getAddress();    // ID of User
                    String clientInput = ""; // timestamp, x, y, status
                    clientInput = data(recBuffer).toString();
                    str = null;
                    str = "'\n'";
                    str = clientAddr.toString();

                    // need to output to file here instead of print
                    str = str + ", " + clientInput;
                    appendStrToFile(fileName, str);                   
                }

                catch (IOException i) {
                    System.out.println(i);
                }
            }
        }

        catch (IOException i) {
            System.out.println(i);
        }
    }

    public static int CountSimulation(String fileName) {
        int count = 0;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                if (line == "--- New Covid Tracer Simulation ---")
                    count++;
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error" + e.getMessage());
        }
        return count;
    }

    public static void appendStrToFile(String fileName, String str) {
        try {
            out.write(str);
        }

        catch (IOException e) {
            System.out.println("Exception occured" + e);
        }
    }

    public static void printTitle(String fileName) {
        try {
            out.write("--- New Covid Tracer Simulation --- \n Simluation #: " + CountSimulation(fileName) + "\n");
        } 
        
		catch (IOException e) { 
			System.out.println("Exception occured" + e); 
		} 
	}

    public static StringBuilder data(byte[] a) 
    { 
        if (a == null) 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) 
        { 
            ret.append((char) a[i]); 
            i++; 
        } 
        return ret; 
    } 

    public static void main(String[] args) {
        CovidServer CS = new CovidServer();
    }
}






















