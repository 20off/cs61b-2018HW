package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.IllegalArgumentException;

public class Percolation {
    public Boolean[][] grid;
    public WeightedQuickUnionUF DS;  //设置root为-1，视为水源，处在地图外的最上方
    public WeightedQuickUnionUF DSf;
    public int l;
    public int size;
    public Percolation(int N){
        // create N-by-N grid, with all sites initially blocked
        l = N;
        if(N <= 0){
            throw new IllegalArgumentException();
        }
        grid = new Boolean[N][N];
        DS = new WeightedQuickUnionUF(N*N + 2);
        DSf = new WeightedQuickUnionUF(N*N + 1);
        clear();
        size = 0;
    }
    //初始化grid 和disjoint set
    private void clear() {
        for(int i = 0; i < grid.length; i += 1){
            for( int j = 0; j < grid.length; j += 1){
                grid[i][j] = false;
            }
        }
    }
    private void unionfullsite(int row, int col){
        if(row == 0){
            DS.union(col , grid.length* grid.length);
            DSf.union(col , grid.length* grid.length);
        }else if(isOpen(row-1, col)){
            DS.union(row*l+col,(row-1)*l +col);
            DSf.union(row*l+col,(row-1)*l +col);
        }if(row == grid.length - 1){
            DS.union(grid.length* grid.length+1,row*l+col);
        }else if(isOpen(row+1, col)){
            DS.union(row*l+col,(row+1)*l +col);
            DSf.union(row*l+col,(row+1)*l +col);
        }
         if(col != 0 && isOpen(row, col - 1)){
            DS.union(row*l+col,row*l +col-1);
             DSf.union(row*l+col,row*l +col-1);
        }if(col != l-1 && isOpen(row, col + 1)){
            DS.union(row*l+col,row*l +col + 1);
            DSf.union(row*l+col,row*l +col + 1);
        }
    }


    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if(row < 0 || row > grid.length-1 || col < 0 || col > grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        if(!grid[row][col]){
            grid[row][col] = true;
            unionfullsite(row,col);
            size += 1;
        }

    }
    public boolean isOpen(int row, int col){
        // is the site (row, col) open?
        if(row < 0 || row > grid.length-1 || col < 0 || col > grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }
    public boolean isFull(int row, int col){
        // is the site (row, col) full?
        if(row < 0 || row > grid.length-1 || col < 0 || col > grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        return DSf.connected(row*l + col , l*l);
    }
    public int numberOfOpenSites(){
        // number of open sites
        return size;
    }
    public boolean percolates()     {
        // does the system percolate?

        return DS.connected(l*l, l*l+1);
    }

    public static void main(String[] args) throws IllegalAccessException {
        Percolation p = new Percolation(4);
        p.open(0,0);
        p.open(3,3);
        p.open(1,0);
        p.open(2,0);
        p.open(3,0);
        assertTrue(p.percolates());
        assertTrue(p.isFull(3,3));
    }   // use for unit testing (not required)

}
