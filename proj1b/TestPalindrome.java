import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    //Uncomment this class once you've created your Palindrome class.
    public void Test1(){
        assertFalse(palindrome.isPalindrome("cat"));

    }
    public void Test2(){
        assertTrue(palindrome.isPalindrome("a"));
    }
    @Test
    public void testIsPalinddrome(){
        Test1();
        Test2();
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("nobon"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("Noon"));
    }
    @Test
    public void testIspoffbyone(){
        CharacterComparator cc=new OffByOne();
        assertTrue(palindrome.isPalindrome("a",cc));
        assertTrue(palindrome.isPalindrome("",cc));
        assertTrue(palindrome.isPalindrome("edf",cc));
        assertTrue(palindrome.isPalindrome("eDf",cc));
        assertFalse(palindrome.isPalindrome("Edf",cc));
        assertFalse(palindrome.isPalindrome("abcd",cc));
        assertFalse(palindrome.isPalindrome("noon",cc));
        assertTrue(palindrome.isPalindrome("dihe",cc));
    }


}
