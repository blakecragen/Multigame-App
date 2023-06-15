package com.example.cs2340Project;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class to help manage the functionality of the wordle game.
 *
 * @version 1.0
 */
public class wordleGameFunctionality {

    /** LinkedList-backed/Stack-backed hashmap for finding positions of words. */
    private Node[] wordHashmap;

    /** Int array to represent the player's level of correctness.
     * 0 - Represents completely incorrect
     * 1 - Represents letter is in word, but in incorrect position.
     * 2 - Represents letter is in word and in correct position.
     */
    private int[] playerCorrectNess;

    /** String of the correct word. */
    private String solution;

    /**
     * Constructor for the class to opperate the correct word/
     * player correctness on guesses.
     */
    public wordleGameFunctionality() {
        wordHashmap = new Node[5];
        playerCorrectNess = new int[5];
    }

    /**
     * Private node class to create a LinkedList-backed stack.
     *
     * @version 1.0
     */
    private class Node {
        /** Next node being pointed to. */
        private Node next;
        /** The letter being held in the node. */
        private char let;
        /** Indice of the letter held in the ndoe. */
        private int pos;

        /** Constructore for the node.
         *
         * @param pos Indice of the letter's location.
         * @param let Letter held in the node.
         * @param next Next node being pointed to.
         */
        public Node (int pos, char let, Node next) {
            this.next = next;
            this.let = let;
            this.pos = pos;
        }

        /**
         * Secondary constructor of the node.
         *
         * @param pos Indice of the letter.
         * @param let Letter being represented.
         */
        public Node (int pos, char let) {
            this(pos, let, null);
        }

        /**
         * Equals method to check if the pos and letter passed
         * are equivalent to this node's pos and let.
         */
        public boolean equals(int pos, char let) {
            if (pos == this.pos && this.let == let) {
                return true;
            }
            return false;
        }
    }

    /**
     * Method to generate a hashcode/get the ind of a letter in the hashmap.
     *
     * @param currLet Letter to create hashcode for.
     * @return An integer of the hashcode.
     */
    public int hashcode(char currLet) {
        currLet = Character.toLowerCase(currLet);
        int val = ((int) currLet - (int) 'a') % wordHashmap.length;
        boolean hasBeenFound = false;
        while (!hasBeenFound) {
            if (wordHashmap[val] == null || wordHashmap[val].let == currLet) {
                return val;
            } else {
                val = (val + 1) % wordHashmap.length;
            }
        }
        return val;
    }

    /**
     * Method to push the letters of a word and position of letter to the hashmap.
     *
     * @param let Letter getting added.
     * @param pos Indice/position of the letter in the original String.
     */
    public void push(int pos, char let) {
        int ind = this.hashcode(let);
        if (wordHashmap[ind] == null) {
            wordHashmap[ind] = new Node(pos, let);
        } else {
            Node last = wordHashmap[ind];
            while (last.next != null) {
                last = last.next;
            }
            last.next = new Node(pos, let);
        }
    }

    /**
     * Method to push the letters of a word and position of letter to the hashmap.
     *
     * @param node Node to add to the hashmap.
     */
    public void push(Node node) {
        this.push(node.pos, node.let);
    }

    /**
     * Method to get the Node containing a letter.
     *
     * @param let Letter key beinglooked for
     * @return Node value associated with the key
     *      (null if it is not a part of the solution).
     */
    public Node get(char let) {
        int pos = this.hashcode(let);
        Node out = wordHashmap[pos];
        if (out == null) {
            return out;
        } else {
            while (out != null) {
                if (out.let == let) {
                    return out;
                }
                out = out.next;
            }
            return out;
        }
    }

    /**
     * Method to clear the current word.
     */
    public void clear() {
        wordHashmap = new Node[5];
        playerCorrectNess = new int[5];
    }

    /**
     * Method to remove a letter given its position and return whether
     * it was found or not.
     */
    public boolean remove(int pos, char let) {
        int ind = this.hashcode(let);
        Node follower = wordHashmap[ind];
        if (follower == null) {
            return false;
        } else {
            if (follower.next == null) {
                if (follower.equals(pos, let)) {
                    wordHashmap[ind] = null;
                    return true;
                } else {
                    return false;
                }
            } else {
                Node leader = follower.next;
                while (leader != null) {
                    if (leader.equals(pos,let)) {
                        follower = leader.next;
                        return true;
                    } else {
                        leader = leader.next;
                        follower = follower.next;
                    }
                }
                return false;
            }
        }
    }

    /**
     * Method to randomly select a new word from previous
     * wordle words.
     */
    public void selectNewWord() {
        Random rand = new Random();
        int whichWord = rand.nextInt(2311); // There are 2310 words in the "answers.txt" file.
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            String myWord = "hello"; // Dummy word to keep from potentially erroring.
            while (myReader.hasNextLine() && i != whichWord) {
                myWord = myReader.nextLine();
                ++i;
            }
            setNewWord(myWord);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method to set a given word as the class's correct word.
     *
     * @param word Word to be set to the correct word.
     * @throws java.lang.IllegalArgumentException When word is not 5 characters.
     */
    public void setNewWord(String word) {
        if (word.length() != 5) {
            throw new IllegalArgumentException("Word given does not have lenght 5");
        }
        this.clear();
        solution = word;
        for (int i = 0; i < word.length(); ++i) {
            push(i, word.charAt(i));
        }
    }



    public int[] checkGuess(String guess) {
        // First pass: check for correct letters in the whole word.
        boolean[] correctLetters = checkGuessCorrLets(guess);
        for (int i = 0; i < 5; ++i) {
            if (correctLetters[i]) {
                playerCorrectNess[i] = 2;
            }
        }
        return null;
    }

    private boolean[] checkGuessCorrLets(String guess) {
        boolean[] match = new boolean[5];
        for (int c = 0; c < guess.length(); c++) {
            match[c] = this.remove(c, guess.charAt(c));
            // set UI with index c to green
        }
        return match;
    }
}
