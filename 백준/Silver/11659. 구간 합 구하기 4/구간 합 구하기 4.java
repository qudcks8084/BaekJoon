import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		dp[0] = Integer.parseInt(st.nextToken());
		for(int i = 1 ; i < N ; i++) {
			dp[i] = dp[i-1] + Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 2;
			int e = Integer.parseInt(st.nextToken()) - 1;
			if(s < 0) sb.append(dp[e]);
			else sb.append(dp[e]-dp[s]);
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}
