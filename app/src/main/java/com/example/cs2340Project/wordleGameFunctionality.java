/**
 * Class to help manage the functionality of the wordle game.
 * 
 * @version 1.0
 */
public class wordleGameFunctionality {

    /** LinkedList-backed/Stack-backed hashmap for finding positions of words. */
    public Node[] wordHashmap;

    public wordleGameFunctionality() {
        wordHashmap = new Node[5];
    }
    
    /**
     * Private node class to create a LinkedList-backed stack.
     *
     * @version 1.0
     */
    private class Node {
        private Node next;
        private char let;
        private int pos;
        
        public Node (int pos, char let, Node next) {
            this.next = next;
            this.let = let;
            this.pos = pos;
        }
        
        public Node (int pos, char let) {
            this(pos, let, null);
        }
    }
    
    /**
     * Method to generate a hashcode/get the ind of a letter in the hashmap.
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
     * Method to pop the Node containing a letter.
     *
     * @param let Letter key beinglooked for
     * @return Node value associated with the key.
     */
    public Node pop(char let) {
        int pos = this.hashcode(let);
        Node out = wordHashmap[pos];
        wordHashmap[pos] = out.next;
        return out;
    }
    
    
}
