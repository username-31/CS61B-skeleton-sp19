/**
 * This class outputs all palindromes in the words file in the current directory.
 */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc = new OffByOne();
        while (!in.isEmpty()) {
            String word = in.readString();

            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            } else if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word + " (obo) ");//此处运行出问题 是因为86凌晨修改arraydeque把 rearPtr 的移动条件改坏掉了，记得把proj1a里面的也改一下
            }
        }
    }
} //Uncomment this class once you've written isPalindrome.