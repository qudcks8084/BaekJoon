import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] score = new int[N + 1];
		
		for(int i = 1 ; i <= N ; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		long[][] dp = new long[2][N+1];
		dp[0][1] = score[1];
		if(N > 1) {
			dp[1][2] = score[2];
			dp[0][2] = score[1] + score[2]; 
		}
		
		
		for(int i = 3 ; i <= N ; i++) {
			// 2칸 전에서 2칸을 뛰는 경우
			if(i >= 2) dp[1][i] = Math.max(dp[0][i-2],dp[1][i-2]) + score[i];
			
			// 1칸 전에서 올라오는 경우
			// 2. i-2 칸에 2칸을 뛰어서 1칸 이동한 경우 2 - 1
			dp[0][i] = dp[1][i-1] + score[i];
			
		}
		
		
		
		System.out.println(Math.max(dp[0][N], dp[1][N]));
	}
}
