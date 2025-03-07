import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] adjList;
    static int[] input_degree;
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            adjList[i] = new ArrayList<>();
        }

        input_degree = new int[N];
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            input_degree[e]++;
            adjList[s].add(e);
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0 ; i < N ; i++){
            if(input_degree[i] == 0) q.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur + 1).append(" ");

            for(int next : adjList[cur]){
                input_degree[next]--;
                if(input_degree[next] == 0){
                    q.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}
