import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer;
	static int[][] adjMatrix;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++){
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		answer = Integer.MAX_VALUE;
		visited = new boolean[N];
		for(int i = 0 ; i < N ; i++) {
			visited[i] = true;
			perm(0, i, i, 0);
			visited[i] = false;
		}
		
		System.out.println(answer);
		
	}
	
	public static void perm(int depth, int start ,int last ,int sum) {
		if(depth == N-1) {
			if(adjMatrix[last][start] != 0) {
				int result = sum + adjMatrix[last][start];
				answer = Math.min(answer, result);
			}
			return;
		}
		
		for(int i = 0 ; i < N ; i++) {
			if(visited[i]) continue; // 이미 방문한 도시면 방문하지 않는다.
			if(adjMatrix[last][i] == 0) continue; // 길이 없는 경우는 제외한다.
			visited[i] = true;
			perm(depth + 1, start , i, sum + adjMatrix[last][i]);
			visited[i] = false;
		}
	}

}
