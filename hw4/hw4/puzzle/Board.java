package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private final int[][] b;
    public Board(int[][] tiles){
        int N = tiles.length;
        b = new int[N][N];
        for(int i = 0; i< N; i ++){
            for(int j = 0; j < N; j ++){
                b[i][j] = tiles[i][j];
            }
        }
    }
    public int tileAt(int i, int j){
        if(i >= 0 && i <= size() - 1 && j >= 0 && j <= size() - 1){
            return b[i][j];
        }else{
            throw new IndexOutOfBoundsException();
        }

    }
    public int size(){
        return b.length;
    }
    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }
    public int hamming(){
        int distance = 0;
        for(int i = 0; i < size(); i ++){
            for(int j = 0; j < size(); j ++){
                if(b[i][j] == 0){
                    continue;
                }
                if(b[i][j] != i*size()+j+1){
                    distance += 1;
                }
            }
        }
        return distance;
    }
    public int manhattan(){
        int distance = 0;
        for(int i = 0; i < size(); i ++){
            for(int j = 0; j < size(); j ++){
                if(b[i][j] == 0){
                    continue;
                }
                if((b[i][j] -1) / size() != i){
                    distance += Math.abs((b[i][j] -1) / size() - i);
                }if((b[i][j]-1) % size() != j){
                    distance += Math.abs((b[i][j] -1) % size() - j);
                }
            }
        }
        return distance;
    }
    public int estimatedDistanceToGoal(){
        return manhattan();
    }
    public boolean equals(Object y){
        if(y==null){
            return false;
        }
        if(y instanceof String){
            String a = this.toString();
            return a.equals(y);
        }
        Board target = (Board) y;
        if( target.size()!= this.size()){
            return false;
        }else{
            for(int i = 0; i < size(); i ++){
                for(int j = 0; j < size(); j ++){
                    if(b[i][j] != target.tileAt(i,j)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
