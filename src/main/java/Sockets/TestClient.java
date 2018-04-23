package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class TestClient {

    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     *
     * Source: http://cs.lmu.edu/~ray/notes/javanetexamples/
     */
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        Socket s = new Socket(serverAddress, 9090);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        System.out.println(answer);
        System.exit(0);
    }
}