import java.util.*;

class Solution {
    public String solution(String[] id_pw, String[][] db) {
        
        HashMap<String, String> map = new HashMap<>();
        
        for(String[] account : db){
            map.put(account[0], account[1]);
        }
        
        if(!map.containsKey(id_pw[0])){
            return "fail";
        }
        
        if(map.get(id_pw[0]).equals(id_pw[1])){
            return "login";
        }
        
        return "wrong pw";
    }
}