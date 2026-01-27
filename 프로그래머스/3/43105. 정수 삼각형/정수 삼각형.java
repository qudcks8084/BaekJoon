/* 
    거꾸로 아래에서부터 위로 올라가면서 풀면 된다.
*/

class Solution {
    public int solution(int[][] triangle) {
        
        int N = triangle.length;
        
        for(int c = N - 2 ; c >= 0 ; c--){
            for(int r = 0 ; r <= c; r++){
                triangle[c][r] += Math.max(triangle[c+1][r], triangle[c+1][r+1]);
            }
        }

        return triangle[0][0];
    }
}