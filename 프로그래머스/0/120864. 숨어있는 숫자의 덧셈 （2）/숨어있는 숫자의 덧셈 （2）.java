import java.util.*;

class Solution {
    public int solution(String my_string) {
        StringTokenizer st = new StringTokenizer(my_string.replaceAll("[^0-9]", " "));
        int answer = 0;
        while(st.hasMoreTokens()){
            answer += Integer.parseInt(st.nextToken());
        }
        return answer;
    }
}