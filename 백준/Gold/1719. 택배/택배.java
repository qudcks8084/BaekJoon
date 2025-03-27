import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class edge implements Comparable<edge> {
		int v, f, w;

		public edge(int v, int f, int w) {
			this.v = v;
			this.f = f;
			this.w = w;
		}

		@Override
		public int compareTo(edge o) {
			return Integer.compare(this.w, o.w);
		}

	}

	static int N, M;
	static int[][] path;
	static ArrayList<edge>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		path = new int[N][N];

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adjList[a].add(new edge(b, -1, w));
			adjList[b].add(new edge(a, -1, w));
		}

		for (int i = 0; i < N; i++) {
			dijkstra(i);
		}

		StringBuilder sb = new StringBuilder();
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				if (c == r)
					sb.append("- ");
				else
					sb.append(path[c][r] + 1).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	public static void dijkstra(int start) {
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE / 10);
		dp[start] = 0;

		PriorityQueue<edge> pq = new PriorityQueue<>();
		
		// 처음에 넣는 것은 무조건 들어감
		for(edge next : adjList[start]) {
			path[start][next.v] = next.v;
			dp[next.v] = next.w;
			pq.offer(new edge(next.v, next.v, next.w));
		}
		

		while (!pq.isEmpty()) {
			edge cur = pq.poll();

			if (cur.w > dp[cur.v])
				continue;

			for (edge next : adjList[cur.v]) {
				int distance = cur.w + next.w;
				if (dp[next.v] > distance) {
					dp[next.v] = distance;
					path[start][next.v] = cur.f;
					pq.offer(new edge(next.v, cur.f, distance));
				}
			}
		}

	}
}
