public class Palindrome {
    /**
     * Convert word to Deque.
     *
     * @param word A string variable.
     * @return A Deque where the characters appear in the same order as in the given String word.
     */
    public Deque<Character> wordToDeque(String word) {
        char[] charArray = word.toCharArray();
        ArrayDeque<Character> resultDeque = new ArrayDeque<>();

        for (char c : charArray) {
            resultDeque.addLast(c);
        }

        return resultDeque;
    }

    /***
     * Check if the given word is Palindrome.
     * @param word A String variable.
     * @return true if the String word is Palindrome, false otherwise.
     */
    public boolean isPalindrome(String word) {
        ArrayDeque<Character> wordDeque = (ArrayDeque<Character>) this.wordToDeque(word);
        int Size = wordDeque.size();//这一步居然没想到，要记在笔记上！
        for (int i = 0; i < Size / 2; i++) {
            if (wordDeque.removeFirst() != wordDeque.removeLast()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if the given word is OffByOne Palindrome.
     *
     * @param word A String variable
     * @param cc   Character Comparator
     * @return true if the given word is OffByOne Palindrome.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        ArrayDeque<Character> wordDeque = (ArrayDeque<Character>) this.wordToDeque(word);
        int Size = wordDeque.size();
        for (int i = 0; i < Size / 2; i++) {
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
