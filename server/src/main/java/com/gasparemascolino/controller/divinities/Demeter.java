package com.gasparemascolino.controller.divinities;


import com.gasparemascolino.ClientHandler;
import com.gasparemascolino.model.Cell;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;
import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.controller.WorkerController;


import java.io.IOException;


/**
 * Class behaviour of Demeter (Worker can build twice this turn)
 * @author Gaspare Mascolino
 */
public class Demeter implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Demeter";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can build once more, but not in the same cell.\n");
    }

    /**
     * Add Behavior of Demeter to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        Cell cellbuilt;
        int choose;

        ClientHandler.cellToMoveOn(worker, board);

        GameBoard.showGameBoard(board);


        cellbuilt = ClientHandler.cellToBuildOn(worker, board);

        GameBoard.showGameBoard(board);

        choose = ClientHandler.NotifyDivinityPowerOn();

        if(choose == 1) {
            ClientHandler.NotifyUseDivinityPower();

            cellbuilt.setisOccupied(true);

            Cell[] availablepositions = WorkerController.enableToBuild(worker, board);

            if(availablepositions[0] == null)
                ClientHandler.looseCondition(-1, -1);

            ClientHandler.cellToBuildOn(worker, board);
            cellbuilt.setisOccupied(false);
        }

        ClientHandler.NotifyDivinityPowerOff();

        GameBoard.showGameBoard(board);

    }
}
