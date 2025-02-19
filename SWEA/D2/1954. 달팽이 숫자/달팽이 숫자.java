import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1 ; testcase <= T ; testcase++) {
			
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int M = N * N;
			
			int snail = 1;
			int c = 0;
			int r = 0;
			map[c][r] = snail++;
			
			while(true) {
				while(r + 1 < N && map[c][r+1] == 0) {
					r++;
					map[c][r] = snail++;
				}
				
				while(c + 1 < N && map[c+1][r] == 0) {
					c++;
					map[c][r] = snail++;
				}
				
				while(r - 1 >= 0 && map[c][r-1] == 0) {
					r--;
					map[c][r] = snail++;
				}
				
				while(c - 1 >= 0 && map[c-1][r] == 0) {
					c--;
					map[c][r] = snail++;
				}
				
				if(snail == M+1) break;
			}	

			
			StringBuilder sb = new StringBuilder();
			
			sb.append("#").append(testcase).append("\n");
			for(int sc = 0 ; sc < N ; sc++) {
				for(int sr = 0 ; sr < N ; sr++) {
					sb.append(map[sc][sr]).append(" ");
				}
				sb.append("\n");
			}
			
			System.out.print(sb);
			
			
		}
		
	}
}
