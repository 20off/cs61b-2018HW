package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean targetfound = false;
    private Maze maze ;
    private int s ;
    private int t ;
    private LinkedList<Integer> fringe;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = s;
        edgeTo[s] = s;
        fringe = new LinkedList<>();
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        fringe.add(s);
        marked[s] = true;
        int v;
        while(!targetfound){
            v = fringe.pollFirst();
            for(int k : maze.adj(v)){
                if(!marked[k]){
                    edgeTo[k] = v;
                    distTo[k] = distTo[v] + 1;
                    marked[k] = true;
                    announce();
                    fringe.add(k);
                    if(k == t){
                        targetfound = true;
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

