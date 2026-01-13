import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        for(int i = 0 ; i < priorities.length ; i++){
            pq.offer(priorities[i]);
            if(i == location) q.offer(new int[]{priorities[i], 1});
            else q.offer(new int[]{priorities[i], 0});
        }
        
        int cnt = 1;
        while(!pq.isEmpty()){
            int target = pq.poll();
            
            while(!pq.isEmpty()){
                
                int[] cur = q.poll();
                
                if(cur[0] == target){
                    
                    if(cur[1] == 1) return cnt;
                    else cnt++;
                    
                    break;
                } 
                
                else{
                    q.offer(cur);  
                }
            }
        }
        
        return cnt;
    }
}