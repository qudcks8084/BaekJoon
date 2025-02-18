import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static boolean[] visited;
	static int N, M, answer;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken())-1;
			int tall = Integer.parseInt(st.nextToken())-1;
			map[tall][small] = 1;
			map[small][tall] = -1;
		}
		
		
		answer = 0;

		for(int i = 0 ; i < N ; i++) {
			visited = new boolean[N];
			visited[i] = true;
			int a = findBiggerThanMe(i);
			int b = findSmallerThanMe(i);
			if(a + b == N - 1) {
				answer++;
			}
		}

		System.out.println(answer);
		
		
	}
	
	public static int findBiggerThanMe(int start) {
		int numOfBigger = 0;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 0 ; i < N ; i++) {
				if(visited[i]) continue;
				if(map[cur][i] == -1) { // 내가 쟤보다는 작다 -> 나보다 큰 얘 찾기
					visited[i] = true;
					q.offer(i);
					numOfBigger++;
				}
			}
		}
		return numOfBigger;
	}
	
	public static int findSmallerThanMe(int start) {
		int numOfSmaller = 0;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.offer(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 0 ; i < N ; i++) {
				if(visited[i]) continue;
				if(map[cur][i] == 1) { // 내가 쟤보다는 크다 -> 나보다 작은 얘 찾기
					visited[i] = true;
					q.offer(i);
					numOfSmaller++;
				}
			}
		}
		return numOfSmaller;
	}
}
