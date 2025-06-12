package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.ClientHandler;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;
import com.gasparemascolino.controller.Divinity;


import java.io.IOException;


/**
 * Class behaviour of Poseidon(Your worker can build until three times more if the other worker of the player is in a ground level cell.)
 * @author Gaspare Mascolino
 */
public class Poseidon implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Poseidon";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can build until three times more if the other worker of the player is in a ground level cell.\n");
    }

    /**
     * Add Behavior of Poseidon to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        int choose;
        int workerindex;

        workerindex = worker.getIndex();

        if(workerindex == 1)
            workerindex = 2;
        else
            workerindex = 1;


        ClientHandler.cellToMoveOn(worker, board);

        GameBoard.showGameBoard(board);

        ClientHandler.cellToBuildOn(worker, board);
        GameBoard.showGameBoard(board);

        if(board.players.get(ClientHandler.currentclient-1).getWorker(workerindex).getPosition().getLevel() == 0) {

            for(int i = 0; i < 3; i++) {
                choose = ClientHandler.NotifyDivinityPowerOn();

                if (choose == 1) {
                    ClientHandler.NotifyUseDivinityPower();
                    ClientHandler.cellToBuildOn(board.players.get(ClientHandler.currentclient-1).getWorker(workerindex), board);
                    ClientHandler.NotifyDivinityPowerOff();

                    GameBoard.showGameBoard(board);
                    ClientHandler.NotifyDivinityPowerOff();
                }
                else {
                    ClientHandler.NotifyDivinityPowerOff();
                    return;
                }




            }
        }


    }
}
