package it.polimi.ingsw.server.controller.divinities;

import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;
import it.polimi.ingsw.server.controller.Divinity;

import java.io.IOException;


import static it.polimi.ingsw.server.ClientHandler.*;
import static it.polimi.ingsw.server.controller.WorkerController.*;
import static it.polimi.ingsw.server.model.GameBoard.showGameBoard;

/**
 * Class behaviour of Apollo (switch positions between two Workers)
 * @author Gaspare Mascolino
 */
public class Apollo implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Apollo";
    }

    /**
     * @return description of divinity power
     */
    public String Description() {
        return("Your worker can move into an enemy worker's cell (using normal move rules) and force him to occupy the new free cell by swapping positions.\n");
    }

    /**
     * Add Behavior of Apollo to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {
        int oldrow, oldcolumn;
        int k = 0;
        Cell[] enemypostions = enemyPositions(worker, board);

        oldrow = worker.getPosition().getRow();
        oldcolumn = worker.getPosition().getColumn();

        cellToMoveOn(worker, board);


        while(enemypostions[k] != null)
        {
            if(worker.getPosition().getRow() == enemypostions[k].getRow() && worker.getPosition().getColumn() == enemypostions[k].getColumn()) {
                NotifyDivinity("Apollo");
                NotifyUseDivinityPower();

                board.cell[oldrow][oldcolumn].setWorker(enemypostions[k].getWorker());
            }

            board.cell[enemypostions[k].getRow()][enemypostions[k].getColumn()].setisOccupied(true);
            k++;
        }

        showGameBoard(board);

        cellToBuildOn(worker, board);

        showGameBoard(board);
    }
}


