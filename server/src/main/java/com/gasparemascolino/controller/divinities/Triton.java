package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.ClientHandler;
import com.gasparemascolino.model.Cell;
import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;

import java.io.IOException;


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

        cellmovedon = ClientHandler.cellToMoveOn(worker, board);

        GameBoard.showGameBoard(board);



        while (cellmovedon.getRow() == 0 || cellmovedon.getRow() == 4 || cellmovedon.getColumn() == 0 || cellmovedon.getColumn() == 4) {

            choose = ClientHandler.NotifyDivinityPowerOn();

            if (choose == 1) {

                 ClientHandler.NotifyUseDivinityPower();
                 cellmovedon = ClientHandler.cellToMoveOn(worker, board);
                 GameBoard.showGameBoard(board);
                 ClientHandler.NotifyDivinityPowerOff();

             }
             else {

                   ClientHandler.NotifyDivinityPowerOff();
                   break;
             }
        }


        ClientHandler.cellToBuildOn(worker, board);

        GameBoard.showGameBoard(board);

    }
}
