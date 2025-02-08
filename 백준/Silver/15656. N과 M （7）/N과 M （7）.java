import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr, input;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        arr = new int[M];

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
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }else{
            for(int i = 0 ; i < N ; i++){
                arr[depth] = input[i];
                DFS(depth + 1);
                arr[depth] = 0;
            }
        }
    }
}
