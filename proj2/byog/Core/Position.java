package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Position implements Serializable {
    public int xpos;
    public int ypos;
    public Position(int x, int y){
        xpos=x;
        ypos=y;
    }
    public boolean equal(Position p){
        if(this.xpos == p.xpos && this.ypos == p.ypos){
            return true;
        }
        return false;
    }
    public boolean Tilecheckout(TETile[][] world, int x , int y ){
        if(world[xpos + x][ypos + y].equals(Tileset.FLOOR)){
            world[xpos][ypos] = Tileset.FLOOR;
            world[xpos + x][ypos + y] = Tileset.PLAYER;
            xpos = xpos + x;
            ypos = ypos + y;
        }else if(world[xpos + x][ypos + y].equals(Tileset.LOCKED_DOOR)){
            return true;

        }

        return false;


    }

}
