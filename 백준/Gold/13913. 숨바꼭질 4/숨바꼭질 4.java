import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean[] visited;
    static int[] path;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        path = new int[100001];

        
        if(N == K){
            sb.append(0).append("\n").append(N);
        }else{
            find();
        }

        System.out.println(sb);
 
    }

    public static void find(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        visited[N] = true;
        int time = 0;
        while (!q.isEmpty()) {
        	int len = q.size();
        	for(int i = 0 ; i < len ; i++) {
        		int now = q.poll();
                int[] next_position = new int[]{now - 1, now + 1, now * 2};
                for (int next : next_position) {
                    if(next < 0 || next > 100000) continue;
                    if(next == K){
                    	sb.append(time+1).append("\n");
                        path[next] = now;
                        print(next);
                        return;
                    }
                    if(!visited[next]){
                        path[next] = now;
                        visited[next] = true;
                        q.offer(next);
                    }
                }
        	}
        	time++;
        }
    }

    public static void print(int before){
        if(before != N)
            print(path[before]);
        sb.append(before).append(" ");
    }
}
