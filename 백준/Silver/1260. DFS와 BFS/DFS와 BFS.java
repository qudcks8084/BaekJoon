import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Edge> edges;
	static boolean[] visited;
	static int N,M,V;
	static StringBuilder sb;
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		
		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.start == o.start) return this.end - o.end;
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		visited = new boolean[N];
		sb = new StringBuilder();
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges.add(new Edge(a, b));
			edges.add(new Edge(b, a));
		}
		
		Collections.sort(edges);

		DFS(V-1);
		sb.append("\n");
		
		// BFS
		visited = new boolean[N];
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(V-1);
		visited[V-1] = true;
		while(!Q.isEmpty()) {
			int size = Q.size();
			for(int i = 0 ; i < size ; i++) {
				int now_start = Q.poll();
				sb.append(now_start + 1).append(" ");
				for(Edge e : edges) {
					if(e.start == now_start && !visited[e.end]) {
						visited[e.end] = true;
						Q.offer(e.end);
					}
				}
			}
		}
		
		System.out.println(sb);
	}
	
	public static void DFS(int point) {
		visited[point] = true;
		sb.append((point + 1)).append(" ");
		for(Edge e : edges) {
			if(e.start == point && !visited[e.end]) {
				DFS(e.end);
			}
		}
	}

}
