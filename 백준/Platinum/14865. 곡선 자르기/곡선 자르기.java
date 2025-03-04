import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] nodes = new long[N][2];
        long min_x = Long.MAX_VALUE;
        long min_y = Long.MAX_VALUE;
        int min_index = -1;
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            nodes[i][0] = x;
            nodes[i][1] = y;
            if(x < min_x || (x == min_x && y < min_y)) { // 더 작은 값 등장
                min_x = x;
                min_y = y;
                min_index = i;
            }
        }

        ArrayList<Long> edge = new ArrayList<>();
        long lx = nodes[min_index][0];
        long ly = nodes[min_index][1];
        for(int i = 1 ; i < N ; i++) { // 모든 간선을 구해
            int index = (min_index + i) % N;
            long x = nodes[index][0];
            long y = nodes[index][1];
            if(x == lx){ // x축이 같아야 y축으로 이동한 것
                if((y > 0 && ly < 0) || (y < 0 && ly > 0)){ // 중간선을 넘어가는 경우
                    edge.add(x);
                }
            }
            lx = x;
            ly = y;
        }

        HashMap<Long, Long> map = new HashMap<>();
        for(int i = 0 ; i < edge.size() ; i+=2){
            long a = edge.get(i);
            long b = edge.get(i + 1);
            map.put(a, b);
            map.put(b, a);
        }

        Collections.sort(edge);

        int numOfHill = 0;
        int numOfDirectHill = 0;
        ArrayDeque<long[]> stack = new ArrayDeque<>();
        for (int i = 0 ; i < edge.size() ; i++){
            long l = edge.get(i);
            if(stack.isEmpty()){
                stack.push(new long[]{l, i});
            }
            else{
                long head = stack.peek()[0];
                if(map.get(l) == head){
                    long time = stack.pop()[1];
                    if(stack.isEmpty()) numOfHill++; // 다른 봉우리에 의해 포함되지 않는 봉우리
                    if(i- time == 1) numOfDirectHill ++; // 다른 봉우리에 의해 포함되지 않는 봉우리 개수 추가
                }else{
                    stack.push(new long[]{l, i});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(numOfHill).append(" ").append(numOfDirectHill);
        System.out.println(sb);
    }
}
