public class ArrayDeque<hhh> {
    private hhh[] arr;
    private int size;
    private int front;
    private int last;
    /*front is always point to the pre box of the first item
    * last is always point to the next box of the last item
    * */

    public ArrayDeque(){
        last=0;
        arr=(hhh[]) new Object[8];
        front=arr.length-1;
        size=0;
    }
    private void Resizing(){
        if(size==arr.length){
            hhh[] b=(hhh[]) new Object[size*2];
            System.arraycopy(arr, last, b, 0, size-last);
            System.arraycopy(arr, 0, b, size-last, last);
            front=b.length-1;
            last=size;
            arr=b;
        }
        if(arr.length>15 & size<(arr.length/4)){
            hhh[] b=(hhh[]) new Object[arr.length/2];
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
    private int T(int x){
        if(x<0){
            x+=arr.length;

        }else if(x>arr.length-1){
            x-=arr.length;
        }
        return x;
    }
    public void addFirst(hhh item){
        this.Resizing();
         arr[front]=item;
         front=this.T(front-1);
         size+=1;
    }
    public void addLast(hhh item){
        this.Resizing();
        arr[last]=item;
        last=this.T(last+1);
        size+=1;
    }
    public boolean isEmpty(){
        if(size==0){git
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int h;
        for(int i=this.T(front+1); i<size+this.T(front+1); i++){
            h=this.T(i);
            if(h==last-1){
                System.out.print(arr[h]);
                break;
            }
            System.out.print(arr[h]+" ");
        }
    }
    public hhh removeFirst(){
        if(this.isEmpty()){
            return null;
        }
        int h=this.T(front+1);
        hhh b=arr[h];
        arr[h]=null;
        front=h;
        size-=1;
        this.Resizing();
        return b;
    }
    public hhh removeLast(){
        if(this.isEmpty()){
            return null;
        }
        int h=this.T(last-1);
        hhh b=arr[h];
        arr[h]=null;
        last=h;
        size-=1;
        this.Resizing();
        return b;
    }
    public hhh get(int index){
        int h=this.T(front+1+index);
        return arr[h];
    }
}
