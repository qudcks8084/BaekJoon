class Solution {
    public int solution(int price) {
        return discount(price);
    }
    
    public int discount(int price){
        if(price >= 500_000) return (int)(price * 0.8);
        else if(price >= 300_000) return (int)(price * 0.9);
        else if(price >= 100_000) return (int)(price * 0.95);
        else return price;
    }
}