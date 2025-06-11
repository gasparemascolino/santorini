package it.polimi.ingsw.server.controller.divinities;

import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.controller.Divinity;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;

import java.io.IOException;

import static it.polimi.ingsw.server.ClientHandler.*;
import static it.polimi.ingsw.server.model.GameBoard.showGameBoard;


/**
 * Class behaviour of Triton (Your worker can move once more if he has moved in a perimetral cell.)
 * @author Gaspare Mascolino
 */
public class Triton implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Triton";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can move once more if he has moved in a perimetral cell.\n");
    }

    /**
     * Add Behavior of Triton to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        int choose;
        Cell cellmovedon;

        cellmovedon = cellToMoveOn(worker, board);

        showGameBoard(board);



        while (cellmovedon.getRow() == 0 || cellmovedon.getRow() == 4 || cellmovedon.getColumn() == 0 || cellmovedon.getColumn() == 4) {

            choose = NotifyDivinityPowerOn();

            if (choose == 1) {

                 NotifyUseDivinityPower();
                 cellmovedon = cellToMoveOn(worker, board);
                 showGameBoard(board);
                 NotifyDivinityPowerOff();

             }
             else {

                   NotifyDivinityPowerOff();
                   break;
             }
        }


        cellToBuildOn(worker, board);

        showGameBoard(board);

    }
}
