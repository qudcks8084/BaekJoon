import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int min;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int max = (int)Math.sqrt(N);

        dp = new int[max + 1];
        for(int i = 1 ; i <= max ; i++){
            dp[i] = i * i;
        }

        min = Integer.MAX_VALUE / 10;
        find(0, max, 0);
        System.out.println(min);

    }

    public static void find(int depth, int index, int sum){
        if(sum > N) return;
        if(depth >= min) return;

        if(sum == N){
            min = Math.min(min, depth);
        }

        for(int i = index ; i >= 0 ; i--){
            find(depth + 1, i, sum + dp[i]);
        }
    }
}
