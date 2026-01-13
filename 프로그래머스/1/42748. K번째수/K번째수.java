import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int T = commands.length;
        int[] answer = new int[T];
        for(int t = 0 ; t < T ; t++){
            
            int[] command = commands[t];
            
            int N = command[1] - command[0] + 1;
            int[] tmp = new int[N];
            
            for(int i = 0 ; i < N ; i++){
                tmp[i] = array[i + command[0] - 1];
            }
            
            Arrays.sort(tmp);
            
            answer[t] = tmp[command[2] - 1];
            
        }
        return answer;
    }
}