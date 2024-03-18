package org.example;
import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new thread to handle the client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable
    {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket)
        {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                    ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream())
            )
            {
                outputStream.writeObject("Welcome to the server!");
                outputStream.flush();

                String message;
                int number_of_messages = (int) inputStream.readObject();
                outputStream.writeObject("Server received number: " + number_of_messages);
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < number_of_messages; i++){
                    Message message1 = (Message) inputStream.readObject();
                    sb.append(message1.toString()).append("\n");
                }
                outputStream.writeObject(sb.toString());
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    clientSocket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            }
        }
    }
}
