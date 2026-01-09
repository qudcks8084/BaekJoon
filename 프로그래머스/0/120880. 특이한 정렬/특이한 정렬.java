import java.util.*;

class Solution {
    
    public static class Node implements Comparable<Node>{
        int idx;
        int x;
        int gap;
        
        @Override
        public int compareTo(Node o){
            if(this.gap == o.gap){
                return - Integer.compare(this.x, o.x);
            }
            else{
                return Integer.compare(this.gap, o.gap);
            }
        }
        
        public Node(int idx, int x, int gap){
            this.idx = idx;
            this.x = x;
            this.gap = gap;
        }
        
    }
    public int[] solution(int[] numlist, int n) {
        
        int N = numlist.length;
        
        ArrayList<Node> arr = new ArrayList<>();
        
        for(int i = 0 ; i < N ; i++){
            arr.add(new Node(i, numlist[i], Math.abs(n - numlist[i])));
        }
        
        Collections.sort(arr);
        
        int[] answer = new int[N];
        
        for(int i = 0 ; i < N ; i++){
            answer[i] = arr.get(i).x;
        }
        
        return answer;
    }
}