public class LinkedListDeque<hhh> {
    /*class T is innode which is a naked list
    * has two pionters
    * */
    private class T{
        public hhh item;
            public T next;
            public T pre;
            public T(hhh x, T p, T n){
                item=x;
                next=n;
                pre=p;
            }
            /*null constructor,if LinkedlistDeque is consturcted as null list
            we make sentinel point a null naked list innode
            * */
            public T(){
                item=null;
                next=null;
                pre=null;
            }
            public hhh get(int index){
                if(index==0) {
                    return item;
                }
                return this.next.get(index-1);
            }
    }
    /*sentinel must piont to a list
    * */
    private T sentinel=new T();
    private int size;
    /*sentinel's pre point to the last item of the list
    sentinel's next point to the first item of the list
    sentinel's item doesn't make sense. It also an be anything
    * */
    public LinkedListDeque(hhh x){
        sentinel.next=new T(x, sentinel, sentinel);
        sentinel.pre=sentinel.next;
        size=1;
    }
    public LinkedListDeque(){
        sentinel.next=sentinel;
        sentinel.pre=sentinel;
        size=0;
    }
    public void addFirst(hhh item){
        sentinel.next=new T(item, sentinel, sentinel.next);
        sentinel.next.next.pre=sentinel.next;
        size+=1;
    }


    public void addLast(hhh item){
        sentinel.pre=new T(item, sentinel.pre , sentinel);
        sentinel.pre.pre.next=sentinel.pre;
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
        T l=sentinel.next;
        for(int i=0; i<size; i+=1){
            if(i==size-1){
                System.out.print(l.item);
            }
            else{
                System.out.print(l.item+" ");
            }
            l=l.next;
        }
    }
    public hhh removeFirst(){
        hhh u;
        if(size==0){
            return null;
        }
        u=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        if(size==1){
            sentinel.pre=sentinel;
        }
        size=size-1;
        return u;
    }
    public hhh removeLast(){
        hhh u;
        if(size==0){
            return null;
        }
        u=sentinel.pre.item;
        sentinel.pre=sentinel.pre.pre;
        if(size==1){
            sentinel.next=sentinel;
        }
        size=size-1;
        return u;
    }
    public hhh get(int index){
        if(index>=size){
            return null;
        }else {
            T p=sentinel;
            for(int i=0; i<=index; i+=1){
                p=p.next;
            }
            return p.item;
        }
    }
    public hhh getRecursive(int index){

        return this.sentinel.next.get(index);
    }

}
