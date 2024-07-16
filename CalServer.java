import java.net.*;
import java.io.*;


public class CalServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8000);
            System.out.println("Server started. Waiting for client to connect...");
           
            // Accept client connection
            Socket s = ss.accept();
            System.out.println("Client connected.");


            // Setup streams
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());


            String receive;
            while ((receive = in.readLine()) != null) {
                try {
                    // Convert received string to double
                    double number = Double.parseDouble(receive);
                   
                    // Calculate square, square root, cube, cube root
                    double square = number * number;
                    double squareRoot = Math.sqrt(number);
                    double cube = number * number * number;
                    double cubeRoot = Math.cbrt(number);


                    // Prepare response
                    String response = "Square: " + square + ", Square Root: " + squareRoot
                                    + ", Cube: " + cube + ", Cube Root: " + cubeRoot;


                    // Send response to client
                    out.writeBytes(response + "\n");
                } catch (NumberFormatException e) {
                    out.writeBytes("Invalid input. Please enter a valid number.\n");
                }
            }


            // Close streams and socket
            in.close();
            out.close();
            s.close();
            ss.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

