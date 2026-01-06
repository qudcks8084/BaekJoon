import java.util.*;

class Solution {
    public int solution(int n) {
        
        int[] arr = new int[101];
        
        if( n < 4 ) return 0;
        
        int answer = n - 3;
        for(int i = 4 ; i <= n ; i++){
            boolean pass = false;
            for(int j = 2 ; j <= Math.sqrt(i) ; j++){
                if(i % j == 0) {
                    pass = true;
                    break;
                }
            }
            
            if(!pass){
                System.out.println(i);
                answer--;
            } 
            
        }

        return answer;
    }
}