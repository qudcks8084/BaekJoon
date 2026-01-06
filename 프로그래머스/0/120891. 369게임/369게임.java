class Solution {
    public int solution(int order) {
        char[] arr = (order + "").toCharArray();
        
        int answer = 0;
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] == '0') continue;
            if((arr[i] - '0') % 3 == 0) answer++;
        }
        
        return answer;
    }
}