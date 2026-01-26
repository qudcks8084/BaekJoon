/* 
    정확히 순위를 매길 수 있는 선수의 수?
    
    - 정확히 순위를 매길 수 있다?
    -> 내 위와 내 아래의 수를 합쳤을때 n-1이 되어야한다.
    
    간선이 100이라면 최대 간선은 4950개. 그렇다면 인접 행렬로 관리한다.
*/

import java.util.*;

class Solution {
    
    static int N;
    static int answer;
    static int[][] winning;
    static boolean[] visited;
    public int solution(int n, int[][] results) {
        
        N = n;
        winning = new int[n][n];
        for(int[] result : results){
            int win = result[0] - 1;
            int lose = result[1] - 1;
            winning[win][lose] = 1;
            winning[lose][win] = -1;
        }
        
        answer = 0;
        
        for(int i = 0 ; i < N ; i++){
            
            visited = new boolean[N];
            find(i, 1);
            find(i, -1);
            
            int cnt = 0;
            for(boolean check : visited)
                if(check) cnt++;
            
            if(cnt == N-1)
                answer++;
        }
        return answer;
    }
    
    static void find(int cur, int type){
        
        for(int i = 0 ; i < N ; i++){
            if(cur == i) continue;
            if(visited[i]) continue;
            if(winning[cur][i] != type) continue;
            
            visited[i] = true;
            find(i, type);
        }

    }
}