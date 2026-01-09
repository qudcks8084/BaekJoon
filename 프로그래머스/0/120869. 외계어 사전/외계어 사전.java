class Solution {
    public int solution(String[] spell, String[] dic) {
        
        boolean[] sp = new boolean[26];
        for(String s : spell){
            sp[s.charAt(0)-'a'] = true;
        }
        
        loop:for(String s : dic){
            boolean[] tg = new boolean[26];
            
            for(char c : s.toCharArray()){
                tg[c-'a'] = true;
            }
            
            for(int i = 0 ; i < 26 ; i++){
                if(tg[i] != sp[i]) continue loop;
            }
            
            return 1;
        }
        
        return 2;
    }
}