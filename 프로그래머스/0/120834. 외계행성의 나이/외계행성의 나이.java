class Solution {
    public String solution(int age) {
        char[] convert = {'a','b','c','d','e','f','g','h','i','j'};
        
        char[] list = (age + "").toCharArray();
        for(int i = 0 ; i < list.length ; i++){
            list[i] = convert[list[i]-'0'];
        }

        return new String(list);
    }
}