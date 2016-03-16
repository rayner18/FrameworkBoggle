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
}