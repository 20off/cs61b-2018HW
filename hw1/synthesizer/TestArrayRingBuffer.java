package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<String> deque= new ArrayRingBuffer<>(4);
        deque.enqueue("I");
        deque.enqueue("am");
        deque.enqueue("very");
        deque.enqueue("strong");
        for(int i=0;i<2;i++){
            deque.dequeue();
        }
        deque.enqueue("4");
        deque.enqueue("3");
        assertEquals("very",deque.dequeue());
        assertEquals("strong",deque.dequeue());
        assertEquals("4",deque.dequeue());
        assertEquals("3",deque.dequeue());
        assertTrue(deque.isEmpty());

        deque.enqueue("I");
        deque.enqueue("yes");
        assertEquals("I",deque.dequeue());
        assertEquals("yes",deque.dequeue());

        assertTrue(deque.isEmpty());
        deque.enqueue("f");


        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
