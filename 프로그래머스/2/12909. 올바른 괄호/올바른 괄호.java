import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for(char c : s.toCharArray()){

            if(c == '('){
                stack.push(c);
            }else{
                if(stack.isEmpty()) 
                    return false;
                
                else {
                    if(stack.peek() == '(')
                        stack.pop();
                    else
                        return false;
                }
            }
        }
        
        if(!stack.isEmpty()) return false;

        return true;
    }
}