import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}

		StringTokenizer st;
		for (int i = 0; i < N - 2; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			union(a,b);
		}
		
		// 서로 숫자가 다른 2개가 나오면 출력
		StringBuilder sb = new StringBuilder("1 ");
		int prev = find(0);
		for(int i = 1 ; i < N ; i++) {
			if(find(i) != prev) {
				sb.append(i+1);
				System.out.println(sb);
				return;
			}
		}

	}

	public static int find(int x) {
		if (p[x] == x)
			return x;
		else
			return p[x] = find(p[x]);
	}

	public static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if (A > B)
			p[A] = B;
		if (B > A)
			p[B] = A;
	}
}
