package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;

import java.io.IOException;

/**
 * Interface for divinities powers
 * @author Eugenio Facciolo
 */
public interface Divinity {

    /**
     * @return description of divinity power
     */
    String Description();

    /**
     * Add Behavior of divinity to the worker
     *
     * @param worker The Worker who need this behavior
     * @param board  The gameboard
     * @throws IOException for input or output exception
     * @throws ClassNotFoundException if the class doesn't exist
     * @throws InterruptedException if the thread was interrupted
     */
    void doAction(Worker worker, GameBoard board) throws IOException, ClassNotFoundException, InterruptedException;

    /**
     * @return Name of Divinity
     */
    @Override
    String toString();
}
