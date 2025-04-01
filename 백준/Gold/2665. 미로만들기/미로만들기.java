import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	static class move implements Comparable<move> {
		int c, r, w;

		public move(int c, int r, int w) {
			this.c = c;
			this.r = r;
			this.w = w;
		}

		@Override
		public int compareTo(move o) {
			return Integer.compare(this.w, o.w);
		}

	}

	static int[] dc = { -1, 0, 1, 0 };
	static int[] dr = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		boolean[][] map = new boolean[N][N];
		for (int c = 0; c < N; c++) {
			char[] line = br.readLine().toCharArray();
			for (int r = 0; r < N; r++) {
				map[c][r] = line[r] == '1';
			}
		}

		int[][] visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE / 10);
		}
		PriorityQueue<move> pq = new PriorityQueue<>();
		visited[0][0] = 0;
		pq.offer(new move(0, 0, 0));

		while (!pq.isEmpty()) {
			move cur = pq.poll();
			
			if(cur.c == N-1 && cur.r == N-1) {
				System.out.println(cur.w);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nc = cur.c + dc[i];
				int nr = cur.r + dr[i];
				if (nc < 0 || nc >= N || nr < 0 || nr >= N)
					continue;
				int nw = map[nc][nr] ? cur.w : cur.w + 1;
				if (visited[nc][nr] > nw) {
					visited[nc][nr] = nw;
					pq.offer(new move(nc, nr, nw));
				}
			}
		}
	}
}
