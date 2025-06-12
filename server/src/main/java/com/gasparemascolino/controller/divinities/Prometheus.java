package com.gasparemascolino.controller.divinities;

import com.gasparemascolino.model.Cell;
import com.gasparemascolino.controller.Divinity;
import com.gasparemascolino.model.GameBoard;
import com.gasparemascolino.model.Worker;

import java.io.IOException;

import static com.gasparemascolino.ClientHandler.*;
import static com.gasparemascolino.controller.WorkerController.enableToMove;
import static com.gasparemascolino.model.GameBoard.showGameBoard;

/**
 * Class behaviour of Prometheus
 * @author Alberto Leo
 */
public class Prometheus implements Divinity {


    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Prometheus";
    }

    /**
     * @return description of divinity power
     */
    public String Description() {
        return("If your worker doesn't level up, he can builds before and after the movement\n");
    }

    /**
     * Add Behavior of Prometheus to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        int choose;
        int k=0;

        choose = NotifyDivinityPowerOn();

        if (choose == 1) {

            NotifyUseDivinityPower();

            cellToBuildOn(worker, board);

            showGameBoard(board);

            Cell[] availableposition = enableToMove(worker, board);

            while (availableposition[k] != null) {

                if(worker.getPosition().getLevel()<availableposition[k].getLevel()){
                    availableposition[k].setisOccupied(true);
                }
                k++;
            }

            cellToMoveOn(worker, board);

            k=0;

            while (availableposition[k]!=null) {

                if(worker.getPosition().getLevel()<availableposition[k].getLevel()){
                    availableposition[k].setisOccupied(false);
                }
                k++;
            }


        }
        else {
            cellToMoveOn(worker, board);
        }
        showGameBoard(board);

        NotifyDivinityPowerOff();

        cellToBuildOn(worker, board);
        showGameBoard(board);

    }
}