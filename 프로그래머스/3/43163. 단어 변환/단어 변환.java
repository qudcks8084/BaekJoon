/* 
    아니다 최단 단계를 찾아야함으로 BFS로 찾는 것이 좋다.
    
    words는 최대 50개의 단어임으로 boolean[] 배열로 방문을 관리한다.
*/

import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        
        int N = words.length;
        int n = begin.length();
        ArrayDeque<String> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        
        q.offer(begin);
        
        int time = 0;
        while(!q.isEmpty()){
            
            int len = q.size();
            
            for(int l = 0 ; l < len ; l++){
                
                String cur = q.poll();
            
                if(cur.equals(target)){
                    return time;
                }
                
                for(int i = 0 ; i < N ; i++){
                    if(visited[i]) continue;
                    String next = words[i];
                    
                    // 1개가 같으면 바뀜.
                    int cnt = 0;
                    for(int idx = 0 ; idx < n ; idx++){
                        if(next.charAt(idx) != cur.charAt(idx))
                            cnt++;
                    }
                    
                    if(cnt == 1){
                        visited[i] = true;
                        q.offer(words[i]);
                    }
   
                }
                
            }
            
            time++;
        }
        
        return 0;
    }
}