package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    private static final int width = 40;
    private static final int height = 40;
    public static void ShowMenu(){
        StdDraw.setCanvasSize(width*16 , height*16);
        Font font = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.WHITE);
        String s = "Cs61b The Game";
        StdDraw.text(20 , 35 , s);
        font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        s = "New Game(N)";
        StdDraw.text( 20, 22 , s);
        s = "Load Game(L) ";
        StdDraw.text( 20, 20 , s);
        s = "Quit(Q)";
        StdDraw.text( 20, 18 , s);
        StdDraw.show();

    }
    public static Position startnewgame(TETile[][] world ){
        long seed =  getseed();
        Random RANDOM = new Random(seed);
        Position playerP = WorldGenerator.newWorld(RANDOM , world);
        return playerP;
    }
    public static void YouWin(){
        StdDraw.setCanvasSize(width*16 , height*16);
        Font font = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(width/2,height/2,"You Win!");
        StdDraw.show();
    }

    private static long getseed(){
        String s = "";
        char input = 'a';
        drawFrame(s);
        int i = 0;
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                input = StdDraw.nextKeyTyped();
                if(input == 's' || input == 'S'){
                    break;
                }
                s = s + input;
                drawFrame(s);
            }
        }

        long a = Long.parseLong(s);
        return a;
    }
    private static void drawFrame(String s) {
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(20 , height-10 , "Please enter the seed firstly");
        StdDraw.text(width/2, 10 , "press 's' to start the game");
        font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(width/2, height/2 , "seed : "+s);
        StdDraw.show();
    }
}
