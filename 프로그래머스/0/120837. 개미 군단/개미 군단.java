class Solution {
    public int solution(int hp) {
        int big = hp / 5;
        int medium = (hp - big * 5) / 3;
        int small = (hp - big * 5 - medium * 3);
        return big + medium + small;
    }
}