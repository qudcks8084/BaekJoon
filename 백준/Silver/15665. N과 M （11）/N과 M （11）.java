import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb;
    static int[] set, input;
    static HashSet<String> isIn;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = new int[M];
        input = new int[N];
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
            StringBuilder tmp = new StringBuilder();
            for (int i : set) {
                tmp.append(i).append(" ");
            }
            if(!isIn.contains(tmp.toString())){
                isIn.add(tmp.toString());
                sb.append(tmp).append("\n");
            }
        }else{
            for(int i = 0 ; i < N ; i++){
                set[depth] = input[i];
                DFS(depth + 1);
                set[depth] = 0;
            }
        }
    }
}
