import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, E;
    static int[][] adjMatrix;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        adjMatrix = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        }

        for(int i = 0 ; i < E ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            if(adjMatrix[s][e] > w) adjMatrix[s][e] = w;
        }

        for(int i = 0 ; i < N ; i++){
            update(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j || adjMatrix[i][j] == Integer.MAX_VALUE) sb.append("0 ");
                else sb.append(adjMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void update(int start){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i = 0 ; i < N ; i++){
            if(adjMatrix[start][i] != Integer.MAX_VALUE) q.offer(new int[]{i, adjMatrix[start][i]});
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int w = cur[1];
            for(int i = 0 ; i < N ; i++){
                // 너 갈수 있늬~?
                if(adjMatrix[v][i] == Integer.MAX_VALUE) continue;
                if(adjMatrix[start][i] > w + adjMatrix[v][i]){
                    adjMatrix[start][i] = w + adjMatrix[v][i];
                    q.offer(new int[]{i, adjMatrix[start][i]});
                }
            }
        }
    }
}
