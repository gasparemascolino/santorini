package com.gasparemascolino.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    GameBoard board;

    @Before
    public void setUp() {
        board = new GameBoard();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void setGameBoard() {
        assertFalse(board.cell[1][2].getisOccupied());
        assertEquals(board.cell[1][2].getLevel(),0);
    }

    @Test
    public void showGameBoardTest() {
        GameBoard.showGameBoard(board);
    }






}



