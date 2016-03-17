package edu.up.cs301.boggle;

import java.util.regex.Pattern;
import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by Jacob on 3/15/2016.
 */
public class BoggleStateTest {

    @Test
    public void testWordLength() throws Exception {



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
    public void testWordAvailable() throws Exception {
        BoggleState state = new BoggleState();
        boolean file2 = state.wordAvailable();
    }

    @Test
    public void testIsCurrentAdjacentToLast() throws Exception {
        BoggleState state = new BoggleState();

        assertEquals(state.isCurrentAdjacentToLast(0, 1, 0, 0), 1);
        assertEquals(state.isCurrentAdjacentToLast(1, 1, 1, 1), 0);
    }
}