package it.polimi.ingsw.server.controller.divinities;

import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;
import it.polimi.ingsw.server.controller.Divinity;


import static it.polimi.ingsw.server.ClientHandler.*;
import static it.polimi.ingsw.server.model.GameBoard.showGameBoard;

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


        cellToMoveOn(worker, board);

        showGameBoard(board);

        cellToBuildOn(worker, board);
        showGameBoard(board);

        if(board.players.get(currentclient-1).getWorker(workerindex).getPosition().getLevel() == 0) {

            for(int i = 0; i < 3; i++) {
                choose = NotifyDivinityPowerOn();

                if (choose == 1) {
                    NotifyUseDivinityPower();
                    cellToBuildOn(board.players.get(currentclient-1).getWorker(workerindex), board);
                    NotifyDivinityPowerOff();

                    showGameBoard(board);
                    NotifyDivinityPowerOff();
                }
                else {
                    NotifyDivinityPowerOff();
                    return;
                }




            }
        }


    }
}
