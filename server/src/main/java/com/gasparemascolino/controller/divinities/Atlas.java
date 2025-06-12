package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.model.Cell;
import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;

import java.io.IOException;

import static com.gasparemascolino.ClientHandler.*;
import static com.gasparemascolino.model.GameBoard.showGameBoard;

/**
 * Class behaviour of Atlas (Add a Dome in a Cell at every construction level)
 * @author Alberto Leo
 */

public class Atlas implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Atlas";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can build a dome on any level, including the terrain.\n");
    }

    /**
     * Add Behavior of Atlas to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        Cell cell;
        int choose;

        cellToMoveOn(worker, board);

        showGameBoard(board);

       choose = NotifyDivinityPowerOn();

        if(choose == 1) {
            NotifyUseDivinityPower();

            cell = cellToBuildOn(worker, board);
            cell.levelDown();
            cell.setDome();
            NotifySetDome(cell.getRow(),cell.getColumn());

        } else {
            cellToBuildOn(worker, board);
        }

        NotifyDivinityPowerOff();

        showGameBoard(board);

    }
}
