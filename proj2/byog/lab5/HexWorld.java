package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;



/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final Random RANDOM= new Random(1235);
    /* 位置将由六边形从下往上数第一条最长边的第一个地砖的位置决定*/
    public static void addHexagon(TETile[][] world , int size , Position p , TETile sample){
        Position[] item = Hexagonp(size , p );
        for(int i = 0 ; i < item.length ; i+= 1){
            world[item[i].xPos][item[i].yPos]=sample;
        }
    }
    /*return an array of all elements' which consiste of Hexagon position
    * */
    private static Position[] Hexagonp(int size, Position p ){
        if(size == 1){
            throw new RuntimeException("size cannot be 1");
        }if(size == 0){
            throw new RuntimeException("size cannot be 0");
        }
        int Hpsize = 4*size*size-2*size;
        Position[] Hp = new Position[Hpsize];
        int row = 0;
        int column = 0;
        for(int i = 0 ; i<Hpsize ; i+= 2){
            if(column==3*size-2-2*row){
                row+= 1;
                column = 0;
            }
            Hp[i] = new Position(p.xPos+column+row , p.yPos-row);
            column+= 1;
            Hp[i+1] = Hp[i].reversex(row);

        }
        return Hp;
    }
    private static Position[] choosep(int size){
        Position[] returnposition = new Position[19];
        int column=0;
        int row=0;
        for(int i = 0 ; i < 19 ; i = i + 1){
            if(row == 5 - column){
                column+=1;
                row = 0 ;
            }
            returnposition[i] = new Position((2*size-1)*(2-column) , (2*row+1)*size+size*column-1);
            if(column > 0){
             returnposition[i+1] =   returnposition[i].reversey( column, size);
             i+=1;
            }
            row+=1;

        }
        return returnposition;
    }

    private static class Position{
        public int xPos;
        public int yPos;
        public Position(int x, int y){
            xPos=x;
            yPos=y;
        }
        public Position reversex(int row){
            return new Position(this.xPos , this.yPos+1+2*row);
        }
        public Position reversey(int column , int size){
            return new Position(this.xPos+2*column*(2*size-1) , this.yPos);
        }
    }
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            default: return Tileset.MOUNTAIN;
        }
    }

    public static void main(String[] args){
        int size=3;
        int WIDTH=11*size-6;
        int HEIGHT=10*size;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position[] pk = choosep(size);
        for(int i = 0; i<pk.length ; i+=1){
            addHexagon(world,size,pk[i],randomTile());
        }

        ter.renderFrame(world);
    }

}
