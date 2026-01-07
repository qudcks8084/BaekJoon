class Solution {
    public int solution(int[] array) {
        int answer = 0;
        
        for(int i = 0 ; i < array.length ; i++){
            for(char c : (array[i] + "").toCharArray()){
                if(c == '7') answer++; 
            }
        }
        return answer;
    }
}