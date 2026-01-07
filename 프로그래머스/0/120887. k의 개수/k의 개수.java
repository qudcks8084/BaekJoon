class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        char ctarget = (char) (k + '0');
        String starget = k + "";
        for(int start = i ; start <= j ; start++){
            String base = start + "";
            if(base.contains(starget)){
                // 몇번 반복되는지 찾기
                for(int c = 0 ; c < base.length() ; c++){
                    if(base.charAt(c) == ctarget) answer++;
                }
            }
        }
        return answer;
    }
}