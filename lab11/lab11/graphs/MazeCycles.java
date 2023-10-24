package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean targetFound = false;
    private Maze maze;
    private int s;
    private int[] parentTo;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        int s = maze.xyTo1D(1,1);
        edgeTo[s] = s;
        distTo[s] = 0;
        parentTo = new int[maze.V()];
    }
    private void findcycle(int v){
        marked[v] = true;
        for(int w : maze.adj(v)){
            if(targetFound){
                return;
            }
            if(marked[w] && parentTo[v] != w){
                targetFound = true;
                line(v,w);
                break;
            }else if(!marked[w]){
                parentTo[w] = v;
                distTo[w] = distTo[v] + 1;
                findcycle(w);
            }
        }
        announce();
    }
    private void line(int v, int w){
        int k;
        k = v;
        while(k!=w){
            edgeTo[k] = parentTo[k];
            k = parentTo[k];
        }
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        findcycle(s);
    }

    // Helper methods go here
}

