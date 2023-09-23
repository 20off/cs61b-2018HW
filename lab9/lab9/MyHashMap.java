package lab9;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.lang.System;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        ArrayMap<K, V> bucket = buckets[Math.floorMod(key.hashCode(), buckets.length)];
        return bucket.get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        resieze();
        buckets[hash(key)].put(key, value);
        this.size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for(int i = 0; i < buckets.length; i +=1){
            keys.addAll(buckets[i].keySet());
        }
        return keys;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if(!buckets[Math.floorMod(key.hashCode(), buckets.length)].containsKey(key)){
            return null;
        }
        return buckets[Math.floorMod(key.hashCode(), buckets.length)].remove(key);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if(!buckets[Math.floorMod(key.hashCode(), buckets.length)].containsKey(key)){
            return null;
        }
        return buckets[Math.floorMod(key.hashCode(), buckets.length)].remove(key , value);
    }

    @Override
    public Iterator<K> iterator() {
        return new hashmapiterator();
    }
    private class hashmapiterator implements Iterator<K>{
        K[] karray = (K[])new Object[size];
        int pos;
        public hashmapiterator(){
            pos = 0;
            Set<K> keyset = keySet();
            karray =(K[]) keyset.toArray();
        }
        public boolean hasNext(){
            return pos < size;
        }
        public K next(){
            pos += 1;
            return karray[pos-1];
        }
    }


    private void resieze(){
        if(loadFactor() >= 6){
            ArrayMap<K, V>[] newBuckets =  new ArrayMap[2* buckets.length];
            for (int i = 0; i < newBuckets.length; i += 1) {
                newBuckets[i] = new ArrayMap<>();
            }
            for( int i = 0; i < buckets.length ;i += 1){
                for(K key : buckets[i]){
                    int h = Math.floorMod(key.hashCode(), newBuckets.length);
                    V c = buckets[i].get(key);
                    newBuckets[h].put(key,c);
                }
            }
            buckets = newBuckets;
        }
    }
}
