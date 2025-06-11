package it.polimi.ingsw.server.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PlayerTest {

    Player player1, player2;

    @Before
    public void setUp() {


        player1 = new Player("Alberto", 1, "Apollo", "blue");
        player2 = new Player("Eugenio", 2, "Athena", "red");
    }

    @After
    public void tearDown()
    {}

    @Test
    public void setNicknameCorrectInputCorrectOutput() {

        assertEquals(player1.getNickname(), "Alberto");

    }

    @Test
    public void isFirstPlayerCorrectInputCorrectOutput() {
        assertTrue(player1.isFirstplayer());
        assertFalse(player2.isFirstplayer());
    }

    @Test
    public void DivinityCorrectInputCorrectOutput() {
        assertEquals(player1.getDivinity(), "Apollo");
        assertEquals(player2.getDivinity(), "Athena");
    }


}
