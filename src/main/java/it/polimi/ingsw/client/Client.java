package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.GUI;
import it.polimi.ingsw.server.Server;

import java.io.IOException;
import java.net.Socket;

/**
 * Handle the connection to the server and the thread CLI
 *
 * @author Eugenio Facciolo
 */
public class Client {


    public static GUI gui;


    public static void main(String[] args){

        gui = new GUI();
        GUI.main(args);


    }

    /**
     * Establish connection
     * @param ip server ip
     * @return connection report
     * @throws IOException for input or output exception
     */
    public static boolean NewConnection(String ip) throws IOException {
        /* open a connection to the server */
        Socket server;
        try {
            server = new Socket(ip, Server.SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("server unreachable");
            return false;
        }
        System.out.println("Connected, wait the others players to join");
        gui.setServerConnection(server);
        return true;
    }

}


