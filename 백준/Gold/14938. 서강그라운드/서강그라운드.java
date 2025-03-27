import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class edge implements Comparable<edge> {
		int v, w;

		public edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(edge o) {
			return Integer.compare(this.w, o.w);
		}

	}

	static int max_item;
	static int N, M, R;
	static int[] item;
	static ArrayList<edge>[] adjlist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		item = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		adjlist = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adjlist[a].add(new edge(b, w));
			adjlist[b].add(new edge(a, w));
		}

		max_item = 0;
		for (int i = 0; i < N; i++) {
			dijkstra(i);
		}
		System.out.println(max_item);

	}

	public static void dijkstra(int s) {
		// 최소 간선을 저장할 배열을 생성
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE/10);
				
		PriorityQueue<edge> pq = new PriorityQueue<>();
		dp[s] = 0; // s에서 시작
		pq.offer(new edge(s, 0));
		
		while(!pq.isEmpty()) {
			edge cur = pq.poll();
			
			if(cur.w > dp[cur.v]) continue;
			
			for(edge next : adjlist[cur.v]) {
				int nextDistance = cur.w + next.w;
				if(dp[next.v] > nextDistance) {
					dp[next.v] = nextDistance;
					pq.offer(new edge(next.v, nextDistance));
				}
			}
		}
		
		int total_item = 0;
		for(int i = 0 ; i < N ; i++) {
			if(dp[i] <= M) total_item += item[i];
		}
		
		max_item = Math.max(max_item, total_item);
	}
}
