import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long answer;
    static long[] sour, bitter;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sour = new long[N];
        bitter = new long[N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            sour[i] = Long.parseLong(st.nextToken());
            bitter[i] = Long.parseLong(st.nextToken());
        }

        answer = Integer.MAX_VALUE;
        subset(0, 1, 0, 0);
        System.out.println(answer);
    }

    public static void subset(int depth, long sour_sum, long bitter_sum, int num_of_true){
        if(depth == N){
            if(num_of_true > 0){
                long gap = Math.abs(sour_sum - bitter_sum);
                answer = Math.min(answer, gap);
            }
        }else{
            subset(depth + 1, sour_sum * sour[depth], bitter_sum + bitter[depth], num_of_true + 1);
            subset(depth + 1, sour_sum, bitter_sum, num_of_true);
        }
    }
}
