import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, N, C;
    static int comein[];
    static ArrayList<Integer> adjList[];
    static Queue<Integer> q;
    static int count;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb = new StringBuilder();
            N = Integer.parseInt(br.readLine());
            comein = new int[N + 1];
            adjList = new ArrayList[N + 1];
            q = new ArrayDeque<Integer>();
            count = 0;
            
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<Integer>();
            }

//            int team[] = Arrays.stream(br.readLine().split(" "))
//                    .mapToInt(Integer::parseInt)
//                    .toArray();
            
            int[] team = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i++) {
            	team[i] = Integer.parseInt(st.nextToken());
            }

            comein[0] = -1;
            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    adjList[team[i - 1]].add(team[j - 1]);
                    comein[team[j - 1]]++;
                }
            }

            int start = -1, end = -1;
            C = Integer.parseInt(br.readLine());
            for (int i = 0; i < C; i++) {
                st = new StringTokenizer(br.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                if (adjList[start].contains(Integer.valueOf(end))) {
                    adjList[start].remove(Integer.valueOf(end));
                    comein[end]--;
                    adjList[end].add(start);
                    comein[start]++;
                } else if (adjList[end].contains(Integer.valueOf(start))) {
                    adjList[end].remove(Integer.valueOf(start));
                    comein[start]--;
                    adjList[start].add(end);
                    comein[end]++;
                }
            }
            
            for (int i = 1; i <= N; i++) {
                if (comein[i] == 0) q.offer(i);
            }
            
            int curV;
            int next;
            boolean ambiguous = false;
            while (!q.isEmpty()) {
                if (q.size() > 1) {
                    ambiguous = true;
                    break;
                }
                curV = q.poll();
                sb.append(curV).append(" ");
                count++;
                for (int i = 0; i < adjList[curV].size(); i++) {
                    next = adjList[curV].get(i);
                    comein[next]--;
                    if (comein[next] == 0) q.offer(next);
                }
            }
            if (count != N || ambiguous) {
                sb = new StringBuilder();
                sb.append("IMPOSSIBLE").append("\n");
            } else sb.append("\n");
            System.out.print(sb);
        }    
    }
}