import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(H == 1 && W == 1) {
			System.out.println("1");
			return;
		} 
		
		boolean[][][] visited = new boolean[H][W][K+1];
		boolean[][] map = new boolean[H][W];
		
		for(int c = 0 ; c < H ; c++) {
			char[] input = br.readLine().toCharArray();
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = input[r] == '1';
			}
		}
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[0][0][K] = true;
		q.offer(new int[] {0, 0, K, 1});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 일단 4방을 봐
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = cur[0] + dc[i];
				int n_r = cur[1] + dr[i];
				int n_k = cur[2];
				int n_d = cur[3];
				if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
				
				// 찾음
				if(n_c == H-1 && n_r == W-1) {
					System.out.println((n_d+1));
					return;
				}
				// 벽인지를 봐 -> 목숨을 봐 -> 가
				if(map[n_c][n_r]) {
					if(n_k > 0 && !visited[n_c][n_r][n_k-1]) { // 방문하지 않았다면
						visited[n_c][n_r][n_k-1] = true;
						q.offer(new int[] {n_c, n_r, n_k-1, n_d + 1});
					}
				} else { // 벽이 아니야 -> 그냥 가
					if(!visited[n_c][n_r][n_k]) {
						visited[n_c][n_r][n_k] = true;
						q.offer(new int[] {n_c, n_r, n_k, n_d + 1});
					}
				}
			}
			
		}
		
		System.out.println("-1");
		
	}

}
