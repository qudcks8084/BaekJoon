/* 
    일단 노란색을 구해.
    갈색을 거기에 감싸.
    최대를 만들어 끝.
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        
        // 일단 노란색의 가능성을 만들어.
        int y_m = yellow / 2;
        if(yellow == 1) y_m = 1;
        for(int h = 1 ; h <= y_m ; h++){
            
            // 가로가 결과임
            if(yellow % h == 0){
                int w = yellow / h;
                
                // 이제 갈색을 검증하면 됨.
                // 갈색 검증법 = (가로 + 2) * (세로 + 2) - yellow == brown이여야함.
                
                for(int i = 1 ; i < 10 ; i++){
                    int cur_brown = (w + i * 2) * (h + i * 2) - yellow;
                    
                    // 빨리 찾은게 정답임.
                    if(cur_brown == brown){
                        return new int[]{w + i * 2, h + i * 2};
                    } 
                    
                    // 더 커지면 애초에 안되는거임.
                    if(cur_brown > brown){
                        break;
                    }
                }
            }
        }
        
        int[] answer = {};
        return answer;
    }
}