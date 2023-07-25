public class LinkedListDeque<T> {
    /*class T is innode which is a naked list
    * has two pionters
    * */
    private class innode{
        public T item;
            public innode next;
            public innode pre;
            public innode(T x, innode p, innode n){
                item=x;
                next=n;
                pre=p;
            }
            /*null constructor,if LinkedlistDeque is consturcted as null list
            we make sentinel point a null naked list innode
            * */
            public innode(){
                item=null;
                next=null;
                pre=null;
            }
            public T get(int index){
                if(index==0) {
                    return item;
                }
                return this.next.get(index-1);
            }
    }
    /*sentinel must piont to a list
    * */
    private innode sentinel=new innode();
    private int size;
    /*sentinel's pre point to the last item of the list
    sentinel's next point to the first item of the list
    sentinel's item doesn't make sense. It also an be anything
    * */
    public LinkedListDeque(){
        sentinel.next=sentinel;
        sentinel.pre=sentinel;
        size=0;
    }
    public void addFirst(T item){
        sentinel.next=new innode(item, sentinel, sentinel.next);
        sentinel.next.next.pre=sentinel.next;
        size+=1;
    }


    public void addLast(T item){
        sentinel.pre=new innode(item, sentinel.pre , sentinel);
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
        innode l=sentinel.next;
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
    public T removeFirst(){
        T u;
        if(size==0){
            return null;
        }
        u=sentinel.next.item;
        innode h=sentinel.next;
        sentinel.next=sentinel.next.next;
        sentinel.next.pre=sentinel;
        h.pre=null;
        h.next=null;
        h.item=null;
        size=size-1;
        return u;
    }
    public T removeLast(){
        T u;
        if(size==0){
            return null;
        }
        u=sentinel.pre.item;
        innode h=sentinel.pre;
        sentinel.pre=sentinel.pre.pre;
        sentinel.pre.next=sentinel;
        h.pre=null;
        h.next=null;
        h.item=null;
        size=size-1;
        return u;
    }
    public T get(int index){
        if(index>=size){
            return null;
        }else {
            innode p=sentinel;
            for(int i=0; i<=index; i+=1){
                p=p.next;
            }
            return p.item;
        }
    }
    public T getRecursive(int index){

        return this.sentinel.next.get(index);
    }

}
