import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int m_c, m_r;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visited = new boolean[H][W][64];
		
		for(int c = 0 ; c < H ; c++) {
			char[] line = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				char tmp = line[r];
				map[c][r] = tmp;
				
				// 시작 위치 파악
				if(tmp == '0') { 
					m_c = c;
					m_r = r;
					map[c][r] = '.';
				}
			}
		}
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {m_c, m_r, 0});
		visited[m_c][m_r][0] = true;
		
		int time = 1;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int l = 0 ; l < len ; l++) {
				int[] cur = q.poll();
				int c = cur[0];
				int r = cur[1];
				int d = cur[2];
				for(int i = 0 ; i < 4 ; i++) {
					int n_c = c + dc[i];
					int n_r = r + dr[i];
					// 좌표 경계값 확인
					if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
					if(visited[n_c][n_r][d] || map[n_c][n_r] == '#') continue;
					char next = map[n_c][n_r];
					if(next == '1') { // 종료 조건
						System.out.println(time);
						return;
					}
					// 그냥 빈공간 -> 가면댐
					if(next == '.') { 
					    visited[n_c][n_r][d] = true;
					    q.offer(new int[] {n_c, n_r, d});
					    continue;
					}
					// 문을 만났을 때 열쇠가 있는지 확인
					if (Character.isAlphabetic(next) && Character.isUpperCase(next)) {
					    int key = next - 'A'; // 'A'는 0번째 비트, 'B'는 1번째 비트 ...
					    if ((d & (1 << key)) == 0) {
					        continue; // 열쇠가 없으면 이동 불가
					    }
					    // 열쇠가 있음 -> 가야지
					    visited[n_c][n_r][d] = true;
					    q.offer(new int[] {n_c, n_r, d});
					    continue;
					}
					// 열쇠 -> 열쇠를 맥이고 가면댐
					if(Character.isAlphabetic(next) && Character.isLowerCase(next)) {
						int key = next - 'a';
						int n_d = d | (1<<key);
						
						// 새로운 차원으로 이동
						visited[n_c][n_r][n_d] = true;
						q.offer(new int[] {n_c, n_r, n_d});
					}
				}
			}
			time++;
		}
		
		System.out.println(-1);
	}
	
}
