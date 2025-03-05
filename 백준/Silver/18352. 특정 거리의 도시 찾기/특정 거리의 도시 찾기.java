import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K, X;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		
		ArrayList<Integer>[] adjList = new ArrayList[N];
		for(int i = 0 ; i < N ; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			adjList[s].add(e); // s에서 e로 가는 길이 존재한다.
		}
		
		// 시작점에서부터 최단거리를 저장할 distance 배열 저장
		int[] distance = new int[N];
		Arrays.fill(distance, -1);
		
		// 거리를 기준으로 정렬해서 탐색
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		// 시작 지점에서 연결된 값들을 1로 설정해서 탐색 시작
		for(int next : adjList[X]) {
			distance[next] = 1;
			q.add(new int[] {next, 1});
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int d = cur[1];
			for(int next : adjList[x]) {
				if(next != X && distance[next] == -1) {
					distance[next] = d + 1;
					q.add(new int[] {next, d + 1});
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			if(distance[i] == K) {
				sb.append((i + 1)).append("\n");
				cnt++;
			} 
		}
		if(cnt == 0) System.out.println("-1");
		else System.out.println(sb);
	}
}
