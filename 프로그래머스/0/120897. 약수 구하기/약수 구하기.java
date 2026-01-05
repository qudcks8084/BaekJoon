import java.util.*;

class Solution {
    public int[] solution(int n) {   
        ArrayList<Integer> arr = new ArrayList<>();
        
        if(n == 1) return new int[]{1};
        
        arr.add(1);
        
        for(int i = 2 ; i <= n/2 ; i++){
            if(n % i == 0) arr.add(i);
        }
        
        arr.add(n);
        
        int[] answer = new int[arr.size()];
        
        for(int i = 0 ; i < arr.size() ; i++){
            answer[i] = arr.get(i);
        }
        
        return answer;
    }
}