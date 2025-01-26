import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int[] list;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		list = new int[M];
		
		for(int i = 0 ; i < N ; i++) {
			arr[i] = i+1;
		}
		
		sb = new StringBuilder();
		
		game(0, 0);
		
		System.out.println(sb);
		
	}
	
	public static void game(int L, int start) {
		if(L == M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(list[i]).append(" ");
			}
			sb.append("\n");
		}else {
			for(int i = start ; i < N ; i++) {
				list[L] = arr[i];
				game(L+1, i+1);
				list[L] = 0;
			}
		}
	}
}
