/* 
    맨 처음에는 A만 있음
    움직이면서 최소한의 움직임으로 이름을 만들면 됨.
    그럼 가면서 앞으로 이동 뒤로 이동을 확인한다.
    Math.min(num - 'A', 26 - num - 'A');
*/
class Solution {
    public int solution(String name) {
        int N = name.length();
        
        // 위아래 다이얼 회전 횟수
        int updown = 0;
        // 좌우로 움직인 거리
        int lfrf = N-1;
        
        for(int i = 0 ; i < N ; i++){
            
            // 위아래 돌리는거 먼저 계산.
            int idx = name.charAt(i) - 'A';
            updown += Math.min(idx, 26 - idx);
            
            // 연속된 A를 찾아.
            int next = i+1;
            while(next < N && name.charAt(next) == 'A'){
                next++;
            }

            lfrf = Math.min(lfrf, (i*2) + N - next);
            lfrf = Math.min(lfrf, (N - next) * 2 + i);
        }
        
        return updown + lfrf;
    }
}