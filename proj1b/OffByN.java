public class OffByN implements CharacterComparator{
    public int n;
    public OffByN(int a){
        n=a;
    }
    public boolean equalChars(char x, char y){
        if(x+n==y | x-n==y){
            return true;
        }return false;
    }
}
