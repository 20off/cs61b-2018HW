package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        rand = new Random(seed);

        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String s="";
        if(n<1){
            throw new RuntimeException("the length of the target String must be greater than 0 ");
        }
        for( int i = 0; i < n; i+= 1){
            int a = rand.nextInt(26);
            s=s+CHARACTERS[a];
        }
        return s;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.text(width/2-1, height/2-1 , s);
        if(!gameOver){
            if(!playerTurn){
                StdDraw.text(width/2-1, height-1, "game begins");
                StdDraw.text(width/2-1, height-3, "please memory the letters shown");
            }else{
                StdDraw.text(width/2-1, height-3, "please type the letters correctly");}
        }
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for(int i=0 ; i<letters.length(); i+=1){
            drawFrame(letters.substring(i,i+1));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String b="";
        drawFrame(b);
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                b=b+StdDraw.nextKeyTyped();
                drawFrame(b);
            }
            if(b.length()==n){
                return b;
            }
            }


    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 0;
        String target;
        String actual;
        //TODO: Establish Game loop
        do{
            round+=1;
            playerTurn=false;
            drawFrame("Round:"+round);
            target=generateRandomString(round);
            flashSequence(target);
            playerTurn=true;
            actual=solicitNCharsInput(round);
            gameOver=!actual.equals(target);
        }while(!gameOver);
        drawFrame("Game Over! You made it to round:" + round);
    }

}
