import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int r_c, r_r, r_d;
	static int answer;
	static boolean[][] map;
	static boolean[][] clean;
	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0 ,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		clean = new boolean[H][W];
		answer = 0;
		
		st = new StringTokenizer(br.readLine());
		r_c = Integer.parseInt(st.nextToken());
		r_r = Integer.parseInt(st.nextToken());
		r_d = Integer.parseInt(st.nextToken());
		
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = st.nextToken().equals("1");
			}
		}
		
		
		while(true) {
			// 로직 실행
			// 1. 만약 현재 칸이 청소되지 않았다면 청소
			if(!clean[r_c][r_r]) { 
				clean[r_c][r_r] = true;
				answer++;
			}
			
			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
			if(check_four_side()) {
				
				// 뒤 방향으로 설정
				int n_d = (r_d + 2) % 4; 
				int n_c = r_c + dc[n_d];
				int n_r = r_r + dr[n_d];
				if(map[n_c][n_r]) { // 벽이라면
					break;
				}else { // 갈 수 있다면
					r_c = n_c;
					r_r = n_r;
				}
			}
			
			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			else {
				// 반 시계 방향으로 90도 회전
				r_d = (r_d + 3) % 4; 
				int n_c = r_c + dc[r_d];
				int n_r = r_r + dr[r_d];
				
				// 청소되지 않은 바닥이라면
				if(!map[n_c][n_r] && !clean[n_c][n_r]) {
					r_c = n_c;
					r_r = n_r;
				}
			}
			
			// 라운드 결과 출력
//			print();
		}
		
		
		System.out.println(answer);
		
		
	}
	
	public static boolean check_four_side() {
		
		for(int i = 0 ; i < 4 ; i++) {
			int n_c = r_c + dc[i];
			int n_r = r_r + dr[i];
			if(map[n_c][n_r]) continue;
			if(!clean[n_c][n_r]) // 청소할 구역이 남아있음
				return false;
		}
		
		// 청소할 구역이 남아있지 않음
		return true;
	}
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				if(r_c == c && r_r == r) sb.append("# ");
				else sb.append(map[c][r] ? "1 " : "0 ");
			}
			sb.append("\t\t");
			for(int r = 0 ; r < W ; r++) {
				sb.append(clean[c][r] ? "1 " : "0 ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
