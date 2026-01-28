/* 
    모든 차량이 단속용 카메라를 한 번은 만나도록?
    
    그럼 결국에는 가장 HashSet으로 넣을 수 있는 것이 많은 곳부터 넣으면 되지 않나?
    
    6만개의 배열로는 맞았지만 시간초과 -> PQ로 풀어보자.
*/

import java.util.*;

class Car implements Comparable<Car>{
    int s , e;
    
    public Car(int s, int e){
        this.s = s;
        this.e = e;
    }
    
    @Override
    public int compareTo(Car o){
        if(this.e == o.e) return Integer.compare(this.s, o.s);
        return Integer.compare(this.e, o.e);
    }
}

class Solution {
    public int solution(int[][] routes) {

        int N = routes.length;
        
        ArrayList<Car> arr = new ArrayList<>();
        for(int[] route : routes)
            arr.add(new Car(route[0], route[1]));
        
        Collections.sort(arr);
    
        int answer = 0;
        int cnt = 0;
        while(cnt < N){
            int end = arr.get(cnt).e;
            
            // 내 뒤에 시작이 end보다 작은 놈을 다찾아.
            for(int i = cnt + 1 ; i < N ; i++){
                if(arr.get(i).s <= end)
                    cnt++;   
                else break;
            }
            
            cnt++;
            answer++;
        }
        
        return answer;
    }
}