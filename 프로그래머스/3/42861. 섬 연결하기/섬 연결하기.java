/* 
    그냥 물구나무서서 봐도 MST인걸....?
*/
import java.util.*;

class Bridge implements Comparable<Bridge>{
    int s;
    int e;
    int w;
       
    public Bridge(int s, int e, int w){
        this.s = s;
        this.e = e;
        this.w = w;
    }
    
    @Override
    public int compareTo(Bridge o){
        return Integer.compare(this.w, o.w);
    }
    
}

class Solution {
    
    static int[] p;
    public int solution(int n, int[][] costs) {
        
        // 사이클 관리용 배열 생성
        p = new int[n];
        for(int i = 0 ; i < n ; i++)
            p[i] = i;
        
        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        for(int[] cost : costs){
            pq.offer(new Bridge(cost[0], cost[1], cost[2]));
        }
        
        // 다리가 n-1되면 종료
        int cnt = 0;
        int answer = 0;
        while(cnt < n-1 && !pq.isEmpty()){
        
            Bridge cur = pq.poll();
            
            if(union(cur.s, cur.e))
                continue;
            
            answer += cur.w;
        }
        
        return answer;
    }
    
    public boolean union(int a, int b){
        int A = find(a);
        int B = find(b);
        if(A == B) return true;
        p[A] = B;
        return false;
    }
    
    public int find(int x){
        if(p[x] == x) return x;
        else return p[x] = find(p[x]);
    }
}