package Sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

public class ClientServer implements Runnable {
    ServerSocket sSocket;
    final private int maxConnections = 5;
    private int numConnections = 0;
    private ArrayList<PeerConnection> connections;
    //Returns true if the Socket was added or false if not
    private synchronized boolean addConnection(PeerConnection con){
        if(numConnections >= maxConnections) return false;
        connections.add(con);
        numConnections++;
        return true;
    }
    //Returns true if the Socket was found and removed, returns false otherwise
    private synchronized boolean removeConnection(PeerConnection con){
        //TODO:
        numConnections--;
        return true;
    }
    //Closes all connections and shuts down the server
    public synchronized void shutdownServer(){
        //TODO: close all connections
        try {
            sSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private Thread t;
    private String threadName = "ClientServer";




    private boolean running = true;
    public synchronized void setRunning(boolean t){ running = t;}
    public synchronized boolean getRunning() {return running;}

    public void run() {
        try {
            sSocket = new ServerSocket(findSocket());
            try {
                while (getRunning()) {
                    try {
                        Socket socket = sSocket.accept();
                        //TODO: addConnection
                        //TODO: authentication of connection
                        //TODO: spawn new window --> likely send socket to new object
                        PeerConnection newCon = new PeerConnection(socket);
                        newCon.sendMessage();
                    }
                    catch(SocketException e){
                        System.out.println("Server shut down");
                        setRunning(false);
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
                sSocket.close();
                running = false;
            }
            finally {
                sSocket.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void start () {
        System.out.println("Starting client server");
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    //TODO: make this find available socket
    private int findSocket(){
        return 9090;
    }
}
