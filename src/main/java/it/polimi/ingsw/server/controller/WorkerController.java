package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Worker;


/**
 * Class for worker actions
 * @author Gaspare Mascolino
 */
public class WorkerController {




    /**
     * When a Player wants to move on his worker in a near cell, choose his worker and a cell
     *
     * @param worker worker to move
     * @param board  GameBoard
     * @param row    which row in GameBoard
     * @param column which column in GameBoard
     * @return true if the movement has occurred, otherwise false
     */
    public static boolean Move(Worker worker, int row, int column, GameBoard board) {

        Cell[] availableposition = enableToMove(worker, board);

        int k = 0;
        while (k<8 && availableposition[k] != null) {
            if (availableposition[k].equals(board.cell[row][column])) {

                board.cell[worker.getPosition().getRow()][worker.getPosition().getColumn()].setWorker(null);
                board.cell[row][column].setWorker(worker);

                return true;
            }
            k++;
        }

        return false;

    }

    public static int completetowers=0;

    /**
     * When a Player wants to level up a cell near him, choose his worker and a cell to build on player who controls the worker who wants to level up a cell
     *
     * @param worker worker to build
     * @param board  GameBoard
     * @param row    which row in GameBoard
     * @param column which column in GameBoard
     * @return true if the building has occurred, otherwise false
     */
    public static boolean Build(Worker worker, int row, int column, GameBoard board)  {

        Cell[] availableposition = enableToBuild(worker, board);

        int k = 0;
        while (k<8 && availableposition[k] != null) {
            if (availableposition[k].equals(board.cell[row][column])) {
                board.cell[row][column].levelUp();
                if(board.cell[row][column].getLevel()==4) completetowers++;
                return true;
            }
            k++;
        }
        return false;
    }



    // When it is true the workers of the other players can't level up
    public static boolean athenapower = false;

    /**
     *
     * @param condition of the Athena power
     */
    public static void SetAthenaPower(boolean condition) {
        athenapower = condition;
    }

    /**
     * Method use to know where the Worker can move in occupied cell by a enemy's worker
     *
     * @param worker The worker who wants to move
     * @param board  GameBoard
     * @return a vector of Cells free where the Worker can move in
     */
    public static Cell[] enableToMove(Worker worker, GameBoard board) {

        Cell[] availableposition = new Cell[8];
        int k = 0;

        for (int i = worker.getPosition().getRow() - 1; i <= worker.getPosition().getRow() + 1; i++) {
            for (int j = worker.getPosition().getColumn() - 1; j <= worker.getPosition().getColumn() + 1; j++) {

                if (i != worker.getPosition().getRow() || j != worker.getPosition().getColumn()) {

                    if ((i >= 0 && i < 5) && (j >= 0 && j < 5)) {

                        if (!board.cell[i][j].getisOccupied() && !board.cell[i][j].getDome() && (board.cell[worker.getPosition().getRow()][worker.getPosition().getColumn()].getLevel() - board.cell[i][j].getLevel() > -2)) {

                            if (!((athenapower) && (board.cell[worker.getPosition().getRow()][worker.getPosition().getColumn()].getLevel() - board.cell[i][j].getLevel() < 0))) {
                                availableposition[k] = board.cell[i][j];
                                k++;
                            }
                        }
                    }
                }

            }

        }
        return availableposition;
    }

    /**
     * Method use to know where the Worker can build
     *
     * @param worker The worker who wants to build
     * @param board  BoardGame
     * @return a vector of Cells free where the Worker can build
     */
    public static Cell[] enableToBuild(Worker worker, GameBoard board) {
        Cell[] availableposition = new Cell[8];
        int k = 0;

        for (int i = worker.getPosition().getRow() - 1; i <= worker.getPosition().getRow() + 1; i++) {
            for (int j = worker.getPosition().getColumn() - 1; j <= worker.getPosition().getColumn() + 1; j++) {

                if (i != worker.getPosition().getRow() || j != worker.getPosition().getColumn()) {

                    if ((i >= 0 && i < 5) && (j >= 0 && j < 5)) {

                        if (!board.cell[i][j].getisOccupied() && !board.cell[i][j].getDome()) {
                            availableposition[k] = board.cell[i][j];
                            k++;
                        }

                    }
                }
            }

        }
        return availableposition;
    }

    /**
     * Method use to know where the enemy's workers are located
     *
     * @param worker The worker who wants to move
     * @param board  BoardGame
     * @return a vector of Cells where there are enemy's Workers near the worker
     */
    public static Cell[] enemyPositions(Worker worker, GameBoard board) {
        Cell[] availableposition = new Cell[8];
        int k = 0;

        for (int i = worker.getPosition().getRow() - 1; i <= worker.getPosition().getRow() + 1; i++) {
            for (int j = worker.getPosition().getColumn() - 1; j <= worker.getPosition().getColumn() + 1; j++) {

                if (i != worker.getPosition().getRow() || j != worker.getPosition().getColumn()) {

                    if ((i >= 0 && i < 5) && (j >= 0 && j < 5)) {
                        if((board.cell[i][j].getisOccupied()) && !board.cell[i][j].getDome() && (!board.cell[i][j].getWorker().getColor().equals(worker.getColor()))) {
                            availableposition[k] = new Cell(i,j);
                            availableposition[k].setWorker(board.cell[i][j].getWorker());
                            board.cell[i][j].setisOccupied(false);
                            k++;
                        }
                    }
                }
            }
        }
        return availableposition;
    }


}
