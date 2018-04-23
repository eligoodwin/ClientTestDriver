package Sockets;

import QueryObjects.IPandPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

//TODO: make this threaded
public class PeerConnection {
    public PeerConnection(Socket s){sock = s;}
    //TODO: consider where to handle IOException
    public PeerConnection(String add, int port) throws IOException{
        sock= new Socket(add, port);
        //TODO: handle sock failure
    }
    public PeerConnection(IPandPort data) throws IOException{
        sock= new Socket(data.ipAddress, data.port);
        //TODO: handle sock failure
    }
    private Socket sock;

    public void sendMessage(){
        //TODO: this is only used for testing at the moment
        try {
            PrintWriter out =
                    new PrintWriter(sock.getOutputStream(), true);
            out.println(new Date().toString());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void receiveMessage(){
        try {
            BufferedReader input =
                new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String answer = input.readLine();
            System.out.println(answer);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
