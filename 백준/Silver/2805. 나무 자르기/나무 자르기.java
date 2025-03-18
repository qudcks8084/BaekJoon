import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static long max_height;
	static long[] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		max_height = 0;
		cut(1, tree[N-1]);
		System.out.println(max_height);
	}
	
	public static void cut(long start, long end) {
		if(start == end) return;
		long mid = (start + end) / 2;
		
		long sum = 0;
		for(long height : tree) {
			if(height > mid) sum += height - mid;
		}
		
		if(sum >= M) {
			cut(mid + 1, end);
			max_height = Math.max(max_height, mid);
		}else {
			cut(start, mid);
		}
	}
}
