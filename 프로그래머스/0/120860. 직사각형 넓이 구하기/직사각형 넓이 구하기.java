class Solution {
    public int solution(int[][] dots) {
        
        int[] x = new int[]{-257, 0};
        int[] y = new int[]{-257, 0};
        
        for(int[] dot : dots){
            // 초기화
            if(x[0] == -257) x[0] = dot[0];
            else if(x[0] != dot[0]) x[1] = dot[0];
            
            if(y[0] == -257) y[0] = dot[1];
            else if(y[0] != dot[1]) y[1] = dot[1];
        }

        return Math.abs(x[0] - x[1]) * Math.abs(y[0] - y[1]);
    }
}