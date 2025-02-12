import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[][][] map = new int[D][H][W];
		int numOfNotGoodTomato = 0;
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		for(int z = 0 ; z < D ; z++) {
			for(int c = 0; c < H ; c++) {
				st = new StringTokenizer(br.readLine());
				for(int r = 0 ; r < W ; r++) {
					map[z][c][r] = Integer.parseInt(st.nextToken());
					if(map[z][c][r] == 1) q.offer(new int[] {z,c,r});
					if(map[z][c][r] == 0) numOfNotGoodTomato++;
				}
			}
		}
		
		int[] dz = {1, -1, 0, 0, 0, 0};
		int[] dc = {0, 0, -1, 0, 1, 0};
		int[] dr = {0, 0, 0, 1, 0, -1};
		int totalTime = 0;
		while(true) {
			int len = q.size();
			for(int i = 0 ; i < len ; i++) {
				int[] now = q.poll();
				for(int j = 0 ; j < 6 ; j++) {
					int n_z = now[0] + dz[j];
					int n_c = now[1] + dc[j];
					int n_r = now[2] + dr[j];
					if(n_z < 0 || n_z >= D || n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
					if(map[n_z][n_c][n_r] == 0) {
						numOfNotGoodTomato--;
						map[n_z][n_c][n_r] = 1;
						q.offer(new int[] {n_z, n_c, n_r});
					}
				}
			}
			if(q.isEmpty()) break;
			totalTime++;
		}
		
		if(numOfNotGoodTomato != 0) {
			System.out.println("-1");
		}else {
			System.out.println(totalTime);
		}
		
	}
}
