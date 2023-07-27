import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testequalChars(){
        assertFalse(offByOne.equalChars('a','z'));
        assertTrue(offByOne.equalChars('i','h'));
        assertTrue(offByOne.equalChars('i','j'));
        assertFalse(offByOne.equalChars('A','z'));
        assertFalse(offByOne.equalChars('a','Z'));
        assertFalse(offByOne.equalChars('%','a'));
    }


    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
