import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W, max_distance;
	static boolean[] visited_alpha;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited_alpha = new boolean[26];
		
		
		for(int c = 0 ; c < H ; c++) {
			char[] input = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = input[r]-'A';
			}
		}
		
		max_distance = 1;
		visited_alpha[map[0][0]] = true;
		backTracking(1, 0, 0);
		System.out.println(max_distance);
	}
	
	static int[] dc = {-1, 0, 1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static void backTracking(int depth, int c, int r) {
		max_distance = Math.max(max_distance, depth);
		for(int i = 0 ; i < 4 ; i++) {
			int n_c = c + dc[i];
			int n_r = r + dr[i];
			if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
			if(visited_alpha[map[n_c][n_r]]) continue;
			visited_alpha[map[n_c][n_r]] = true;
			backTracking(depth + 1, n_c, n_r);
			visited_alpha[map[n_c][n_r]] = false;
		}
	}
}
