/* 
    visited로 탐색한다. 끝.
*/

import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        
        boolean[] visited = new boolean[n];
        
        int answer = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0 ; i < n ; i++){
            if(visited[i]) continue;
            visited[i] = true;
            answer++;
            
            q.offer(i);
            
            while(!q.isEmpty()){
                int cur = q.poll();
                    
                for(int k = 0 ; k < n ; k++){
                    if(cur == k) continue;
                    if(computers[cur][k] == 0) continue;
                    if(visited[k]) continue;
                    visited[k] = true;
                    q.offer(k);
                }
            }
        }
        
        return answer;
    }
}