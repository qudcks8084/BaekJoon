import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		for(int c = 0 ; c < H ; c++) {
			char[] input = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				if(input[r] == '.') map[c][r] = -1;
				else map[c][r] = 0;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				if(map[c][r] == 0) {
					cloud(c, r, 1);
				}
				sb.append(map[c][r]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	
	}

	private static void cloud(int c, int r, int weight) {
		int n_r = r+1;
		if(n_r >= W || map[c][n_r] == 0) return;
		map[c][n_r] = weight;
		cloud(c, n_r, weight+1);
	}
}
