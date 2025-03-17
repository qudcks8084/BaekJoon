import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0, -1};
	static int[] ddc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] ddr = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] map = new boolean[N][N];
		
		int[][] start = new int[3][2];
		int[][] end = new int[3][2];
		
		int start_index = 0;
		int end_index = 0;
		for(int c = 0 ; c < N ; c++) {
			char[] line = br.readLine().toCharArray();
			for(int r = 0 ; r < N ; r++) {
				if(line[r] == 'B') {
					start[start_index][0] = c;
					start[start_index][1] = r;
					start_index++;
				}else if(line[r] == 'E') {
					end[end_index][0] = c;
					end[end_index][1] = r;
					end_index++;
				}else if(line[r] == '1') {
					map[c][r] = true;
				}
			}
		}
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][N][2];
		
		// 시작점이 가로인지 세로인지 판단
		// start[1]이 중심점
		if(start[0][0] == start[1][0]) { // c가 같은 경우 
			q.offer(new int[] {start[1][0], start[1][1], 0});
			visited[start[1][0]][start[1][1]][0] = true;
		}else {
			q.offer(new int[] {start[1][0], start[1][1], 1});
			visited[start[1][0]][start[1][1]][1] = true;
		}
		
		// 종료 지점을 파악
		int endc, endr, endd;
		if(end[0][0] == end[1][0]) {
			endc = end[1][0];
			endr = end[1][1];
			endd = 0;
		}else {
			endc = end[1][0];
			endr = end[1][1];
			endd = 1;
		}
		
		int time = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int l = 0 ; l < len ; l++) {
				
				int[] cur = q.poll();
				int c = cur[0];
				int r = cur[1];
				int d = cur[2];
				
//				print(N, visited, map, cur, end);
				
				// 정답 처리
				if(c == endc && r == endr && d == endd) {
					System.out.println(time);
					return;
				}
				
				// 4방향 이동 탐색
				move : for(int i = 0 ; i < 4 ; i++) {
					int n_c = c + dc[i];
					int n_r = r + dr[i];
					// 이동한 곳의 3개가 모두 벽이 아닌지 확인
					if(d == 0) { // 가로인 경우
						if(n_c < 0 || n_c >= N) continue move; // 세로는 고정이니 확인
						int[] d_r = {n_r - 1, n_r ,n_r + 1};
						for(int nd_r : d_r) {
							if(nd_r < 0 || nd_r >= N) continue move; // 가로 범위 확인
							if(map[n_c][nd_r]) continue move; // 벽인지 확인
						}
						
						// 방문 가능한지 확인
						if(!visited[n_c][n_r][d]) {
							visited[n_c][n_r][d] = true;
							q.offer(new int[] {n_c, n_r, d});
						}
						
					}else {
						if(n_r < 0 || n_r >= N) continue move; // 세로는 고정이니 확인
						int[] d_c = {n_c - 1, n_c, n_c + 1};
						for(int nd_c : d_c) {
							if(nd_c < 0 || nd_c >= N) continue move; // 세로 범위 확인
							if(map[nd_c][n_r]) continue move;
						}
						
						// 방문 가능한지 확인
						if(!visited[n_c][n_r][d]) {
							visited[n_c][n_r][d] = true;
							q.offer(new int[] {n_c, n_r, d});
						}
					}
				}
				
				// 방향 바꾸기
				// 기준점 기준으로 8방을 전부 확인
				if(d == 0) { // 가로 -> 세로
					boolean flag = true;
					for(int i = 0 ; i < 8 ; i++) {
						int n_c = c + ddc[i];
						int n_r = r + ddr[i];
						if(n_c < 0 || n_c >= N-1 || n_r < 0 || n_r >= N || map[n_c][n_r]) {
							flag = false; 
							break;
						}
					}
					
					// 위 조건을 만족하고, 해당 위치를 방문하지 않았다면 이동
					if(flag && !visited[c][r][1]) {
						visited[c][r][1] = true;
						q.offer(new int[] {c, r, 1});
					}
				}else { // 세로 -> 가로
					boolean flag = true;
					for(int i = 0 ; i < 8 ; i++) {
						int n_c = c + ddc[i];
						int n_r = r + ddr[i];
						if(n_c < 0 || n_c >= N-1 || n_r < 0 || n_r >= N || map[n_c][n_r]) {
							flag = false; 
							break;
						}
					}
					
					// 위 조건을 만족하고, 해당 위치를 방문하지 않았다면 이동
					if(flag && !visited[c][r][0]) {
						visited[c][r][0] = true;
						q.offer(new int[] {c, r, 0});
					}
				}
			
			}
			time ++;
		}
		System.out.println(0);
	}
	
	static void print(int N, boolean[][][] visited, boolean[][] map, int[] cur, int[][] end) {
		char[][] print_map = new char[N][N];
		for(int c = 0 ; c < N ; c++) {
			Arrays.fill(print_map[c], '.');
			for(int r = 0 ; r < N ; r++) {
				if(map[c][r]) print_map[c][r] = '#';
			}
		}
		
		for(int[] end_p : end) {
			print_map[end_p[0]][end_p[1]] = 'E';
		}
		
		int nc = cur[0];
		int nr = cur[1];
		int nd = cur[2];
		if(nd == 0) {
			int[] nd_r = {nr -1, nr, nr+1};
			for(int n_r : nd_r) {
				print_map[nc][n_r] = 'B';
			}
		}else {
			int[] nd_c = {nc -1, nc, nc+1};
			for(int n_c : nd_c) {
				print_map[n_c][nr] = 'B';
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int c = 0 ; c < N ; c++) {
			for(int r = 0 ; r < N ; r++) {
				sb.append(print_map[c][r]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
