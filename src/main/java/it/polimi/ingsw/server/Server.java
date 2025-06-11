package it.polimi.ingsw.server;


import it.polimi.ingsw.server.model.GameBoard;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server of the game, established new connection with the client and create new thread for each client, in order to handle it.
 * @author Alberto Leo
 */
public class Server
{
    public final static int SOCKET_PORT = 7777;
    private static int indexPlayer = 1;
    public static ArrayList<Socket> clients = new ArrayList<>();
    public static ArrayList<Thread> scheduler = new ArrayList<>();
    public static int playernum = 3;
    public static GameBoard board = new GameBoard();


    public static void main(String[] args) {
        ServerSocket socket;


        try {

            socket = new ServerSocket(SOCKET_PORT);


        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }

        ClientHandler clientThread = new ClientHandler();

        while (true) {
            try {

                        Socket client = socket.accept();
                        clients.add(client);

                        if(indexPlayer > playernum) {

                            System.err.println("Too many players");
                            socket.close();
                            break;
                        }

                        Thread thread = new Thread(clientThread, "client " + indexPlayer);
                        scheduler.add(thread);
                        thread.start();


                        System.out.print("Client " + indexPlayer + " join the game\n");
                        indexPlayer++;





            } catch (IOException e) {
                System.out.println("connection dropped");
            }
        }
    }
}