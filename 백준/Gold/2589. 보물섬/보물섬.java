import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int max_distance;
	static boolean[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new boolean[H][W];
		visited = new boolean[H][W];
		
		for(int c = 0 ; c < H ; c++) {
			char[] line = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = line[r] == 'L';
			}
		}
		
		max_distance = Integer.MIN_VALUE;
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				if(map[c][r]) {
					int max_len = find(c, r);
					max_distance = Math.max(max_distance, max_len);
				}
			}
		}
		
		System.out.println(max_distance);
	}
	
	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1 ,0, -1};
	public static int find(int c, int r) {
		visited = new boolean[H][W];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[c][r] = true;
		q.offer(new int[] {c, r});
		int max_length = 0;
		while(true) {
			int len = q.size();
			for(int l = 0 ; l < len ; l++) {
				int[] cur = q.poll();
				for(int i = 0 ; i < 4 ; i++) {
					int n_c = cur[0] + dc[i];
					int n_r = cur[1] + dr[i];
					if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
					if(!visited[n_c][n_r] && map[n_c][n_r]) {
						visited[n_c][n_r] = true;
						q.offer(new int[] {n_c, n_r});
					}
				}
			}
			if(q.isEmpty()) break;
			max_length++;
		}
		return max_length;
	}
}
