package com.gasparemascolino;

import com.gasparemascolino.model.Player;
import com.gasparemascolino.model.GameBoard;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import static com.gasparemascolino.Server.*;

/**
 * Handle the logic of the game
 * @author Gaspare Mascolino
 * @author Alberto Leo
 * @author Eugenio Facciolo
 */
public class NotifyServer {

    // ArrayList that contains the divinities chosen by the first player
    static public final ArrayList<String> names =new ArrayList<>();
    static public final ArrayList<String> divinitieschosen = new ArrayList<>();
    static public boolean condition = false;

    static public int clientdisconnected;
    static public boolean ingame=true;
    static public int playerlost=0;


    /**
     * Set the player to join the game
     * @throws IOException for input or output exception
     */
    synchronized public void SetPlayers() throws IOException {

        String[] colours = new String[3];
        colours[0] = "blue";
        colours[1] = "red";
        colours[2] = "green";

        try {
            // Index of the player
            clientdisconnected= ClientHandler.currentclient-1;
            ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Player index");
            ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject(ClientHandler.currentclient);


            // Only the first player comes in this condition
            if(!condition) {
                // Number of the players for the game
                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("How many players?");
                Object next = ClientHandler.inputs.get(ClientHandler.currentclient - 1).readObject();
                playernum = (int) next;


                System.out.println("The number of players is: " + playernum);
            }
            else {
                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Number of players");
                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject(playernum);
            }

            // Update players that already join the game
            for(int i = 0; i < ClientHandler.currentclient - 1; i++) {
                clientdisconnected=i;
                ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Update Players");
                ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject(names.get(i));
            }
            clientdisconnected= ClientHandler.currentclient-1;


            // Send the message to the client in order to insert the name of the player
            ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Insert the name of the player");
            Object next = ClientHandler.inputs.get(ClientHandler.currentclient-1).readObject();
            String nickname = (String) next;

            while (names.contains(nickname)) {
                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Not available!");
                ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Insert the name of the player");
                next = ClientHandler.inputs.get(ClientHandler.currentclient-1).readObject();
                nickname = (String) next;
            }

            ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Available!");

            names.add(nickname);

            for(int i = 0; i < ClientHandler.outputs.size()-1; i++) {
                clientdisconnected=i;
                ClientHandler.outputs.get(i).writeObject("Update player name");
                ClientHandler.outputs.get(i).writeObject(ClientHandler.currentclient-1);
                ClientHandler.outputs.get(i).writeObject(nickname);
            }
            clientdisconnected= ClientHandler.currentclient-1;

            System.out.println(names.get(ClientHandler.currentclient-1)+" join the game");

            // Only the first player enter in this condition
            if (!condition) {


                board.players.add(new Player(null, 0, " ", null));
                condition = true;

                // Send the message to the client in order to wait the other player for join
                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Wait the others players for join...");

                nextClient();

                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("StartTurn");

                // choose playernum (number of players) divinities
                for (int i = 0; i < playernum; i++) {
                    ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Choose divinities");
                    Object name = ClientHandler.inputs.get(ClientHandler.currentclient-1).readObject();
                    String divinityname = (String) name;

                    divinitieschosen.add(divinityname);
                    System.out.println(divinityname + " was choosen");

                }

                // Send the message to the client in order to wait the other player for join
                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Wait the others players for join...");


                nextClient();

                String divinityname = divinitieschosen.get(0);
                board.players.set(0, new Player(names.get(0), 1, divinityname, colours[0]));

                for(int i = 0; i < playernum; i++) {
                    clientdisconnected=i;
                    ClientHandler.outputs.get(i).writeObject("Update divinity name");
                    ClientHandler.outputs.get(i).writeObject(ClientHandler.currentclient - 1);
                    ClientHandler.outputs.get(i).writeObject(divinityname);
                }
                clientdisconnected= ClientHandler.currentclient-1;


            }
            else {

                // Send the message to the client in order to wait the other player for join
                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Wait the others players for join...");

                nextClient();


                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Update available divinities");

                // Update the divinities choosen by the first player to the others
                for (String s : divinitieschosen) {
                    ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject(s);
                }

                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("StartTurn");

                ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Choose divinities");

                next = ClientHandler.inputs.get(ClientHandler.currentclient-1).readObject();
                String divinityname = (String) next;

                board.players.add(new Player(names.get(ClientHandler.currentclient-1), ClientHandler.currentclient, divinityname, colours[ClientHandler.currentclient - 1]));

                divinitieschosen.remove(divinityname);

                ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Update Divinity");
                ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject(board.players.get(ClientHandler.currentclient-1).getDivinity());

                for(int i = 0; i < playernum; i++) {
                    if(i != ClientHandler.currentclient-1) {
                        clientdisconnected=i;
                        ClientHandler.outputs.get(i).writeObject("Update divinity name");
                        ClientHandler.outputs.get(i).writeObject(ClientHandler.currentclient - 1);
                        ClientHandler.outputs.get(i).writeObject(board.players.get(ClientHandler.currentclient - 1).getDivinity());
                    }
                }
                clientdisconnected= ClientHandler.currentclient-1;

                if(ClientHandler.currentclient == playernum) {
                    ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Update Divinity");
                    //first player's divnity
                    ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject(divinitieschosen.get(0));

                    for(int i = 1; i < playernum-1; i++) {
                        clientdisconnected=i;
                        ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Update Divinity");
                        ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject(board.players.get(i).getDivinity());
                    }
                }
                clientdisconnected= ClientHandler.currentclient-1;

                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Wait the others players for join...");

                nextClient();

            }

            updateCurrentClient();

            ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Choose the initial position of worker 1");

            ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("StartTurn");

            // Set worker positions

            for(int i = 0; i < playernum; i++) {
                clientdisconnected=i;
                if(i != ClientHandler.currentclient - 1)
                    ClientHandler.outputs.get(i).writeObject("Waiting others players");
            }
            clientdisconnected= ClientHandler.currentclient-1;

            ClientHandler.SetInitialWorkerPosition(board.players.get(ClientHandler.currentclient - 1).getWorker(1),board);

            for(int i = 0; i < playernum; i++) {
                clientdisconnected=i;
                ClientHandler.outputs.get(i).writeObject("Set worker position");
                ClientHandler.outputs.get(i).writeObject(1);
                ClientHandler.outputs.get(i).writeObject(board.players.get(ClientHandler.currentclient - 1).getWorker(1).getPosition().getRow());
                ClientHandler.outputs.get(i).writeObject(board.players.get(ClientHandler.currentclient - 1).getWorker(1).getPosition().getColumn());
            }
            clientdisconnected= ClientHandler.currentclient-1;

            GameBoard.showGameBoard(board);

            ClientHandler.outputs.get(ClientHandler.currentclient-1).writeObject("Choose the initial position of worker 2");

            ClientHandler.SetInitialWorkerPosition(board.players.get(ClientHandler.currentclient - 1).getWorker(2),board);
            for(int i = 0; i < playernum; i++) {
                clientdisconnected=i;
                ClientHandler.outputs.get(i).writeObject("Set worker position");
                ClientHandler.outputs.get(i).writeObject(2);
                ClientHandler.outputs.get(i).writeObject(board.players.get(ClientHandler.currentclient - 1).getWorker(2).getPosition().getRow());
                ClientHandler.outputs.get(i).writeObject(board.players.get(ClientHandler.currentclient - 1).getWorker(2).getPosition().getColumn());
            }
            clientdisconnected= ClientHandler.currentclient-1;

            GameBoard.showGameBoard(board);

            ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Wait the others players for join...");

            nextClient();

        } catch (ClassNotFoundException | ClassCastException | IOException | InterruptedException e) {
            System.out.println("invalid stream from client");
            clientdisconnected++;
            ClientHandler.connectionoff=true;
            System.err.println("client "+clientdisconnected+" left the game in SetPlayers");
            for (int i = 0; i < playernum; i++) {
                if(i!=clientdisconnected-1) ClientHandler.outputs.get(i).writeObject("Disconnected");

                try{
                    ClientHandler.outputs.get(i).close();
                }catch (SocketException ignored){}
            }

        }


    }

    /**
     *  Handle the turn of the game for each client
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     * @throws InterruptedException for deadlock
     */
    synchronized public void Turn() throws InterruptedException, IOException, ClassNotFoundException {


        Player currentPlayer;
        int workerchosen;

        try {
            while (ingame) {

                updateCurrentClient();

                for (int i = 0; i < playernum; i++) {
                    clientdisconnected=i;
                    if (i != ClientHandler.currentclient - 1)
                        ClientHandler.outputs.get(i).writeObject("Wait the others players");
                }
                clientdisconnected= ClientHandler.currentclient-1;

                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Choose a worker");

                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("StartTurn");

                // Set worker positions

                GameBoard.showGameBoard(board);
                System.out.println("client " + ClientHandler.currentclient + " play");

                currentPlayer = board.players.get(ClientHandler.currentclient - 1);

                do {
                    ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Worker chosen");
                    workerchosen = (int) ClientHandler.inputs.get(ClientHandler.currentclient - 1).readObject();
                    ClientHandler.looseCondition(1, 2);
                } while (workerchosen < 0);

                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Available!");

                currentPlayer.getWorker(workerchosen).getDivinity().doAction(currentPlayer.getWorker(workerchosen), board);

                ClientHandler.looseCondition(1, 2);


                ClientHandler.outputs.get(ClientHandler.currentclient - 1).writeObject("Wait the others players for join...");

                nextClient();


            }
        }catch (SocketException | EOFException e){
            System.out.println("invalid stream from client");
            clientdisconnected++;
            System.err.println("client "+clientdisconnected+" left the game in Turn");
            for (int i = 0; i < playernum; i++) {
                try{
                    if(i!=clientdisconnected-1) ClientHandler.outputs.get(i).writeObject("Disconnected");
                    ClientHandler.outputs.get(i).close();
                }catch (SocketException ignored){}
            }
        }


    }

    /**
     * Update current client to the others players
     * @throws IOException for input or output exception
     */
    synchronized public void updateCurrentClient() throws IOException {
        try {
            for (int i = 0; i < playernum; i++) {
                clientdisconnected=i;
                ClientHandler.outputs.get(i).writeObject("Update current client");
                ClientHandler.outputs.get(i).writeObject(ClientHandler.currentclient);

            }
        }catch (SocketException e) {
            System.out.println("invalid stream from client");
            clientdisconnected++;
            System.err.println("client "+clientdisconnected+" left the game in updateCurrentClient");
            for (int i = 0; i < playernum; i++) {
                if(i!=clientdisconnected-1) ClientHandler.outputs.get(i).writeObject("Disconnected");

                try{
                    ClientHandler.outputs.get(i).close();
                }catch (SocketException ignored){}
            }
        }
    }

    /**
     * Set the next client thread of the scheduler as the current client, leaving the others in wait
     * @throws InterruptedException for deadlock
     */
    synchronized public void nextClient() throws InterruptedException {

        while(playerlost== ClientHandler.currentclient){
            System.out.println("Client "+ playerlost + " is out from the game");
            if (ClientHandler.currentclient != playernum)
                ClientHandler.currentclient++;
            else
                ClientHandler.currentclient = 1;
            notifyAll();
            wait();
            wait();
        }

        if(scheduler.size() == playernum) {
            do {
                if (ClientHandler.currentclient != playernum)
                    ClientHandler.currentclient++;
                else
                    ClientHandler.currentclient = 1;

            } while (scheduler.get(ClientHandler.currentclient - 1) == null);
        }
        else
            ClientHandler.currentclient++;


        notifyAll();

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(playernum == 3) {
            switch (ClientHandler.currentclient) {
                case 1: {
                    if(scheduler.indexOf(Thread.currentThread()) == 1) {
                        System.out.println("client 1 start, client 2 lock");
                        wait();
                    }

                    break;
                }
                case 2: {
                    if(scheduler.indexOf(Thread.currentThread()) == 2) {
                        System.out.println("client 2 start, client 3 lock");
                        wait();
                    }
                    break;
                }
                case 3: {
                    if(scheduler.indexOf(Thread.currentThread()) == 0) {
                        System.out.println("client 3 start, client 1 lock");
                        wait();
                    }
                    break;
                }

            }

        }

        clientdisconnected= ClientHandler.currentclient-1;

    }

}