package it.polimi.ingsw.server.controller.divinities;


import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;
import it.polimi.ingsw.server.controller.Divinity;


import static it.polimi.ingsw.server.ClientHandler.*;
import static it.polimi.ingsw.server.controller.WorkerController.enableToBuild;
import static it.polimi.ingsw.server.model.GameBoard.showGameBoard;

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

        cellToMoveOn(worker, board);

        showGameBoard(board);


        cellToBuildOn(worker, board);

        showGameBoard(board);



        choose = NotifyDivinityPowerOn();

        if(choose == 1) {
            NotifyUseDivinityPower();

            Cell[] availableposition = enableToBuild(worker, board);

            int k = 0;

            while (availableposition[k]!=null) {

                if(availableposition[k].getRow() == 0 || availableposition[k].getRow() == 4 || availableposition[k].getColumn() == 0 || availableposition[k].getColumn() == 4)
                    availableposition[k].setisOccupied(true);
                k++;
            }

            Cell[] availablepositions = enableToBuild(worker, board);

            if(availablepositions[0] == null)
                looseCondition(-1, -1);

            cellToBuildOn(worker, board);

            showGameBoard(board);

            k = 0;

            while (availableposition[k]!=null) {
                if(!availableposition[k].getDome())
                     availableposition[k].setisOccupied(false);
                k++;
            }


        }

        NotifyDivinityPowerOff();

        showGameBoard(board);

    }
}
