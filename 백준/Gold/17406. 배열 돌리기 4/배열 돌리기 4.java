import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W, K;
	static int answer;
	static int[][] origin;
	static int[][] map;
	static int[][] command;
	static int[] p;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		origin = new int[H][W];
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = Integer.parseInt(st.nextToken());
				origin[c][r] = map[c][r];
			}
		}
		
		
		command = new int[K][3];
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			command[i][0] = Integer.parseInt(st.nextToken()) - 1;
			command[i][1] = Integer.parseInt(st.nextToken()) - 1;
			command[i][2] = Integer.parseInt(st.nextToken());
		}
		
		p = new int[K];
		visited = new boolean[K];
		answer = Integer.MAX_VALUE;
		perm(0);
		System.out.println(answer);
	}
	
	public static void perm(int depth) {
		if(depth == K) {
			oper();
			return;
		}
		
		for(int i = 0 ; i < K ; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			p[depth] = i;
			perm(depth + 1);
			p[depth] = 0;
			visited[i] = false;
		}
	}
	
	public static void oper() {
		// 각 순서별 회전 연산 진행
		for(int i = 0 ; i < K ; i++) {
			int[] cur = command[p[i]];
			int c = cur[0];
			int r = cur[1];
			int d = cur[2];
			for(int j = 1 ; j <= d ; j++) {
				snail(c+j, r-j, j * 2);
			}
		}
		
		for(int c = 0 ; c < H ; c++) {
			int sum = 0;
			for(int r = 0 ; r < W ; r++) {
				sum += map[c][r];
			}
			answer = Math.min(answer, sum);
		}
		
		// 연산 종료 후에 최소값 갱신
		for(int c = 0 ; c < H ; c++) {
			System.arraycopy(origin[c], 0, map[c], 0, W);
		}
	}
	
	static int[] dc = {-1, 0, 1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static void snail(int s_c, int s_r, int size) {
		int c = s_c;
		int r = s_r;
		int start = map[c][r];
		int[] direction = {1, 0, 3, 2};
		// 방향을 선정하는 for문 - 위/우/아/왼
		for(int d : direction) {
			for(int i = 0 ; i < size ; i++) {
				int n_c = c + dc[d];
				int n_r = r + dr[d];
				map[c][r] = map[n_c][n_r];
				c = n_c;
				r = n_r;
			}
		}
		map[c-1][r] = start;
	}
	
}
