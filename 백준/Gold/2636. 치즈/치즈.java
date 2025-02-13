import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static public class Block {
		int c, r;
		public Block(int c, int r) {
			this.c = c;
			this.r = r;
		}
	}
	
	static int H, W;
	static boolean[][] map, visited;
	static int[] dc = {-1,0,1,0};
	static int[] dr = {0,1,0,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		int num_of_cheese = 0;
		
		// 입력
		for(int i = 0 ; i < H ;  i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < W ; j++) {
				map[i][j] = st.nextToken().equals("1");
				if(map[i][j]) num_of_cheese++;
			}
		}
		
		int time = 1;
		while(true) {
			int num_of_melt_cheese = removeCheese();
			if(num_of_melt_cheese == num_of_cheese) {
				StringBuilder sb = new StringBuilder();
				sb.append(time).append("\n").append(num_of_cheese);
				System.out.println(sb);
				break;
			}
			num_of_cheese -= num_of_melt_cheese;
			time++;
		} 
	
	}
	
	// 가장 외부에 있는 치즈를 삭제
	public static int removeCheese() { 
		int melting = 0;
		visited = new boolean[H][W];
		ArrayDeque<Block> q = new ArrayDeque<>();
		q.offer(new Block(0, 0)); // 가장 외부 공기에서부터 접근
		visited[0][0] = true;
		while(!q.isEmpty()) {
			Block now = q.poll();
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = now.c + dc[i];
				int n_r = now.r + dr[i];
				
				if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W || visited[n_c][n_r]) continue;
				
				visited[n_c][n_r] = true;
				
				if(map[n_c][n_r]) {
					map[n_c][n_r] = false;
					melting++;
				} 
				else q.offer(new Block(n_c, n_r));
				
			}
		}
		return melting;
	}
}
