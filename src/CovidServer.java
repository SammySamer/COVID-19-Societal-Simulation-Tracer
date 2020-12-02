import java.net.*;
import java.io.*;

public class CovidServer {
    
    //private ServerSocket serverS;
    //private Socket cSocket;
    private DataInputStream in;

    //UDP
    DatagramSocket clientUDP;
    byte recBuffer[] = new byte[65535];
    DatagramPacket serverRec = null;

    private int serverPort = 8080;
    String fileName = "tracer.log";
    String str = "";
    
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
            serverRec = new DatagramPacket(recBuffer, recBuffer.length);
            System.out.println("Server started, now waiting for client...");

            clientUDP.receive(serverRec);
            System.out.println("Client online: they sent their first datagram!");

            printTitle(fileName);

            InetAddress clientAddr = serverRec.getAddress();    // ID of User


            // once it gets the string "Close.", it'll start closing the server and server's socket, 
            // otherwise keep running
            while (!data(recBuffer).toString().equals("Close.")) {

                try {
                    String clientInput = ""; // timestamp, x, y, status
                    clientInput = data(recBuffer).toString();
                    str = clientAddr.toString();

                    // need to output to file here instead of print
                    str = str + ", " + clientInput;
                    appendStrToFile(fileName, str);
                    
                    clientUDP.receive(serverRec);
                }

                catch (IOException i) {
                    System.out.println(i);
                }
            }
            
            // close all connections
            //cSocket.close();
            //serverS.close();
            in.close();
            out.close();

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






















