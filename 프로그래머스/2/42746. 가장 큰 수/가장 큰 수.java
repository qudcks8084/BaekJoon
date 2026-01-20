/* 
    음. 들어온 문제는 다음과 같다.
    뭐냐? 이어 붙여서 만들 수 있는 가장 큰 수? 
    
    -> 그거슨 바로 숫자를 사전식으로 정렬하면 된다
    어떤 순서로?
    
    1. 일단 전부 String[]으로 만들어.
    
    2. 앞에서부터 자리수 기반으로 정렬을 해.
    
    3. 3, 30, 34와 같은 경우를 처리를 해.
    
    4. 그 뒤에 StringBuilder로 전부 이어붙여서 출력. 끝.
    
    -> 실패.
    
    정답은... 실제 두개를 붙여서 더 큰게 앞으로 오게 하면됨...
*/



import java.util.*;
class Solution {
    
    static class Number implements Comparable<Number>{
    String number;
    
    @Override
    public int compareTo(Number o){
    
        /* 
        앞에 나오는 숫자가 클때까지 비교. 
        int n_t = this.number.length();
        int n_o = o.number.length();
        
        int min = Math.min(n_t, n_o);
        int max = min * 2;
        
        기존에는 앞에서부터 숫자를 비교하고, 그 뒤에는 짧은게 앞으로 오게 함.
        그러니 3, 30, 34와 같이 뒤에오는 숫자가 커버리는것을 잡지 못함
        그래서 Mod 연산으로 그 뒤까지 계속 반복해서 정렬하도록 수정
        
        for(int i = 0 ; i < max ; i++){
            if(this.number.charAt(i % n_t) == o.number.charAt(i % n_o)) continue;
            return Character.compare(o.number.charAt(i % n_o), this.number.charAt(i % n_t));
        }
        
        */
        
        // -> 실패 ( 다른 방법으로 풀이... )
        // 실제 순서를 뒤집어서 비교하면 끝....?
        String a = this.number + o.number;
        String b = o.number + this.number;
        return b.compareTo(a);
        
    }
    
    public Number(int number){
        this.number = number + "";
    }
    
    }
    
    public String solution(int[] numbers) {
        
        ArrayList<Number> arr = new ArrayList<>();
        
        for(int number : numbers){
            arr.add(new Number(number));
        }
        
        Collections.sort(arr);
        
        // 0처리
        if(arr.get(0).number.startsWith("0"))
            return "0";
        
        StringBuilder sb = new StringBuilder();
        for(Number number : arr){
            sb.append(number.number);
        }
        
        return sb.toString();
    }
}