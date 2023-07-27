public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> arrayc=new ArrayDeque<>();
        for(int i=0; i<word.length(); i++){
            arrayc.addLast(word.charAt(i));
        }
        return arrayc;
    }
    private boolean helperisPalindrome(Deque<Character> a){
        if(a.isEmpty() | a.size()==1){
            return true;
        }

        char x=a.removeFirst();
        char y=a.removeLast();
        if(x!=y){
            return false;
        }
        return helperisPalindrome(a);
    }
    public boolean isPalindrome(String word){
        Deque<Character> a=wordToDeque(word);
        return helperisPalindrome(a);
    }
    private boolean helperisPoffbyone(Deque<Character> a, CharacterComparator cc){
        if(a.isEmpty() | a.size()==1){
            return true;
        }
        char x=a.removeFirst();
        char y=a.removeLast();
        if(!cc.equalChars(x,y)){
            return false;
        }
        return helperisPoffbyone(a, cc);
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> b=wordToDeque(word);
        return helperisPoffbyone(b, cc);
    }




}
