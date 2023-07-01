package com.example.cs2340Project;

public class blackJackCard {
    private int value;
    private char suite;
    private String type;

    /**
     * Constructor for a single card
     *
     * @param value Card's value
     * @param suite Suite of the card (spaces, clubs, etc.)
     * @param type Type of card (for faces)
     */
    public blackJackCard(int value, char suite, String type) {
        this.suite = suite;
        this.value = value;
        this.type = type;
    }

    public blackJackCard(int value, char suite) {
        this(value, suite, null);
    }

    /**
     * Setter for value of card
     *
     * @param value Card's value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Getter for card's value
     *
     * @return Card's value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Setter for card's suite.
     *
     * @param suite Suite of card
     */
    public void setSuite(char suite) {
        this.suite = suite;
    }

    /**
     * Getter for card's suite
     *
     * @return Card's suite
     */
    public char getSuite() {
        return this.suite;
    }

    /**
     * Setter for the card's type
     *
     * @param type Type of card (for face cards)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for the card's type
     *
     * @return Card's type
     */
    public String getType() {
        return this.type;
    }
}
