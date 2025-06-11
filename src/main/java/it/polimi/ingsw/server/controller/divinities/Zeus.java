package it.polimi.ingsw.server.controller.divinities;

import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.controller.Divinity;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;

import java.io.IOException;

import static it.polimi.ingsw.server.ClientHandler.*;
import static it.polimi.ingsw.server.model.GameBoard.showGameBoard;

/**
 * Class behaviour of Zeus (Your worker can build a block under himself.)
 * @author Alberto Leo
 */

public class Zeus implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Zeus";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can build a block under himself.\n");
    }

    /**
     * Add Behavior of Zeus to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        Cell cell;
        int choose;

        cell = cellToMoveOn(worker, board);

        showGameBoard(board);

        if(worker.getPosition().getLevel() <= 2) {
            choose = NotifyDivinityPowerOnExtra();

            if (choose == 1) {

                cell.setisOccupied(false);

                NotifyUseDivinityPower();
                cell.levelUp();
                NotifyBuild(cell.getRow(), cell.getColumn());

                cell.setisOccupied(true);

                showGameBoard(board);

            } else {
                cellToBuildOn(worker, board);
            }


            NotifyDivinityPowerOff();
        }
        else {
            cellToBuildOn(worker, board);
        }


        showGameBoard(board);

    }
}
