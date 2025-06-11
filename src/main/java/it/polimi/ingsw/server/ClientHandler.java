package it.polimi.ingsw.server;

import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static it.polimi.ingsw.server.NotifyServer.*;
import static it.polimi.ingsw.server.Server.*;
import static it.polimi.ingsw.server.Server.board;
import static it.polimi.ingsw.server.controller.WorkerController.*;
import static it.polimi.ingsw.server.controller.divinities.Chronus.getChronusPower;

/**
 * ClientHandler uses by the thread for handel the client for all the game
 * @author Gaspare Mascolino
 */
public class ClientHandler implements Runnable {


    public static NotifyServer notifyServer;
    public static int currentclient = 1;
    public static ArrayList<ObjectInputStream> inputs = new ArrayList<>();
    public static ArrayList<ObjectOutputStream> outputs = new ArrayList<>();

    private static int clientindex = 0;
    protected static boolean connectionoff=false;



    public ClientHandler() {

        notifyServer = new NotifyServer();

    }


    @Override
    public void run() {


        try {


            clientindex++;
            ObjectInputStream input = new ObjectInputStream(clients.get(clientindex-1).getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(clients.get(clientindex-1).getOutputStream());

            inputs.add(input);
            outputs.add(output);

            notifyServer.SetPlayers();
            if(!connectionoff) notifyServer.Turn();


        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * Setter for initial position of Worker, method called by a player at the start of the game
     *
     * @param worker set this worker
     * @param board  GameBoard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public static void SetInitialWorkerPosition(Worker worker, GameBoard board) throws IOException, ClassNotFoundException {

        outputs.get(currentclient-1).writeObject("Set worker position");
        int row=(int) inputs.get(currentclient-1).readObject();
        int column=(int) inputs.get(currentclient-1).readObject();

        if (!board.cell[row][column].getisOccupied()) {
            board.cell[row][column].setWorker(worker);
            board.cell[row][column].setisOccupied(true);

            outputs.get(currentclient-1).writeObject("Cell is available!");
        }
        else {
            outputs.get(currentclient-1).writeObject("Cell is unavailable, retry");

            SetInitialWorkerPosition(worker, board);

        }

    }

    /**
     * Requires the cell where the worker wants to move on until the choice is available
     *
     * @param worker worker to move
     * @param board GameBoard
     * @return the cell where the worker moves on
     * @throws IOException  for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     * @throws InterruptedException for interrupted
     */
    public static Cell cellToMoveOn(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {
        boolean condition = false;
        int row = -1, column = -1;

        looseCondition(worker.getIndex(), worker.getIndex());
        outputs.get(currentclient-1).writeObject("Choose the cell where you want to move on:");

        while (!condition) {

            outputs.get(currentclient-1).writeObject("Cell chosen");
            row = (int) inputs.get(currentclient-1).readObject();
            column = (int) inputs.get(currentclient-1).readObject();

            int oldrow= worker.getPosition().getRow();
            int oldcolumn= worker.getPosition().getColumn();
            int oldlevel = worker.getPosition().getLevel();

            condition = Move(worker, row, column, board);

            if(!condition){
                NotifyCellIsUnavailable();
            } else{
                outputs.get(currentclient-1).writeObject("Available!");

                NotifyMove(worker,oldrow,oldcolumn);

                if ((oldlevel == 2) && (worker.getPosition().getLevel() == 3))
                    winCondition();


            }

        }


        return board.cell[row][column];
    }

    /**
     * Requires the cell where the worker wants to build on until the choice is available
     *
     * @param worker worker to move
     * @param board GameBoard
     * @return the cell where the worker builds on
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     * @throws InterruptedException for interrupted
     */
    public static Cell cellToBuildOn(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {
        boolean condition = false;
        int row = -1, column = -1;

        Cell[] availableposition = enableToBuild(board.players.get(currentclient-1).getWorker(worker.getIndex()), board);
        if(availableposition[0]==null)
            looseCondition(-1,-1);

        outputs.get(currentclient-1).writeObject("Choose the cell where you want to build on:");

        while (!condition) {

            outputs.get(currentclient-1).writeObject("Cell chosen");
            row = (int) inputs.get(currentclient-1).readObject();
            column = (int) inputs.get(currentclient-1).readObject();

            condition = Build(worker, row, column, board);

            if(!condition){
                NotifyCellIsUnavailable();
            } else {
                outputs.get(currentclient-1).writeObject("Available!");

                NotifyBuild(row,column);
            }

        }

        return board.cell[row][column];

    }

    /**
     * @param worker the worker who moves in another cell
     * @param oldrow cell's row where worker moves
     * @param oldcolumn cell's column where worker moves
     * @throws IOException for input or output exception
     */
    public static void NotifyMove(Worker worker,int oldrow,int oldcolumn) throws IOException{
        for(int i = 0; i < playernum; i++) {
            clientdisconnected=i;

            outputs.get(i).writeObject("Move Worker");
            outputs.get(i).writeObject(worker.getIndex());
            outputs.get(i).writeObject(oldrow);
            outputs.get(i).writeObject(oldcolumn);

            outputs.get(i).writeObject(board.players.get(currentclient - 1).getWorker(worker.getIndex()).getPosition().getRow());
            outputs.get(i).writeObject(board.players.get(currentclient - 1).getWorker(worker.getIndex()).getPosition().getColumn());

        }
        clientdisconnected=currentclient-1;
    }

    /**
     * @param row cell's row where put a block
     * @param column cell's column where put a block
     * @throws IOException for input or output exception
     */
    public static void NotifyBuild(int row,int column) throws IOException{
        for(int i = 0; i < playernum; i++) {
            clientdisconnected=i;

            outputs.get(i).writeObject("Build a block");
            outputs.get(i).writeObject(row);
            outputs.get(i).writeObject(column);
            outputs.get(i).writeObject(board.cell[row][column].getLevel());

        }
        clientdisconnected=currentclient-1;
    }

    /**
     * @return active =1 active, =0 not active
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public static int NotifyDivinityPowerOn() throws IOException, ClassNotFoundException {
        outputs.get(currentclient-1).writeObject("Do you want to use the divinity power?");
        outputs.get(currentclient-1).writeObject("Power Chosen");
        return ((int) inputs.get(currentclient-1).readObject());
    }

    /**
     * @return active =1 active, =0 not active
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public static int NotifyDivinityPowerOnExtra() throws IOException, ClassNotFoundException {
        outputs.get(currentclient-1).writeObject("Do you want to use the divinity power?");
        outputs.get(currentclient-1).writeObject("Power Chosen Extra");
        return ((int) inputs.get(currentclient-1).readObject());
    }

    /**
     * @throws IOException for input or output exception
     */
    public static void NotifyDivinityPowerOff() throws IOException {
        outputs.get(currentclient-1).writeObject("Power Off");
    }

    /**
     * @throws IOException for input or output exception
     */
    public static void NotifyUseDivinityPower() throws IOException {
        for(int i = 0; i < playernum; i++) {
            clientdisconnected=i;
            if(i!=currentclient-1){
                outputs.get(i).writeObject("Notify useDivinityPower");
                if(board.players.get(currentclient-1).getDivinity().equals("Athena")) outputs.get(i).writeObject("Athena Power is activated");
                else outputs.get(i).writeObject(board.players.get(currentclient-1).getNickname() +" has just used the divinity power");
            }
        }
        clientdisconnected=currentclient-1;
    }

    /**
     * @param row cell's row where put a dome
     * @param column cell's column where put a dome
     * @throws IOException for input or output exception
     */
    public static void NotifySetDome(int row,int column) throws IOException {
        for(int i = 0; i < playernum; i++) {
            clientdisconnected=i;

            outputs.get(i).writeObject("Notify setDome");
            outputs.get(i).writeObject(row);
            outputs.get(i).writeObject(column);

        }
        clientdisconnected=currentclient-1;
    }

    /**
     * @param divinity diviinity to notify
     * @throws IOException for input or output exception
     */
    public static void NotifyDivinity(String divinity) throws IOException {
        for (int i = 0; i < playernum; i++) {
            clientdisconnected=i;
            outputs.get(i).writeObject(divinity);
        }
        clientdisconnected=currentclient-1;
    }

    /**
     * @param pushrow row where the enemy where pushed
     * @param pushcolumn column where the enemy where pushed
     * @throws IOException for input or output exception
     */
    public static void NotifyPushedPosition(int pushrow, int pushcolumn) throws IOException {
        for (int i = 0; i < playernum; i++) {
            clientdisconnected=i;

            outputs.get(i).writeObject("Push done");
            outputs.get(i).writeObject(pushrow);
            outputs.get(i).writeObject(pushcolumn);
        }
        clientdisconnected=currentclient-1;
    }

    /**
     * @throws IOException for input or output exception
     */
    public static void NotifyResetPosition() throws IOException {
        for (int i = 0; i < playernum; i++) {
            clientdisconnected=i;
            outputs.get(i).writeObject("Reset position");

        }
        clientdisconnected=currentclient-1;
    }

    /**
     * @throws IOException for input or output exception
     */
    public static void NotifyCellIsUnavailable() throws IOException {
        outputs.get(currentclient-1).writeObject("Cell is unavailable, retry");
    }


    /**
     * Notify end of the game, stop Turn method
     * @throws IOException for input or output exception
     */
    public static void winCondition() throws IOException {

        for(int i= 0; i < playernum; i++) {
            outputs.get(i).writeObject("EndGame");
            outputs.get(i).writeObject(board.players.get(currentclient-1).getNickname()+" WIN!");
            outputs.get(i).close();
            ingame=false;

        }

    }

    /**
     * Check if a player looses for "can't move" "can't build" "Chrous power"
     * In a 3 players game, the looser player become a viewer of the game which goes on
     * In a 2 players game, call win condition method
     * @param workerindex1 index of worker 1
     * @param workerindex2 index of worker 2
     * @throws IOException for input or output exception
     * @throws InterruptedException for interupted exception
     */
    public static void looseCondition(int workerindex1, int workerindex2) throws IOException, InterruptedException {


        Cell[] availablepostion1 = enableToMove(board.players.get(currentclient-1).getWorker(workerindex1), board);
        Cell[] availableposition2 = enableToMove(board.players.get(currentclient-1).getWorker(workerindex2), board);

        if(getChronusPower()){
            if(completetowers>=5){
                for(int i=0;i<playernum;i++){
                    outputs.get(i).writeObject("Chronus power is activated");
                   if(board.players.get(i).getDivinity().equals("Chronus")){
                       currentclient=i+1;
                   }
                }
                winCondition();
            }
        }

        if (((availablepostion1[0] == null ) && (availableposition2[0] == null)) || (workerindex1 == -1)) {

            if(names.size()==3){

                int row1 = board.players.get(currentclient-1).getWorker(1).getPosition().getRow();
                int column1 = board.players.get(currentclient-1).getWorker(1).getPosition().getColumn();
                int row2 = board.players.get(currentclient-1).getWorker(2).getPosition().getRow();
                int column2 = board.players.get(currentclient-1).getWorker(2).getPosition().getColumn();

                board.players.get(currentclient-1).getWorker(workerindex1).getPosition().setWorker(null);
                board.players.get(currentclient-1).getWorker(workerindex1).getPosition().setisOccupied(false);
                if(workerindex1==1){
                    board.players.get(currentclient-1).getWorker(workerindex1+1).getPosition().setWorker(null);
                    board.players.get(currentclient-1).getWorker(workerindex1+1).getPosition().setisOccupied(false);
                }else if(workerindex1==2){
                    board.players.get(currentclient-1).getWorker(workerindex1-1).getPosition().setWorker(null);
                    board.players.get(currentclient-1).getWorker(workerindex1-1).getPosition().setisOccupied(false);
                }

                for (int i = 0; i < playernum; i++) {
                    if(i==currentclient-1){
                        outputs.get(i).writeObject("Lost");
                    }else{
                        clientdisconnected=i;
                        outputs.get(i).writeObject("Exit");
                        outputs.get(i).writeObject("Player "+  board.players.get(currentclient-1).getNickname() +" lost the game because he couldn't move in any cell");
                    }
                    outputs.get(i).writeObject(row1);
                    outputs.get(i).writeObject(column1);
                    outputs.get(i).writeObject(row2);
                    outputs.get(i).writeObject(column2);
                }

                outputs.get(currentclient-1).writeObject("Wait the others players for join...");

                playerlost=currentclient;
                names.remove(currentclient-1);

                notifyServer.nextClient();
            }
            else{
                outputs.get(currentclient-1).writeObject("You loose! Can't move in any position");
                outputs.get(currentclient-1).close();
                outputs.remove(currentclient-1);
                outputs.get(0).writeObject("You Win! Enemy can't move");
                outputs.get(0).close();
                outputs.remove(0);
                ingame=false;
            }

        }

    }
}

