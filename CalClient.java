import java.net.*;
import java.io.*;


public class CalClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 8000);
           
            // Setup streams
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));


            String numberStr;
            while (true) {
                System.out.print("Enter a number (or 'STOP' to quit): ");
                numberStr = br.readLine();
               
                // Send number to server
                out.writeBytes(numberStr + "\n");


                // Check for termination condition
                if (numberStr.equalsIgnoreCase("STOP")) {
                    break;
                }


                // Receive response from server and print
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
            }


            // Close streams and socket
            br.close();
            out.close();
            in.close();
            s.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

