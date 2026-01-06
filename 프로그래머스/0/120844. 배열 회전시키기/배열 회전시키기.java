import java.util.*;

class Solution {
    public int[] solution(int[] numbers, String direction) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i : numbers) q.offer(i);
        
        if(direction.equals("right")) q.addFirst(q.removeLast());
        else q.addLast(q.removeFirst());
        
        for(int i = 0 ; i < numbers.length ; i++){
            numbers[i] = q.poll();
        }
        return numbers;
    }
}