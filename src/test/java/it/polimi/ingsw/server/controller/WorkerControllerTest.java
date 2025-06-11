package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Cell;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static it.polimi.ingsw.server.controller.WorkerController.*;

public class WorkerControllerTest {

    Player player;
    GameBoard board;
    int flag = 0;

    @Before
    public void setUp() {

        player = new Player("Alberto", 1, "Apollo","blue");

        board = new GameBoard();

        board.cell[0][0].setWorker(player.getWorker(1));


    }

    @After
    public void tearDown()
    {}

    @Test
    public void MoveTest(){

        Move(player.getWorker(1),1,1,board);
        assertEquals(player.getWorker(1), board.cell[1][1].getWorker());


    }

    @Test
    public void BuildTest() {

        Build(player.getWorker(1),1,1,board);
        assertEquals(board.cell[1][1].getLevel(),1);



    }

    @Test
    public void enableToMoveTest() {

        player.getWorker(1).setPosition(board.cell[1][1]);
        board.cell[1][2].setisOccupied(true);

        board.cell[1][1].levelUp();

        board.cell[2][2].levelUp();
        board.cell[2][2].levelUp();
        board.cell[2][2].levelUp();

        board.cell[2][1].levelUp();
        board.cell[2][1].levelUp();

        // Cella occupata non compare tra le celle disponibili per il movimento
        int k=0;

        while ( enableToMove(player.getWorker(1),board)[k]!=null ) {
            assertNotEquals(enableToMove(player.getWorker(1),board)[k], board.cell[1][2]);
            k++;
        }

        // Costruttore non deve poter salire dal livello 1 al livello 3
        k=0;
        while (enableToMove(player.getWorker(1),board)[k]!=null ) {
            assertNotEquals(enableToMove(player.getWorker(1),board)[k], board.cell[2][2]);
            k++;
        }

        // Costruttore deve poter salire dal livello 1 al livello 2
        k=0;
        while (enableToMove(player.getWorker(1),board)[k]!=null ) {

            if(enableToMove(player.getWorker(1),board)[k].equals(board.cell[2][1]))
                flag = 1;

            k++;
        }

        if (flag == 0) {
            assertEquals(1,2);
        }
        else
            assertEquals(1,1);

        // Costruttore non deve poter salire dal livello 1 al livello 3
        k=0;
        while (enableToMove(player.getWorker(1),board)[k]!=null ) {

            assertNotEquals(enableToMove(player.getWorker(1),board)[k], board.cell[2][2]);

            k++;
        }



    }

    @Test
    public void enableToBuildTest() {

        player.getWorker(1).setPosition(board.cell[1][1]);
        board.cell[1][2].setisOccupied(true);

        int k=0;
        while (enableToBuild(player.getWorker(1),board)[k]!=null ){
            assertNotEquals(enableToBuild(player.getWorker(1),board)[k], board.cell[1][2]);
            k++;
        }

    }

    @Test
    public void enemyPositionsTest() {
        Player player1 = new Player("Alberto", 1, "Apollo", "blue");
        Player player2 = new Player("Gaspare", 2, "Apollo", "red");
        Player player3 = new Player("Eugenio", 3, "Apollo", "green");

        board.cell[1][1].setWorker(player1.getWorker(1));

        board.cell[1][2].setWorker(player2.getWorker(1));
        board.cell[2][2].setWorker(player2.getWorker(2));
        board.cell[2][1].setWorker(player3.getWorker(1));
        board.cell[0][0].setWorker(player3.getWorker(2));

        Cell[] enemyposition = enemyPositions(player1.getWorker(1), board);

        int k=0;
        while (enemyposition[k] !=null ){
            assertNotEquals(enemyposition[k], board.cell[0][1]);
            assertNotEquals(enemyposition[k], board.cell[0][2]);
            assertNotEquals(enemyposition[k], board.cell[2][0]);
            assertNotEquals(enemyposition[k], board.cell[1][0]);
            k++;
        }

        assert enemyposition[0] != null;
        assertEquals(enemyposition[0].getRow(), 0);
        assertEquals(enemyposition[0].getColumn(), 0);
        assertEquals(enemyposition[1].getRow(), 1);
        assertEquals(enemyposition[1].getColumn(), 2);
        assertEquals(enemyposition[2].getRow(), 2);
        assertEquals(enemyposition[2].getColumn(), 1);
        assertEquals(enemyposition[3].getRow(), 2);
        assertEquals(enemyposition[3].getColumn(), 2);



    }

}
