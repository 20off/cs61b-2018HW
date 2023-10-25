package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;

public class Solver {
    private MinPQ<SearchNode> pq;
    private LinkedList<WorldState> slo = new LinkedList<>();
    public Solver(WorldState initial) {
        SearchNode i = new SearchNode(initial, 0, null);
        SearchNode a ;
        Comparator<SearchNode> comparator = SearchNode.getcomparator();
        pq = new MinPQ<>(10, comparator);
        pq.insert(i);
        while(true){
            i = pq.delMin();
            if(i.wo.isGoal()){
                break;
            }
             for(WorldState x: i.wo.neighbors()){
                 if(i.pre != null && !x.equals(i.pre.wo)) {
                     a = new SearchNode(x, i.M1 + 1, i);
                     pq.insert(a);
                 }
                 else if(i.pre == null){
                     a = new SearchNode(x, i.M1 + 1, i);
                     pq.insert(a);
                 }
             }
        }LinkedList<WorldState> sl = new LinkedList<>();
        while(i.pre!=null){
            sl.add(i.wo);
            i=i.pre;
        }
        sl.add(i.wo);
        WorldState x;
        while(!sl.isEmpty()){
            x = sl.removeLast();
            slo.add(x);
        }
    }

    public int moves() {
        return slo.size()-1;
    }

    public Iterable<WorldState> solution() {
        Iterable<WorldState> k = slo;
        return k;
    }


}
