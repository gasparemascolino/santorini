package com.gasparemascolino.controller.divinities;


import com.gasparemascolino.ClientHandler;
import com.gasparemascolino.model.Cell;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;
import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.controller.WorkerController;


import java.io.IOException;


/**
 * Class behaviour of Hestia (Your worker can build once more, but not in a perimetral cell.)
 * @author Gaspare Mascolino
 */
public class Hestia implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Hestia";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can build once more, but not in a perimetral cell.\n");
    }

    /**
     * Add Behavior of Hestia to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        int choose;

        ClientHandler.cellToMoveOn(worker, board);

        GameBoard.showGameBoard(board);


        ClientHandler.cellToBuildOn(worker, board);

        GameBoard.showGameBoard(board);



        choose = ClientHandler.NotifyDivinityPowerOn();

        if(choose == 1) {
            ClientHandler.NotifyUseDivinityPower();

            Cell[] availableposition = WorkerController.enableToBuild(worker, board);

            int k = 0;

            while (availableposition[k]!=null) {

                if(availableposition[k].getRow() == 0 || availableposition[k].getRow() == 4 || availableposition[k].getColumn() == 0 || availableposition[k].getColumn() == 4)
                    availableposition[k].setisOccupied(true);
                k++;
            }

            Cell[] availablepositions = WorkerController.enableToBuild(worker, board);

            if(availablepositions[0] == null)
                ClientHandler.looseCondition(-1, -1);

            ClientHandler.cellToBuildOn(worker, board);

            GameBoard.showGameBoard(board);

            k = 0;

            while (availableposition[k]!=null) {
                if(!availableposition[k].getDome())
                     availableposition[k].setisOccupied(false);
                k++;
            }


        }

        ClientHandler.NotifyDivinityPowerOff();

        GameBoard.showGameBoard(board);

    }
}
