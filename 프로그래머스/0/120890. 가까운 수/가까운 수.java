import java.util.*;

class Solution {
    public int solution(int[] array, int n) {
        
        Arrays.sort(array);
        
        int N = array.length;

        int index = Arrays.binarySearch(array, n);
        if(index >= 0) return n;
        
        int answer = 0;
        
        int lf_idx = -index -2;
        int rf_idx = -index -1;
        
        int gap = Integer.MAX_VALUE;
        
        if(lf_idx >= 0){
            answer = array[lf_idx];
            gap = n - array[lf_idx];
        }
        
        if(rf_idx < N){
            if(array[rf_idx] - n < gap){
                answer = array[rf_idx];
            }
        }
        
        return answer;
    }
}