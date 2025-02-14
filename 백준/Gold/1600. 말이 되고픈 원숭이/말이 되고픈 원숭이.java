import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int N, M, K;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] hr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] hc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println(Arrays.deepToString(map));

		System.out.println(bfs());

	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
//		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0] + o1[1], o2[0] + o2[1]));
		queue.add(new int[] { 0, 0, 0, K + 2 }); // r, c, cnt, 말 남은 횟수
		map[0][0] = 2;

		while (!queue.isEmpty()) {
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			
			int[] q = queue.poll();
			if (q[0] == N - 1 && q[1] == M - 1) {
				return q[2];
			}

            if (q[3] > 2) {
				for (int d = 0; d < 8; d++) {
					int r = q[0] + hr[d];
					int c = q[1] + hc[d];
					if (!check(r, c) || map[r][c] == 1)
						continue;
					if (map[r][c] == 0 || map[r][c] < q[3] - 1) {
						queue.offer(new int[] { r, c, q[2] + 1, q[3] - 1 });
						map[r][c] = q[3] - 1;
					}
				}
			}
            
			for (int d = 0; d < 4; d++) {
				int r = q[0] + dr[d];
				int c = q[1] + dc[d];
				if (!check(r, c) || map[r][c] == 1)
					continue;
				if (map[r][c] == 0 || map[r][c] < q[3]) {
					queue.offer(new int[] { r, c, q[2] + 1, q[3] });
					map[r][c] = q[3];
				}
			}
			
		}
		return -1;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
