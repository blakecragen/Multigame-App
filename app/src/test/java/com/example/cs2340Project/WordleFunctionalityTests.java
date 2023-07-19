package com.example.cs2340Project;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordleFunctionalityTests {
    private wordleGameFunctionality wordle;

    @Before
    public void init() {
        wordle = new wordleGameFunctionality();
    }

    @Test
    public void testHash() {
        int aHash = wordle.hashcode('a');
        int zHash = wordle.hashcode('z');
        assertEquals(0, aHash);
        assertEquals(wordle.hashcode('a'), aHash);
        assertEquals(aHash, zHash);
        assertNotEquals(5, aHash);
    }

    @Test
    public void rejectInvalidSolution() {
        assertThrows(IllegalArgumentException.class, () -> wordle.setNewWord("code"));
    }

    @Test
    public void testInitialization() {
        wordleGameFunctionality correctWord = new wordleGameFunctionality();
        assertNull(correctWord.getSolution());
    }

    @Test
    public void testSetNewWord() {
        wordleGameFunctionality correctWord = new wordleGameFunctionality();
        correctWord.setNewWord("Hello");
        assertEquals("Hello", correctWord.getSolution());
        correctWord.selectNewWord();
        assertNotEquals("Hello", correctWord.getSolution());
        String temp = correctWord.getSolution();
        correctWord.selectNewWord();
        assertNotEquals(temp, correctWord.getSolution());
        temp = correctWord.getSolution();
        correctWord.selectNewWord();
        assertNotEquals(temp, correctWord.getSolution());
        temp = correctWord.getSolution();
        correctWord.selectNewWord();
        assertNotEquals(temp, correctWord.getSolution());
    }

    @Test
    public void testCheckGuess() {
        wordleGameFunctionality correctWord = new wordleGameFunctionality();

        // Check if the method works if a guess is completely correct.
        String ans = "Hello";
        correctWord.setNewWord(ans);
        int[] expected = {2,2,2,2,2};
        correctWord.checkGuess(ans);
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "Calls";
        correctWord.setNewWord(ans);
        correctWord.checkGuess(ans);
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "Hello";
        correctWord.setNewWord(ans);
        correctWord.checkGuess(ans);
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "yeeHe";
        correctWord.setNewWord(ans);
        correctWord.checkGuess(ans);
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "tests";
        correctWord.setNewWord(ans);
        correctWord.checkGuess(ans);
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "tEstS";
        correctWord.setNewWord(ans);
        correctWord.checkGuess("TeSTs");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        // Test if part of word is correct and rest incorrect
        ans = "Hello";
        expected = new int[]{0, 2, 0, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("TeSTs");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "yoink";
        expected = new int[]{0, 2, 2, 2, 2};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("zoink");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "china";
        expected = new int[]{2, 2, 2, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("child");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "guess";
        expected = new int[]{0, 0, 0, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("chill");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "pairs";
        expected = new int[]{2, 2, 0, 0, 2};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("palms");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        // Test if letters are correct in wrong spot (with correct letters)
        ans = "hello";
        expected = new int[]{1, 0, 0, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("eager");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "candy";
        expected = new int[]{1, 0, 0, 1, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("yoink");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "trace";
        expected = new int[]{2, 1, 1, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("tarry");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        // A game of wordle I played and it's results:
        ans = "strap";
        expected = new int[]{1, 0, 0, 0, 1};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("penis");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 1, 0, 0, 0};
        correctWord.checkGuess("spoil");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 0, 0, 2};
        correctWord.checkGuess("stump");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 2, 2, 2};
        correctWord.checkGuess("strap");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        // Game 2:
        ans = "billy";
        expected = new int[]{0, 0, 2, 2, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("Hello");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 0, 2, 2, 2};
        correctWord.checkGuess("jelly");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 0, 2, 2, 2};
        correctWord.checkGuess("bully");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 2, 2, 2, 2};
        correctWord.checkGuess("silly");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 2, 2, 2};
        correctWord.checkGuess("billy");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        // Game 3:
        ans = "sheer";
        expected = new int[]{0, 1, 0, 0, 1};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("crane");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{1, 0, 0, 0, 1};
        correctWord.checkGuess("range");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 0, 1, 2, 1};
        correctWord.checkGuess("fires");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 0, 1, 2, 2};
        correctWord.checkGuess("poser");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 2, 2, 2};
        correctWord.checkGuess("sheer");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        //Game 4:
        ans = "scant";
        expected = new int[]{1, 0, 2, 2, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("crane");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 2, 2, 2};
        correctWord.checkGuess("scant");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        //Game 5:
        ans = "wacky";
        expected = new int[]{1, 0, 1, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("crane");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 2, 0, 0, 2};
        correctWord.checkGuess("parry");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{1, 2, 2, 0, 0};
        correctWord.checkGuess("yacht");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 2, 2, 2};
        correctWord.checkGuess("wacky");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 0, 0, 0, 0};
        correctWord.checkGuess("hello");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        //Game 6:
        ans = "ranch";
        expected = new int[]{0, 0, 1, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("plays");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 2, 1, 2, 2};
        correctWord.checkGuess("march");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 0, 2, 2};
        correctWord.checkGuess("ratch");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 2, 2, 2};
        correctWord.checkGuess("ranch");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        //Game 7:
        ans = "ranch";
        expected = new int[]{0, 0, 0, 1, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("plead");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{0, 1, 1, 1, 0};
        correctWord.checkGuess("shart");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{1, 0, 1, 0, 1};
        correctWord.checkGuess("abhor");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        expected = new int[]{2, 2, 2, 2, 2};
        correctWord.checkGuess("ranch");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
    }
}
