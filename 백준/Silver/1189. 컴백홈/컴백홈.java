import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W, K;
	static int answer;
	static boolean[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=  new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		visited = new boolean[H][W];
		
		for(int c = 0 ; c < H ; c++) {
			char[] line = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = line[r] == 'T';
			}
		}
		
		answer = 0;
		visited[H-1][0] = true;
		backtracking(1, H-1, 0);
		System.out.println(answer);
	}
	
	static int[] dc = {-1, 0, 1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static void backtracking(int depth, int c, int r) {
		if(depth == K) { // 거리가 K인 경우에
			if(c == 0 && r == W-1) {
				answer++;
			}
		}
		
		for(int i = 0 ; i < 4 ; i++) {
			int n_c = c + dc[i];
			int n_r = r + dr[i];
			if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
			if(visited[n_c][n_r] || map[n_c][n_r]) continue;
			visited[n_c][n_r] = true;
			backtracking(depth + 1, n_c, n_r);
			visited[n_c][n_r] = false;
		}
	}
//	
//	public static void print() {
//		StringBuilder sb = new StringBuilder();
//		for(int c = 0 ; c < H ; c++) {
//			for(int r = 0 ; r < W ; r++) {
//				if(map[c][r]) sb.append("# ");
//				else {
//					if(visited[c][r]) sb.append("1 ");
//					else sb.append("0 ");
//				}
//			}
//			sb.append("\n");
//		}
//		System.out.println(sb);
//	}
}
