import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main{
	
	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0, -1};
	
	static int[] hc = {2, 1, -1, -2, -2, -1, 1, 2};
	static int[] hr = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[H][W];
		boolean[][][] visited = new boolean[H][W][K+1];
		
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = st.nextToken().equals("1");
			}
		}
		
		if(W == 1 && H == 1) {
			System.out.println(0);
			return;
		}
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[0][0][0] = true;
		q.offer(new int[] {0,0,K,0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int horse = cur[2];
			int time = cur[3] + 1;
			
			// 일단 인간 폼으로 찾아봐
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = cur[0] + dc[i];
				int n_r = cur[1] + dr[i];
				if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W ) continue;
				if(visited[n_c][n_r][horse] || map[n_c][n_r]) continue;
				if(n_c == H-1 && n_r == W-1) { // 정답을 찾은 경우
					System.out.println(time);
					return;
				}
				visited[n_c][n_r][horse] = true;
				q.offer(new int[] {n_c, n_r, horse, time});
			}
			
			// 말의 이동 방식이 남아있어서 이동하는 경우
			if(horse > 0) {
				horse--;
				for(int i = 0 ; i < 8 ; i++) {
					int h_c = cur[0] + hc[i];
					int h_r = cur[1] + hr[i];
					if(h_c < 0 || h_c >= H || h_r < 0 || h_r >= W ) continue;
					if(visited[h_c][h_r][horse] || map[h_c][h_r]) continue;
					if(h_c == H-1 && h_r == W-1) { // 정답을 찾은 경우
						System.out.println(time);
						return;
					}
					visited[h_c][h_r][horse] = true;
					q.offer(new int[] {h_c, h_r, horse, time});
				}
			}
		}
		
		System.out.println("-1");
	}

}
