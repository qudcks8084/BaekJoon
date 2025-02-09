import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] input;
    static int[] comb;
    static HashSet<String> isIn;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        isIn = new HashSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        comb = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        DFS(0, 0);
        System.out.print(sb);
    }

    public static void DFS(int depth, int start){
        if(depth == M){
            StringBuilder line = new StringBuilder();
            for (int c : comb) {
                line.append(c).append(" ");
            }
            if (!isIn.contains(line.toString())) {
                isIn.add(line.toString());
                sb.append(line).append("\n");
            }
        }else{
            for(int i = start ; i < N ; i++){
                comb[depth] = input[i];
                DFS(depth + 1, i);
                comb[depth] = ' ';
            }
        }
    }
}
