import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int H, W;
	static int[][] visited;
	static boolean[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		if(H == 1 && W == 1) {
			System.out.println(1);
			return;
		}
		
		map = new boolean[H][W];
		
		for(int c = 0 ; c < H ; c++) {
			char[] input = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = input[r] == '1';
			}
		}
		
		visited = new int[H][W];
		
		int new_max = bfs(0,0);
	
		System.out.println(new_max);
		
	}
	
	static int[] dc = {-1, 0, 1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static int bfs(int c, int r) {
		int path = 1;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[c][r] = 2;
		q.offer(new int[] {c, r, 1});
		while(!q.isEmpty()) {
			int len = q.size();
			for(int l = 0 ; l < len ; l++) {
				int[] cur = q.poll();
				for(int i = 0 ; i < 4 ; i++) {
					int n_c = cur[0] + dc[i];
					int n_r = cur[1] + dr[i];
					if(n_c == H-1 && n_r == W-1) {
						return path+1;
					} 
					if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W || visited[n_c][n_r] >= 2) continue; // 경계값
					
					if(cur[2] == 1) { // 목숨이 있는 얘가 지나갈때
						if(map[n_c][n_r]) { // 벽을 만나면
							if(visited[n_c][n_r] == 0) {
								visited[n_c][n_r] = 1;
								q.offer(new int[] {n_c, n_r, 0});
							}
						} else { // 벽이 아니라면
							if(visited[n_c][n_r] < 2)
							visited[n_c][n_r] = 2;
							q.offer(new int[] {n_c, n_r, 1});
						}
					}else { // 목숨이 없는 얘가 지나갈때
						// 벽이 아니고 방문을 하지 않았다면 갈 수 있음
						if(!map[n_c][n_r] && visited[n_c][n_r] == 0) {
							visited[n_c][n_r] = 1;
							q.offer(new int[] {n_c, n_r, 0});
						}
					}
				}
			}
			path++;
		}
		return -1;
	}
	
}
