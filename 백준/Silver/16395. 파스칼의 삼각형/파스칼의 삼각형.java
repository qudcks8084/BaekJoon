import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int K = H-W;
		
		long[][] dp = new long[H][W];
		
		Arrays.fill(dp[0], 1);
		for(int i = 0 ; i < H ; i++) {
			dp[i][0] = 1;
		}
		
		for(int c = 1 ; c < H ; c++) {
			for(int r = 1 ; r < W ; r++) {
				dp[c][r] = dp[c-1][r] + dp[c][r-1];
			}
		}
		
		System.out.println(dp[H-W][W-1]);
	}
}
