package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.model.Cell;
import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;

import java.io.IOException;

import static com.gasparemascolino.ClientHandler.*;
import static com.gasparemascolino.model.GameBoard.showGameBoard;


/**
 * Class behaviour of Artemis (Worker can move twice in this turn)
 * @author Gaspare Mascolino
 */
public class Artemis implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Artemis";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can move once more, but cannot return to the cell from which he started.\n");
    }

    /**
     * Add Behavior of Artemis to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        Cell oldposition;
        int choose;

        oldposition = worker.getPosition();

        cellToMoveOn(worker, board);

        showGameBoard(board);


        choose = NotifyDivinityPowerOn();

        if(choose == 1) {
            NotifyUseDivinityPower();

            oldposition.setisOccupied(true);
            cellToMoveOn(worker, board);
            oldposition.setisOccupied(false);

            showGameBoard(board);

        }

        NotifyDivinityPowerOff();

        cellToBuildOn(worker, board);

        showGameBoard(board);

    }
}
