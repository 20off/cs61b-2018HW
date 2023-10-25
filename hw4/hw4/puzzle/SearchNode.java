package hw4.puzzle;

import java.util.Comparator;

public class SearchNode implements Comparable<SearchNode>{
    WorldState wo;
    int M1;
    int ed;
    SearchNode pre;
    public SearchNode(WorldState w, int g, SearchNode p){
        M1 = g;
        wo = w;
        pre = p;
        ed = w.estimatedDistanceToGoal();
    }

    @Override
    public int compareTo(SearchNode o){
        return this.M1 + this.ed - (o.M1 + o.ed);
    }
    public static Comparator<SearchNode> getcomparator(){
        return new SearchNode.sncomparbtor();
    }
    public static class sncomparbtor implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.compareTo(o2);
        }
    }
}

