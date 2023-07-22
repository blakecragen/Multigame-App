package com.example.cs2340Project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WordleFunctionalityTests {

    @Test
    public void testInitialization() {
        WordleGameFunctionality correctWord = new WordleGameFunctionality();
        assertNull(correctWord.getSolution());
    }

    @Test
    public void testSelectNewWord() {
        WordleGameFunctionality correctWord = new WordleGameFunctionality();

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
    public void testCheckGuessCorrectLetters() {
        WordleGameFunctionality correctWord = new WordleGameFunctionality();

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
    }

    @Test
    public void checkGuessValid() {
        WordleGameFunctionality wordle = new WordleGameFunctionality();
        String word = "hello";
        assertEquals(true, wordle.checkGuessValid(word) >= 0);
        word = "horse";
        assertEquals(true, wordle.checkGuessValid(word) >= 0);
        word = "this";
        assertEquals(true, wordle.checkGuessValid(word) < 0);
        word = "mall";
        assertEquals(true, wordle.checkGuessValid(word) < 0);
        word = "frail";
        assertEquals(true, wordle.checkGuessValid(word) >= 0);
        word = "htrys";
        assertEquals(true, wordle.checkGuessValid(word) < 0);
    }

    @Test
    public void testHashcode() {
        WordleGameFunctionality hF = new WordleGameFunctionality();
        char ascii = 'a';
        assertEquals(2, hF.hashcode(ascii));
        ascii = 'b';
        assertEquals(3, hF.hashcode(ascii));
        ascii = 'c';
        assertEquals(4, hF.hashcode(ascii));
        ascii = 'd';
        assertEquals(0, hF.hashcode(ascii));
        ascii = 'e';
        assertEquals(1, hF.hashcode(ascii));
        assertEquals(hF.hashcode('e'), hF.hashcode(ascii));
    }

    @Test
    public void testClear() {
        WordleGameFunctionality game = new WordleGameFunctionality();
        game.push(0, 'a');
        game.push(1, 'l');
        assertTrue(game.get(0, 'a'));
        game.clear();
        assertFalse(game.get(0,'a'));
    }

    @Test
    public void testExistingLettersWrongIndex() {
        WordleGameFunctionality correctWord = new WordleGameFunctionality();
        String ans = "candy";
        int[] expected = new int[]{1, 0, 0, 1, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("yoink");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "strap";
        expected = new int[]{1, 0, 0, 0, 1};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("penis");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
        ans = "hello";
        expected = new int[]{1, 0, 0, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("eager");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());
    }

    @Test
    public void testGet() {
        WordleGameFunctionality game = new WordleGameFunctionality();
        game.push(0,'a');
        game.push(1,'b');
        game.push(2,'c');
        assertTrue(game.get(0,'a'));
        assertFalse(game.get(1,'g'));
    }

    @Test
    public void testNodeClass() {
        Node newNode = new Node(1, 'a');
        assertNull(newNode.next);
        Node node2 = new Node(1, 'b');
        newNode.next = node2;
        assertNull(newNode.next.next);
    }

    @Test
    public void  testPush() {
        WordleGameFunctionality wordle = new WordleGameFunctionality();
        wordle.push(new Node(0,'a'));
        wordle.push(new Node(6,'b'));
        wordle.push(new Node(11,'c'));
        Node[] map1 = wordle.getWordHashmap();
        Node[] map2 = wordle.mapDeepCopy();
        assertNotEquals(map2, map1);
        assertEquals(true, map2[0].equals(map1[0].pos, map1[0].let));
    }

    @Test
    public void testRemove() {
        WordleGameFunctionality wordle = new WordleGameFunctionality();
        wordle.push(0, 'a');
        wordle.push(1, 'b');
        wordle.push(2, 'c');
        wordle.push(3, 'd');
        wordle.push(4, 'e');
        wordle.remove(0,'a');
        Node[] map = wordle.getWordHashmap();
        assertNull(map[0]);
        assertEquals(1,map[1].pos);
        assertEquals('b',map[1].let);
        wordle.remove(1,'b');
        map = wordle.getWordHashmap();
        assertNull(map[1]);
    }

    @Test
    public void testCheckGuess() {
        WordleGameFunctionality correctWord = new WordleGameFunctionality();

        // Check if the method works if a guess is completely correct.
        String ans = "Hello";
        int[] expected = {2,2,2,2,2};

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
        ans = "trace";
        expected = new int[]{2, 1, 1, 0, 0};
        correctWord.setNewWord(ans);
        correctWord.checkGuess("tarry");
        assertArrayEquals(expected, correctWord.getPlayerCorrectness());

        // A game of wordle I played and it's results:
        correctWord.setNewWord("strap");
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
