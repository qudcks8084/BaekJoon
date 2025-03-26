import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("1"))
					union(i, j);
			}
		}

		st = new StringTokenizer(br.readLine());
		int boss = find(Integer.parseInt(st.nextToken()) - 1);
		while (st.hasMoreElements()) {
			if (boss != find(Integer.parseInt(st.nextToken()) - 1)) {
				System.out.println("NO");
				return;
			}
		}

		System.out.println("YES");

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
		if (A < B)
			p[B] = A;
	}
}
