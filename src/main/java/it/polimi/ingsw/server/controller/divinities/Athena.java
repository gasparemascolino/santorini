package it.polimi.ingsw.server.controller.divinities;

import it.polimi.ingsw.server.controller.Divinity;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;

import java.io.IOException;

import static it.polimi.ingsw.server.ClientHandler.*;
import static it.polimi.ingsw.server.controller.WorkerController.*;
import static it.polimi.ingsw.server.model.GameBoard.showGameBoard;

/**
 * Class behaviour of Athena (If one of the player's workers has leveled up in the last turn, enemy workers cannot go to cells with levels greater than theirs)
 * @author Eugenio Facciolo
 */
public class Athena implements Divinity {

    /**
     * @return name of divinity
     */
    @Override
    public String toString() {
        return "Athena";
    }

    /**
     * @return description of divinity power
     */
    public String Description(){
        return("If one of your workers leveled on your last turn, enemy workers cannot level up this turn.\n");
    }

    /**
     * Add Behavior of Athena to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     */
    public void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException {

        SetAthenaPower(false);
        int oldlevel;

        oldlevel = board.cell[worker.getPosition().getRow()][worker.getPosition().getColumn()].getLevel();
        cellToMoveOn(worker, board);

        showGameBoard(board);

        if(oldlevel<board.cell[worker.getPosition().getRow()][worker.getPosition().getColumn()].getLevel()) {

            NotifyUseDivinityPower();

            SetAthenaPower(true);
        }

        cellToBuildOn(worker, board);

        showGameBoard(board);
    }
}
