package org.example;

import java.io.*;
import java.net.*;

public class Client
{
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8000;

    public static void main(String[] args)
    {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT))
        {


            // Get input and output streams for the socket
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String back_message =  (String) inputStream.readObject();
            System.out.println(back_message);
            // Example message to send to the server
            System.out.println("Enter an integer value:");
            int number = Integer.parseInt(reader.readLine());
            outputStream.writeObject(number);
            outputStream.flush();
            back_message =  (String) inputStream.readObject();
            System.out.println(back_message);

            for(int i = 0; i < number; i++){
                System.out.println("Enter a string value:");
                String name = reader.readLine();
                Message message1 = new Message(i, name);
                outputStream.writeObject(message1);
                outputStream.flush();
            }
            back_message =  (String) inputStream.readObject();
            System.out.println(back_message);

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
