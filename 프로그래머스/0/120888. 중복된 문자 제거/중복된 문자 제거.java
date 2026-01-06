import java.util.*;

class Solution {
    public String solution(String my_string) {
        
        HashSet<Character> visited = new HashSet<>();
        
        StringBuilder sb = new StringBuilder();
        for(char c : my_string.toCharArray()){
            if(visited.contains(c)) continue;
            visited.add(c);
            sb.append(c);
        }
        
        return sb.toString();
    }
}