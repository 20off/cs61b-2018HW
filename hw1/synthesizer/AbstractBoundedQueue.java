package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int fillCount=0;
    protected int capacity=0;
    @Override
    public int capacity(){
        return capacity;
    };
    @Override
    public int fillCount(){
        return fillCount;
    };

}
