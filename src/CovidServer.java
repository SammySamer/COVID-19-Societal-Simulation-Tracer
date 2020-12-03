import java.net.*;
import java.io.*;

public class CovidServer {
    
    //UDP
    DatagramSocket clientUDP;
    byte recBuffer[] = new byte[65535];
    DatagramPacket serverRec = null;

    private int serverPort = 8080;
    String fileName = "tracer.log";
    static String str = "";

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
            clientUDP.setSoTimeout(25000);
            serverRec = new DatagramPacket(recBuffer, recBuffer.length);
            System.out.println("Server started, now waiting for client...");

            clientUDP.receive(serverRec);
            System.out.println("Client online: they sent their first datagram!");

            InetAddress clientAddr = serverRec.getAddress(); // hostname/IP of server
            str = clientAddr.toString();
            printTitle(fileName);
            
            String clientInput = new String(serverRec.getData(), 0, serverRec.getLength()); // ID, timestamp, x, y, status
            clientInput = clientInput + '\n';
            appendStrToFile(fileName, clientInput);

            while (true) {
                try {
                    try {
                        clientUDP.receive(serverRec);
                    }

                    catch (SocketTimeoutException s) {
                        System.out.println("Client timed out after 25 seconds of idle time.");
                        out.close();
                        clientUDP.close();
                        System.exit(1);
                    }

                    clientInput = new String(serverRec.getData(), 0, serverRec.getLength()); // ID, timestamp, x, y, status

                    // if output is to be closed
                    if (clientInput.equals("Close.")) {
                        System.out.println("Client sent their last datagram! Commencing server shutdown.");
                        out.close();
                        clientUDP.close();
                        System.exit(1);
                    }
                    
                    //printing to tracer.log
                    clientInput = clientInput + '\n';
                    appendStrToFile(fileName, clientInput);

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
            out.write("\n\n--- New Covid Tracer Simulation ---\n\n"
                    + "ID of Host Machine: " + str + "\n");
        } 
        
		catch (IOException e) { 
			System.out.println("Exception occured" + e); 
		} 
	}

    public static void main(String[] args) {
        CovidServer CS = new CovidServer();
    }
}






















