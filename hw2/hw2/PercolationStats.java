package hw2;
import java.lang.IllegalArgumentException;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] pofPercplate;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
        pofPercplate = new double[T];
        Percolation[] p = new Percolation[T];
        for(int i = 0; i < T; i += 1){
            p[i] = pf.make(N);
        }
        for(int i = 0; i < T; i += 1){
            int index;
            int l = N*N ;
            int[] blockbox = new int[N*N];
            for( int j = 0; j < l; j += 1 ){
                blockbox[j] = j;
            }
            while(!p[i].percolates()){
                index = StdRandom.uniform(l);
                p[i].open(blockbox[index]/N,blockbox[index]%N);
                blockbox[index] = blockbox[l-1];
                l = l - 1;
            }
            pofPercplate[i] = p[i].numberOfOpenSites()*1.0/(N*N);
        }
    }
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(pofPercplate);
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(pofPercplate);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        double m = mean();
        double dev = Math.sqrt(stddev());
        return m-(1.96*dev)/Math.sqrt(pofPercplate.length);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        double m = mean();
        double dev = Math.sqrt(stddev());
        return m+(1.96*dev)/Math.sqrt(pofPercplate.length);
    }

}
