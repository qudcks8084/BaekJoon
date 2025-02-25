import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	
	static int N, answer;
	static boolean[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		for(int c = 0 ; c < N ; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < N ; r++) {
				map[c][r] = st.nextElement().equals("1");
			}
		}
		
		answer = 0;
		pipe(0, 1 ,0);
		System.out.println(answer);
	}
	
	static int[] dc = {0, 1, 1};
	static int[] dr = {1, 1, 0};
	public static void pipe(int c, int r, int d) {
		// 정답 조건
		if(c == N-1 && r == N-1) {
			answer++;
			return;
		}
		
		// 오른쪽, 아래쪽 좌표값을 계산해
		int n_r = r + 1;
		int n_c = c + 1;
		
		// 방향에 따른 탐색 시작
		// 오른쪽 방향 탐색 -> 오른쪽으로 가고있거나, 대각선으로 가고있는 경우에 탐색
		if(d == 0 || d == 1) { 
			// 경계값을 안넘어가고, 장애물이 없으며, 아직 방문하지 않았다면
			if(n_r < N && !map[c][n_r]) {
				pipe(c, n_r, 0); // 오른쪽으로 이동
			}
		}
		
		// 아래쪽 방향 탐색 -> 아래쪽으로 가고있거나, 대각선으로 가고있는 경웨만 탐색
		if(d == 1 || d == 2) {
			// 경계값을 안넘어가고, 장애물이 없으며, 아직 방문하지 않았다면
			if(n_c < N && !map[n_c][r] ) {
				pipe(n_c, r, 2);
			}
		}
		
		// 방향과 관계없이 대각선 방향은 모두 시행함
		if(n_c >= N || n_r >= N) return;
		if(map[c][n_r] || map[n_c][r] || map[n_c][n_r]) return;
		pipe(n_c ,n_r, 1);

	}
}
