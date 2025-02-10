import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int W, H;
	static boolean[][] map, visited;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = st.nextToken().equals("1");
			}
		} 
		
		int max_distance = Integer.MIN_VALUE;
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				if(!map[c][r]) {
					visited = new boolean[H][W];
					int shark = safeDistance(c, r);
//					System.out.println(c + " " + r + " " + " " + shark);
					max_distance = Math.max(max_distance, shark);
				}
			}
		} 
		System.out.println(max_distance);
	}
	
	
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dr = {0, 1, 1 ,1 , 0, -1, -1, -1};
	public static int safeDistance(int c, int r) {
		int nearest_shark = 0;
		Queue<int[]> q = new ArrayDeque<>();
		visited[c][r] = true;
		q.offer(new int[] {c,r});
		while(!q.isEmpty()) {
			int size = q.size();
			for(int j = 0 ; j < size ; j++) {
				int[] cur = q.poll();
				for(int i = 0 ; i < 8 ; i++) {
					int n_c = cur[0] + dc[i];
					int n_r = cur[1] + dr[i];
					if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W || visited[n_c][n_r]) continue;
					if(map[n_c][n_r]) { // 상어임
						return nearest_shark+1;
					}else {
						visited[n_c][n_r] = true;
						q.offer(new int[] {n_c, n_r});
					}
				}
			}
			nearest_shark++;
		}
		return 0;
	}
}
