class Solution {
    public int solution(int chicken) {
        return coupon(chicken);
    }
    
    public int coupon(int n){
        if(n < 10) return 0;
        return n/10 + coupon(n/10 + n%10);      
    }
}