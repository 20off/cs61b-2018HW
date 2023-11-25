import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

public class SeamCarver {
    private Picture p;
    private int width;
    private int height;
    private boolean isVertical;
    public SeamCarver(Picture picture){
        p = new Picture(picture);
        width = p.width();
        height = p.height();
        isVertical = true;
    }
    public Picture picture(){
        return p;
    }
    // width of current picture
    public int width(){
        return p.width();
    }

    // height of current picture
    public int height(){
        return p.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > width - 1) {
            throw new IndexOutOfBoundsException();
        } else if (y < 0 || y > height - 1) {
            throw new IndexOutOfBoundsException();
        }
         if(isVertical){
             return Rx(x,y) + Ry(x,y);
         }
         return Rx(y,x) + Ry(y,x);
    }
    private double Rx(int x, int y){
        Color rgb;
        Color rgb1;
        if(x-1 < 0){
            rgb = p.get(p.width()-1, y);
        }else{
            rgb = p.get(x-1,y);
        }
        if(x + 1 == p.width()){
            rgb1 = p.get(0, y);
        }else{
            rgb1 = p.get(x+1,y);
        }
        int r = rgb.getRed();
        int g = rgb.getGreen();
        int b = rgb.getBlue();
        int r1 = rgb1.getRed();
        int g1 = rgb1.getGreen();
        int b1 = rgb1.getBlue();
        return (g1-g)*(g1-g) + (r1-r)*(r1-r) + (b1-b)*(b1-b);
    }
    private double Ry(int x, int y){
        Color rgb;
        Color rgb1;
        if(y-1 < 0){
            rgb = p.get(x, p.height()-1);
        }else{
            rgb = p.get(x,y-1);
        }
        if(y + 1 == p.height()){
            rgb1 = p.get(x, 0);
        }else{
            rgb1 = p.get(x,y+1);
        }
        int r = rgb.getRed();
        int g = rgb.getGreen();
        int b = rgb.getBlue();
        int r1 = rgb1.getRed();
        int g1 = rgb1.getGreen();
        int b1 = rgb1.getBlue();
        return (g1-g)*(g1-g) + (r1-r)*(r1-r) + (b1-b)*(b1-b);
    }
    public int[] findHorizontalSeam() {// sequence of indices for horizontal seam
        swap();
        int[] res = findVerticalSeam();
        swap();
        return res;
    }
    private void swap(){
        int a = width;
        width = height;
        height = a;
        isVertical = !isVertical;
    }
    public int[] findVerticalSeam() {              // sequence of indices for vertical seam
        int[][] path = new int[width][height];
        //use M and N so that we can use less memory
        double[] M = new double[width];
        double[] N = new double[width];
        int a;
        int[] c = new int[height];
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                if(j == 0){
                    N[i] = energy(i,j);
                    path[i][j] = 0;
                }else{
                    a = minM(i,M);
                    N[i] = energy(i,j) + M[a];
                    path[i][j] = a;
                }
            }M = N;
            N = new double[width];
        }
        double h;
        a = 0;
        h = M[0];
        for(int i = 0; i < M.length; i++){
            if(h > M[i]){
                h = M[i];
                a = i;
            }
        }
        for(int i = height - 1; i >= 0; i--){
            c[i] = a;
            a = path[a][i];

        }
        return c;
    }
    private int minM(int j,double[] M){
        if(j -1 < 0 && j + 1 >= width){
            return j;
        }else if(j -1 < 0){
            if(M[j] < M[j+1]){
                return j;
            }else{
                return j + 1;
            }
        }else if (j + 1 >= width){
            if(M[j-1]<M[j]){
                return j-1;
            }else{
                return j;
            }
        }else{
            double h = M[j-1];
            int a = j-1;
            for(int i = 0; i < 2; i++){
                if(h>M[j+i]){
                    h = M[j+i];
                    a = j + i;
                }
            }return a;
        }
    }
    public void removeHorizontalSeam(int[] seam) {  // remove horizontal seam from picture
        SeamRemover.removeHorizontalSeam(p,findHorizontalSeam());
    }
    public void removeVerticalSeam(int[] seam){
        SeamRemover.removeVerticalSeam(p, findVerticalSeam());
    }
}
