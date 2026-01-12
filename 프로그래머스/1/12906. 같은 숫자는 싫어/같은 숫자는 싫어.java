import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        for(int i : arr){
            if(stack.isEmpty()) stack.push(i);
            else if(stack.peek() != i){
                stack.push(i); 
            } 
        }
        
        int N = stack.size();
        
        int[] answer = new int[N];

        
        for(int i = 0 ; i < N ; i++){
            answer[i] = stack.removeLast();
        }

        return answer;
    }
}