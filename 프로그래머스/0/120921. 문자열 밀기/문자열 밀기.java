class Solution {
    public int solution(String A, String B) {
        
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        
        int N = A.length();
        
        int min = Integer.MAX_VALUE;
        
        // 오른쪽으로 몇칸을 밀어야 되는지 찾기
        ml:for(int i = 0 ; i < N ; i++){
            
            // i칸을 미는 경우
            for(int j = 0 ; j < N ; j++){
                if(a[j] != b[(j + i)%N]) continue ml;
            }
            
            // 끝나면 여기에서 정답 리턴
            // i칸 미루는 것이 정답
            min = Math.min(min, i);
        }
        
        if(min == Integer.MAX_VALUE) return -1;
        return min;
    }
}