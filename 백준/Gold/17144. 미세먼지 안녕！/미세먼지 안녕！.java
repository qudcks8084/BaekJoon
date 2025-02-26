
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	
	static int H, W, T;
	static int[] purifier;
	static int[][] map;
	static int[][] change;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		purifier = new int[4];
		map = new int[H][W];
		
		int purifier_index = 0;
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[c][r] = tmp;
				if(tmp == -1) {
					purifier[purifier_index++] = c;
					purifier[purifier_index++] = r;
				}
			}
		}
		
		for(int i = 0 ; i < T ; i++) {
			defuse();
			move_up();
			move_down();
		}
		
		int sum = 2;
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				sum += map[c][r];
			}
		}
		System.out.println(sum);
	}
	
	static int[] dc = {-1, 0 ,1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static void defuse() {
		// 확산값을 저장할 새로운 배열 생성
		change = new int[H][W];
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				// 나눠줄 먼지가 있늬~?
				if(map[c][r] > 4) {
					// 있다면 나눠줘
					int amount = map[c][r] / 5;
					for(int i = 0 ; i < 4 ; i++) {
						int n_c = c + dc[i];
						int n_r = r + dr[i];
						// 경계값 밖인지를 검사
						if(n_c < 0 || n_c >= H || n_r <0 || n_r >= W) continue;
						// 공기청정기인지를 검사
						if(map[n_c][n_r] == -1) continue; 
						map[c][r] -= amount;
						change[n_c][n_r] += amount;
 					}
				}
			}
		}
		
		// 확산값을 전부 다 측정했다면 원래 배열에 다시 넣어주기
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				map[c][r] += change[c][r];
			}
		}
	}
	
	// 위쪽 배열 돌리기
	public static void move_up() {
		// 위쪽 공기청정기 기준 -> 위 -> 오른쪽 -> 왼쪽 -> 뒤쪽
		int s_c = purifier[0];
		int s_r = purifier[1];
		int c = s_c - 1;
		int r = s_r;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int d = 0 ; d < 4 ; d++) {
			while(true) {
				int n_c = c + dc[d];
				int n_r = r + dr[d];
				// 경계값을 만나면 종료합니다.
				if(n_c < 0 || n_c > s_c || n_r < 0 || n_r >= W) break;
				// 다시 공기청정기를 만나면 종료합니다.
				if(n_c == s_c && n_r == s_r) break;
				q.offer(map[n_c][n_r]);
				c = n_c;
				r = n_r;
			}
		}
		
		// 큐의 맨 뒤에 0을 하나 추가
		q.offer(0);
		
		// 다시 값 넣어주기 
		c = s_c;
		r = s_r;
		for(int d = 0 ; d < 4 ; d++) {
			while(true) {
				int n_c = c + dc[d];
				int n_r = r + dr[d];
				// 경계값을 만나면 종료합니다.
				if(n_c < 0 || n_c > s_c || n_r < 0 || n_r >= W) break;
				// 다시 공기청정기를 만나면 종료합니다.
				if(n_c == s_c && n_r == s_r) break;
				map[n_c][n_r] = q.poll();
				c = n_c;
				r = n_r;
			}
		}
	}
	
	// 아래쪽 배열 돌리기
	public static void move_down() {
		// 아래쪽 공기청정기 기준 -> 아래 -> 오른쪽 -> 위쪽 -> 왼쪽
		int[] down_order = {2, 1, 0, 3};
		int s_c = purifier[2];
		int s_r = purifier[3];
		int c = s_c + 1;
		int r = s_r;
		ArrayDeque<Integer> q =  new ArrayDeque<>();
		for(int d : down_order) {
			while(true) {
				int n_c = c + dc[d];
				int n_r = r + dr[d];
				// 경계값을 만나면 종료합니다.
				if(n_c < s_c || n_c >= H || n_r < 0 || n_r >= W) break;
				// 다시 공기청정기를 만나면 종료합니다.
				if(n_c == s_c && n_r == s_r) break;
				q.offer(map[n_c][n_r]);
				c = n_c;
				r = n_r;
			}
		}

		// 큐의 맨 뒤에 0을 하나 추가
		q.offer(0);
		
		// 다시 값 넣어주기
		c = s_c;
		r = s_r;
		for(int d : down_order) {
			while(true) {
				int n_c = c + dc[d];
				int n_r = r + dr[d];
				// 경계값을 만나면 종료합니다.
				if(n_c < s_c || n_c >= H || n_r < 0 || n_r >= W) break;
				// 다시 공기청정기를 만나면 종료합니다.
				if(n_c == s_c && n_r == s_r) break;
				map[n_c][n_r] = q.poll();
				c = n_c;
				r = n_r;
			}
		}
	}
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				sb.append(map[c][r]).append("\t");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
