package client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Svirinstel on 19.03.17.
 */
public class Main {

    public static void main(String[] args) {

        final String SERVER_NAME = "localhost";
        final int SERVER_SOCKET = 8189;

        Thread outThread = null;
        Thread inThread = null;
        Socket socket = null;

        try {
            socket = new Socket(SERVER_NAME,SERVER_SOCKET);
            outThread = new Thread(new Sender(socket));
            outThread.start();

            inThread = new Thread(new ServerHandler(socket));
            inThread.start();

        } catch (IOException e) {
            System.out.println("Server is not available now.");

            e.printStackTrace();

        }

        try {
            outThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
