package it.polimi.ingsw.server.model;
import java.util.ArrayList;

/**
 * Class of the GameBoard
 * @author Eugenio Facciolo
 */
public class GameBoard {

    public Cell[][] cell;
    public ArrayList<Player> players = new ArrayList<>();

    /**
     * builder of GameBoard, set each Cell of GameBoard is free
     */
    public GameBoard() {

        Cell[][] cell = new Cell[5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {

                cell[i][j] = new Cell(i,j);

            }
        }

       this.cell = cell;
    }


    /**
     * Method that prints the current state of the GameBoard on the server
     *
     * @param board current state of the GameBoard
     */
    public static void showGameBoard(GameBoard board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (board.cell[i][j].getWorker() == null) {
                    if (board.cell[i][j].getDome())
                        System.out.print("\u001B[35m" + board.cell[i][j].getLevel() + "        ");
                    else
                        System.out.print("\u001B[0m" + board.cell[i][j].getLevel() + "        ");
                }
                else if(board.cell[i][j].getWorker().getColor().equals("blue"))
                    System.out.print("\u001B[34m" + board.cell[i][j].getLevel() + " w"+board.cell[i][j].getWorker().getIndex()+"     ");
                else if(board.cell[i][j].getWorker().getColor().equals("red"))
                    System.out.print("\u001B[31m" + board.cell[i][j].getLevel() + " w"+board.cell[i][j].getWorker().getIndex()+"     ");
                else if(board.cell[i][j].getWorker().getColor().equals("green"))
                    System.out.print("\u001B[32m" + board.cell[i][j].getLevel() + " w"+board.cell[i][j].getWorker().getIndex()+"     ");




            }
            System.out.println("\n");
        }
        System.out.println("\u001B[0m");
    }

}
