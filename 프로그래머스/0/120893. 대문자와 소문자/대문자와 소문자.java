class Solution {
    public String solution(String my_string) {
        char[] str = my_string.toCharArray();
        for(int i = 0 ; i < str.length ; i++){
            if(Character.isLowerCase(str[i]))
                str[i] = Character.toUpperCase(str[i]);
            else
                str[i] = Character.toLowerCase(str[i]);
        }
        return new String(str);
    }
}