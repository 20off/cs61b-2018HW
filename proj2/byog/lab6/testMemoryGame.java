package byog.lab6;

import org.junit.Test;

public class testMemoryGame {
    public MemoryGame m = new MemoryGame(50,50,2300);
    @Test
    public void testgeneraterandomstring(){
        String s=m.generateRandomString(10);
        System.out.println(s);
    }
    @Test
    public void testframshow(){
        String s=m.generateRandomString(10);
        int u=10;
        m.drawFrame("this is:"+u);

    }
    @Test
    public void testinput(){
        String s=m.generateRandomString(10);
        m.solicitNCharsInput(5);
    }
}
