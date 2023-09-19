package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class Room {
    //选择矩形room左下角顶点为标定,room包括墙
    public Position Rp;
    public int width;
    public int height;
    public Room(Position p , int w,int h){
        Rp=p;
        width=w;
        height=h;
    }
    //roomgenerator will generate a room array with all rooms in this world
    private boolean Positioncheckout(){
        if((Rp.xpos+width - 1)>=Game.WIDTH){
            return false;
        }else if((Rp.ypos+height - 1)>=Game.HEIGHT){
            return false;
        }
        return true;
    }
    private static Room[] roomgenerator(Random RANDOM){
        int arraysize=RANDOM.nextInt(10)+15;
        Room[] Ra = new Room[arraysize];
        int i=0;
        while(i<arraysize){
            Position p=new Position(RANDOM.nextInt(Game.WIDTH - 3) + 1,RANDOM.nextInt(Game.HEIGHT - 3) + 1 );
            Room x = new Room(p,RANDOM.nextInt(8)+3,RANDOM.nextInt(8)+3);
            if(x.Positioncheckout()){
                Ra[i]=x;
                i+=1;
            }
        }
        return Ra;
    }
    private static void changeorderofRoom(Room[] RoomArray){
        int[] h = new int[RoomArray.length];
        for(int i = 0 ; i < h.length ; i += 1){
            h[i] = RoomArray[i].Rp.xpos * RoomArray[i].Rp.xpos + RoomArray[i].Rp.ypos * RoomArray[i].Rp.ypos;
        }
        Room room;
        for( int i = 0 ; i < RoomArray.length ; i+=1){
            int min = 0 ;
            int index=0;
            for(int j = i ; j<h.length ; j+=1){
                if(h[j] < min){
                    min = h[j];
                    index = j;
                }
                room = RoomArray[i];
                RoomArray[i] = RoomArray[index];
                RoomArray[index] = room;
                min = h[i];
                h[i]=h[index];
                h[index]=min;
            }
        }
    }
    public static Room[] addroom(Random RANDOM , TETile[][] world){
        Room[] Ra = roomgenerator(RANDOM);
        changeorderofRoom(Ra);
        for(int i=0 ; i<Ra.length ; i+=1 ){
            Position p=Ra[i].Rp;

            /*for(int x=p.xpos+1 ; x<Ra[i].width+p.xpos-1 ; x+=1){
                for(int y=p.ypos+1 ; y<Ra[i].height+p.ypos-1 ; y+=1){
                    world[x][y]=Tileset.FLOOR;
                }
            }*/
            for(int x=p.xpos ; x < Ra[i].width + p.xpos ; x += 1){

                world[x][Ra[i].Rp.ypos] = Tileset.WALL;
            }
            for(int x=p.xpos ; x<Ra[i].width+p.xpos ; x+=1){
                world[x][ Ra[i].Rp.ypos + Ra[i].height - 1] = Tileset.WALL;
            }
            for(int y=p.ypos ; y<Ra[i].height+p.ypos ; y+=1){
                world[Ra[i].Rp.xpos][y] = Tileset.WALL;
            }
            for(int y=p.ypos ; y<Ra[i].height+p.ypos ; y+=1){
                world[Ra[i].Rp.xpos + Ra[i].width - 1][y] = Tileset.WALL;
            }

        }
        return Ra;
    }



}
