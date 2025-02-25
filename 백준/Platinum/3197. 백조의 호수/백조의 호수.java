import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int[] swan;
	static boolean[][] map;
	static boolean[][] visited;
	static int[][] time;
	static ArrayDeque<int[]> ice;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		visited = new boolean[H][W];
		time = new int[H][W];
		swan = new int[4];
		
		int swan_i = 0;
		for(int c = 0 ; c < H ; c++) {
			char[] line = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				char tmp = line[r];
				map[c][r] = tmp == 'X';
				if(tmp == 'L') {
					swan[swan_i++] = c;
					swan[swan_i++] = r;
				}
			}
		}
		
		ice = new ArrayDeque<>();
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				// 물 공간이고, 현재 방문하지 않았다면
				if(!map[c][r] && !visited[c][r]) {
					findIce(c, r);
				}
			}
		}

		timeFind();
		
		visited = new boolean[H][W];
		int t_c = swan[2];
		int t_r = swan[3];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[2], o2[2]);
		});
		pq.add(new int[] {swan[0], swan[1], 0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int c = cur[0];
			int r = cur[1];
			int t = cur[2];
			if(c == t_c && r == t_r) { // 백조를 찾음
				System.out.println(t);
				return;
			}
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = c + dc[i];
				int n_r = r + dr[i];
				if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
				if(visited[n_c][n_r]) continue;
				visited[n_c][n_r] = true;
				// 만약 time[n_c][n_r]이 나보다 작거나 같으면 그냥 간다.
				if(time[n_c][n_r] <= t) pq.add(new int[] {n_c, n_r, t});
				else pq.add(new int[] {n_c, n_r, time[n_c][n_r]});
			}
		}
	}
	
	
	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0, -1};
	
	public static void findIce(int sc, int sr) {
		visited[sc][sr] = true;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sc, sr});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int c = cur[0];
			int r = cur[1];
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = c + dc[i];
				int n_r = r + dr[i];
				if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
				if(visited[n_c][n_r]) continue; // 물은 visited로 중복을 제거
				visited[n_c][n_r] = true;
				if(map[n_c][n_r]) ice.offer(new int[] {n_c, n_r});
				else q.offer(new int[] {n_c, n_r});
			}
		}
	}
	
	public static void timeFind() {
		int t = 1;
		while(!ice.isEmpty()) {
			int len = ice.size();
			for(int l = 0 ; l < len ; l++) {
				int[] cur = ice.poll();
				int c = cur[0];
				int r = cur[1];
				time[c][r] = t;
				for(int i = 0 ; i < 4 ; i++) {
					int n_c = c + dc[i];
					int n_r = r + dr[i];
					if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
					if(visited[n_c][n_r] || !map[n_c][n_r]) continue; // 얼음은 visited로 중복을 제거
					visited[n_c][n_r] = true;
					ice.offer(new int[] {n_c, n_r});
				}
			}
			t++;
		}
		
	}
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				sb.append(map[c][r] ? "X " : ". ");
			}
			sb.append("\t");
			for(int r = 0 ; r < W ; r++) {
				sb.append(time[c][r]).append(" ");
			}
			
			sb.append("\t");
			for(int r = 0 ; r < W ; r++) {
				sb.append(visited[c][r] ? "1 " : "0 ").append(" ");
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
