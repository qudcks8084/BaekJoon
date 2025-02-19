import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dc = {-1, 0 ,1, 0};
	static int[] dr = {0, 1 ,0, -1};
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int c = 0 ; c < N ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < N ; r++) {
				map[c][r] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(true) {
			int numOfmove = 0;
			visited = new boolean[N][N];
			for(int c = 0 ; c < N ; c++) {
				for(int r = 0 ; r < N ; r++) {
					if(!visited[c][r]) {
						if(population_move(c, r)) {
							numOfmove++;
						}
					} 
				}
			}
			if(numOfmove == 0) {
				break;
			}
			time++;
		}
		System.out.println(time);
		
		
	}
	
	public static boolean population_move(int c, int r) {
		int sum = map[c][r];
		int[] start = new int[] {c,r};
		ArrayList<int[]> city = new ArrayList<>();
		ArrayDeque<int[]> q=  new ArrayDeque<>();
		city.add(start);
		q.offer(new int[] {c, r});
		visited[c][r] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = cur[0] + dc[i];
				int n_r = cur[1] + dr[i];
				if(n_c < 0 || n_c >= N || n_r < 0 || n_r >= N) continue;
				if(visited[n_c][n_r]) continue;
				int gap = Math.abs(map[n_c][n_r] - map[cur[0]][cur[1]]);
				if(gap >= L && gap <= M) { // 인구수 차이가 조건에 부합한다면
					visited[n_c][n_r] = true;
					int[] new_city = new int[] {n_c, n_r};
					city.add(new_city);
					q.offer(new_city);
					sum += map[n_c][n_r];
				}
			}
		}
		if(sum > map[c][r]) { // 경계가 열려서 도시간의 이동이 가능하다면
			int avg = sum / city.size();
			for(int[] each_city : city) {
				map[each_city[0]][each_city[1]] = avg;
			}
			return true;
		}
		
		return false;
	}
}
