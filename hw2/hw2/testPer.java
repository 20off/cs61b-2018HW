package hw2;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

public class testPer {
    @Test
    public void testRandom(){
        int n=10;
        int T = 10;
        PercolationFactory pf = new PercolationFactory();
        PercolationStats p = new PercolationStats(n,T,pf);
    }
}
