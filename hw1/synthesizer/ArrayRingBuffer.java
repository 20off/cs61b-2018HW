// TODO: Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int period(int f){
        if(f<0){
            return f+capacity;
        }else if(f>capacity-1){
            return f-capacity;
        }
        return f;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        this.capacity=capacity;
        first=0;
        last=0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last]=x;
        last=period(last+1);
        fillCount+=1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T item=rb[first];
        rb[first]=null;
        first=period(first+1);
        fillCount-=1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }
    private class ArraybufferIterator implements Iterator<T>{
        private int wizPos;
        public ArraybufferIterator(){
            wizPos=0;
        }
        public boolean hasNext(){
            return wizPos<capacity;
        }
        public T next(){
            T returnitem=rb[period(wizPos+first)];
            wizPos+=1;
            return returnitem;

        }
    }
    public Iterator<T> iterator(){
        return new ArraybufferIterator();
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
