package it.polimi.ingsw.server.model;

import org.junit.After;
import org.junit.Before;

public class WorkerTest {

    Player player1;
    GameBoard board;

    @Before
    public void setUp() {

        player1 = new Player("Alberto", 1, "Atlas", "blue");

        board = new GameBoard();


    }

    @After
    public void tearDown()
    {}


}
