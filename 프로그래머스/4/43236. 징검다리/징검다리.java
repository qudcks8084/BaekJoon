/* 
    parametric search로 다리간 최소 거리를 N이라고 두고, 다리를 건넌다.
    
    min은 0, max는 가장 먼 다리와의 거리.
    
    즉 핵심은 이분 탐색으로는 바위 사이의 최소 길이를 정하고, 건너가면서 더 짧은건 없애면서 간다.

    매 턴별로 int mid = (min + max) / 2;

    여기에서 mid는 현재 다리의 최소값이야.
    
    그래서 만약 최소값을 유지하기 위해서 다리를 n보다 많이 부셨으면 min = mid + 1로, 만약 너무 적게 부셨으면 max = mid - 1로.

*/

import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        // 자 일단 정렬을 해.
        Arrays.sort(rocks);
        
        int N = rocks.length;
        int min = 0;
        int max = distance;
        
        int answer = 0;
        
        while(min <= max){
            int mid = (int)((min + max) / 2);
            
            // 이제 앞에서부터 순회하면서 다리를 삭제시킨다.
            int cur = 0; // 현재 위치 
            int cnt = 0; // 건너뛴 다리의 개수
            
            for(int i = 0 ; i < N ; i++){
                int len = rocks[i] - cur;
                
                // 만약 현재 거리가 더 작으면 최소가 안되버림으로 부셔버린다.
                if(len < mid){
                    cnt++;
                }
                
                // mid보다 크거나 같으면 해당 돌로 이동한다.
                else{
                    cur = rocks[i];
                }
            }
            
            // 도착지까지를 계산
            int len = distance - cur;
            if(len < mid)
                cnt++;
            
            // 만약 부신 것이 n보다 적으면 더 적게 부신거니까 최소를 더 늘릴 수 있다.
            
            if(cnt <= n){
                answer = Math.max(answer, mid);
                min = mid + 1;
            }
            
            // 만약 부신 것이 n보다 많으면 더 많이 부신거니까 길이를 줄여야한다.
            else{
                max = mid - 1;
            }
            
        }

        return answer;
    }
}