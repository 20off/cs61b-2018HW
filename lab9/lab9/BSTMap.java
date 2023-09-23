package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }

    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }
        if( key.compareTo( p.key ) == 0 ){
            return p.value;
        }
        else if( key.compareTo(p.key) > 0 ){
            return getHelper( key , p.right);
        }else {
            return getHelper( key , p.left);
        }

    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key , root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null){
            size += 1;
            return p = new Node(key , value);
        }
        if( key.compareTo( p.key ) > 0){
            p.right = putHelper(key , value , p.right);
        }else if( key.compareTo( p.key ) == 0){
            p.value = value;
            return p;
        }else {
            p.left = putHelper(key , value ,p.left);
        }
        return p;

    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key , value , root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////
    private K keySethelper(Node p , Set<K> k){
        if(p == null){
            return null;
        }
        k.add(keySethelper( p.left , k));
        k.add(keySethelper( p.right , k));
        return p.key;
    }


    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> k = new HashSet<>();
        k.add(keySethelper(root, k));
        k.remove(null);
        return k;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private Node chooseNewRoot(Node p){
        if(p.right == null){
            return p;
        }
        return chooseNewRoot(p.right);
    }
    private Node removehelper(K key, Node p, V[] a){
        if(p == null){
            a[0] = null;
            return null;
        }
        if( key.compareTo( p.key ) == 0 ){
            a[0] = p.value;
            int l = 0;
            int r = 0;
            if(p.left != null){
                l += 1;
            }if(p.right != null){
                r += 1;
            }
            switch(l + r){
                case 0: return null;
                case 1:
                    if(l == 1){
                        return p.left;
                    }else{
                        return p.right;
                    }
                case 2: Node p1 = p.left;
                    Node p2 = p.right;
                    p = chooseNewRoot(p.left);
                    if(p != p1){
                        p.left = p1;
                    }
                    p.right = p2;
                    return p;
            }
        }
        else if( key.compareTo(p.key) > 0 ){
            p.right = removehelper( key, p.right, a);
        }else {
            p.left = removehelper( key, p.left, a);
        }
        return p;
    }
    @Override
    public V remove(K key) {
        V[] a = (V[]) new Object[1];
        root = removehelper(key , root ,a);
        V b = a[0];
        return b;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    private Node removehelper(K key, V value, Node p, V[] a){
        if(p == null){
            a[0] = null;
            return null;
        }
        if( key.compareTo( p.key ) == 0 ){
            if(p.value != value){
                a[0] = null;
                return p;
            }
            a[0] = p.value;
            int l = 0;
            int r = 0;
            if(p.left != null){
                l += 1;
            }if(p.right != null){
                r += 1;
            }
            switch(l + r){
                case 0: return null;
                case 1:
                    if(l == 1){
                        return p.left;
                    }else{
                        return p.right;
                    }
                case 2: Node p1 = p.left;
                    Node p2 = p.right;
                    p = chooseNewRoot(p.left);
                    if(p != p1){
                        p.left = p1;
                    }
                    p.right = p2;
                    return p;
            }
        }
        else if( key.compareTo(p.key) > 0 ){
            p.right = removehelper( key, p.right, a);
        }else {
            p.left = removehelper( key, p.left, a);
        }
        return p;
    }
    @Override
    public V remove(K key, V value) {
        V[] a = (V[]) new Object[1];
        root = removehelper(key, value, root ,a);
        V b = a[0];
        return b;
    }

    @Override
    public Iterator<K> iterator() {
        return new keywatcher();
    }
    private class keywatcher implements Iterator<K>{
        K[] keyarray;
        int p;
        public keywatcher(){
            p = 0;
            Set<K> keyset = keySet();
            keyarray =(K[]) keyset.toArray();
        }
        public boolean hasNext(){
            return p < keyarray.length ;
        }
        public K next(){
            p += 1;
            return keyarray[p - 1];
        }
    }
}
