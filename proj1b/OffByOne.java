public class OffByOne implements CharacterComparator {
    /**
     * @param x The given char
     * @param y The given char
     * @return true if x and y are different by one, false otherwise.
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == 1 || diff == -1) {
            return true;
        }
        return false;
    }


}
