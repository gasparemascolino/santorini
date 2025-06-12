package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.model.Cell;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;
import com.gasparemascolino.controller.Divinity;

import java.io.IOException;


import static com.gasparemascolino.ClientHandler.*;
import static com.gasparemascolino.controller.WorkerController.*;
import static com.gasparemascolino.model.GameBoard.showGameBoard;

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


