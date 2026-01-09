import java.util.*;

class Solution {
    public int[] solution(int[][] score) {
        
        int N = score.length;
        
        boolean[] num = new boolean[201];
        int[] sums = new int[N];
        int[] answer = new int[N];
        
        for(int i = 0 ; i < N ; i++){
            int sum = score[i][0] + score[i][1];
            sums[i] = sum;
            num[sum] = true;
        }
        
        int k = 1;
        for(int i = 200 ; i >= 0 ; i--){
            if(!num[i]) continue;
            
            int cnt = 0;
            // 만약 있는 숫자면 업데이트
            for(int j = 0 ; j < N ; j++){
                if(sums[j] == i){
                    answer[j] = k;
                    cnt++;
                }
            }
            
            k += cnt;
        }
        

        return answer;
    }
}