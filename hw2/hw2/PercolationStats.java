package hw2;

import org.junit.Test;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    int N;
    int T;
    double[] x;
    Percolation[] perc;

    public PercolationStats(int N, int T, PercolationFactory pf) { // perform T independent experiments on an N-by-N grid
        this.T = T;
        this.N = N;
        this.x = new double[T];
        this.perc = new Percolation[T];
        for (int i = 0; i < T; i++) {
            perc[i] = pf.make(N);
            while (!perc[i].percolates()) {
                perc[i].open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
            }
        }
    }

    public double mean() { // sample mean of percolation threshold
        for (int i = 0; i < T; i++) {
            x[i] = 1.0 * perc[i].numberOfOpenSites() / (N * N);
        }

        return StdStats.mean(x);
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(x);
    }

    public double confidenceLow() { // low endpoint of 95% confidence interval
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    public double confidenceHigh() { // high endpoint of 95% confidence interval
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

    public static void main(String[] args) {
        PercolationStats ps1 = new PercolationStats(10, 10000, new PercolationFactory());
        System.out.println(ps1.mean());
        System.out.println(ps1.stddev());
        System.out.println(ps1.confidenceLow());
        System.out.println(ps1.confidenceHigh());
    }
}
