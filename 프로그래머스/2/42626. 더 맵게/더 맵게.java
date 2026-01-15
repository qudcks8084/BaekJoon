/* 
    PQ에 전부 넣는다.
    맨 앞에껄 꺼낸다.
    만약 그게 K보다 크면 끝.
    만약 K보다 작으면 한개 더꺼내서 더해서 다시 넣기 더꺼내서 더해서 다시 넣기
*/

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
    
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i : scoville)
            pq.offer(i);
        
        int cnt = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            
            if(cur < K){
                if(pq.isEmpty()) return -1;
                pq.offer(cur + pq.poll() * 2);
                cnt++;
            }
            
            else{
                return cnt;
            }
        }

        return -1;
    }
}