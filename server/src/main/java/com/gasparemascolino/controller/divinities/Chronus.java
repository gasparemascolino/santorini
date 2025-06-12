package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;

import java.io.IOException;

import static com.gasparemascolino.ClientHandler.*;
import static com.gasparemascolino.controller.WorkerController.completetowers;
import static com.gasparemascolino.model.GameBoard.showGameBoard;


/**
 * Class behaviour of Chronus (You win even if there is 5 complete towers on the gameboard)
 * @author Eugenio Facciolo
 */
public class Chronus implements Divinity {

    private static boolean chronuspower;

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Chronus";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("You win even if there is 5 complete towers on the gameboard\n");
    }

    /**
     * Add Behavior of Chronus to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        chronuspower=true;

        cellToMoveOn(worker, board);

        showGameBoard(board);

        cellToBuildOn(worker, board);

        showGameBoard(board);

        if(completetowers >= 5) {

            NotifyUseDivinityPower();
            outputs.get(currentclient-1).writeObject("You Win");
            winCondition();
        }


    }

    public static boolean getChronusPower(){
        return chronuspower;
    }
}
