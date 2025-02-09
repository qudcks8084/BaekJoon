import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb;
    static HashSet<String> isIn;
    static int[] arr, input;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        visited = new boolean[N];
        arr = new int[M];
        isIn = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        DFS(0);
        System.out.println(sb);

    }

    public static void DFS(int depth){
        if(depth == M){
            StringBuilder comb = new StringBuilder();
            for (int i : arr) {
                comb.append(i).append(" ");
            }
            if(!isIn.contains(comb.toString())){
                isIn.add(comb.toString());
                sb.append(comb).append("\n");
            }
        } else {
            for(int i = 0 ; i < N ; i++){
                if(visited[i]) continue;
                visited[i] = true;
                arr[depth] = input[i];
                DFS(depth + 1);
                arr[depth] = 0;
                visited[i] = false;
            }
        }
    }
}
