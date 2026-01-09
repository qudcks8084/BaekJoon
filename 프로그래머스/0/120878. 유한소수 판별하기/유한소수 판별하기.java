class Solution {
    public int solution(int a, int b) {
        
        while(true){
            if(b % 2 == 0) b /= 2;
            else break;
        }
        
        while(true){
            if(b % 5 == 0) b /= 5;
            else break;
        }
        
        // 분모가 2와 5로만 구성되어있다면 1
        if(b == 1) return 1;
        
        // 만약 남는 숫자가 있다면 분자로 완벽히 나눠지면 1
        if(a % b == 0) return 1;
        
        return 2;

    }
}