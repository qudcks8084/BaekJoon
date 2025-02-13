import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int[][] cost;
	
	static class Cell implements Comparable<Cell>{
		int c,r;
		int cost;
		
		public Cell(int c, int r, int cost) {
			this.c = c;
			this.r = r;
			this.cost = cost;
		}

		@Override
		public int compareTo(Cell o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int numOfProblem = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			map = new int[N][N];
			cost = new int[N][N];
			
			for(int i = 0 ; i < N ; i++)
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			
			for(int c = 0 ; c < N ; c++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int r = 0 ; r < N ; r++) {
					map[c][r] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = bfs();
			sb.append("Problem ").append(numOfProblem).append(": ").append(result).append("\n");
			numOfProblem++;
		}
		System.out.println(sb);
	}
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	private static int bfs() {
		PriorityQueue<Cell> pq = new PriorityQueue<>();
		pq.offer(new Cell(0,0,map[0][0]));
		cost[0][0] = map[0][0];
		while(!pq.isEmpty()) {
			Cell cur = pq.poll();
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = cur.c + dc[i];
				int n_r = cur.r + dr[i];
				if(n_c < 0 || n_c >= N || n_r < 0 || n_r >= N) continue;
				if(cost[n_c][n_r] > cost[cur.c][cur.r] + map[n_c][n_r]) {
					cost[n_c][n_r] = cost[cur.c][cur.r] + map[n_c][n_r];
					pq.offer(new Cell(n_c, n_r, cost[n_c][n_r]));
				}
			}
		}
		return cost[N-1][N-1];
	}
}
