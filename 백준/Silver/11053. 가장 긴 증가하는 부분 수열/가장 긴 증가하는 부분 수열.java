import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dp = new int[N];
		int[] input = new int[N];
		
		// 입력받기
		for(int i = 0 ; i < N ; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for(int i = 1 ; i < N ; i++) {
			for(int j = i ; j >= 0 ; j--) {
				if(input[j] < input[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max+1);
	}
}
