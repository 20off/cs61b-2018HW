package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;


import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public boolean gameover = false;
    public boolean win = false;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */


    public void playWithKeyboard() {
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        Menu.ShowMenu();
        KeyboardOperate(finalWorldFrame);
        if(gameover){
            System.exit(0);
        }



    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        Position Playerp ;
        if(input.charAt(0) =='N' || input.charAt(0) == 'n'){
            newgame(input , finalWorldFrame);
        }
        else if(input.charAt(0) == 'l' || input.charAt(0) == 'L'){
            finalWorldFrame = loadworld();
            Playerp = loadPlayer();
            for(int i = 1 ; i < input.length() ; i += 1){
                win = Playeroperate(finalWorldFrame , input.charAt(i) , input.charAt(i-1) , Playerp);
                if(gameover){
                    return finalWorldFrame;
                }
            }
        }


        return finalWorldFrame;
    }











   //在playwithinoutString()中，开启并运行游戏
    private TETile[][] newgame(String input , TETile[][] finalWorldFrame){
        Position Playerp;
        int i = getseedindex(input);
        Playerp = startnewgame(input , i , finalWorldFrame);
        for(int a = i+1 ; a < input.length() ; a++){
            win = Playeroperate(finalWorldFrame , input.charAt(a) , input.charAt(a-1) , Playerp);
            if(win){
                gameover = true;
                return finalWorldFrame;
            }if(gameover){
                return finalWorldFrame;
            }
        } return finalWorldFrame;
    }
    //开始一个新游戏所需要的准备工作
    private  Position startnewgame(String input , int i , TETile[][] world){
        String s = "";
        for(int a = 1 ; a < i ;a+=1){
            s = s + input.charAt(a);
        }
        long seed = Long.parseLong(s);
        if(input.charAt(i)=='s' || input.charAt(i)=='S'){
            gameover = false;
        }
        Random RANDOM = new Random(seed);
        Position playerP = WorldGenerator.newWorld(RANDOM , world);
        return playerP;
    }
    //返回字符s(分割seed与后续操作的关键字符)所在的index
    private int getseedindex(String str){
        String s = "";
        char number;
        int i = 1;
        number = str.charAt(i);
        while(number <='9' && number>='0'){
            s = s + number;
            i = i + 1;
            number = str.charAt(i);
        }
        return i;
    }
    //读取玩家游戏内操作，并且作出反应
    private boolean Playeroperate(TETile[][] world , char operation , char prechar, Position Playerp){
        int x=0;
        int y=0;
        switch(operation){
            case 'a' :
            case 'A' :
                x = -1;
            break;
            case 'w' :
            case 'W' :
                y = 1;
            break;
            case 'd' :
            case 'D' :
                x = 1;
            break;
            case 's' :
            case 'S' :
                y = -1;
            break;
            case 'Q' :
            case 'q' : if(prechar == ':'){
                saveworld(world);
                savePlayerp(Playerp);
                gameover = true;
            }
                return false;
            default: return false;
        }

        return Playerp.Tilecheckout(world , x ,y );
    }
    private void KeyboardOperate(TETile[][] world){
        char a = '0';
        int i = 0;
        char b;
        Position Playerp = new Position(0,0);
        while(!gameover){
            if(StdDraw.hasNextKeyTyped()){
                b = a;
                a = StdDraw.nextKeyTyped();
                if(i ==0){
                    switch(a){
                        case 'N' :
                        case 'n' :
                            Playerp = Menu.startnewgame(world);
                            ter.initialize(WIDTH,HEIGHT);
                            ter.renderFrame(world);
                            break;
                        case 'l' :
                        case 'L' :
                            world = loadworld();
                            Playerp = loadPlayer();
                            ter.initialize(WIDTH,HEIGHT);
                            ter.renderFrame(world);
                            break;
                        case 'Q' :
                        case 'q' :
                            System.exit(0);
                            break;
                        default: continue;
                    }
                    i= 1;
                }
                else{
                    win = Playeroperate(world,a,b,Playerp);
                    ter.renderFrame(world);
                }
            }
        }if(win){
            Menu.YouWin();
            gameover = true;
        }
    }
    private void saveworld(TETile[][] world ){
        File f = new File("./savedGame.ser");
        try{
            if(!f.exists()){
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(world);
            os.close();


        }catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }

    }
    private void savePlayerp(Position Playerp){
        File f = new File("./savedPlayerp.ser");
        try{
            if(!f.exists()){
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(Playerp);
            os.close();

        }catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }
    }
    private TETile[][] loadworld(){
        File f = new File("./savedGame.ser");
        if(f.exists() ){
            try{
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                TETile[][] world = (TETile[][]) os.readObject();
                os.close();
                return world;
            }catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }return new TETile[WIDTH][HEIGHT];
    }
    private Position loadPlayer(){
        File f = new File("./savedPlayerp.ser");
        if(f.exists()){
            try{
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                Position Playerp = (Position) os.readObject();
                os.close();
                return Playerp;
            }catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        return new Position(0 , 0);
    }

}
