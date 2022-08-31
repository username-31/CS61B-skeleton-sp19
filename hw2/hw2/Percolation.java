package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF sites;
    private boolean[] openCheck;
    private boolean[] fullCheck; // TODO: Do we really need this? Maybe it's helpful when gravity comes in!
    private int N;
     private int numberOfOpenSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        numberOfOpenSites = 0;
        sites = new WeightedQuickUnionUF(N * N + 2);
        openCheck = new boolean[N * N + 2];
        fullCheck = new boolean[N * N + 2];
        for (int i = 0; i < N * N; i++) {
            openCheck[i] = false;
            fullCheck[i] = false;
        }

        /**
         *  the N * N -th item is the virtual top site,
         *  the N * N + 1 -th item is the virtual bottom site.
         */
        openCheck[N * N] = true;
        fullCheck[N * N] = true;
        openCheck[N * N + 1] = false;
        fullCheck[N * N + 1] = false;
    }

    private int xyTo1D(int row, int col) {

        return N * row + col;
    }

    public void open(int row, int col) { // open the site (row, col) if it is not open already
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        int self = xyTo1D(row, col);
        int up = xyTo1D(row - 1, col);
        int down = xyTo1D(row + 1, col);
        int left = xyTo1D(row, col - 1);
        int right = xyTo1D(row, col + 1);

        boolean notTopmost = (row != 0);
        boolean notLeftmost = (col != 0);
        boolean notRightmost = (col != N - 1);
        boolean notBottom = (row != N - 1);

        boolean leftIsFull = false;
        boolean rightIsFull = false;
        boolean upIsFull = false;

        openCheck[xyTo1D(row, col)] = true;
        numberOfOpenSites += 1;

        if (row == 0) {
            sites.union(self, N * N);
        }
        if (notTopmost) {
            if (isOpen(row - 1, col)) {
                sites.union(up, self);
                upIsFull = isFull(row - 1, col);
            }
        }

        if (notLeftmost) {
            if (isOpen(row, col - 1)) {
                sites.union(left, self);
                leftIsFull = isFull(row, col - 1);
            }
        }
        if (notRightmost) {
            if (isOpen(row, col + 1)) {
                sites.union(right, self);
                rightIsFull = isFull(row, col + 1);
            }
        }

        boolean hasFullNeighbor = (leftIsFull || upIsFull || rightIsFull);
        if (notBottom) {
            if (isOpen(row + 1, col)) {
                sites.union(down, self);
            }
        }

        if (!notBottom) {
            sites.union(N * N + 1, self);
        }

    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return openCheck[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return sites.connected(xyTo1D(row, col), N * N);
    }

    public int numberOfOpenSites() { // number of open sites
        return numberOfOpenSites;
    }

    public boolean percolates() { // does the system percolate?
        return sites.connected(N * N, N * N + 1);
    }

    public static void main(String[] args) { // use for unit testing (not required, but keep this here for the autograder)

        Percolation p = new Percolation(5);

    }
}
