package edu.up.cs301.boggle;

import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jacob on 3/15/2016.
 */
public class BoggleStateTest {

    @Test
    public void testWordLength() throws Exception {
        BoggleState state = new BoggleState();
        boolean lengthLongEnough1 = state.wordLength("hello"); //tests a word long enough
        assertTrue(lengthLongEnough1);
        boolean lengthLongEnough2 = state.wordLength(""); //tests no word
        assertFalse(lengthLongEnough2);
        boolean lengthLongEnough3 = state.wordLength("hi"); //tests a short word
        assertFalse(lengthLongEnough3);
    }
    @Test
    public void testRemoveLetter() throws Exception {
        BoggleState state = new BoggleState();

        int[][] selectedLetters = new int[16][2];

        for (int i = 0; i < 16; i++) {
            selectedLetters[i][0] = 4;
            selectedLetters[i][0] = 4;
        }






        String word1 = ("hello");
        word1 = state.removeLetter(word1, selectedLetters);
        assertEquals(word1, "hell");

        String word2 = ("");
        word2 = state.removeLetter(word2, selectedLetters);
        assertEquals(word2, "");

        String word3 = ("h");
        word3 = state.removeLetter(word2, selectedLetters);
        assertEquals(word3, "");

        String word4 = ("hi");

        //represents a letter selected at 0, 0
        selectedLetters[0][0] = 0;
        selectedLetters[0][1] = 0;

        //represents a letter selected at 1, 0
        selectedLetters[1][0] = 1;
        selectedLetters[1][1] = 0;

        word4 = state.removeLetter(word4, selectedLetters);

        assertEquals(selectedLetters[1][0], 4);



    }

    @Test
    public void testInDictionary() throws Exception {
        BoggleState state = new BoggleState();
        File file = new File("words.txt");
        String word1 = "a";
        String word2 = "dog";
        String word3 = "agy";

        boolean aWord1 = state.inDictionary(word1);
        assertTrue(aWord1);
        boolean aWord2 = state.inDictionary(word2);
        assertTrue(aWord2);
        boolean aWord3 = state.inDictionary(word3);
        assertFalse(aWord3);
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

    @Test
    public void testIsCurrentAdjacentToLast() throws Exception {
        BoggleState state = new BoggleState();

        assertEquals(state.isCurrentAdjacentToLast(0, 1, 0, 0), 1);
        assertEquals(state.isCurrentAdjacentToLast(1, 1, 1, 1), 0);
    }

    @Test
    public void testAddLetter() throws Exception {
        BoggleState state = new BoggleState();

        int[][] selectedLetters = new int[16][2];

        String word = "foo";
        String letter = "b";

        word = state.addLetter(word, selectedLetters, 3, 4, letter);

        assertEquals(word, "foob");

        word = "";
        letter = "a";

        word = state.addLetter(word, selectedLetters, 3, 4, letter);

        assertEquals(word, "a");


    }

    @Test
    public void testCanAdd() throws Exception {
        BoggleState state = new BoggleState();

        int[][] selectedLetters = new int[16][2];
        boolean trueOrFalse = state.canAdd(selectedLetters, 3, 3, 3, 3);
        assertEquals(trueOrFalse, false);


        selectedLetters[0][0] = 1;
        selectedLetters[0][1] = 2;

        trueOrFalse = state.canAdd(selectedLetters, 1, 2, 3, 3);
        assertEquals(trueOrFalse, false);



    }

    @Test
    public void testCanRemove() throws Exception {
        BoggleState state = new BoggleState();


        boolean trueOrFalse = state.canRemove(3, 3, 3, 3);
        assertEquals(trueOrFalse, true);

        trueOrFalse = state.canRemove(3, 3, 3, 4);
        assertEquals(trueOrFalse, false);


    }
}