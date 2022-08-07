public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    /**
     * @param x The given char
     * @param y The given char
     * @return true if x and y are different by N, false otherwise.
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == N || diff == -N) {
            return true;
        }
        return false;
    }

}
