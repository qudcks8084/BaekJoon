/* 
    설계 시작. 아마 순서는 중요하지 않는다면 각 시간초별로 나누면 된다.
    
    즉. paremestric Search로 한다면, 시간이 mid로 한다.
    
    그래서 각 심사관별로 그냥 시간을 전부 나눠서 값만 더했을때 사람이 n명이 되는지 찾으면 된다.
    
    시간의 최대값은 몇으로 놔야하나...?
    
    그냥 맘편하게.... Long.MAX_VALUE....?
*/

class Solution {
    public long solution(int n, int[] times) {
        
        // 시간의 최대를 지정
        long min = 0;
        long max = Long.MAX_VALUE;
        
        long answer = Long.MAX_VALUE;
        while(min <= max){
            
            long mid = (long)((min + max) / 2);
            
            long cnt = n;
            
            for(long time : times){
                if(cnt < 0) break;
                cnt -= (long)(mid / time);
            }
            
            // 만약 현재 시간대의 사람이 기준보다 더 많으면. 줄이면서 정답을 갱신
            if(cnt <= 0){
                answer = Math.min(answer, mid);
                max = mid - 1;
            }
            
            else{
                min = mid + 1;
            }
        }
        
        return answer;
    }
}