/* 
    DFS로 탐색하는게 좋을거같다.
    왜? BFS 기준으로 보는 경우 너무 경우의 수가 많아질 것 같음.
*/

import java.util.*;
class Solution {
    
    static int N;
    static int num;
    static int cnt;
    static int[] nums;
    public int solution(int[] numbers, int target) {
        
        // 전역 변수 선언
        N = numbers.length;
        num = target;
        nums = numbers;
        
        // 탐색한다.
        cnt = 0;
        dfs(0, 0);
        
        return cnt;
    }
    
    public static void dfs(int depth, int sum){
        // 만약 정답이 되는 경우 갱신
        // 이미 최대 길이인 경우 리턴
        if(depth == N){
            if(sum == num) cnt++;
            return;
        }
            
        
        // + 인 경우 먼저
        dfs(depth + 1, sum + nums[depth]);
        
        // - 인 경우 다음
        dfs(depth + 1, sum - nums[depth]);
    }
}