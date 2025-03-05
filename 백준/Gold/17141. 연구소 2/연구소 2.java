import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, V;
	static int minInfectionTime;
	static int numOfBlank;
	static ArrayList<int[]> virus_stack;
	static int[][] map;
	static int[] comb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minInfectionTime = Integer.MAX_VALUE;
		
		virus_stack = new ArrayList<>();
		comb = new int[M];
		map = new int[N][N];
		
		numOfBlank = N*N-M;
		for(int c = 0 ; c < N ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < N ; r++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[c][r] = tmp;
				if(tmp == 2) {
					virus_stack.add(new int[] {c, r});
					map[c][r] = 0;
				} 
				if(tmp == 1) numOfBlank--;
			}
		}
		
		if(numOfBlank == 0) {
			System.out.println(0);
			return;
		}
		
		V = virus_stack.size();
		comb(0, 0);
		
		StringBuilder sb = new StringBuilder();
		if(minInfectionTime == Integer.MAX_VALUE) sb.append("-1"); 
		else sb.append(minInfectionTime);
		System.out.println(sb);
		
	}
	
	public static void comb(int depth, int start) {
		if(depth == M) {
			virus();
			return;
		}
		
		for(int i = start ; i < V ; i++) {
			comb[depth] = i;
			comb(depth + 1, i + 1);
			comb[depth] = 0;
		}
	}
	
	
	static int[] dc = {-1, 0, 1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static void virus() {
		boolean[][] visited = new boolean[N][N];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		// 선택된 시작 바이러스를 삽입
		for(int v : comb) {
			int[] start = virus_stack.get(v);
			q.offer(start);
			visited[start[0]][start[1]] = true;
		} 
		
		int time = 0;
		int numOfInfection = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int l = 0 ; l < len ; l++) {
				int[] cur = q.poll();
				int c = cur[0];
				int r = cur[1];
				for(int i = 0 ; i < 4 ; i++) {
					int n_c = c + dc[i];
					int n_r = r + dr[i];
					if(n_c < 0 || n_c >= N || n_r < 0 || n_r >= N) continue;
					if(map[n_c][n_r] == 0 && !visited[n_c][n_r]) {
						visited[n_c][n_r] = true;
						q.offer(new int[] {n_c, n_r});
						numOfInfection++;
					}
				}
			}
			time++;
			
			// 바이러스가 빈 공간을 다 채운 경우
			if(numOfInfection == numOfBlank) {
				minInfectionTime = Math.min(time, minInfectionTime);
				return;
			}
		}
		
	}
}
