import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        
        for(int i = speeds.length - 1 ; i >= 0 ; i--){
            stack.push(new int[]{progresses[i], speeds[i]});
        }
        
        ArrayList<Integer> arr = new ArrayList<>();
    
        int t = 0;
        
        while(!stack.isEmpty()){
           
            int[] top = stack.pop();
            
            // 만약 작업이 종료되는 경우에는
            if(top[0] + top[1] * t >= 100){
               
                int cnt = 1;
                
                while(!stack.isEmpty()){
                    
                    int[] tmp = stack.peek();
                    if(tmp[0] + tmp[1] * t >= 100){
                        cnt++;
                        stack.pop();
                    }else{
                        break;
                    }
                }
                
                arr.add(cnt);
            }
            
            else{
                stack.push(top);
            }
            
            t++;
        }
        
        
        int N = arr.size();
        int[] answer = new int[N];
        for(int i = 0 ; i < N ; i++)
            answer[i] = arr.get(i);
        
        return answer;
    }
}