package hw3.hash;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int[] bucketNumRecord = new int[M];
        for (int i = 0; i < M; i++) {
            bucketNumRecord[i] = 0;
        }
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFF) % M;
            bucketNumRecord[bucketNum] += 1;
        }
//        for (int i = 0; i < bucketNumRecord.length; i++) {
//            System.out.println("bucketNumRecord[" + i + "] = " + bucketNumRecord[i]);
//        }
//        double mu = StdStats.mean(bucketNumRecord);
//        double sigma = StdStats.stddev(bucketNumRecord);
//        double confidentLow = mu - 1.96 * sigma / bucketNumRecord.length;
//        double confidentHigh = mu + 1.96 * sigma / bucketNumRecord.length;
//        System.out.println("N / 50 = " + N / 50 + ", N / 2.5 = " + N / 2.);
//        System.out.println("Average is " + mu + ", stddev is " + sigma);
//        System.out.println("The 95% confidence interval is [ " + confidentLow + ", " + confidentHigh + " ]");
        for (int bnr : bucketNumRecord) {
            if (bnr < N / 50 || bnr > N / 2.5) {
//                System.out.println(bnr + " is out of range.");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<Oomage> oomages = new LinkedList<>();
        for (int i = 0; i < 999; i++) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }
        System.out.println(haveNiceHashCodeSpread(oomages, 31));
    }
}
