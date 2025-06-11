package it.polimi.ingsw.server.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CellTest {

    Cell cell1, cell2;

    @Before
    public void setUp() {


        cell1 = new Cell(1,1);
        cell2 = new Cell(1,1);
    }

    @After
    public void tearDown()
    {}

    @Test
    public void levelUpTest() {
        cell1.levelUp();
        assertEquals(cell1.getLevel(),1);

        cell1.levelDown();
        assertEquals(cell1.getLevel(),0);



    }

    @Test
    public void setDomeTest() {
        cell1.levelUp();
        cell1.levelUp();
        cell1.levelUp();
        cell1.levelUp();
        assertTrue(cell1.getisOccupied());
        assertTrue(cell1.getDome());
    }
}
