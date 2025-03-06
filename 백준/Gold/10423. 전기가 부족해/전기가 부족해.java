import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class edge implements Comparable<edge>{
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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] start = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i++) {
			start[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		ArrayList<edge>[] adjList = new ArrayList[N];
		for(int i = 0 ; i < N ; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adjList[s].add(new edge(e, w));
			adjList[e].add(new edge(s, w));
		}

		PriorityQueue<edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		// 발전소에 대한 전체 연결 노드를 추가
		for(int psNum : start) {
			visited[psNum] = true;
			for(edge pipe : adjList[psNum]) {
				pq.offer(pipe);
			}
		}

		int min_distance = 0;
		while(!pq.isEmpty()) {
			edge cur = pq.poll();
			if(visited[cur.v]) continue;
			visited[cur.v] = true;
			min_distance += cur.w;
			for(edge pipe : adjList[cur.v]) {
				pq.offer(pipe);
			}
		}
		System.out.println(min_distance);
		
	}
}
