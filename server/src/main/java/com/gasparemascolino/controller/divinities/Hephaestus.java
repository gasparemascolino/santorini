package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.model.Cell;
import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;

import java.io.IOException;

import static com.gasparemascolino.ClientHandler.*;
import static com.gasparemascolino.model.GameBoard.showGameBoard;

/**
 * Class behaviour of Hephaestus (can build an additional block (not a dome) above the first block)
 * @author Gaspare Mascolino
 */
public class Hephaestus implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Hephaestus";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("Your worker can build an additional block (not a dome) above the first block. \n");
    }

    /**
     * Add Behavior of Hephaestus to the worker
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

        if(cellbuilt.getLevel() < 3) {
           choose = NotifyDivinityPowerOnExtra();
             if(choose == 1) {

                 NotifyUseDivinityPower();

                 cellbuilt.levelUp();
                 NotifyBuild(cellbuilt.getRow(),cellbuilt.getColumn());

                 showGameBoard(board);
             }

        }


        NotifyDivinityPowerOff();

    }
}
