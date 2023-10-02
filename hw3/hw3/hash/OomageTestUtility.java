package hw3.hash;

import java.util.HashSet;
import java.util.List;

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
        HashSet<Integer>[] buckets = new HashSet[M];
        for(int i = 0; i < M; i += 1){
            buckets[i] = new HashSet<Integer>();
        }
        int bucketNum;
        for(int i = 0; i < oomages.size(); i += 1){
            bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum].add(oomages.get(i).hashCode());
        }
        for(int i = 0; i < M; i += 1){
            int size = buckets[i].size();
            if(size < oomages.size()/50 || size > oomages.size()/2.5){
                return false;
            }
        }
        return true;
    }
}
