package edu.up.cs301.boggle;

import java.io.File;
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

    }

    @Test
    public void testFindFile() throws Exception {

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
    public void wordAvailable() throws Exception {
        BoggleState state = new BoggleState();
        Boolean file2 = state.wordAvailable();
        assertTrue(file2);
    }
}