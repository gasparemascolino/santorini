package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;

import java.io.IOException;

import static com.gasparemascolino.ClientHandler.*;
import static com.gasparemascolino.model.GameBoard.showGameBoard;


/**
 * Class behaviour of Pan (win even if your worker has fallen two or more levels)
 * @author Alberto Leo
 */
public class Pan implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Pan";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("You win even if your worker has fallen two or more levels\n");
    }

    /**
     * Add Behavior of Pan to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        int oldlevel;

        oldlevel = worker.getPosition().getLevel();

        cellToMoveOn(worker, board);

        showGameBoard(board);

        if(oldlevel - worker.getPosition().getLevel() >= 2) {

                NotifyUseDivinityPower();
                outputs.get(currentclient-1).writeObject("YouWin");
                winCondition();

        }

        cellToBuildOn(worker, board);

        showGameBoard(board);

    }
}
