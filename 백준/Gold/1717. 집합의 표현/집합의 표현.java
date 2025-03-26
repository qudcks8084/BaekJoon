import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] p;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		p = new int[N + 5];
		for(int i = 0 ; i < N + 5 ; i++) {
			p[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(command == 0) Union(a, b);
			else {
				if(find(a) == find(b))sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		System.out.println(sb);
		
	}
	
	public static int find(int x) {
		if(p[x] == x) return x;
		else return p[x] = find(p[x]);
	}
	
	public static void Union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if(A == B) return;
		else if(A > B) p[A] = B;
		else p[B] = A;
	}
}
