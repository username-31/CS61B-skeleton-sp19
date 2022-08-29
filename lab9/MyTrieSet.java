import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    /**
     * Radix
     */
    private final int R = 128;
    private Node root;

    private class Node {
        private char ch;
        private Node[] nexts;

        private boolean isKey;

        private List<Character> nextsList;

        public Node(boolean isKey) {
            this.isKey = isKey;
            this.nexts = new Node[R];
        }

        public Node(char c, boolean isKey) {
            this.ch = c;
            this.isKey = isKey;
            this.nexts = new Node[R];
        }

    }

    public MyTrieSet() {
        root = new Node(false);
    }


    /**
     * Clears all items out of Trie
     */
    @Override
    public void clear() {
        root = new Node(false);
    }

    /**
     * Returns true if the Trie contains KEY, false otherwise
     *
     * @param key String key, ASCII 0~127
     */
    @Override
    public boolean contains(String key) {
        return contains(root, key);
    }

    private boolean contains(Node node, String key) {
        Node temp = node;
        char[] keyCharArr = key.toCharArray();
        for (int i = 0; i < key.length(); i++) {
            char c = keyCharArr[i];
            if (temp.nexts[c] == null || temp.nexts[c].ch != c) {
                return false;
            }
            temp = temp.nexts[c];
        }
        // Now temp is the last node, check if it's the end
        return temp.isKey;
    }

    /**
     * Inserts string KEY into Trie
     *
     * @param key String key, ASCII 0~127
     */
    @Override
    public void add(String key) {
        this.add(root, key);
    }

    private void add(Node node, String key) {
        Node temp = node;
        for (char c : key.toCharArray()) {
            if (temp.nexts[c] == null || temp.nexts[c].ch != c) {
                temp.nexts[c] = new Node(c, false);
            }
            temp = temp.nexts[c];
        }
        temp.isKey = true;
    }

    /**
     * Returns a list of all words that start with PREFIX
     *
     * @param prefix String, ASCII 0~127
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        //TODO
       return null;
    }

    /**
     * Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key String key, ASCII 0~127
     */
    @Override
    public String longestPrefixOf(String key) {
        //TODO
        return null;
    }

    public static void main(String[] args) {
        MyTrieSet trie = new MyTrieSet();
        trie.add("abba");
        trie.add("abb");
        trie.add("abc");
        System.out.println(trie.contains("abba"));
        System.out.println(trie.contains("abb"));
        System.out.println(trie.contains("abcd"));
    }

}
