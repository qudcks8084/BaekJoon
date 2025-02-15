import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());

        ArrayDeque<Long> q = new ArrayDeque<>();
        q.offer(A);

        int time = 1;
        while (!q.isEmpty()) {
            int len = q.size();
            for(int l = 0 ; l < len ; l++){
                long cur = q.poll();
                long[] next_list = new long[]{cur * 2, cur * 10 + 1};
                for (long next : next_list) {
                    if(next > B) continue;
                    if(next == B){
                        System.out.println(time+1);
                        return;
                    }
                    q.add(next);
                }
            }
            time++;
        }
        System.out.println("-1");
    }
}
