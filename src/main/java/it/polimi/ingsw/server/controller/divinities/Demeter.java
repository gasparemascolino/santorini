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

        cellToMoveOn(worker, board);

        showGameBoard(board);


        cellbuilt = cellToBuildOn(worker, board);

        showGameBoard(board);

        choose = NotifyDivinityPowerOn();

        if(choose == 1) {
            NotifyUseDivinityPower();

            cellbuilt.setisOccupied(true);

            Cell[] availablepositions = enableToBuild(worker, board);

            if(availablepositions[0] == null)
                looseCondition(-1, -1);

            cellToBuildOn(worker, board);
            cellbuilt.setisOccupied(false);
        }

        NotifyDivinityPowerOff();

        showGameBoard(board);

    }
}
