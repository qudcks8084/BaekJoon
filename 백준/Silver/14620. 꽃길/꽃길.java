import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Seed{
		int c, r;
		
		public Seed(int c, int r) {
			this.c = c;
			this.r = r;
		}
	}
	
	static int N, M;
	static int r = 3;
	static int min_price;
	static int[][] map;
	static Seed[] seed_map;
	static Seed[] comb_seed; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = (N-4) * N + 4;
		map = new int[N][N];
		seed_map = new Seed[M];
		
		int index = 0;
		for(int c = 0 ; c < N ; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < N ; r++) {
				map[c][r] = Integer.parseInt(st.nextToken());
				if(c == 0 || r == 0 || c == N-1 || r == N-1) continue;		
				seed_map[index++] = new Seed(c, r);
			}
		}
		
		comb_seed = new Seed[r];
		min_price = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(min_price);
	}
	
	static void comb(int depth, int start) {
		if(depth == r) {
			flower();
			return;
		}
		else {
			for(int i = start ; i < M ; i++) {
				comb_seed[depth] = seed_map[i];
				comb(depth + 1, i + 1);
				comb_seed[depth] = null;
			}
		}	
	}
	
	static int[] dc = {-1, 0, 1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static void flower() {
		boolean[][] visited = new boolean[N][N];
		int price = 0;
		for(int i = 0 ; i < r ; i++) {
			// 씨앗을 가져와
			Seed cur = comb_seed[i];
			int c = cur.c;
			int r = cur.r;
			if(visited[c][r]) return;
			visited[c][r] = true;
			price += map[c][r];
			for(int d = 0 ; d < 4 ; d++) {
				int n_c = c + dc[d];
				int n_r = r + dr[d];
				if(visited[n_c][n_r]) return;
				visited[n_c][n_r] = true;
				price += map[n_c][n_r];
			}
		}
		
		// 3개의 꽃을 전부 놓을 수 있음
		min_price = Math.min(min_price, price);
	}
}
