import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        
        int N = emergency.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> (-Integer.compare(o1[1], o2[1]))
        );
        
        for(int i = 0 ; i < N ; i++){
            pq.offer(new int[]{i, emergency[i]});
        }
        
        int[] answer = new int[N];
        for(int i = 1 ; i <= N ; i++){
            answer[pq.poll()[0]] = i;
        }
        return answer;
    }
}