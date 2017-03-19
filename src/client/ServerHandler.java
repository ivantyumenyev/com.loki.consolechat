package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Svirinstel on 19.03.17.
 */
public class ServerHandler implements Runnable{

    private Socket socket;

    private Scanner inStream;

    public ServerHandler(Socket socket) {
        this.socket = socket;
        try {
            inStream = new Scanner(socket.getInputStream());

            System.out.println("Sever is connected.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        while (true) {
            if (inStream.hasNext()) {
                String inputMessage = inStream.nextLine();
                if (inputMessage.equalsIgnoreCase("END_SESSION")) break;
                System.out.println("Server: " + inputMessage);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Server is disconnect");

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
