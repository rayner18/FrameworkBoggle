package edu.up.cs301.boggle;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

/**
 * JUnit Test for testing the functionality of the BoggleState class
 *
 *
 * @version 15 March 2016
 *
 * @author Michael Waitt
 * @author Jacob Kirby
 * @author Charles Rayner
 */
public class BoggleStateTest {

    @Test
    public void testWordLength() throws Exception {

    }

    @Test
    public void testRemoveLetter() throws Exception {

    }

    @Test
    public void testFindFile() throws Exception {

    }

    @Test
    public void testInDictionary() throws Exception {
        BoggleState state = new BoggleState();
        state.wordAvailable();
    }

    @Test
    public void wordAvailable() throws Exception {
        BoggleState state = new BoggleState();
        boolean file2 = state.wordAvailable();
    }

    @Test
    public void testUpdateScore() throws Exception {
        BoggleState state = new BoggleState();
        String longWord = "pneumonoultramicroscopicsilicovolcanoconiosis";
        state.updateScore(longWord);
        assertEquals(state.getPlayer1Score(), 11);

        String littleWord = "hue";
        state.updateScore(littleWord);
        assertEquals(state.getPlayer1Score(), 1);

        String fourLetter = "goku";
        state.updateScore(fourLetter);
        assertEquals(state.getPlayer1Score(), 1);

        String fiveLetter = "hello";
        state.updateScore(fiveLetter);
        assertEquals(state.getPlayer1Score(), 2);

        String sixLetter = "pizzaz";
        state.updateScore(sixLetter);
        assertEquals(state.getPlayer1Score(), 3);

        String sevenLetter = "jacuzzi";
        state.updateScore(sevenLetter);
        assertEquals(state.getPlayer1Score(), 5);
    }

    @Test
    public void testRotateBoard() throws Exception {
        BoggleState state = new BoggleState();
        String[][] gameBoard = state.getGameBoard();
        state.rotateBoard(gameBoard);
        gameBoard[0][0] = "a";
        gameBoard[0][1] = "a";
        gameBoard[0][2] = "a";
        gameBoard[0][3] = "a";
        gameBoard[1][0] = "b";
        gameBoard[1][1] = "b";
        gameBoard[1][2] = "b";
        gameBoard[1][3] = "b";
        gameBoard[2][0] = "c";
        gameBoard[2][1] = "c";
        gameBoard[2][2] = "c";
        gameBoard[2][3] = "c";
        gameBoard[3][0] = "d";
        gameBoard[3][1] = "d";
        gameBoard[3][2] = "d";
        gameBoard[3][3] = "d";

        assertEquals(gameBoard[3][3], "d");
        assertEquals(gameBoard[2][2], "c");
        assertEquals(gameBoard[1][1], "b");
        assertEquals(gameBoard[0][0], "a");
    }

    @Test
    public void testAddToWordBank() throws Exception {
        BoggleState state = new BoggleState();
        String longWord = "pneumonoultramicroscopicsilicovolcanoconiosis";
        state.addToWordBank(longWord);
        assertEquals(state.getWordBank().get(0), longWord);

        String testString = "test";
        state.addToWordBank(testString);
        assertEquals(state.getWordBank().get(1), testString);

        String testString1 = "test";
        state.addToWordBank(testString1);
        assertEquals(state.getWordBank().get(2), testString1);

        String testString2 = "test";
        state.addToWordBank(testString2);
        assertEquals(state.getWordBank().get(3), testString2);

        assertEquals(state.getWordBank().size(), 4);
    }
}