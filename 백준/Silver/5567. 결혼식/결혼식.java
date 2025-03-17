import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] map = new boolean[N][N];
		
		for(int i = 0 ; i < M ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			map[a][b] = true;
			map[b][a] = true;
		}
		
		boolean[] visited = new boolean[N];
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited[0] = true;
		q.offer(0);
		
		int cnt = 0;
		int time = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int l = 0 ; l < len ; l++) {
				int cur = q.poll();
				for(int i = 0 ; i < N ; i++) {
					if(map[cur][i] && !visited[i]) {
						visited[i] = true;
						cnt++;
						q.offer(i);
					}
				}
			}
			time++;
			if(time == 2) break;
		}
		System.out.println(cnt);
	}

}
