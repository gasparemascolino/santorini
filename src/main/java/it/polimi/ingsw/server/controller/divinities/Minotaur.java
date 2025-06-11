package it.polimi.ingsw.server.controller.divinities;

import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.controller.Divinity;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;

import java.io.IOException;

import static it.polimi.ingsw.server.ClientHandler.*;
import static it.polimi.ingsw.server.controller.WorkerController.*;
import static it.polimi.ingsw.server.model.GameBoard.showGameBoard;

/**
 * Class behaviour of Minotaur (can move to an opposing worker's cell (according to the normal rules of the move) if the next cell in the same direction is free. The opposing worker is forced to move to that box (regardless of level))
 * @author Eugenio Facciolo
 */
public class Minotaur implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Minotaur";
    }

    /**
     * @return description of divinity power
     */
    public String Description() {
        return("Your worker can move to an opposing worker's cell (according to the normal rules of the move) if the next cell in the same direction is free. The opposing worker is forced to move to that box (regardless of level). \n");
    }

    /**
     * Add Behavior of Minotaur to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        int oldrow, oldcolumn, row, column, pushrow, pushcolumn;
        int k;
        Cell[] enemypostions;
        Worker enemy;
        boolean condition;

        oldrow = worker.getPosition().getRow();
        oldcolumn = worker.getPosition().getColumn();

        do {

            enemy=null;
            enemypostions = enemyPositions(worker, board);
            condition=true;
            k=0;

            cellToMoveOn(worker, board);


            while (enemypostions[k] != null) {
                if(enemypostions[k].getRow() == worker.getPosition().getRow() && enemypostions[k].getColumn() == worker.getPosition().getColumn()){
                    enemy = enemypostions[k].getWorker();
                    condition=false;
                } else board.cell[enemypostions[k].getRow()][enemypostions[k].getColumn()].setisOccupied(true);
                k++;
            }

            if(enemy!=null) {

                NotifyDivinity("Minotaur");

                row = worker.getPosition().getRow();
                column = worker.getPosition().getColumn();

                pushrow = row - oldrow;
                pushcolumn = column - oldcolumn;

                if (((pushrow + row) < 5) && ((pushcolumn + column) < 5) && ((pushrow + row) > -1) && ((pushcolumn + column) > -1)) {
                    if (!board.cell[pushrow + row][pushcolumn + column].getisOccupied() && (board.cell[row][column].getLevel() - board.cell[pushrow + row][pushcolumn + column].getLevel() > -2)) {


                        board.cell[pushrow + row][pushcolumn + column].setWorker(enemy);

                        condition = true;

                    }
                }

                if (!condition) {
                    board.cell[oldrow][oldcolumn].setWorker(worker);
                    board.cell[row][column].setWorker(enemy);
                    NotifyResetPosition();
                    NotifyCellIsUnavailable();
                }
                else {
                    NotifyPushedPosition(pushrow + row, pushcolumn + column);
                    NotifyUseDivinityPower();
                }
            }

        }while(!condition);

        showGameBoard(board);

        cellToBuildOn(worker, board);

        showGameBoard(board);
    }
}
