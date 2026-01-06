class Solution {
    public int[] solution(int[] array) {
        
        int max_index = 0;
        int max_value = array[0];
        
        int N = array.length;
        for(int i = 0 ; i < N ; i++){
            if(array[i] > max_value){
                max_value = array[i];
                max_index = i;
            }
        }
        
        return new int[]{max_value, max_index};
    }
}