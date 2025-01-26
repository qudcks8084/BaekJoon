import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int[] list;
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		list = new int[M];
		visited = new boolean[N];
		
		for(int i = 0 ; i < N ; i++) {
			arr[i] = i+1;
		}
		
		sb = new StringBuilder();
		
		game(0);
		
		System.out.println(sb);
		
	}
	
	public static void game(int L) {
		if(L == M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(list[i]).append(" ");
			}
			sb.append("\n");
		}else {
			for(int i = 0 ; i < N ; i++) {
				if(visited[i]) continue;
				list[L] = arr[i];
				visited[i] = true;
				game(L+1);
				visited[i] = false;
				list[L] = 0;
			}
		}
	}
}
