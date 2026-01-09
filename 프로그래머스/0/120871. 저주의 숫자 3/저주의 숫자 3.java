class Solution {
    public int solution(int n) {
        
        
        int cnt = 0;
        int t = 0;
        
        while(true){

            if(cnt == n){
                break;
            }
            
            t++;
            
            if(t % 3 != 0 && !(t+"").contains("3")){
                cnt++;               
            }
            
            
        }
        
        return t;
    }
}