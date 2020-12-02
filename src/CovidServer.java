import java.net.*;
import java.io.*;

public class CovidServer {
    
    private ServerSocket serverS;
    private Socket cSocket;
    private DataInputStream in;

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
            serverS = new ServerSocket(serverPort);
            System.out.println("Server started, now waiting for client...");

            cSocket = serverS.accept();
            System.out.println("Client connected!");

            in = new DataInputStream(new BufferedInputStream(cSocket.getInputStream()));

            String clientInput = ""; // timestamp, x, y, status
            InetAddress clientAddr = cSocket.getInetAddress(); // ID of User

            printTitle(fileName);

            // once it gets the string "Close.", it'll start closing the server, 
            // otherwise keep running
            while (!clientInput.equals("Close.")) {

                try {
                    clientInput = in.readUTF();
                    str = clientAddr.toString();

                    // need to output to file here instead of print
                    str = str + ", " + clientInput;
                    appendStrToFile(fileName, str);
                }

                catch (IOException i) {
                    System.out.println(i);
                }
            }
            
            // close all connections
            cSocket.close();
            serverS.close();
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


    public static void main(String[] args) {
        CovidServer CS = new CovidServer();
    }
}






















