package byog.Core;
import java.util.Random;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
/*this class will generate the world
*/
public class WorldGenerator {
    private static Position choosePosition(Random RANDOM , Room room){
        int x = RANDOM.nextInt(room.width - 2) + 1 +room.Rp.xpos;
        int y = RANDOM.nextInt(room.height - 2) + 1 +room.Rp.ypos;
        return new Position( x , y );
    }
    //connect method will connect all the rooms in order
    private static void connect(Room[] Ra,Random RANDOM,TETile[][] world){
        for(int i=0 ; i < Ra.length-1 ; i+=1){
            Position p1 = choosePosition(RANDOM , Ra[i]);
            Position p2 = choosePosition(RANDOM , Ra[i+1]);
            int x1=p1.xpos;
            int y1=p1.ypos;
            int x2=p2.xpos;
            int y2=p2.ypos;
            int x=x2-x1;
            int y=y2-y1;
            int a = 1;
            int b = 1;
            if(x<0){
                a=-a;
            }
            if(y<0){
                b=-b;
            }
            for(int row = 1 ; row <= x*a ; row += 1){
                world[x1 + row*a][y1]=Tileset.FLOOR;
                addWallx(world,x1 + row*a,y1);
            }
            if(world[x2+a][y1-b].equals(Tileset.NOTHING)){
                world[x2+a][y1-b] = Tileset.WALL;
            }
            for(int column = 0 ; column <= y*b ; column += 1){
                world[x2][column*b + y1]=Tileset.FLOOR;
                addWally(world , x2 , column*b + y1);
            }
        }

    }
    //为道路加砖块
    private static void addWallx(TETile[][] world , int x , int y ){
        if(world[x][y + 1].equals(Tileset.NOTHING)){
            world[x][y + 1] = Tileset.WALL;
        }
        if(world[x][y - 1].equals(Tileset.NOTHING)){
            world[x][y - 1] = Tileset.WALL;
        }
        }
    private static void addWally(TETile[][] world , int x , int y ){
        if(world[x + 1][y].equals(Tileset.NOTHING)){
            world[x + 1][y] = Tileset.WALL;
        }
        if(world[x - 1][y].equals(Tileset.NOTHING)){
            world[x - 1][y] = Tileset.WALL;
        }
    }
    private static void addWallturn(TETile[][] world , int x , int y ){
        addWally(world , x , y);
        if(world[x + 1][y + 1].equals(Tileset.NOTHING)){
            world[x + 1][y] = Tileset.WALL;
        }
        if(world[x - 1][y + 1].equals(Tileset.NOTHING)){
            world[x + 1][y] = Tileset.WALL;
        }
        if(world[x - 1][y - 1].equals(Tileset.NOTHING)){
            world[x + 1][y] = Tileset.WALL;
        }
        if(world[x + 1][y - 1].equals(Tileset.NOTHING)){
            world[x + 1][y] = Tileset.WALL;
        }

    }
    private static void fillRoom(Room[] Rarray , TETile[][] world){
        Position p;
        for(int i = 0 ; i < Rarray.length ; i+= 1){
            p = Rarray[i].Rp;
            for(int x=p.xpos+1 ; x<Rarray[i].width+p.xpos-1 ; x+=1){
                for(int y=p.ypos+1 ; y<Rarray[i].height+p.ypos-1 ; y+=1){
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }
    }
    private static boolean adddoor(Random RANDOM , TETile[][] world , Room[] Ra) {
        int index = RANDOM.nextInt(Ra.length);
        int num = RANDOM.nextInt(Ra[index].width - 2);
        int x = Ra[index].Rp.xpos;
        int y = Ra[index].Rp.ypos;
        int l = Ra[index].width - 2 + Ra[index].height - 2;
        for (int i = 0; i < (Ra[index].width - 2 + Ra[index].height - 2) * 2; i += 1) {
            if( i < Ra[index].width-2){
                if(world[x + 1 + i ][y].equals(Tileset.WALL)){
                    world[x + i + 1][y] = Tileset.LOCKED_DOOR;
                    return true;
                }
            }else if(i < Ra[index].width - 2 + Ra[index].height - 2){
                if(world[x + Ra[index].width - 1 ][y + i + 1].equals(Tileset.WALL)){
                    world[x + Ra[index].width - 1 ][y + i + 1] = Tileset.LOCKED_DOOR;
                    return true;
                }
            }else if(i < 2 * Ra[index].width - 4 + Ra[index].height - 2){
                if(world[x + 1 + i - l ][y + Ra[index].height - 1].equals(Tileset.WALL)){
                    world[x + 1 + i - l ][y + Ra[index].height - 1] = Tileset.LOCKED_DOOR;
                    return true;
                }
            }else{
                if(world[x][y + 1 + i - l -Ra[index].width - 2].equals(Tileset.WALL)){
                    world[x][y + 1 + i - l -Ra[index].width - 2] = Tileset.LOCKED_DOOR;
                    return true;
                }
            }
        }return adddoor(RANDOM , world , Ra);
    }
    private static Position addplayer(Random RANDOM , TETile[][] world , Room[] Ra){
        int index = RANDOM.nextInt(Ra.length);
        int x = Ra[index].Rp.xpos + 1;
        int y = Ra[index].Rp.ypos + 1;
        Position p = new Position(x , y);
        world[x][y]=Tileset.PLAYER;
        return p;
    }

    public static Position newWorld(Random RANDOM , TETile[][] world){
        for (int x = 0; x < Game.WIDTH; x += 1) {
            for (int y = 0; y < Game.HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Room[] Ra = Room.addroom(RANDOM , world);
        connect(Ra , RANDOM , world);
        fillRoom(Ra , world);
        adddoor(RANDOM , world , Ra);
        return addplayer(RANDOM , world , Ra);
    }


    }





