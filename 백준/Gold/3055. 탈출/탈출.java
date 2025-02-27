import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int d_c, d_r;
	static int c_c, c_r;
	static int[] dc = {-1, 0, 1 ,0};
	static int[] dr = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[H][W];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		for(int c = 0 ; c < H ; c++) {
			char[] line = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				char tmp = line[r];
				map[c][r] = tmp;
				if(tmp == 'S') {
					d_c = c;
					d_r = r;
				}
				else if(tmp == '*') q.addFirst(new int[] {c, r});
				else if(tmp == 'D') {
					c_c = c;
					c_r = r;
				}
			}
		}
		
		q.offer(new int[] {d_c, d_r});
		int time = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int l = 0 ; l < len ; l++) {
				int[] cur = q.poll();
				int c = cur[0];
				int r = cur[1];
				for(int i = 0 ; i < 4 ; i++) {
					int n_c = c + dc[i];
					int n_r = r + dr[i];
					if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
					// 물인 경우
					if(map[c][r] == '*') {
						if(map[n_c][n_r] == '.') { // 빈칸이면 물을 퍼트린다.
							map[n_c][n_r] = '*';
							q.offer(new int[] {n_c, n_r});
						}
					}else { // 두더쥐가 가는 경우
						if(map[n_c][n_r] == '.') { // 빈칸인 경우
							map[n_c][n_r] = 'S';
							q.offer(new int[] {n_c, n_r});
						}
						else if(map[n_c][n_r] == 'D') { // 두더쥐가 동굴을 찾은 경우
							System.out.println(time + 1);
							return;
						}
					}
				}
			}
			time++;
		}
		
		System.out.println("KAKTUS");
	}

}
