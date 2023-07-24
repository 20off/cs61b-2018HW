public class ArrayDeque<T> {
    private T[] arr;
    private int size;
    private int front;
    private int last;
    /*front is always point to the pre box of the first item
    * last is always point to the next box of the last item
    * */

    public ArrayDeque(){
        last=0;
        arr=(T[]) new Object[8];
        front=arr.length-1;
        size=0;
    }
    private void Resizing(){
        if(size==arr.length){
            T[] b=(T[]) new Object[size*2];
            System.arraycopy(arr, last, b, 0, size-last);
            System.arraycopy(arr, 0, b, size-last, last);
            front=b.length-1;
            last=size;
            arr=b;
        }
        if(arr.length>15 & size<(arr.length/4)){
            T[] b=(T[]) new Object[arr.length/2];
            if(front<last){
                System.arraycopy(arr, front+1, b, 0, last-front);
            }else if(front>last){
                System.arraycopy(arr, front+1, b, 0, size-last);
                System.arraycopy(arr, 0, b, size-last,last);
            }
            front=b.length-1;
            last=size;
            arr=b;
            }
    }
    //transform front and last ,so that they are all valid
    private int Tt(int x){
        if(x<0){
            x+=arr.length;

        }else if(x>arr.length-1){
            x-=arr.length;
        }
        return x;
    }
    public void addFirst(T item){
        this.Resizing();
         arr[front]=item;
         front=this.Tt(front-1);
         size+=1;
    }
    public void addLast(T item){
        this.Resizing();
        arr[last]=item;
        last=this.Tt(last+1);
        size+=1;
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int h;
        for(int i=this.Tt(front+1); i<size+this.Tt(front+1); i++){
            h=this.Tt(i);
            if(h==last-1){
                System.out.print(arr[h]);
                break;
            }
            System.out.print(arr[h]+" ");
        }
    }
    public T removeFirst(){
        if(this.isEmpty()){
            return null;
        }
        int h=this.Tt(front+1);
        T b=arr[h];
        arr[h]=null;
        front=h;
        size-=1;
        this.Resizing();
        return b;
    }
    public T removeLast(){
        if(this.isEmpty()){
            return null;
        }
        int h=this.Tt(last-1);
        T b=arr[h];
        arr[h]=null;
        last=h;
        size-=1;
        this.Resizing();
        return b;
    }
    public T get(int index){
        int h=this.Tt(front+1+index);
        return arr[h];
    }
}
